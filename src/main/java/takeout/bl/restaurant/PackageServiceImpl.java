package takeout.bl.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.restaurant.PackageService;
import takeout.dao.restaurant.GoodsDao;
import takeout.dao.restaurant.PackageDao;
import takeout.dao.restaurant.RestaurantDao;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Package;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.PackageVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {
    private final PackageDao packageDao;

    private final RestaurantDao restaurantDao;

    private final GoodsDao goodsDao;
    @Autowired
    public PackageServiceImpl(PackageDao packageDao, RestaurantDao restaurantDao, GoodsDao goodsDao) {
        this.packageDao = packageDao;
        this.restaurantDao = restaurantDao;
        this.goodsDao = goodsDao;
    }


    @Override
    public void addPackage(PackageVO packageVO) throws NotExistException {
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(packageVO.getRestaurantId());
        List<Goods> goods=new ArrayList<>();
        for(int i=0;i<packageVO.getGoodsIdList().size();i++){
            Optional<Goods> optionalGoods=goodsDao.findById(packageVO.getGoodsIdList().get(i));
            if(optionalGoods.isPresent()){
                goods.add(optionalGoods.get());
            }
            else{
                throw new NotExistException("Good ID", packageVO.getGoodsIdList().get(i));
            }
        }
        if(optionalRestaurant.isPresent()){
             Package pack=new Package(packageVO.getName(),packageVO.getPrice(),packageVO.getNumber(),packageVO.getImageUrl(),packageVO.getUtilDate(),optionalRestaurant.get(),goods);
             packageDao.save(pack);
        }else {
            throw new NotExistException("Restaurant ID", packageVO.getRestaurantId());
        }
    }

    @Override
    public void updatePackage(PackageVO packageVO) throws NotExistException {
        Optional<Package> optionalPackage=packageDao.findById(packageVO.getId());
        List<Goods> goods=new ArrayList<>();
        for(int i=0;i<packageVO.getGoodsIdList().size();i++){
            Optional<Goods> optionalGoods=goodsDao.findById(packageVO.getGoodsIdList().get(i));
            if(optionalGoods.isPresent()){
                goods.add(optionalGoods.get());
            }
            else{
                throw new NotExistException("Good ID", packageVO.getGoodsIdList().get(i));
            }
        }
        Restaurant restaurant=restaurantDao.getOne(packageVO.getRestaurantId());
        if(optionalPackage.isPresent()){
            Package lastPackage=optionalPackage.get();
            lastPackage.setGoodsList(goods);
            lastPackage.setImageUrl(packageVO.getImageUrl());
            lastPackage.setName(packageVO.getName());
            lastPackage.setNumber(packageVO.getNumber());
            lastPackage.setPrice(packageVO.getPrice());
            lastPackage.setUtilDate(packageVO.getUtilDate());
            lastPackage.setRestaurant(restaurant);
            packageDao.save(lastPackage);
        }else {
            throw new NotExistException("Package ID", packageVO.getId());
        }
    }

    @Override
    public void deletePackage(String id) throws NotExistException {
       Optional<Package> optionalPackage=packageDao.findById(id);
       if(optionalPackage.isPresent()){
           packageDao.deleteById(id);
       }else {
           throw new NotExistException("Package ID", id);
       }

    }

    @Override
    public Package findById(String id) throws NotExistException {
        Optional<Package> optionalPackage=packageDao.findById(id);
        if(optionalPackage.isPresent()){
            return optionalPackage.get();
        }else {
            throw new NotExistException("Package ID", id);
        }
    }

    @Override
    public List<Package> findAll() {
        return packageDao.findAll();
    }

    @Override
    public List<Package> findByRestaurant(String restaurantId) throws NotExistException {
        return packageDao.findByRestaurant(restaurantDao.getOne(restaurantId));
    }
}
