package takeout.blservice.order;

import takeout.entity.restaurant.Goods;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;

import java.util.List;

public interface ShoppingCartService {
    void addGoods(String userId,String goodsId) throws NotExistException;
    void deleteGoods(String userId,String goodsId) throws NotExistException;
    void addPackage(String userId,String PackageId) throws NotExistException;
    void deletePackage(String userId,String PackageId) throws NotExistException;
    void addShoppingCart(String userId) throws NotExistException;
}
