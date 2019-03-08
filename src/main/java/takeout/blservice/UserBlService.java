package takeout.blservice;

import takeout.entity.User;
import takeout.exception.NotExistException;

import java.util.List;

public interface UserBlService {
    public void addUser(User user);

    public void deleteUserById(String id) throws NotExistException;

    public void updateUserById(User user) throws NotExistException;

    public User getUserById(String id) throws NotExistException;

    public List<User> getAllUser();

    public void getVertificationCode(String email);

    public String register(User user,String code);

    public String loginByEmail(String email,String password);

    public String loginByMobilePhone(String mobilePhone,String password);
}
