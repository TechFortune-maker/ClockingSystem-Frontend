package za.ac.tut.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.databases.DatabaseManager;

public class StudentFrame extends JFrame {

    //create panels
    private JPanel headingPn1;
    private JPanel usernamePn1;
    private JPanel genderPn1;
    private JPanel rolePnl;
    private JPanel btnPnl;
    public JPanel componenetsCombinedPnl;
    private JPanel mainPnl;

    //create labels 
    public JLabel headingLb1;
    public JLabel usernameLb1;
    public JLabel genderLb1;
    public JLabel roleLb1;

    //create buttons 
    private JButton clockInBtn;
    private JButton clockOutBtn;

    private final DatabaseManager manager = new DatabaseManager();

    public StudentFrame() {

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
        componenetsCombinedPnl = new JPanel(new GridLayout(3, 1, 1, 1));
        componenetsCombinedPnl.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "Personal Information"));

        mainPnl = new JPanel(new BorderLayout());

        //create labels 
        headingLb1 = new JLabel("Clocking Management System");
        headingLb1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingLb1.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 20));
        headingLb1.setForeground(Color.RED);

        usernameLb1 = new JLabel("Username: ");
        genderLb1 = new JLabel("Gender: ");
        roleLb1 = new JLabel("Role: ");

        //Create buttons 
        clockInBtn = new JButton("Clock In");
        clockInBtn.addActionListener(new ClockInBtn());
        clockOutBtn = new JButton("Clock Out");
        clockOutBtn.addActionListener(new ClockOutBtn());

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

    private class ClockInBtn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] args = usernameLb1.getText().split(":");
            String username = args[1].trim();

            manager.addClockInTime(username);

            Timestamp clockInTime = manager.getClockInTime(username);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 8);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            Timestamp dutyTime = new Timestamp(today.getTimeInMillis());
            
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(clockInTime);

            if (clockInTime.after(dutyTime)) {
                JOptionPane.showMessageDialog(null, "Clock in time: " + formattedTime + "\n\n"
                        + "You are late. You are supposed to report for duty at 8 AM.");
            } else {
                JOptionPane.showMessageDialog(null, "Clock in time: " + formattedTime
                        + "\n\nGood job! You have reported for duty on time.");
            }
        }
    }

    private class ClockOutBtn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] args = usernameLb1.getText().split(":");
            String username = args[1].trim();

            manager.addClockOutTime(username);

            Timestamp clockOutTime = manager.getClockOutTime(username);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 16);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            Timestamp knockOff = new Timestamp(today.getTimeInMillis());

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(clockOutTime);
            
            if (clockOutTime.after(knockOff)) {
                JOptionPane.showMessageDialog(null, ""
                        + "Job done for the day. Goodbye!"
                        + "\n\nClock out time: " + formattedTime);
            } else {
                JOptionPane.showMessageDialog(null, "You are clocking off too early. Please note that"
                        + " duty time is until 4 PM."
                        + "\n\nClock out time: " + formattedTime);
            }
        }

    }
}
