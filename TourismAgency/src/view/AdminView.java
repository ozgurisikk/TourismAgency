package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout{

    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JTabbedPane pnl_bottom;
    private JTable tbl_employees;
    private Object[] col_employee;
    private DefaultTableModel tmdl_employees = new DefaultTableModel();
    private JPanel lbl_employees;
    private JScrollPane scrl_tab;
    private JLabel lbl_role_filter;
    private JComboBox cmb_role_filter;
    private JButton btn_filter_search;
    private JButton btn_filter_clear;
    private User user;
    private UserManager userManager;
    private JPopupMenu employee_menu;

    public AdminView(User user) {
        this.add(container);
        this.guiInitialize(600,600);
        this.user = user;
        this.userManager = new UserManager();

        if (this.user == null){
            dispose();
        }
        this.lbl_welcome.setText("Hosgeldiniz " + user.getUsername().toUpperCase() + ".");
        loadEmployeeTable(null);
        loadEmployeeComponent();
        loadEmployeeFilter();


    }
    public void loadEmployeeTable(ArrayList<Object[]> userList){
        this.col_employee = new Object[]{"ID", "İsim", "Rol"};
        if( userList == null){
            userList = this.userManager.getForTable(col_employee.length,this.userManager.findAll());
        }
        this.createTable(this.tmdl_employees, this.tbl_employees, col_employee, userList);
    }
    public void loadEmployeeComponent(){
        this.tableRowSelect(this.tbl_employees);

        this.employee_menu = new JPopupMenu();
        this.employee_menu.add("Ekle").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadEmployeeTable(null);
                    loadEmployeeComponent();
                }
            });
        });
        this.employee_menu.add("Guncelle").addActionListener(e -> {
                int selectedEmployeeId = this.getTableSelectedRow(tbl_employees, 0);
                UserView userView = new UserView(this.userManager.getById(selectedEmployeeId));
                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadEmployeeTable(null);
                        loadEmployeeComponent();
                    }
                });
        });
        this.employee_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedId = this.getTableSelectedRow(tbl_employees, 0);
                if (this.userManager.delete(selectedId)){
                    Helper.showMsg("done");
                    loadEmployeeTable(null);

                }else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_employees.setComponentPopupMenu(employee_menu);


        this.btn_filter_clear.addActionListener(e -> loadEmployeeTable(null));
        this.btn_filter_clear.addActionListener(e -> this.cmb_role_filter.setSelectedItem(null));

        btn_filter_search.addActionListener(e -> {
            ArrayList<User> userListBySearch = this.userManager.searchForTable((Role) cmb_role_filter.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_employee.length,userListBySearch);

            loadEmployeeTable(userRowListBySearch);

        });

    }
    public void loadEmployeeFilter(){
        this.cmb_role_filter.setModel(new DefaultComboBoxModel<>( Role.values()));
        this.cmb_role_filter.setSelectedItem(null);
    }

}
