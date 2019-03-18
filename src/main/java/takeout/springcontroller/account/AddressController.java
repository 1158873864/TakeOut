package takeout.springcontroller.account;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.account.AddressService;
import takeout.blservice.account.UserBlService;
import takeout.entity.account.Address;
import takeout.exception.NotExistException;
import takeout.parameters.account.AddressVO;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final UserBlService userBlService;

    @Autowired
    public AddressController(AddressService addressService, UserBlService userBlService) {
        this.addressService = addressService;
        this.userBlService = userBlService;
    }


    @ApiOperation(value = "新增地址")
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> addAddress(@Valid @RequestBody AddressVO addressVO) throws NotExistException {
        Address address=new Address(addressVO.getName(),addressVO.getLatitude(),addressVO.getLatitude(),userBlService.getUserById(addressVO.getUser_id()));
        addressService.addAddress(address);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改地址")
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> updateAddress(@Valid @RequestBody AddressVO addressVO) throws NotExistException {
        Address address=new Address(addressVO.getName(),addressVO.getLatitude(),addressVO.getLatitude(),userBlService.getUserById(addressVO.getUser_id()));
        addressService.updateAddress(address);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除地址")
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> deleteLevel(@RequestParam("id") String id) throws NotExistException {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找地址")
    @RequestMapping(value = "/findLevelById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findLevelById(@RequestParam("id") String id) throws NotExistException {
        Address address=addressService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("address",address);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有地址")
    @RequestMapping(value = "/findAllAddress", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAllAddress(){
        List<Address> addresses=addressService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("addresses",addresses);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }
}
