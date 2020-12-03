import dao.*;
import models.*;
import controller.MainFrameController;
import controller.SecondFrameController;

import view.SecondFrameView;

public class Main {
    public static void main(String[] args) {


        User user = new User();
        Promotion promotion = new Promotion();
        Session session = new Session();


        MainFrameController mainFrameController = new MainFrameController(session, promotion);
        SecondFrameController secondFrameController = new SecondFrameController(session, user);


        SecondFrameView secondFrameView = new SecondFrameView(secondFrameController, mainFrameController);


    }
}
