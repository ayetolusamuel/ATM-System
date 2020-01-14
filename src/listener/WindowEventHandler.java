package listener;

import views.Homepage;
import views.LoginFirst;
import views.OpenAccount;

public class WindowEventHandler {

    static WindowEventHandler mHandler;
    public static WindowEventHandler getInstance() {
        if (mHandler == null) {
            mHandler = new WindowEventHandler();
        }
        return mHandler;
    }

    public void depositCashWindowOpen() {
//        views.DepositCash cash = new views.DepositCash();
//        cash.setVisible(true);
//        cash.setSize(500,330);
//        cash.setLocation(300,100);
    }

    public void transferFundsWindowOpen() {
//        TransferFunds  transferFunds = new TransferFunds();
//        transferFunds.setVisible(true);
//        transferFunds.setSize(530,320);
//        transferFunds.setLocation(300, 100);
    }

    public void homePageWindowOpen() {

        Homepage homepage = new Homepage();
        homepage.setSize(500,600);
        homepage.setResizable(false);
        homepage.setVisible(true);
    }

    public void openAccountWindowOpen() {
        OpenAccount openAccount = new OpenAccount();
        openAccount.setVisible(true);
    }

    public void loginWindowOpen() {
        LoginFirst loginFirst = new LoginFirst();
        loginFirst.setVisible(true);
        loginFirst.setDefaultCloseOperation(3);
    }


}
