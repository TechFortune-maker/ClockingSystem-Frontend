package za.ac.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.databases.DatabaseManager;
import za.ac.tut.model.User;

public class RegisterFrame extends JFrame {

    //panels 
    private JPanel headingPnl;
    private JPanel usernamePn1;
    private JPanel genderPnl;
    private JPanel rolePnl;
    private JPanel passwordPnl;
    private JPanel repasswordPnl;
    private JPanel submitBtnPnl;
    private JPanel componentsCombinedPnl;
    private JPanel mainPnl;

    //labels 
    private JLabel headingLbl;
    private JLabel usernameLbl;
    private JLabel genderLbl;
    private JLabel roleLbl;
    private JLabel passwordLbl;
    private JLabel repasswordLb1;

    //textfields
    private JTextField usernameTxtFld;
    private JTextField passwordTxtFld;
    private JTextField repasswordTxtFld;

    //buttons 
    private JButton submitBtn;

    //ComboBox
    private JComboBox genderComboBox;
    private JComboBox roleComboBox;

    public RegisterFrame() {
        setTitle("Register page");
        setSize(600, 650);
        setResizable(true);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Create panels
        headingPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        repasswordPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        submitBtnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        componentsCombinedPnl = new JPanel(new GridLayout(6, 1, 1, 1));
        componentsCombinedPnl.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "Personal Detail(s)"));
        mainPnl = new JPanel(new BorderLayout());

        //create labels
        headingLbl = new JLabel("Clocking Management System");
        headingLbl.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 30));
        headingLbl.setForeground(Color.RED);

        usernameLbl = new JLabel("Username:                    ");
        genderLbl = new JLabel("Gender:                        ");
        roleLbl = new JLabel("Role:                             ");
        passwordLbl = new JLabel("Password:                    ");
        repasswordLb1 = new JLabel("Re-enter Password:   ");

        //Create text fields
        usernameTxtFld = new JTextField(20);
        passwordTxtFld = new JTextField(20);
        repasswordTxtFld = new JTextField(20);

        //create comboBox
        genderComboBox = new JComboBox();
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");

        roleComboBox = new JComboBox();
        roleComboBox.addItem("HR");
        roleComboBox.addItem("Supervisor");
        roleComboBox.addItem("Student");

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(new SubmitBtnListener());

        //add componenets to the panel
        headingPnl.add(headingLbl);

        usernamePn1.add(usernameLbl);
        usernamePn1.add(usernameTxtFld);

        genderPnl.add(genderLbl);
        genderPnl.add(genderComboBox);

        passwordPnl.add(passwordLbl);
        passwordPnl.add(passwordTxtFld);

        repasswordPnl.add(repasswordLb1);
        repasswordPnl.add(repasswordTxtFld);

        rolePnl.add(roleLbl);
        rolePnl.add(roleComboBox);

        //add components to the collective panel 
        componentsCombinedPnl.add(usernamePn1);
        componentsCombinedPnl.add(genderPnl);
        componentsCombinedPnl.add(passwordPnl);
        componentsCombinedPnl.add(repasswordPnl);
        componentsCombinedPnl.add(rolePnl);

        submitBtnPnl.add(submitBtn);

        //add Components to the main panel
        mainPnl.add(headingPnl, BorderLayout.NORTH);
        mainPnl.add(componentsCombinedPnl, BorderLayout.CENTER);
        mainPnl.add(submitBtnPnl, BorderLayout.SOUTH);

        //add the main panel to the panel of the frame
        add(mainPnl);

        //pack the frame
        pack();

        //make the frame visible
        setVisible(true);

    }

    private class SubmitBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username, password, role, gender;

            //read data from the fields
            username = usernameTxtFld.getText();
            password = passwordTxtFld.getText();
            gender = (String) genderComboBox.getSelectedItem();
            role = (String) roleComboBox.getSelectedItem();

            DatabaseManager manager = new DatabaseManager();
            User user = manager.getUserDetails(username);

            if (user != null) {
                JOptionPane.showMessageDialog(
                        null, "User with " + username + " is already registered.",
                        "Registration Failed", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            manager.registerUser(username, gender, password, role);
            JOptionPane.showMessageDialog(
                    null, "You have successfully registered.",
                    "Registration Successful", JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
