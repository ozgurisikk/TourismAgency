package view;

import business.UserManager;
import core.Helper;
import entity.User;
import entity.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends Layout{
    private JPanel container;
    private JLabel lbl_title;
    private JTextField fld_name;
    private JTextField fld_password;
    private JComboBox cmb_role;
    private JButton btn_save;
    private JLabel lbl_name;
    private JPanel pnl_info;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private User user;
    private UserManager userManager;

    public UserView(User user) {
        this.add(container);
        this.user = user;
        this.userManager = new UserManager();
        this.guiInitialize(300,400);

        loadEmployeeValue();

        if (this.fld_name != null){
            this.fld_name.setText(fld_name.getText());
            this.fld_password.setText(fld_password.getText());
            this.cmb_role.setModel(cmb_role.getModel());
        }



        this.btn_save.addActionListener(e -> {
             if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_password})){
                 Helper.showMsg("fill");
             }else {
                 boolean result;
                 if (this.user.getId() == 0){
                     this.user.setUsername(this.fld_name.getText());
                     this.user.setPassword(this.fld_password.getText());
                     this.user.setRole((Role) this.cmb_role.getSelectedItem());
                     result = this.userManager.save(this.user);
                 }else {
                     this.user.setUsername(this.fld_name.getText());
                     this.user.setPassword(this.fld_password.getText());
                     this.user.setRole((Role) this.cmb_role.getSelectedItem());
                     result = this.userManager.update(this.user);
                 }
                 if (result){
                     Helper.showMsg("done");
                     dispose();
                 }else{
                     Helper.showMsg("error");
                 }
             }
        });
    }
    private void loadEmployeeValue(){
        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));
    }
}
