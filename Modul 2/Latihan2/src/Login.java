import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MenuUtama menuUtama;

    public Login(MenuUtama menuUtama) {
        this.menuUtama = menuUtama; // Menyimpan referensi ke MenuUtama

        setTitle("Login Aplikasi Penjualan");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 220, 220));

        JLabel labelTitle = new JLabel("LOGIN APLIKASI PENJUALAN", SwingConstants.CENTER);
        labelTitle.setBounds(0, 20, 550, 30);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelTitle);

        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setBounds(80, 80, 100, 25);
        panel.add(labelUsername);

        usernameField = new JTextField();
        usernameField.setBounds(160, 80, 171, 25);
        panel.add(usernameField);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(80, 120, 100, 25);
        panel.add(labelPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 120, 171, 25);
        panel.add(passwordField);

        ImageIcon imageIcon = new ImageIcon("assets/log.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel labelImage = new JLabel(new ImageIcon(scaledImage));
        labelImage.setBounds(340, 50, 150, 150);
        panel.add(labelImage);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 160, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("Admin") && password.equals("admin123")) {
                    menuUtama.setLoggedIn(true); // Ubah status login di MenuUtama
                    dispose(); // Tutup login form
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 160, 80, 25);
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        JLabel labelNote = new JLabel("*Masukkan username dan password untuk masuk sistem aplikasi");
        labelNote.setBounds(50, 200, 400, 25);
        labelNote.setFont(new Font("Arial", Font.ITALIC, 10));
        panel.add(labelNote);

        add(panel);
        setVisible(true);
    }
}
