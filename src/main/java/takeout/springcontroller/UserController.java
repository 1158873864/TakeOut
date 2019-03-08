package takeout.springcontroller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.jvm.hotspot.asm.Register;
import takeout.bl.UserBlServiceImpl;
import takeout.blservice.UserBlService;
import takeout.entity.User;
import takeout.exception.NotExistException;
import takeout.parameters.RegisterVO;
import takeout.response.Result;
import takeout.response.ResultCode;
import takeout.response.ResultGenerator;

import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    private final UserBlService userBlService;

    @Autowired
    public UserController(UserBlService userBlService) {
        this.userBlService = userBlService;
    }

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "image", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> upload(@RequestParam("image")MultipartFile image){
        Map<String, Object> result = new HashMap<>();
        if(image.isEmpty()){
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.NOT_FOUND,"上传文件不能为空"), HttpStatus.OK);
        } else {

            // 获取文件名
            String fileName = image.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = image.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[200000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            File file = new File(fileName);
            String[] temp=fileName.split("\\.");
            String thePath="image/"+uuid+"."+temp[1];
            String path="image/"+uuid+"."+temp[1];
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream =new FileInputStream(fileName);
                FileOutputStream fs = new FileOutputStream(path);
                byte[] buffer = new byte[20000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            result.put("path",path);
            return new ResponseEntity<>(ResultGenerator.genSuccessResult(result),HttpStatus.OK);
        }
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
    public ResponseEntity<Result> register(@Valid @RequestBody RegisterVO registerVO) {
        String re=userBlService.register(registerVO.getUser(),registerVO.getCode());
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
}
