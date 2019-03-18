package takeout.springcontroller.restaurant;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.restaurant.MoneyOffService;
import takeout.entity.restaurant.MoneyOff;
import takeout.entity.restaurant.Package;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.MoneyOffVO;
import takeout.parameters.restaurant.PackageVO;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/moneyOff")
public class MoneyOffController {
    private final MoneyOffService moneyOffService;

    @Autowired
    public MoneyOffController(MoneyOffService moneyOffService) {
        this.moneyOffService = moneyOffService;
    }


    @ApiOperation(value = "新增满减")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> add(@Valid @RequestBody MoneyOffVO moneyOffVO) throws NotExistException {
        moneyOffService.addMoneyOff(moneyOffVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改满减")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> update(@Valid @RequestBody MoneyOffVO moneyOffVO) throws NotExistException {
        moneyOffService.updateMoneyOff(moneyOffVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除满减")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> delete(@RequestParam("id") String id) throws NotExistException {
        moneyOffService.deleteMoneyOff(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找满减")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findById(@RequestParam("id") String id) throws NotExistException {
        MoneyOff moneyOff=moneyOffService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("moneyOff",moneyOff);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有满减")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAll(){
        List<MoneyOff> moneyOffs=moneyOffService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("moneyOffs",moneyOffs);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "根据餐厅查找满减")
    @RequestMapping(value = "/findByRestaurant", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findByRestaurant(@RequestParam("restaurantId") String restaurantId) throws NotExistException {
        List<MoneyOff> moneyOffs=moneyOffService.findByRestaurant(restaurantId);
        Map<String, Object> result = new HashMap<>();
        result.put("moneyOffs",moneyOffs);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

}
