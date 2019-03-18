package takeout.blservice.restaurant;

import takeout.entity.restaurant.Goods;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;

import java.util.List;

public interface GoodsService {
    void addGoods(GoodsVO goodsVO) throws NotExistException;
    void updateGoods(GoodsVO goodsVO)throws NotExistException;
    void deleteGoods(String id) throws NotExistException;
    Goods findById(String id) throws NotExistException;
    List<Goods> findAll();
    List<Goods> findByRestaurant(String restaurantId) throws NotExistException;
}
