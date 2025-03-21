package za.ac.tut.ui;

import za.ac.tut.frames.StudentFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.databases.DatabaseManager;
import za.ac.tut.frames.HRFrame;
import za.ac.tut.frames.SupervisorFrame;
import za.ac.tut.model.User;

public class LoginFrame extends JFrame {
    //panels 

    private JPanel headingPn1;
    private JPanel usernamePnl;
    private JPanel passwordPnl;
    private JPanel btnsPnl;
    private JPanel mainPnl;
    private JPanel usernamePasswordCombinedPn1;

    //labels 
    private JLabel headingLbl;
    private JLabel usernameLbl;
    private JLabel passwordLbl;

    //textfields
    private JTextField usernameTxtFld;

    //password field
    private JPasswordField passwordFld;

    //buttons
    private JButton loginBtn;
    private JButton registerBtn;

    public LoginFrame() {
        //Basic Login page   setTitle("Login page");
        setSize(600, 650);
        setResizable(true);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Create panels 
        headingPn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnsPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

        usernamePasswordCombinedPn1 = new JPanel(new GridLayout(2, 1, 1, 1));
        usernamePasswordCombinedPn1.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "Login details"));

        mainPnl = new JPanel(new BorderLayout());

        //Create labels
        headingLbl = new JLabel("Clocking Management System");
        headingLbl.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 30));
        headingLbl.setForeground(Color.RED);

        usernameLbl = new JLabel("Username: ");
        passwordLbl = new JLabel("Password: ");

        //Create fields
        usernameTxtFld = new JTextField(20);
        usernameTxtFld.setFocusable(true);

        passwordFld = new JPasswordField(20);

        //Create buttons 
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(new LoginBtnlistener());
        registerBtn = new JButton("Register");
        registerBtn.addActionListener(new RegisterBtnListener());

        //add components to the respective panels
        headingPn1.add(headingLbl);

        usernamePnl.add(usernameLbl);
        usernamePnl.add(usernameTxtFld);

        passwordPnl.add(passwordLbl);
        passwordPnl.add(passwordFld);

        usernamePasswordCombinedPn1.add(usernamePnl);
        usernamePasswordCombinedPn1.add(passwordPnl);

        btnsPnl.add(loginBtn);
        btnsPnl.add(registerBtn);

        mainPnl.add(headingPn1, BorderLayout.NORTH);
        mainPnl.add(usernamePasswordCombinedPn1, BorderLayout.CENTER);
        mainPnl.add(btnsPnl, BorderLayout.SOUTH);

        //add the main panel to the panel of the frame
        add(mainPnl);

        //pack the frame
        pack();

        setVisible(true);

    }

    private class RegisterBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //Call the register frame page
            new RegisterFrame();

        }
    }

    private class LoginBtnlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredUsername = usernameTxtFld.getText();
            String enteredPassword = new String(passwordFld.getPassword());

            DatabaseManager manager = new DatabaseManager();
            User user = manager.getUserDetails(enteredUsername);

            if (user == null) {
                JOptionPane.showMessageDialog(
                        null, "User doesn't exist. Create an account first.",
                        "User Invalid", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String role = user.getRole();
            String gender = user.getGender();
            String username = user.getUsername();
            String password = user.getPassword();

            if (!enteredPassword.equals(password)) {
                JOptionPane.showMessageDialog(
                        null, "Password doesn't match. Try again.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (role.equalsIgnoreCase("student")) {
                StudentFrame sf = new StudentFrame();
                sf.usernameLb1.setText("Username: " + username);
                sf.genderLb1.setText("Gender: " + gender);
                sf.roleLb1.setText("Role: " + role);
                sf.componenetsCombinedPnl.setBorder(
                        new TitledBorder(new LineBorder(Color.BLUE, 2), "Student Personal Information")
                );

            } else if (role.equalsIgnoreCase("supervisor")) {
                SupervisorFrame sf = new SupervisorFrame();
                sf.usernameLb1.setText("Username: " + username);
                sf.genderLb1.setText("Gender: " + gender);
                sf.roleLb1.setText("Role: " + role);
                sf.componenetsCombinedPnl.setBorder(
                        new TitledBorder(new LineBorder(Color.BLUE, 2), "Supervisor Personal Information")
                );
                
            } else if (role.equalsIgnoreCase("hr")) {
                HRFrame hf = new HRFrame();
                hf.usernameLb1.setText("Username: " + username);
                hf.genderLb1.setText("Gender: " + gender);
                hf.roleLb1.setText("Role: " + role);
                hf.componenetsCombinedPnl.setBorder(
                        new TitledBorder(new LineBorder(Color.BLUE, 2), "HR Personal Information")
                );
            }
        }
    }
}
