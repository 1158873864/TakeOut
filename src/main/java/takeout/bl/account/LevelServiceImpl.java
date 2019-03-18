package takeout.bl.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.account.LevelService;
import takeout.dao.account.LevelDao;
import takeout.entity.account.Level;
import takeout.exception.NotExistException;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelDao levelDao;

    @Autowired
    public LevelServiceImpl(LevelDao levelDao) {
        this.levelDao = levelDao;
    }

    @Override
    public void addLevel(Level level) {
        levelDao.save(level);
    }

    @Override
    public void updateLevel(Level level) throws NotExistException {
        Optional<Level> optionalLevel=levelDao.findById(level.getId());
        if(optionalLevel.isPresent()){
            Level lastLevel=optionalLevel.get();
            lastLevel.setDiscount(level.getDiscount());
            lastLevel.setName(level.getName());
            lastLevel.setUrl(level.getUrl());
            levelDao.save(level);
        }
       else {
        throw new NotExistException("Level ID", String.valueOf(level.getId()));
        }
    }

    @Override
    public void deleteLevel(int id) throws NotExistException{
        Optional<Level> optionalLevel=levelDao.findById(id);
        if(optionalLevel.isPresent()){
            levelDao.deleteById(id);
        }
        else {
            throw new NotExistException("Level ID", String.valueOf(id));
        }
    }

    @Override
    public Level findById(int id) throws NotExistException{
        Optional<Level> optionalLevel=levelDao.findById(id);
        Level level=new Level();
        if(optionalLevel.isPresent()){
            level=optionalLevel.get();
        }
        else {
            throw new NotExistException("Level ID", String.valueOf(level.getId()));
        }
        return level;
    }

    @Override
    public List<Level> findAll() {
        return levelDao.findAll();
    }
}