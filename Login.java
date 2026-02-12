
package com.mycompany.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");
    JButton cancel = new JButton("Cancel");
    JLabel n_name = new JLabel("Username:");
    JLabel p_password = new JLabel("Password:");
    JTextField t_name = new JTextField("");
    JPasswordField password = new JPasswordField();
    JCheckBox show = new JCheckBox("Show the password");
    JLabel form = new JLabel("Login");
    JPanel panel = new JPanel(null);

    // قوائم لتخزين أسماء المستخدمين وكلمات المرور
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();

    public Login() {
        this.setTitle("Login");
        this.setLayout(null);
        this.setSize(800, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBounds(0, 0, 800, 140);
        form.setBounds(330, 50, 200, 40);
        n_name.setBounds(620, 200, 100, 35);
        p_password.setBounds(620, 250, 100, 35);
        t_name.setBounds(250, 200, 350, 35);
        password.setBounds(250, 250, 350, 35);
        login.setBounds(250, 330, 150, 35);
        register.setBounds(450, 330, 150, 35);
        cancel.setBounds(350, 400, 150, 35);
        show.setBounds(100, 260, 125, 20);
        //show.setBounds(WIDTH x, WIDTH y, WIDTH, HEIGHT);

        panel.add(form);
        this.add(panel);
        this.add(n_name);
        this.add(p_password);
        this.add(t_name);
        this.add(password);
        this.add(login);
        this.add(register);
        this.add(cancel);
        this.add(show);
        
        show.addActionListener(this);
        login.addActionListener(this);
        register.addActionListener(this);
        cancel.addActionListener(this);
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            if (show.isSelected()) {
                password.setEchoChar('\0');
                show.setText("Hide the password");
            } else {
                password.setEchoChar('*');
                show.setText("Show the password");
            }
        } else if (e.getSource() == cancel) {
            int c = JOptionPane.showConfirmDialog(null, "Do you want to go out?", "to choose", JOptionPane.YES_NO_OPTION);
            if (c == 0) {
                System.exit(0);
            }
        } else if (e.getSource() == login) {
            String username = t_name.getText();
            String passwordInput = new String(password.getPassword());
            
            // تسجيل دخول
            if (usernames.contains(username) && passwords.get(usernames.indexOf(username)).equals(passwordInput)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            }
            /**هذي القائمه عشان تتحقق اذا كان اسم المستخدم موجود في القائمه
             * اذا كان موجود نطلع المعلومات حقته
             * بعدها يقارن اذا البيانات المدخله صحيحه نفس المخزنه
            */
             
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
            //تسجيل مستخدم جديد
        } else if (e.getSource() == register) {
            String username = t_name.getText();
            String passwordInput = new String(password.getPassword());

            if (usernames.contains(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose another one.");
            } else {
                // إضافة المستخدم الجديد
             usernames.add(username);
                passwords.add(passwordInput);
                JOptionPane.showMessageDialog(this, "User registered successfully!");
            }
        }
    }
}   
