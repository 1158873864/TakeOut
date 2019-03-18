package takeout.blservice.account;

import takeout.entity.account.User;
import takeout.exception.NotExistException;
import takeout.parameters.account.UserVO;

import java.util.List;

public interface UserBlService {
    void addUser(User user);

    void deleteUserById(String id) throws NotExistException;

    void updateUserById(User user) throws NotExistException;

    User getUserById(String id) throws NotExistException;

    List<User> getAllUser();

    void getVertificationCode(String email);

    String register(UserVO user);

    String loginByEmail(String email,String password);

    String loginByMobilePhone(String mobilePhone,String password);

    void withdraw(String id) throws NotExistException;

    void setDefaultAddress(String userId,String addressId) throws NotExistException;
}
