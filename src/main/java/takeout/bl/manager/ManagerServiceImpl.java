package takeout.bl.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.manager.ManagerService;
import takeout.dao.manager.ManagerDao;
import takeout.entity.account.User;
import takeout.entity.manager.Manager;


@Service
public class ManagerServiceImpl implements ManagerService {

	private final ManagerDao managerDao;

	@Autowired
	public ManagerServiceImpl(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}


	@Override
	public String login(String username, String password) {
		boolean isUsernameExist=!managerDao.findManagerByUsername(username).isEmpty();
		if (!isUsernameExist){
			return "用户名不存在";
		}
		else{
			Manager manager=managerDao.findManagerByUsername(username).get(0);
			if(manager.getPassword().equals(password)){
				return "1";
			}
			else{
				return "密码错误";
			}
		}
	}
}
