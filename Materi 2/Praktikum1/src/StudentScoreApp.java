import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentScoreApp {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Form Nilai Mahasiswa");
        frame.setSize(400, 350);  // Adjusted size for the title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // H1 Style Title for Form
        JLabel titleLabel = new JLabel("Form Nilai Mahasiswa", JLabel.CENTER);
        titleLabel.setBounds(20, 10, 350, 40);  // Positioning the title
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));  // H1 style (larger, bold)
        frame.add(titleLabel);

        // NIM Label and Field
        JLabel nimLabel = new JLabel("NIM");
        nimLabel.setBounds(20, 60, 100, 25);
        frame.add(nimLabel);

        JTextField nimField = new JTextField();
        nimField.setBounds(150, 60, 200, 25);
        frame.add(nimField);

        // Name Label and Field
        JLabel nameLabel = new JLabel("Nama Mahasiswa");
        nameLabel.setBounds(20, 100, 150, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 25);
        frame.add(nameField);

        // Score Label and Field
        JLabel scoreLabel = new JLabel("Nilai");
        scoreLabel.setBounds(20, 140, 100, 25);
        frame.add(scoreLabel);

        JTextField scoreField = new JTextField();
        scoreField.setBounds(150, 140, 200, 25);
        frame.add(scoreField);

        // Result Label
        JLabel resultLabel = new JLabel("Keterangan");
        resultLabel.setBounds(20, 180, 100, 25);
        frame.add(resultLabel);

        JTextField resultField = new JTextField();
        resultField.setBounds(150, 180, 200, 25);
        resultField.setEditable(false);
        frame.add(resultField);

        // Process Button
        JButton processButton = new JButton("Proses");
        processButton.setBounds(150, 220, 100, 25);
        frame.add(processButton);

        // Clear Button
        JButton clearButton = new JButton("Hapus");
        clearButton.setBounds(260, 220, 100, 25);
        frame.add(clearButton);

        // Exit Button
        JButton exitButton = new JButton("Keluar");
        exitButton.setBounds(150, 260, 210, 25);
        frame.add(exitButton);

        // Action Listener for Process Button
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int score = Integer.parseInt(scoreField.getText());
                    if (score >= 65) {
                        resultField.setText("Lulus");
                    } else {
                        resultField.setText("Mengulang");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener for Clear Button with SweetAlert-like confirmation
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, 
                        "Apakah Anda yakin ingin menghapus semua data?", 
                        "Konfirmasi Hapus", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    nimField.setText("");
                    nameField.setText("");
                    scoreField.setText("");
                    resultField.setText("");
                }
            }
        });

        // Action Listener for Exit Button with SweetAlert-like confirmation
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, 
                        "Apakah Anda yakin ingin keluar?", 
                        "Konfirmasi Keluar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
}
