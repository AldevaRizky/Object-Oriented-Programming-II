import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuUtama extends JFrame {
    private boolean isLoggedIn = false;
    private JLabel lblBarang, lblPengadaan, lblPenjualan, lblLaporan;
    private JMenuItem menuItemLogin, menuItemManageUser, menuItemLogout;

    public MenuUtama() {
        setTitle("Form Menu Utama");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelUtama = new JPanel(new BorderLayout());
        add(panelUtama);

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Setup user menu
        ImageIcon iconUser = new ImageIcon(new ImageIcon("assets/1.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        JLabel lblUser = new JLabel("User", iconUser, JLabel.CENTER);
        JPopupMenu userMenu = new JPopupMenu();
        menuItemLogin = new JMenuItem("Login");
        menuItemManageUser = new JMenuItem("Manage User");
        menuItemLogout = new JMenuItem("Logout");

        userMenu.add(menuItemLogin);
        userMenu.add(menuItemManageUser);
        userMenu.add(menuItemLogout);
        lblUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userMenu.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblUser.setOpaque(true); // Aktifkan latar belakang solid
                lblUser.setBackground(Color.LIGHT_GRAY); // Ubah warna saat hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblUser.setBackground(null); // Kembalikan warna default
                lblUser.setOpaque(false); // Nonaktifkan latar belakang solid
            }
        });
        panelMenu.add(lblUser);

        // Setup pop-up menus for other sections (Barang, Pengadaan, Penjualan, Laporan)
        lblBarang = createMenuWithPopup(panelMenu, "Manajemen Barang", "assets/2.png", "Barang");
        lblPengadaan = createMenuWithPopup(panelMenu, "Pengadaan", "assets/3.png", "Pengadaan");
        lblPenjualan = createMenuWithPopup(panelMenu, "Penjualan", "assets/4.png", "Penjualan");
        lblLaporan = createMenuWithPopup(panelMenu, "Laporan", "assets/6.png", "Laporan");

        panelUtama.add(panelMenu, BorderLayout.NORTH);

        // JSeparator untuk garis pemisah
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GRAY);
        panelUtama.add(separator, BorderLayout.CENTER);

        // Set "Manage User" dan "Logout" tidak aktif sebelum login
        menuItemManageUser.setEnabled(false);
        menuItemLogout.setEnabled(false);

        setMenuEnabled(false);

        // Action listener untuk login
        menuItemLogin.addActionListener(e -> new Login(MenuUtama.this).setVisible(true));

        // Konfirmasi sebelum logout
        menuItemLogout.addActionListener(e -> {
            if (isLoggedIn) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin logout?",
                        "Konfirmasi Logout",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    logout();
                    JOptionPane.showMessageDialog(null, "Logout berhasil.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Anda belum login.");
            }
        });

        setVisible(true);
    }

    // Fungsi untuk membuat label dengan pop-up menu dan efek hover hanya jika sudah login
    private JLabel createMenuWithPopup(JPanel parentPanel, String labelText, String iconPath, String menuName) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(labelText, icon, JLabel.CENTER);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isLoggedIn) {
                    label.setOpaque(true); // Aktifkan latar belakang solid
                    label.setBackground(Color.LIGHT_GRAY); // Ubah warna saat hover
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isLoggedIn) {
                    label.setBackground(null); // Kembalikan warna default
                    label.setOpaque(false); // Nonaktifkan latar belakang solid
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isLoggedIn) {
                    JOptionPane.showMessageDialog(null, menuName + " clicked!");
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.");
                }
            }
        });

        parentPanel.add(label);
        return label;
    }

    public void setMenuEnabled(boolean enabled) {
        lblBarang.setEnabled(enabled);
        lblPengadaan.setEnabled(enabled);
        lblPenjualan.setEnabled(enabled);
        lblLaporan.setVisible(enabled); // Sembunyikan menu laporan jika belum login
        menuItemLogin.setVisible(!enabled); // Sembunyikan menu Login setelah login
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        setMenuEnabled(loggedIn);
        menuItemManageUser.setEnabled(loggedIn);
        menuItemLogout.setEnabled(loggedIn);
    }

    private void logout() {
        setLoggedIn(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuUtama().setVisible(true));
    }
}
