package takeout.springcontroller.account;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.account.LevelService;
import takeout.entity.account.Level;
import takeout.entity.account.User;
import takeout.exception.NotExistException;
import takeout.response.Result;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/level")
public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @ApiOperation(value = "新增等级")
    @RequestMapping(value = "/addLevel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> addLevel(@Valid @RequestBody Level level){
        levelService.addLevel(level);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改等级")
    @RequestMapping(value = "/updateLevel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> updateLevel(@Valid @RequestBody Level level) throws NotExistException {
        levelService.updateLevel(level);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除等级")
    @RequestMapping(value = "/deleteLevel", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> deleteLevel(@RequestParam("id") int id) throws NotExistException {
        levelService.deleteLevel(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找等级")
    @RequestMapping(value = "/findLevelById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findLevelById(@RequestParam("id") int id) throws NotExistException {
        Level level=levelService.findById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("level",level);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

    @ApiOperation(value = "查找所有等级")
    @RequestMapping(value = "/findAllLevel", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> findAllLevel(){
        List<Level> levels=levelService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("levels",levels);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result), HttpStatus.OK);
    }

}
