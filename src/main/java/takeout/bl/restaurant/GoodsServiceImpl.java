package takeout.bl.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.restaurant.GoodsService;
import takeout.dao.restaurant.GoodsDao;
import takeout.dao.restaurant.RestaurantDao;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService{

    private final GoodsDao goodsDao;

    private final RestaurantDao restaurantDao;

    @Autowired
    public GoodsServiceImpl(GoodsDao goodsDao, RestaurantDao restaurantDao) {
        this.goodsDao = goodsDao;
        this.restaurantDao = restaurantDao;
    }

    @Override
    public void addGoods(GoodsVO goodsVO) throws NotExistException{
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(goodsVO.getRestaurantId());
        if(optionalRestaurant.isPresent()){
            Goods goods=new Goods(goodsVO.getName(),goodsVO.getPrice(),goodsVO.getDiscount(),goodsVO.getNumber(),goodsVO.getUtilDate(),goodsVO.getImageUrl(),optionalRestaurant.get());
            goodsDao.save(goods);
        }else {
            throw new NotExistException("Restaurant ID", goodsVO.getRestaurantId());
        }
    }

    @Override
    public void updateGoods(GoodsVO goodsVO) throws NotExistException {
        Optional<Goods> optionalGoods=goodsDao.findById(goodsVO.getId());
        Restaurant restaurant=restaurantDao.getOne(goodsVO.getRestaurantId());
        if(optionalGoods.isPresent()){
            Goods goods=optionalGoods.get();
            goods.setDiscount(goodsVO.getDiscount());
            goods.setImageUrl(goodsVO.getImageUrl());
            goods.setName(goodsVO.getName());
            goods.setNumber(goodsVO.getNumber());
            goods.setPrice(goodsVO.getPrice());
            goods.setUtilDate(goodsVO.getUtilDate());
            goods.setRestaurant(restaurant);
            goodsDao.save(goods);
        }else {
            throw new NotExistException("Goods ID", goodsVO.getId());
        }

    }

    @Override
    public void deleteGoods(String id) throws NotExistException {
        Optional<Goods> optionalGoods=goodsDao.findById(id);
        if(optionalGoods.isPresent()){
            goodsDao.deleteById(id);
        }else {
            throw new NotExistException("Goods ID", id);
        }

    }

    @Override
    public Goods findById(String id) throws NotExistException {
        Optional<Goods> optionalGoods=goodsDao.findById(id);
        Goods goods;
        if(optionalGoods.isPresent()){
            goods=goodsDao.getOne(id);
        }else {
            throw new NotExistException("Goods ID", id);
        }
        return goods;
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Goods> findByRestaurant(String restaurantId) throws NotExistException {
        return goodsDao.findByRestaurant(restaurantDao.getOne(restaurantId));
    }
}
