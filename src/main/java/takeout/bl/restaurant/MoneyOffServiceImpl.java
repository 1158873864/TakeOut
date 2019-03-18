package takeout.bl.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.restaurant.MoneyOffService;
import takeout.dao.restaurant.MoneyOffDao;
import takeout.dao.restaurant.RestaurantDao;
import takeout.entity.restaurant.MoneyOff;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.MoneyOffVO;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyOffServiceImpl implements MoneyOffService {
    private final MoneyOffDao moneyOffDao;

    private final RestaurantDao restaurantDao;
    @Autowired
    public MoneyOffServiceImpl(MoneyOffDao moneyOffDao, RestaurantDao restaurantDao) {
        this.moneyOffDao = moneyOffDao;
        this.restaurantDao = restaurantDao;
    }


    @Override
    public void addMoneyOff(MoneyOffVO moneyOffVO) throws NotExistException {
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(moneyOffVO.getRestaurantId());
        if(optionalRestaurant.isPresent()){
            MoneyOff moneyOff=new MoneyOff(moneyOffVO.getPrice(),moneyOffVO.getOff(),moneyOffVO.getUtilDate(),optionalRestaurant.get());
            moneyOffDao.save(moneyOff);
        }else {
            throw new NotExistException("Restaurant ID", moneyOffVO.getRestaurantId());
        }
    }

    @Override
    public void updateMoneyOff(MoneyOffVO moneyOffVO) throws NotExistException {
        Optional<MoneyOff> optionalMoneyOff=moneyOffDao.findById(moneyOffVO.getId());
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(moneyOffVO.getRestaurantId());
        if(optionalMoneyOff.isPresent()){
            MoneyOff lastMoneyOff=optionalMoneyOff.get();
            lastMoneyOff.setOff(moneyOffVO.getOff());
            lastMoneyOff.setPrice(moneyOffVO.getPrice());
            if(optionalRestaurant.isPresent()){
                lastMoneyOff.setRestaurant(optionalRestaurant.get());
            }else {
                throw new NotExistException("Restaurant ID", moneyOffVO.getRestaurantId());
            }
            lastMoneyOff.setUtilDate(moneyOffVO.getUtilDate());
        }else {
            throw new NotExistException("MoneyOff ID", moneyOffVO.getId());
        }
    }

    @Override
    public void deleteMoneyOff(String id) throws NotExistException {
        Optional<MoneyOff> optionalMoneyOff=moneyOffDao.findById(id);

        if(optionalMoneyOff.isPresent()){
            moneyOffDao.deleteById(id);
        }else {
            throw new NotExistException("MoneyOff ID", id);
        }
    }

    @Override
    public MoneyOff findById(String id) throws NotExistException {
        Optional<MoneyOff> optionalMoneyOff=moneyOffDao.findById(id);

        if(optionalMoneyOff.isPresent()){
            return optionalMoneyOff.get();
        }else {
            throw new NotExistException("MoneyOff ID", id);
        }
    }

    @Override
    public List<MoneyOff> findAll() {
        return moneyOffDao.findAll();
    }

    @Override
    public List<MoneyOff> findByRestaurant(String restaurantId) throws NotExistException {
        return moneyOffDao.findByRestaurant(restaurantDao.getOne(restaurantId));
    }
}
