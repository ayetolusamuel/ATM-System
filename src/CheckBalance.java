/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class CheckBalance extends JFrame{
    
    
    private void checkBalance(){
        popUp();
    }
    
    private void popUp(){
        JOptionPane.showConfirmDialog(null, "SM");
    }
    
    public static void main(String[] args) {
        CheckBalance balance = new CheckBalance();
        balance.setSize(300,100);
        
        balance.setVisible(true);
    }
}
