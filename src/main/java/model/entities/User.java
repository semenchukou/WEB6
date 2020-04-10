package model.entities;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = User.tableName, schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    public static final String tableName = "User";
    private String username;
    private String password;
    Role role;
    private int refid;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", refid=" + refid +
                '}';
    }

    public enum Role {
        VISITOR,
        ADMIN
    }

    public User() {

    }

    public User(String username, String password, Role role, int refid) {
        this.username = username;
        this.password = password;
        this.refid = refid;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRefid() {
        return refid;
    }

    public void setRefid(int refid) {
        this.refid = refid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
