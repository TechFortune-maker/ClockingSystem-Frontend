
package za.ac.tut.hr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class HRFrame extends JFrame{
    
    //create panels
    private JPanel headingPn1;
    private JPanel usernamePn1;
    private JPanel genderPn1;
    private JPanel rolePnl;
    private JPanel btnPnl;
    private JPanel componenetsCombinedPnl;
    private JPanel mainPnl;
    
    //create labels 
    private JLabel headingLb1;
    private JLabel usernameLb1;
    private JLabel genderLb1;
    private JLabel roleLb1;
    
    //create buttons 
    private JButton clockInBtn;
    private JButton clockOutBtn;
    private JButton viewReportsBtn;


    public HRFrame() {
        
        setSize(600, 650);
        setResizable(true);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //create panels 
        headingPn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        componenetsCombinedPnl = new JPanel(new GridLayout(3,1,1,1));
        componenetsCombinedPnl.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"Personal Information"));
        
        mainPnl = new JPanel(new BorderLayout());
        
        //create labels 
        headingLb1 = new JLabel("Clocking Management System");
        headingLb1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingLb1.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC,20));
        headingLb1.setForeground(Color.RED);
        
        usernameLb1 = new JLabel("Username: ");
        genderLb1 = new JLabel("Gender: ");
        roleLb1 = new JLabel("Role: ");
        
        //Create buttons 
        clockInBtn = new JButton("Clock In");
        clockOutBtn = new JButton("Clock Out");
        viewReportsBtn = new JButton("View Reports");
        
        //add componenets to the panel 
        headingPn1.add(headingLb1);
        
        usernamePn1.add(usernameLb1);
        genderPn1.add(genderLb1);
        rolePnl.add(roleLb1);
        
        //collective panel
        componenetsCombinedPnl.add(usernamePn1);
        componenetsCombinedPnl.add(genderPn1);
        componenetsCombinedPnl.add(rolePnl);
        
        //button panel 
        btnPnl.add(clockInBtn);
        btnPnl.add(clockOutBtn);
        btnPnl.add(viewReportsBtn);
        
        mainPnl.add(headingPn1, BorderLayout.NORTH);
        mainPnl.add(componenetsCombinedPnl, BorderLayout.CENTER);
        mainPnl.add(btnPnl, BorderLayout.SOUTH);
        
        //add main panel to the frame 
        add(mainPnl);
        
        //pack the frame
        pack();
        
        //make the frame visible 
        setVisible(true);

        
    }

}
