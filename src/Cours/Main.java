package Cours;


public class Main {
    public static void main(String[] args) {
        Cours test = new Cours(Cours.Campus.LYON, Cours.Batiment.C, 402, Cours.TypeCourse.PRESENTIEL, Cours.Course.INFO, Cours.Groupe.A);
        Cours test1= new Cours(Cours.Campus.PARIS, Cours.Batiment.D,69, Cours.TypeCourse.PRESENTIEL, Cours.Course.PHYSIQUE,Cours.Groupe.C);
        System.out.println(test.toString());
        System.out.println(test1.toString());
        System.out.println("samy test");
        System.out.println("test ines");




    }
}
