package takeout.parameters;

import takeout.entity.User;

public class RegisterVO {
    private User user;
    private String code;

    public RegisterVO(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
