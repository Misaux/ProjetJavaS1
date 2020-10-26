package User;
import ClassesBDD.GroupPromo;

//(#ID_USER, NUMBER, #ID_GROUP …)
public class Student extends User {

private int m_Number;
private int m_Id;
private int m_IdGroup;



public Student (int Number,int ID,GroupPromo group, User user, String email,String password,String first_name,String last_name,Permission permission)
{
    super(ID,email,password,first_name,last_name,permission);
    this.m_Number = Number;
    this.m_Id= user.getId();
    this.m_IdGroup=group.getID();

}

}
