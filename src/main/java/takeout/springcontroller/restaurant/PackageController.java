package takeout.springcontroller.restaurant;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.restaurant.PackageService;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Package;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;
import takeout.parameters.restaurant.PackageVO;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/package")
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }


    @ApiOperation(value = "新增套餐")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> add(@Valid @RequestBody PackageVO packageVO) throws NotExistException {
        packageService.addPackage(packageVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改套餐")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> update(@Valid @RequestBody PackageVO packageVO) throws NotExistException {
        packageService.updatePackage(packageVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除套餐")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> delete(@RequestParam("id") String id) throws NotExistException {
        packageService.deletePackage(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找套餐")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findById(@RequestParam("id") String id) throws NotExistException {
        Package pack=packageService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("pack",pack);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有套餐")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAll(){
        List<Package> packages=packageService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("packages",packages);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "根据餐厅查找套餐")
    @RequestMapping(value = "/findByRestaurant", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findByRestaurant(@RequestParam("restaurantId") String restaurantId) throws NotExistException {
        List<Package> packages=packageService.findByRestaurant(restaurantId);
        Map<String, Object> result = new HashMap<>();
        result.put("packages",packages);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

}
