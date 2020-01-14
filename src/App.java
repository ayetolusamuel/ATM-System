import daoFactory.UserController;
import model.User;
import sun.rmi.runtime.Log;
import views.LoginFirst;
import views.OpenAccount;

import javax.swing.*;

public class App extends JFrame {
//    public static void main(String[] args) {
//        OpenAccount openAccount = new OpenAccount();
//        openAccount.setVisible(true);
//    }


    public static void main(String args[]) {
        {
            try {
                UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
                JFrame.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                System.out.println("error in loading theme " + e.getMessage());
            }
        }
        LoginFirst loginFirst = new LoginFirst();
        loginFirst.setVisible(true);
        loginFirst.setLocationRelativeTo(null);
        loginFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
