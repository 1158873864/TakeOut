package takeout.bl.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.account.EmailService;
import takeout.blservice.account.UserBlService;
import takeout.dao.account.LevelDao;
import takeout.dao.account.UserDao;
import takeout.dao.account.VerificationDao;
import takeout.entity.account.User;
import takeout.entity.account.Verification;
import takeout.exception.NotExistException;
import takeout.parameters.account.UserVO;

import java.util.List;
import java.util.Optional;


@Service
public class UserBlServiceImpl implements UserBlService {
	private final UserDao userDao;
	private final EmailService emailService;
	private final VerificationDao verificationDao;
	private final LevelDao levelDao;
	@Autowired
	public UserBlServiceImpl(UserDao userDao, EmailService emailService, VerificationDao verificationDao, LevelDao levelDao) {
		this.userDao = userDao;
		this.emailService=emailService;
		this.verificationDao=verificationDao;
        this.levelDao = levelDao;
    }

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUserById(String id) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			userDao.deleteById(id);
		} else {
			throw new NotExistException("User ID", id);
		}
	}

	@Override
	public void updateUserById(User user) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(user.getId());
		if(optionalUser.isPresent()) {
			User newUser = optionalUser.get();
			newUser.setId(user.getId());
			newUser.setEmail(user.getEmail());
			newUser.setMobilePhone(user.getMobilePhone());
			newUser.setUsername(user.getUsername());
			newUser.setPassword(user.getPassword());
			newUser.setDefaultAddress(user.getDefaultAddress());
			newUser.setFaceUrl(user.getFaceUrl());
		} else {
			throw new NotExistException("User ID", user.getId());
		}
	}

	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(String id) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			throw new NotExistException("User ID", id);
		}
	}

	@Override
	public void getVertificationCode(String email) {
		int ran = (int) ((Math.random() * 9 + 1) * 10000);//随机生成一个5位验证码
		String content = "欢迎您注册Yummy!外卖平台系统账号，您的验证码为" + ran + "，请尽快完成注册。";
		emailService.sendSimpleMail(email, "Yummy!外卖平台系统注册", content);
		/*
		 *保存信息至服务器
		 */
		verificationDao.save(new Verification(email,ran+""));
	}

	@Override
	public String register(UserVO user){
		String ver = verificationDao.findByEmail(user.getEmail()).get(0).getCode();
		if (ver.equals(user.getCode())) {
			boolean isEmailExist=!userDao.findUserByEmail(user.getEmail()).isEmpty();
			if (isEmailExist){
				return "邮箱已被注册";
			}
			else{
				boolean isPhoneExist=!userDao.findUserByMobilePhone(user.getMobilePhone()).isEmpty();
				if(isPhoneExist){
					return "手机号已被注册";
				}
				else{
					userDao.save(new User(user.getUsername(),user.getPassword(),user.getMobilePhone(),user.getName(),user.getEmail(),user.getDefaultAddress(),user.getFaceUrl(),levelDao.getOne(1),true,0));
					verificationDao.deleteById(user.getEmail());
					return "1";
				}
			}
		} else {
			return "验证码不正确";
		}
	}

	@Override
	public String loginByEmail(String email,String password){
		System.out.println(userDao.findUserByEmail("1158873864@qq.com"));
		boolean isEmailExist=!userDao.findUserByEmail(email).isEmpty();
		if (!isEmailExist){
			return "邮箱不存在";
		}
		else{
			User user=userDao.findUserByEmail(email).get(0);
			if(user.getPassword().equals(password)){
				return "1";
			}
			else{
				return "密码错误";
			}
		}
	}

	@Override
	public String loginByMobilePhone(String mobilePhone,String password){
		boolean isPhoneExist=!userDao.findUserByMobilePhone(mobilePhone).isEmpty();
		if (!isPhoneExist){
			return "手机号不存在";
		}
		else{
			User user=userDao.findUserByEmail(mobilePhone).get(0);
			if(user.getPassword().equals(password)){
				return "1";
			}
			else{
				return "密码错误";
			}
		}
	}

    @Override
    public void withdraw(String id) throws NotExistException {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(false);
            userDao.save(user);
        } else {
            throw new NotExistException("User ID", id);
        }
    }

	@Override
	public void setDefaultAddress(String userId, String addressId) throws NotExistException {
		Optional<User> optionalUser=userDao.findById(userId);
		if(optionalUser.isPresent()){
            User lastUser=optionalUser.get();
            lastUser.setDefaultAddress(addressId);
            userDao.save(lastUser);
		}
		else {
			throw new NotExistException("User ID", userId);
		}
	}


}
