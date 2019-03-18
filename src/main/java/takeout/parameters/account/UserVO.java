package takeout.parameters.account;


public class UserVO {
    private String username;//用户名

    private String password; //密码

    private String mobilePhone; //手机号

    private String name; //姓名

    private String email; //邮箱

    private String defaultAddress; //默认地址

    private String faceUrl; //头像地址

    private String code;

    public UserVO() {
    }

    public UserVO(String username, String password, String mobilePhone, String name, String email, String defaultAddress, String faceUrl,String code) {
        this.username = username;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.name = name;
        this.email = email;
        this.defaultAddress = defaultAddress;
        this.faceUrl = faceUrl;
        this.code=code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
