package takeout.blservice.restaurant;

import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.MoneyOff;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;
import takeout.parameters.restaurant.MoneyOffVO;

import java.util.List;

public interface MoneyOffService {
    void addMoneyOff(MoneyOffVO moneyOffVO) throws NotExistException;
    void updateMoneyOff(MoneyOffVO moneyOffVO)throws NotExistException;
    void deleteMoneyOff(String id) throws NotExistException;
    MoneyOff findById(String id) throws NotExistException;
    List<MoneyOff> findAll();
    List<MoneyOff> findByRestaurant(String restaurantId) throws NotExistException;
}
