package takeout.blservice.restaurant;

import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Package;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.PackageVO;

import java.util.List;

public interface PackageService {
    void addPackage(PackageVO packageVO) throws NotExistException;
    void updatePackage(PackageVO packageVO) throws NotExistException;
    void deletePackage(String id) throws NotExistException;
    Package findById(String id)throws NotExistException;
    List<Package> findAll();
    List<Package> findByRestaurant(String restaurantId) throws NotExistException;
}
