package takeout.springcontroller.restaurant;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.restaurant.GoodsService;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.restaurant.GoodsVO;
import takeout.parameters.restaurant.RestaurantVO;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "新增商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> add(@Valid @RequestBody GoodsVO goodsVO) throws NotExistException {
        goodsService.addGoods(goodsVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> update(@Valid @RequestBody GoodsVO goodsVO) throws NotExistException {
        goodsService.updateGoods(goodsVO);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> delete(@RequestParam("id") String id) throws NotExistException {
        goodsService.deleteGoods(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找商品")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findById(@RequestParam("id") String id) throws NotExistException {
        Goods goods=goodsService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("goods",goods);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有商品")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAll(){
        List<Goods> goods=goodsService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("goods",goods);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "根据餐厅查找商品")
    @RequestMapping(value = "/findByRestaurant", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findByRestaurant(@RequestParam("restaurantId") String restaurantId) throws NotExistException {
        List<Goods> goods=goodsService.findByRestaurant(restaurantId);
        Map<String, Object> result = new HashMap<>();
        result.put("goods",goods);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

}
