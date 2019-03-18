package takeout.bl.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.account.AddressService;
import takeout.dao.account.AddressDao;
import takeout.entity.account.Address;
import takeout.exception.NotExistException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public void addAddress(Address address) {
        addressDao.save(address);
    }

    @Override
    public void updateAddress(Address address) throws NotExistException {
        Optional<Address> optionalAddress=addressDao.findById(address.getId());
        if(optionalAddress.isPresent()){
            Address lastAddress=optionalAddress.get();
            lastAddress.setName(address.getName());
            lastAddress.setUser(address.getUser());
            lastAddress.setLatitude(address.getLatitude());
            lastAddress.setLongitude(address.getLongitude());
            addressDao.save(lastAddress);
        }
        else {
            throw new NotExistException("Address ID", String.valueOf(address.getId()));
        }
    }

    @Override
    public void deleteAddress(String id) throws NotExistException {
        Optional<Address> optionalAddress=addressDao.findById(id);
        if(optionalAddress.isPresent()){
            addressDao.deleteById(id);
        }
        else {
            throw new NotExistException("Address ID", String.valueOf(id));
        }
    }

    @Override
    public takeout.entity.account.Address findById(String id) throws NotExistException {
        Optional<Address> optionalAddress=addressDao.findById(id);
        Address address=new Address();
        if(optionalAddress.isPresent()){
            address=optionalAddress.get();
        }
        else {
            throw new NotExistException("Address ID", String.valueOf(id));
        }
        return address;
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }
}