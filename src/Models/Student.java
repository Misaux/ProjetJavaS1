package Models;

//(#ID_USER, NUMBER, #ID_GROUP …)
public class Student extends User {

private int Number;
private Long Id;
private Long IdGroup;



public Student (int Number,Long ID,GroupPromo group, User user, String email,String password,String first_name,String last_name,Permission permission)
{
    super(ID,email,password,first_name,last_name,permission);
    this.Number = Number;
    this.Id = user.getId();
    this.IdGroup=group.getID();

}

}
