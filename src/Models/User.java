
package Models;

public class User { //USER (ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)

    public enum Permission {REFERENT, ETUDIANT, ENSEIGNANT, ADMIN}

    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private Long ID;
    private Permission permission;

    public User() {
    }

    public User(Long Id,String email,String password,String first_name,String last_name,Permission permission){
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.permission = permission;
        this.ID=Id;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getPermission() {
        return permission.name();
    }

    public void setPermission(String permission) {
        this.permission = Permission.valueOf(permission);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + ID + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", permission=" + permission +
                '}';
    }
}