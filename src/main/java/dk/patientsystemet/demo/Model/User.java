package dk.patientsystemet.demo.Model;

public class User {

    private int id;
    private int roleId;
    private String name;
    private String email;
    private String password;
    private String roleName;

    public User() {
    }

    public User(int id, int roleId, String name, String email, String password, String roleName) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

