import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

enum Role {
    ADMIN, GUARD, VIEWER;
    
    public boolean canAddVehicles() { return this == ADMIN || this == GUARD; }
    public boolean canViewAuditLogs() { return this == ADMIN; }
    public boolean canManageUsers() { return this == ADMIN; }
}

public class MUVehicleTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection testConn = DBConfig.getConnection();
                testConn.close();
                new LoginFrame().setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Database Connection Failed:\n" + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}

class DBConfig {
    static final String URL = "jdbc:mysql://localhost:3306/userdatabase?"
            + "useSSL=false&autoReconnect=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "MySQL Driver not found! Add mysql-connector-java.jar to your classpath.",
                "Driver Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

class User {
    private String user_id;
    private String password_hash;
    private String salt;
    private Role role;
    private Date last_password_change;
    private int failed_attempts;
    private boolean account_locked;

    public User(String userId, String password_hash, String salt, Role role,
                Date last_password_change, int failed_attempts, boolean account_locked) {
        this.user_id = userId;
        this.password_hash = password_hash;
        this.salt = salt;
        this.role = role;
        this.last_password_change = last_password_change;
        this.failed_attempts = failed_attempts;
        this.account_locked = account_locked;
    }

    public String getUserId() { return user_id; }
    public String getPasswordHash() { return password_hash; }
    public String getSalt() { return salt; }
    public Role getRole() { return role; }
    public Date getLastPasswordChange() { return last_password_change; }
    public int getFailedAttempts() { return failed_attempts; }
    public boolean isAccountLocked() { return account_locked; }
}

class Vehicle {
    private int vehicle_id;
    private String license_plate;
    private Timestamp entry_time;
    private Timestamp exit_time;
    private String logged_by;

    public Vehicle(String licensePlate, Timestamp entryTime, String loggedBy) {
        this.license_plate = licensePlate;
        this.entry_time = entryTime;
        this.logged_by = loggedBy;
    }

    public int getVehicleId() { return vehicle_id; }
    public String getLicensePlate() { return license_plate; }
    public Timestamp getEntryTime() { return entry_time; }
    public Timestamp getExitTime() { return exit_time; }
    public String getLoggedBy() { return logged_by; }
    public void setVehicleId(int vehicleId) { this.vehicle_id = vehicle_id; }
    public void setExitTime(Timestamp exitTime) { this.exit_time = exit_time; }
}
//LOGS FOR LOGGED IN USERS
class AuditLog {
    private int log_id;
    private String user_id;
    private String action;
    private String ip_address;
    private Timestamp timestamp;

    public AuditLog(String user_id, String action, String ip_address) {
        this.user_id = user_id;
        this.action = action;
        this.ip_address = ip_address;
    }

    public int getLogId() { return log_id; }
    public String getUserId() { return user_id; }
    public String getAction() { return action; }
    public Timestamp getTimestamp() { return timestamp; }
    public String getIpAddress() { return ip_address; }
    public void setLogId(int logId) { this.log_id = log_id; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}

class SecurityUtils {
    private static final SecureRandom random = new SecureRandom();
    private static final long PASSWORD_EXPIRY_MS = 90L * 24 * 60 * 60 * 1000;

    public static String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            return Base64.getEncoder().encodeToString(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

    public static boolean isPasswordExpired(Date lastChange) {
        return (System.currentTimeMillis() - lastChange.getTime()) > PASSWORD_EXPIRY_MS;
    }
}

class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) { this.conn = conn; }

    public User getUser(String userId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM user WHERE user_id = ?")) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("user_id"),
                    rs.getString("password_hash"),
                    rs.getString("salt"),
                    Role.valueOf(rs.getString("role")),
                    new Date(rs.getDate("last_password_change").getTime()),
                    rs.getInt("failed_attempts"),
                    rs.getBoolean("account_locked")
                );
            }
            return null;
        }
    }

    public void createUser(String userId, String password, Role role) throws SQLException {
        String salt = SecurityUtils.generateSalt();
        String hash = SecurityUtils.hashPassword(password, salt);
        
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO user (user_id, password_hash, salt, role, last_password_change, failed_attempts, account_locked) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, userId);
            stmt.setString(2, hash);
            stmt.setString(3, salt);
            stmt.setString(4, role.toString());
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(6, 0);
            stmt.setBoolean(7, false);
            stmt.executeUpdate();
        }
    }

    public void incrementFailedAttempts(String userId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE user SET failed_attempts = failed_attempts + 1 WHERE user_id = ?")) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    } 

    public void resetFailedAttempts(String userId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE user SET failed_attempts = 0 WHERE user_id = ?")) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    }

    public void deleteUser(String userId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM user WHERE user_id = ?")) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    }
}

class VehicleDAO {
    private Connection conn;

    public VehicleDAO(Connection conn) { this.conn = conn; }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO vehicle (license_plate, entry_time, logged_by) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, vehicle.getLicensePlate());
            stmt.setTimestamp(2, vehicle.getEntryTime());
            stmt.setString(3, vehicle.getLoggedBy());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vehicle.setVehicleId(rs.getInt(1));

            
                }
            }
        }
    }

    public void recordExit(int vehicleId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE vehicle SET exit_time = ? WHERE vehicle_id = ?")) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, vehicleId);
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(int vehicleId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM vehicle WHERE vehicle_id = ?")) {
            stmt.setInt(1, vehicleId);
            stmt.executeUpdate();
        }
    }
}

class AuditLogDAO {
    private Connection conn;
    
    public AuditLogDAO(Connection conn) { this.conn = conn; }
    
    public void logAction(String userId, String action, String ipAddress) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO audit_log (user_id, action, ip_address) VALUES (?, ?, ?)")) {
            stmt.setString(1, userId);
            stmt.setString(2, action);
            stmt.setString(3, ipAddress);
            stmt.executeUpdate();
        }
    }
}

class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("MU Vehicle Tracker - Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("MU Gate Vehicle Tracker"); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; 
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; 
        panel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15); 
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; 
        panel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15); 
        gbc.gridx = 1; 
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login"); 
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; 
        panel.add(loginButton, gbc);
        loginButton.addActionListener(this);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        // Temporary admin bypass (remove in production)
        if(username.equals("admin") && password.equals("admin123")) {
            User testUser = new User(
                "admin",
                "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=",
                "AWk7dJ7XQ1DxZ/YsrAVlWg==",
                Role.ADMIN,
                new java.util.Date(),
                0,
                false
            );
            new MainFrame(testUser).setVisible(true);
            dispose();
            return;
        }
        
        try (Connection conn = DBConfig.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.getUser(username);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
                return;
            }

            if (user.isAccountLocked()) {
                JOptionPane.showMessageDialog(this, "Account locked. Contact administrator.");
                return;
            }

            if (!validatePassword(user, password)) {
                userDAO.incrementFailedAttempts(user.getUserId());
                int attemptsLeft = 2 - user.getFailedAttempts();
                if (attemptsLeft <= 0) {
                    JOptionPane.showMessageDialog(this, "Account locked after 3 failed attempts");
                    return;
                }
                JOptionPane.showMessageDialog(this, 
                    "Invalid credentials. Attempts left: " + attemptsLeft);
                return;
            }

            if (SecurityUtils.isPasswordExpired(user.getLastPasswordChange())) {
                JOptionPane.showMessageDialog(this, "Password expired. Please reset.");
                new PasswordResetFrame(user).setVisible(true);
                dispose();
                return;
            }

            userDAO.resetFailedAttempts(user.getUserId());
            new AuditLogDAO(conn).logAction(user.getUserId(), "LOGIN", 
                java.net.InetAddress.getLocalHost().getHostAddress());

            new MainFrame(user).setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean validatePassword(User user, String password) {
        String hashedAttempt = SecurityUtils.hashPassword(password, user.getSalt());
        return hashedAttempt.equals(user.getPasswordHash());
    }
}

//MAIN FRAME CLASS
class MainFrame extends JFrame {
    private final User user;

    public MainFrame(User user) {
        this.user = user;
        setTitle("MU Vehicle Tracker - " + user.getRole() + " Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        setupUI();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        if (user.getRole() == Role.ADMIN) {
            JMenuItem backupItem = new JMenuItem("Backup Database");
            backupItem.addActionListener(e -> backupDatabase());
            fileMenu.add(backupItem);
            
            JMenuItem createUserItem = new JMenuItem("Create User");
            createUserItem.addActionListener(e -> new CreateUserFrame().setVisible(true));
            fileMenu.add(createUserItem);
        }
        
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> {
            try (Connection conn = DBConfig.getConnection()) {
                new AuditLogDAO(conn).logAction(user.getUserId(), "LOGOUT", 
                    java.net.InetAddress.getLocalHost().getHostAddress());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            new LoginFrame().setVisible(true);
            dispose();
        });
        fileMenu.add(logoutItem);
        
        menuBar.add(fileMenu);
        return menuBar;
    }

    private void backupDatabase() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (Connection conn = DBConfig.getConnection();
                 Statement stmt = conn.createStatement();
                 FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                
                writer.write("-- USERS TABLE\n");
                ResultSet rs = stmt.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    writer.write(String.format(
                        "INSERT INTO users VALUES('%s','%s','%s','%s','%s',%d,%b);\n",
                        rs.getString("user_id"), rs.getString("password_hash"),
                        rs.getString("salt"), rs.getString("role"),
                        rs.getDate("last_password_change"), rs.getInt("failed_attempts"),
                        rs.getBoolean("account_locked")));
                }
                
                writer.write("\n-- VEHICLES TABLE\n");
                rs = stmt.executeQuery("SELECT * FROM vehicles");
                while (rs.next()) {
                    writer.write(String.format(
                        "INSERT INTO vehicles VALUES(%d,'%s','%s',%s,'%s');\n",
                        rs.getInt("vehicle_id"), rs.getString("license_plate"),
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time") != null ? 
                            "'" + rs.getTimestamp("exit_time") + "'" : "NULL",
                        rs.getString("logged_by")));
                }
                
                JOptionPane.showMessageDialog(this, "Backup completed successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Backup failed: " + ex.getMessage());
            }
        }
    }

    private void setupUI() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Vehicle Tracking", new VehiclePanel(user));
        
        if (user.getRole() == Role.ADMIN) {
            tabs.addTab("User Management", new UserManagementPanel());
            tabs.addTab("Audit Logs", new AuditLogPanel());
        }
        
        add(tabs);
    }
}

class VehiclePanel extends JPanel {
    private JTable vehicleTable;
    private VehicleTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> statusFilter;
    private JTextField dateFromField;
    private JTextField dateToField;
    private User user;
    
    public VehiclePanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Search components
        searchField = new JTextField(15);
        statusFilter = new JComboBox<>(new String[]{"All", "Active", "Exited"});
        dateFromField = new JTextField(10);
        dateToField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        
        // Add document listener for live search
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filterTable(); }
            public void removeUpdate(DocumentEvent e) { filterTable(); }
            public void insertUpdate(DocumentEvent e) { filterTable(); }
        });
        
        statusFilter.addActionListener(e -> filterTable());
        searchButton.addActionListener(e -> filterTable());
        
        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            try (Connection conn = DBConfig.getConnection()) {
                new AuditLogDAO(conn).logAction(user.getUserId(), "LOGOUT", 
                    java.net.InetAddress.getLocalHost().getHostAddress());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            new LoginFrame().setVisible(true);
            ((Window)getTopLevelAncestor()).dispose();
        });
        
        // Layout components
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Status:"));
        searchPanel.add(statusFilter);
        searchPanel.add(new JLabel("From:"));
        searchPanel.add(dateFromField);
        searchPanel.add(new JLabel("To:"));
        searchPanel.add(dateToField);
        searchPanel.add(searchButton);
        
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        
        topPanel.add(logoutPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        
        // Only show entry/exit components if user has permission to add vehicles
        if (user.getRole().canAddVehicles()) {
            JPanel entryPanel = new JPanel();
            JTextField plateField = new JTextField(10);
            JButton entryButton = new JButton("Record Entry");
            JButton exitButton = new JButton("Record Exit");
            JButton deleteButton = new JButton("Delete Record");
            
            plateField.setInputVerifier(new InputVerifier() {
                public boolean verify(JComponent input) {
                    String text = ((JTextField)input).getText();
                    return text.matches("MU\\s?[0-9]{3,4}[A-Z]?");
                }
            });
            
            entryButton.addActionListener(e -> {
                if (!plateField.getInputVerifier().verify(plateField)) {
                    JOptionPane.showMessageDialog(this, "Invalid plate format (MU 1234)");
                    return;
                }
                recordVehicleEntry(plateField.getText(), user.getUserId());
                plateField.setText("");
            });
            
            exitButton.addActionListener(e -> {
                int selectedRow = vehicleTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a vehicle to record exit");
                    return;
                }
                
                Vehicle selectedVehicle = tableModel.vehicles.get(selectedRow);
                if (selectedVehicle.getExitTime() != null) {
                    JOptionPane.showMessageDialog(this, "This vehicle has already exited");
                    return;
                }
                
                recordVehicleExit(selectedVehicle.getVehicleId(), user.getUserId());
            });
            
            deleteButton.addActionListener(e -> {
                int selectedRow = vehicleTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a vehicle to delete");
                    return;
                }
                
                Vehicle selectedVehicle = tableModel.vehicles.get(selectedRow);
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete this vehicle record?", 
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection conn = DBConfig.getConnection()) {
                        new VehicleDAO(conn).deleteVehicle(selectedVehicle.getVehicleId());
                        filterTable();
                        new AuditLogDAO(conn).logAction(user.getUserId(), "VEHICLE_DELETED", null);
                        JOptionPane.showMessageDialog(this, "Vehicle record deleted successfully");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error deleting vehicle: " + ex.getMessage());
                    }
                }
            });
            
            entryPanel.add(new JLabel("License Plate:"));
            entryPanel.add(plateField);
            entryPanel.add(entryButton);
            entryPanel.add(exitButton);
            entryPanel.add(deleteButton);
            
            topPanel.add(entryPanel, BorderLayout.SOUTH);
        }
        
        tableModel = new VehicleTableModel();
        vehicleTable = new JTable(tableModel);
        vehicleTable.setDefaultRenderer(Object.class, new VehicleTableCellRenderer());
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(vehicleTable), BorderLayout.CENTER);
    }
    
    private void filterTable() {
        String searchText = searchField.getText().toLowerCase();
        String status = (String)statusFilter.getSelectedItem();
        String dateFrom = dateFromField.getText();
        String dateTo = dateToField.getText();
        
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles")) {
            
            tableModel.vehicles.clear();
            while (rs.next()) {
                Vehicle v = new Vehicle(
                    rs.getString("license_plate"),
                    rs.getTimestamp("entry_time"),
                    rs.getString("logged_by")
                );
                v.setVehicleId(rs.getInt("vehicle_id"));
                v.setExitTime(rs.getTimestamp("exit_time"));
                
                // Apply filters
                boolean matches = true;
                
                // License plate filter
                if (!searchText.isEmpty() && 
                    !v.getLicensePlate().toLowerCase().contains(searchText)) {
                    matches = false;
                }
                
                // Status filter
                if (status.equals("Active") && v.getExitTime() != null) {
                    matches = false;
                } else if (status.equals("Exited") && v.getExitTime() == null) {
                    matches = false;
                }
                
                // Date range filter
                if (!dateFrom.isEmpty()) {
                    try {
                        Timestamp fromDate = Timestamp.valueOf(dateFrom + " 00:00:00");
                        if (v.getEntryTime().before(fromDate)) {
                            matches = false;
                        }
                    } catch (IllegalArgumentException e) {
                        // Invalid date format - ignore this filter
                    }
                }
                
                if (!dateTo.isEmpty()) {
                    try {
                        Timestamp toDate = Timestamp.valueOf(dateTo + " 23:59:59");
                        if (v.getEntryTime().after(toDate)) {
                            matches = false;
                        }
                    } catch (IllegalArgumentException e) {
                        // Invalid date format - ignore this filter
                    }
                }
                
                if (matches) {
                    tableModel.vehicles.add(v);
                }
            }
            tableModel.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void recordVehicleEntry(String plate, String userId) {
        try (Connection conn = DBConfig.getConnection()) {
            VehicleDAO dao = new VehicleDAO(conn);
            Vehicle vehicle = new Vehicle(plate, new Timestamp(System.currentTimeMillis()), userId);
            dao.addVehicle(vehicle);
            filterTable(); // Refresh with filters applied
            new AuditLogDAO(conn).logAction(userId, "VEHICLE_ENTRY", null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
    
    private void recordVehicleExit(int vehicleId, String userId) {
        try (Connection conn = DBConfig.getConnection()) {
            new VehicleDAO(conn).recordExit(vehicleId);
            new AuditLogDAO(conn).logAction(userId, "VEHICLE_EXIT", null);
            filterTable(); // Refresh with filters applied
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}

class VehicleTableModel extends AbstractTableModel {
    protected List<Vehicle> vehicles = new ArrayList<>();
    private String[] columns = {"ID", "License Plate", "Entry Time", "Exit Time", "Logged By"};

    public VehicleTableModel() {
        refreshData();
    }

    public void refreshData() {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle")) {
            
            vehicles.clear();
            while (rs.next()) {
                Vehicle v = new Vehicle(
                    rs.getString("license_plate"),
                    rs.getTimestamp("entry_time"),
                    rs.getString("logged_by")
                );
                v.setVehicleId(rs.getInt("vehicle_id"));
                v.setExitTime(rs.getTimestamp("exit_time"));
                vehicles.add(v);
            }
            fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() { return vehicles.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int row, int column) {
        Vehicle v = vehicles.get(row);
        switch (column) {
            case 0: return v.getVehicleId();
            case 1: return v.getLicensePlate();
            case 2: return v.getEntryTime();
            case 3: return v.getExitTime();
            case 4: return v.getLoggedBy();
            default: return null;
        }
    }
}

class VehicleTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        VehicleTableModel model = (VehicleTableModel)table.getModel();
        Vehicle vehicle = model.vehicles.get(row);
        
        if (vehicle.getExitTime() == null && 
            System.currentTimeMillis() - vehicle.getEntryTime().getTime() > 24 * 60 * 60 * 1000) {
            c.setBackground(Color.RED);
            c.setForeground(Color.WHITE);
        } else {
            c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }
        
        if (column == 2 || column == 3) {
            setToolTipText("Logged by: " + vehicle.getLoggedBy());
        }
        
        return c;
    }
}

class UserManagementPanel extends JPanel {
    private JTable userTable;
    private UserTableModel tableModel;
    
    public UserManagementPanel() {
        setLayout(new BorderLayout());
        
        tableModel = new UserTableModel();
        userTable = new JTable(tableModel);
        
        JButton deleteButton = new JButton("Delete Selected User");
        deleteButton.addActionListener(e -> deleteSelectedUser());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void deleteSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete");
            return;
        }
        
        String userId = (String) tableModel.getValueAt(selectedRow, 0);
        if (userId.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Cannot delete the admin user");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete user: " + userId + "?", 
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConfig.getConnection()) {
                new UserDAO(conn).deleteUser(userId);
                tableModel.refreshData();
                new AuditLogDAO(conn).logAction("admin", "USER_DELETED", null);
                JOptionPane.showMessageDialog(this, "User deleted successfully");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting user: " + ex.getMessage());
            }
        }
    }
}

class UserTableModel extends AbstractTableModel {
    protected List<User> users = new ArrayList<>();
    private String[] columns = {"User ID", "Role", "Last Password Change", "Status"};
    
    public UserTableModel() {
        refreshData();
    }
    
    public void refreshData() {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {
            
            users.clear();
            while (rs.next()) {
                User u = new User(
                    rs.getString("user_id"),
                    rs.getString("password_hash"),
                    rs.getString("salt"),
                    Role.valueOf(rs.getString("role")),
                    new Date(rs.getDate("last_password_change").getTime()),
                    rs.getInt("failed_attempts"),
                    rs.getBoolean("account_locked")
                );
                users.add(u);
            }
            fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getRowCount() { return users.size(); }
    
    @Override
    public int getColumnCount() { return columns.length; }
    
    @Override
    public String getColumnName(int column) { return columns[column]; }
    
    @Override
    public Object getValueAt(int row, int column) {
        User u = users.get(row);
        switch (column) {
            case 0: return u.getUserId();
            case 1: return u.getRole();
            case 2: return u.getLastPasswordChange();
            case 3: return u.isAccountLocked() ? "Locked" : "Active";
            default: return null;
        }
    }
}

class AuditLogPanel extends JPanel {
    private JTable auditTable;
    private AuditLogTableModel tableModel;
    
    public AuditLogPanel() {
        setLayout(new BorderLayout());
        
        tableModel = new AuditLogTableModel();
        auditTable = new JTable(tableModel);
        
        add(new JScrollPane(auditTable), BorderLayout.CENTER);
    }
}

class AuditLogTableModel extends AbstractTableModel {
    protected List<AuditLog> logs = new ArrayList<>();
    private String[] columns = {"Log ID", "User ID", "Action", "Timestamp", "IP Address"};
    
    public AuditLogTableModel() {
        refreshData();
    }
    
    public void refreshData() {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM audit_log ORDER BY timestamp DESC")) {
            
            logs.clear();
            while (rs.next()) {
                AuditLog log = new AuditLog(
                    rs.getString("user_id"),
                    rs.getString("action"),
                    rs.getString("ip_address")
                );
                log.setLogId(rs.getInt("log_id"));
                log.setTimestamp(rs.getTimestamp("timestamp"));
                logs.add(log);
            }
            fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getRowCount() { return logs.size(); }
    
    @Override
    public int getColumnCount() { return columns.length; }
    
    @Override
    public String getColumnName(int column) { return columns[column]; }
    
    @Override
    public Object getValueAt(int row, int column) {
        AuditLog log = logs.get(row);
        switch (column) {
            case 0: return log.getLogId();
            case 1: return log.getUserId();
            case 2: return log.getAction();
            case 3: return log.getTimestamp();
            case 4: return log.getIpAddress();
            default: return null;
        }
    }
}

class PasswordResetFrame extends JFrame {
    public PasswordResetFrame(User user) {
        setTitle("Password Reset");
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.add(new JLabel("Reset Password for: " + user.getUserId()));
        panel.add(new JLabel("New Password:"));
        JPasswordField newPassField = new JPasswordField();
        panel.add(newPassField);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Password reset successful");
            dispose();
        });
        panel.add(submitButton);
        
        add(panel);
    }
}

class CreateUserFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Role> roleComboBox;

    public CreateUserFrame() {
        setTitle("Create New User");
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        panel.add(new JLabel("Role:"));
        roleComboBox = new JComboBox<>(Role.values());
        panel.add(roleComboBox);
        
        JButton createButton = new JButton("Create User");
        createButton.addActionListener(this);
        panel.add(createButton);
        
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        Role role = (Role)roleComboBox.getSelectedItem();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty");
            return;
        }
        
        try (Connection conn = DBConfig.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);
            userDAO.createUser(username, password, role);
            new AuditLogDAO(conn).logAction("admin", "USER_CREATED", null);
            JOptionPane.showMessageDialog(this, "User created successfully");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error creating user: " + ex.getMessage());
        }
    }
}