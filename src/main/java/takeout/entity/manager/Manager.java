package takeout.entity.manager;

import org.hibernate.annotations.GenericGenerator;
import takeout.entity.account.Level;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Manager {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "password")
    private String password; //密码

    @Column(name = "balance")
    private double balance;//yummy余额

    public Manager(String username, String password, double balance) {
        this.username = username;
        this.password = password;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
