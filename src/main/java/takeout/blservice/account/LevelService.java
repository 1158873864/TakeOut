package takeout.blservice.account;

import takeout.entity.account.Level;
import takeout.exception.NotExistException;

import java.util.List;

public interface LevelService {
    void addLevel(Level level);
    void updateLevel(Level level) throws NotExistException;
    void deleteLevel(int id) throws NotExistException;
    Level findById(int id) throws NotExistException;
    List<Level> findAll();
}
