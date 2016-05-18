package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.User;
import dao.UserDao;

@Transactional
@Service("loginService")
public class LoginServiceImp implements LoginService{

    @Autowired
    private UserDao userMapper;
	
	public boolean isRegistered(User user) {
		User u = userMapper.findUserByMobile(user.getMobile()) ;
		return u == null;
	}

	public boolean addUser(User user) {
		int r = 0 ;
		try {
		r= userMapper.addUser(user);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			e.printStackTrace();
		}
		return r > 0;
	}

	public User getLogin(User user) {
		return userMapper.findUserByMobileAndPassword(user);
	}

	public boolean changeUser(User user) {

		int r = userMapper.updateUser(user);
		return r > 0;
	}

}
