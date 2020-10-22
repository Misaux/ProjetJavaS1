package Cours;

import Cours.Cours;

public class Main {
    public static void main(String[] args) {
        Cours test = new Cours(Cours.Campus.LYON, Cours.Batiment.C, 402, Cours.TypeCourse.PRESENTIEL, Cours.Course.INFO, Cours.Groupe.A);
        System.out.println(test.toString());
    }
}
