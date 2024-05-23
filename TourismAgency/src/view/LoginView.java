package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{
    private JPanel container;
    private UserManager userManager;
    private JPanel pnl_wlcm;
    private JLabel lbl_wlcm;
    private JLabel pnl_wlcm2;
    private JPanel pnl_bottom;
    private JTextField fld_name;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_name;
    private JLabel lbl_pass;

    public LoginView(){
        this.add(container);
        this.userManager = new UserManager();
        guiInitialize(300, 500);

        //checking and login process
        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_name) || Helper.isFieldEmpty(this.fld_pass)){
                Helper.showMsg("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_name.getText(), this.fld_pass.getText());
                if(loginUser == null){
                    Helper.showMsg("notFound");
                }else {
                    if (loginUser.getRole().toString().equals("ADMIN")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    }else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}
