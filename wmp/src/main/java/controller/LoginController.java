package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.ResultBean;
import beans.User;
import param.ChangeMobilePara;
import param.LoginParam;
import service.LoginService;
import utils.Global;
import utils.IOUtil;
import utils.IdGen;
import utils.MD5;
import utils.MessageConstants;
import utils.RedisUtil;

@Controller
@RequestMapping(value = "mobile")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String tokenExpiresIn = Global.getConfig("token_expires_in");

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/x-www-form-urlencoded",
			"application/json" })
	@ResponseBody
	public Object login(@RequestBody LoginParam lp, HttpServletRequest req) {

		String contenttype = req.getHeader("content-type");
		String token, mobile, password;
		ResultBean rb = new ResultBean();

		if (contenttype.equalsIgnoreCase("application/json")) {
			mobile = lp.getMobile();
			password = lp.getPassword();
		} else {
			mobile = req.getParameter("mobile");
			password = req.getParameter("password");
		}

		Map<String, String> map = new HashMap<String, String>();
		password = MD5.getMD5Code(password);

		if (StringUtils.isEmpty(password) | StringUtils.isEmpty(mobile)) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("login fail , mobile or password can not be empty");
			return rb;
		}
		mobile = mobile.trim();
		password = password.trim();
		User lu = new User();
		lu.setMobile(mobile);
		lu.setPassword(password);
		User u = loginService.getLogin(lu);
		if (u == null) {
			logger.info("loginService.getLogin(lu) and user is null ");
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("login fail , wrong password or mobile");
		} else {
			// try to get current time token by mobile no. then delete this .
			token = RedisUtil.get(mobile);
			if (!StringUtils.isEmpty(token)) {
				RedisUtil.del(token);
			}

			// generate new token and bind to mobile user.
			token = IdGen.uuid();
			rb.setCode(MessageConstants.success.getValue());
			rb.setMessage("login success");
			map.put("userid", u.getId() + "");
			map.put("token", token);
			rb.setData(map);
			RedisUtil.setex(mobile, Integer.valueOf(tokenExpiresIn), token);
			RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), u.getId() + "");
		}
		return rb;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestBody LoginParam lp,HttpServletRequest req) {
		
		String mobile = lp.getMobile();
		String password = lp.getPassword();

		ResultBean rb = new ResultBean();

		if ( StringUtils.isEmpty(mobile) | StringUtils.isEmpty(password)) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("register fail , mobile or username or password can not be empty");
			return rb;
		} else {
			mobile = mobile.trim();
			password = password.trim();
			User u = new User();
			u.setMobile(mobile);
			logger.info("register user and password = " + password);
			password = MD5.getMD5Code(password);
			u.setPassword(password);

			if (loginService.addUser(u)) {
				rb.setCode(MessageConstants.success.getValue());
				rb.setMessage("register success");
			} else {
				rb.setCode(MessageConstants.error.getValue());
				rb.setMessage("register fail , mobile number has been used");
			}
		}
		return rb;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout(HttpServletRequest req) {
		ResultBean rb = new ResultBean();
		try {
			String str = IOUtil.getBodyString(req.getReader());
			JSONObject object = new JSONObject(str);
			String token = (String) object.get("token");

			if (StringUtils.isEmpty(token)) {
				rb.setCode(1);
				rb.setMessage("logout success");
				return rb;
			}

			RedisUtil.del(token);
			rb.setCode(1);
			rb.setMessage("logout success");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}

	@ResponseBody
	@RequestMapping(value = "/changeMobile", method = RequestMethod.POST)
	public Object changeMobile(@RequestBody ChangeMobilePara cmp,HttpServletRequest req) {

		String oldmobile = cmp.getOriginalmobile();
		String newmobile = cmp.getNewmobile();
		String password = cmp.getPassword();
		ResultBean rb = new ResultBean();
		
		if ( StringUtils.isEmpty(oldmobile) | StringUtils.isEmpty(password) | StringUtils.isEmpty(newmobile)) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("changeMobile fail , mobile or password can not be empty");
			return rb;
		}
		
		newmobile = newmobile.trim();
		oldmobile = oldmobile.trim();
		password = password.trim();
		password = MD5.getMD5Code(password);
		
		User lu = new User();
		lu.setMobile(oldmobile);
		lu.setPassword(password);
		
		User u = loginService.getLogin(lu);
		if (u == null) {
			logger.info("loginService.getLogin(lu) and user is null ");
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("changeMobile fail , wrong password or mobile");
		} else {
			u.setMobile(newmobile);
			if(loginService.changeUser(u)){
				rb.setCode(MessageConstants.success.getValue());
				rb.setMessage("changeMobile success");
				
				String oldtoken = RedisUtil.get(oldmobile);				
				RedisUtil.del(oldtoken);			
				RedisUtil.del(oldmobile);
				
			} else {
				rb.setCode(MessageConstants.error.getValue());
				rb.setMessage("changeMobile fail , mobile may have been used");
			}
		}
		
		return rb;
	}

}
