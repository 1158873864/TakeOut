package takeout.blservice.account;

import takeout.entity.account.Address;
import takeout.exception.NotExistException;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);
    void updateAddress(Address address) throws NotExistException;
    void deleteAddress(String id) throws NotExistException;
    Address findById(String id) throws NotExistException;
    List<Address> findAll();
}
