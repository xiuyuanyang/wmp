package controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.ResultBean;
import utils.Global;

@Controller
@RequestMapping("/info")
public class InfoController {

	private Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@RequestMapping(value = "/version")
	@ResponseBody
	public Object getVersion(HttpServletResponse res) {
		
		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("version_info"));
		return rb;

	}
	
	@RequestMapping(value = "/iosLink")
	@ResponseBody
	public Object getIosDownloadLink(HttpServletResponse res) {
		
		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("ios_download_link"));
		return rb;

	}
	
	@RequestMapping(value = "/androidLink")
	@ResponseBody
	public Object getAndroidDownloadLink(HttpServletResponse res) {
		
		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("android_download_link"));
		return rb;

	}
	
}
