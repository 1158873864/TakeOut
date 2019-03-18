package takeout.bl.restaurant;

import org.springframework.stereotype.Service;
import takeout.blservice.restaurant.RestaurantService;
import takeout.dao.restaurant.RestaurantDao;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.RestaurantVO;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDao restaurantDao;

    public RestaurantServiceImpl(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    public void addRestaurant(RestaurantVO restaurantVO) {
        String identification="";
        List<Restaurant> restaurants=restaurantDao.findAll();
        for(int i=0;i<7;i++){
            int ran=(int)Math.random();
            identification+=String.valueOf(ran);
        }
        for(int i=0;i<restaurants.size();i++){
            String tempId=restaurants.get(i).getIdentification();
            if(tempId.equals(identification)){
                for(int j=0;j<7;j++){
                    int ran=(int)Math.random();
                    identification+=String.valueOf(ran);
                }
                break;
            }
        }
        Restaurant restaurant=new Restaurant(identification,restaurantVO.getPassword(),restaurantVO.getName(),restaurantVO.getAddress(),restaurantVO.getLongitude(),restaurantVO.getLatitude(),restaurantVO.getMobilePhone(),0,restaurantVO.getFaceUrl(),restaurantVO.getLicense(),"using");
        restaurantDao.save(restaurant);
    }

    @Override
    public void registerRestaurant(RestaurantVO restaurantVO) {
        String identification="";
        List<Restaurant> restaurants=restaurantDao.findAll();
         for(int i=0;i<7;i++){
             int ran=(int)Math.random();
             identification+=String.valueOf(ran);
         }
         for(int i=0;i<restaurants.size();i++){
             String tempId=restaurants.get(i).getIdentification();
             if(tempId.equals(identification)){
                 for(int j=0;j<7;j++){
                     int ran=(int)Math.random();
                     identification+=String.valueOf(ran);
                 }
                 break;
             }
         }
         Restaurant restaurant=new Restaurant(identification,restaurantVO.getPassword(),restaurantVO.getName(),restaurantVO.getAddress(),restaurantVO.getLongitude(),restaurantVO.getLatitude(),restaurantVO.getMobilePhone(),0,restaurantVO.getFaceUrl(),restaurantVO.getLicense(),"applying");
         restaurantDao.save(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws NotExistException {
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(restaurant.getId());
        if(optionalRestaurant.isPresent()){
            Restaurant lastRestaurant=optionalRestaurant.get();
            lastRestaurant.setIdentification(restaurant.getIdentification());
            lastRestaurant.setName(restaurant.getName());
            lastRestaurant.setAddress(restaurant.getAddress());
            lastRestaurant.setPassword(restaurant.getPassword());
            lastRestaurant.setBalance(restaurant.getBalance());
            lastRestaurant.setLicense(restaurant.getLicense());
            lastRestaurant.setFaceUrl(restaurant.getFaceUrl());
            lastRestaurant.setMobilePhone(restaurant.getMobilePhone());
            lastRestaurant.setStatus(restaurant.getStatus());
            restaurantDao.save(lastRestaurant);
        }else {
            throw new NotExistException("Restaurant ID", restaurant.getId());
        }
    }

    @Override
    public void deleteRestaurant(String id) throws NotExistException {
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(id);
        if(optionalRestaurant.isPresent()){
            restaurantDao.deleteById(id);
        }else {
            throw new NotExistException("Restaurant ID", id);
        }
    }

    @Override
    public Restaurant findById(String id) throws NotExistException {
        Restaurant restaurant;
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(id);
        if(optionalRestaurant.isPresent()){
            restaurant=optionalRestaurant.get();
        }else {
            throw new NotExistException("Restaurant ID", id);
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantDao.findAll();
    }

    @Override
    public void pass(String id) throws NotExistException {
        Optional<Restaurant> optionalRestaurant=restaurantDao.findById(id);
        if(optionalRestaurant.isPresent()){
            Restaurant restaurant=optionalRestaurant.get();
            restaurant.setStatus("using");
            restaurantDao.save(restaurant);
        }else {
            throw new NotExistException("Restaurant ID", id);
        }
    }


}