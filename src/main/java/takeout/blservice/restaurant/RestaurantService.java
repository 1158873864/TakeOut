package takeout.blservice.restaurant;

import takeout.entity.account.Address;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.RestaurantVO;

import java.util.List;

public interface RestaurantService {
    void addRestaurant(RestaurantVO restaurantVO);
    void registerRestaurant(RestaurantVO restaurantVO);
    void updateRestaurant(Restaurant restaurant) throws NotExistException;
    void deleteRestaurant(String id) throws NotExistException;
    Restaurant findById(String id) throws NotExistException;
    List<Restaurant> findAll();
    void pass(String id) throws NotExistException;
}
