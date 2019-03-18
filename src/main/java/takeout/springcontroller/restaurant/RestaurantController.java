package takeout.springcontroller.restaurant;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.restaurant.RestaurantService;
import takeout.entity.account.Level;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.RestaurantVO;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "新增餐厅")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> add(@Valid @RequestBody RestaurantVO restaurantVO){
        restaurantService.addRestaurant(restaurantVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "注册餐厅")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> register(@Valid @RequestBody RestaurantVO restaurantVO){
        restaurantService.registerRestaurant(restaurantVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改餐厅")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> update(@Valid @RequestBody Restaurant restaurant) throws NotExistException {
        restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除餐厅")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> delete(@RequestParam("id") String id) throws NotExistException {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找餐厅")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findById(@RequestParam("id") String id) throws NotExistException {
        Restaurant restaurant=restaurantService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("restaurant",restaurant);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有餐厅")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAllLevel(){
        List<Restaurant> restaurants=restaurantService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("restaurants",restaurants);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "通过申请")
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> pass(@RequestParam("id") String id) throws NotExistException {
        restaurantService.pass(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

}
