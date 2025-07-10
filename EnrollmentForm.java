
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EnrollmentForm extends JFrame {
    private JTextField txtRollNo, txtFullName, txtClass, txtBirthDate, txtEnrollmentDate;
    private JTextArea txtAddress;
    private JButton btnSubmit;

    public EnrollmentForm() {
        setTitle("Student Enrollment Form");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblRollNo = new JLabel("Roll No:");
        lblRollNo.setBounds(30, 30, 100, 25);
        add(lblRollNo);
        txtRollNo = new JTextField();
        txtRollNo.setBounds(150, 30, 200, 25);
        add(txtRollNo);

        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setBounds(30, 70, 100, 25);
        add(lblFullName);
        txtFullName = new JTextField();
        txtFullName.setBounds(150, 70, 200, 25);
        add(txtFullName);

        JLabel lblClass = new JLabel("Class:");
        lblClass.setBounds(30, 110, 100, 25);
        add(lblClass);
        txtClass = new JTextField();
        txtClass.setBounds(150, 110, 200, 25);
        add(txtClass);

        JLabel lblBirthDate = new JLabel("Birth Date (yyyy-mm-dd):");
        lblBirthDate.setBounds(30, 150, 200, 25);
        add(lblBirthDate);
        txtBirthDate = new JTextField();
        txtBirthDate.setBounds(150, 180, 200, 25);
        add(txtBirthDate);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 220, 100, 25);
        add(lblAddress);
        txtAddress = new JTextArea();
        txtAddress.setBounds(150, 220, 200, 60);
        add(txtAddress);

        JLabel lblEnrollmentDate = new JLabel("Enrollment Date (yyyy-mm-dd):");
        lblEnrollmentDate.setBounds(30, 300, 250, 25);
        add(lblEnrollmentDate);
        txtEnrollmentDate = new JTextField();
        txtEnrollmentDate.setBounds(150, 330, 200, 25);
        add(txtEnrollmentDate);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 380, 100, 30);
        add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertStudentData();
            }
        });
    }

    private void insertStudentData() {
        String url = "jdbc:mysql://localhost:3306/SCHOOL_DB";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "INSERT INTO STUDENT (Roll_No, Full_Name, Class, Birth_Date, Address, Enrollment_Date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, Integer.parseInt(txtRollNo.getText()));
            pst.setString(2, txtFullName.getText());
            pst.setString(3, txtClass.getText());
            pst.setDate(4, java.sql.Date.valueOf(txtBirthDate.getText()));
            pst.setString(5, txtAddress.getText());
            pst.setDate(6, java.sql.Date.valueOf(txtEnrollmentDate.getText()));

            int i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Enrollment Successful!");
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new EnrollmentForm().setVisible(true);
    }
}
