
package Models;

public abstract class User { //USER (ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)

    public enum Permission{ADMINISTRATEUR, REFERENT_PEDAGOGIQUE, ENSEIGNANT, ETUDIANT}

    private final String email;
    private final String password;
    private final String first_name;
    private final String last_name;
    private  final Long ID;

    private final Permission permission;



    public User(Long Id,String email,String password,String first_name,String last_name,Permission permission){
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.permission = permission;
        this.ID=Id;

    }

    public Long getId(){

        return ID;

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