package takeout.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name = "verification")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Verification {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private String code;
    
    public Verification() {
    }

    public Verification(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
