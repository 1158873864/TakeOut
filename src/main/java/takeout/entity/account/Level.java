package takeout.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "level")
public class Level {
    @Id
    private int id;//等级号

    @Column(name="name")
    private String name;//等级名称

    @Column(name="discount")
    private double discount;//折扣率

    @Column(name="url")
    private String url;//等级图标


    public Level() {
    }

    public Level(int id, String name, double discount, String url) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
