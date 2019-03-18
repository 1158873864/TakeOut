package takeout.springcontroller.manager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takeout.blservice.manager.ManagerService;
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
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;


    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation(value = "根据username登录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> loginByEmail(@RequestParam("username")String username,@RequestParam("password")String password) {
        String re=managerService.login(username,password);
        if(re.equals("1")){
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR,re),HttpStatus.OK);
        }

    }
}
