
package Models;

public abstract class User { //USER (ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)

    public enum Permission{ADMINISTRATEUR, REFERENT_PEDAGOGIQUE, ENSEIGNANT, ETUDIANT}

    private final String email;
    private final String password;
    private final String first_name;
    private final String last_name;
    private  final int m_ID;

    private final Permission permission;



    public User(int Id,String email,String password,String first_name,String last_name,Permission permission){
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.permission = permission;
        this.m_ID=Id;

    }

    public int getId(){

        return m_ID;

    }



    @Override
    public String toString() {
        return "User{" +
                "Id='" + m_ID + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", permission=" + permission +
                '}';
    }
}