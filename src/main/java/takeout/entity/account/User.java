package takeout.entity.account;

import org.hibernate.annotations.GenericGenerator;
import takeout.entity.account.Level;

import javax.persistence.*;

@Entity
@Table(name = "user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "password")
    private String password; //密码

    @Column(name = "mobilePhone")
    private String mobilePhone; //手机号

    @Column(name = "name")
    private String name; //姓名

    @Column(name = "email")
    private String email; //邮箱

    @Column(name = "defaultAddress")
    private String defaultAddress; //默认地址的id

    @Column(name = "faceUrl")
    private String faceUrl; //头像地址

    @ManyToOne
    private Level level;//用户等级

    @Column(name= "status")
    private boolean status;//是否启用

    @Column(name= "balance")
    private double balance;//余额

    public User() {
    }

    public User(String username, String password, String mobilePhone, String name, String email, String defaultAddress, String faceUrl,Level level,boolean status,double balance) {
        this.username = username;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.name = name;
        this.email = email;
        this.defaultAddress = defaultAddress;
        this.faceUrl = faceUrl;
        this.level=level;
        this.status=status;
        this.balance=balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
