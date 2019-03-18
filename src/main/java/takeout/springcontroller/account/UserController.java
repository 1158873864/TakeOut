package takeout.springcontroller.account;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.account.UserBlService;
import takeout.entity.account.User;
import takeout.exception.NotExistException;
import takeout.parameters.account.UserVO;
import takeout.response.Result;
import takeout.response.ResultCode;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserBlService userBlService;

    @Autowired
    public UserController(UserBlService userBlService) {
        this.userBlService = userBlService;
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> addUser(@Valid @RequestBody User user){
        userBlService.addUser(user);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> deleteUserById(@RequestParam("id") String id) throws NotExistException {
        userBlService.deleteUserById(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/updateUserById", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> updateUserById(@Valid @RequestBody User user) throws NotExistException {
        userBlService.updateUserById(user);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查找用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> getUserById(@RequestParam("id") String id) throws NotExistException {
        Map<String, Object> result = new HashMap<>();
        result.put("user",userBlService.getUserById(id));
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result),HttpStatus.OK);
    }

    @ApiOperation(value = "所有用户", notes = "")
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> getAllUser() {
        Map<String, Object> result = new HashMap<>();
        result.put("userList",userBlService.getAllUser());
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(result),HttpStatus.OK);
    }

    @ApiOperation(value = "获取验证码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getVertificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> getVertificationCode(@RequestParam("email") String email) {
        userBlService.getVertificationCode(email);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }

    @ApiOperation(value = "注册", notes = "")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> register(@Valid @RequestBody UserVO userVO) {
        String re=userBlService.register(userVO);
        if(re.equals("1")){
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR,re),HttpStatus.OK);
        }

    }

    @ApiOperation(value = "根据email登录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loginByEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> loginByEmail(@RequestParam("email")String email,@RequestParam("password")String password) {
        String re=userBlService.loginByEmail(email,password);
        if(re.equals("1")){
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR,re),HttpStatus.OK);
        }

    }

    @ApiOperation(value = "根据手机号登录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobilePhone", value = "mobilePhone", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loginByMobilePhone", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> loginByMobilePhone(@RequestParam("mobilePhone")String mobilePhone,@RequestParam("password")String password) {
        String re=userBlService.loginByMobilePhone(mobilePhone,password);
        if(re.equals("1")){
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR,re),HttpStatus.OK);
        }
    }

    @ApiOperation(value = "撤销", notes = "")
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> withdraw(@RequestParam("id")String id) throws NotExistException {
        userBlService.withdraw(id);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }

    @ApiOperation(value = "修改默认地址", notes = "")
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> setDefaultAddress(@RequestParam("userId")String userId,@RequestParam("addressId")String addressId) throws NotExistException {
        userBlService.setDefaultAddress(userId,addressId);
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
    }
}
