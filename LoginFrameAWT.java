import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginFrameAWT extends Frame implements ActionListener {

    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;

    // In-memory users
    private HashMap<String, String> users;

    public LoginFrameAWT() {

        users = new HashMap<>();
        users.put("admin", "1234");
        users.put("user", "pass");
        // we are here using hashmap as if i dont i have to give this in if(name==admin
        // and pass ==12334) else loop
        // and that will not be efficent here i do direct put method use
        setTitle("Login - Ticket System");
        setSize(350, 250);
        setLayout(null);

        Label title = new Label("LOGIN", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(100, 40, 120, 30);
        add(title);

        Label userLabel = new Label("Username:");
        userLabel.setBounds(40, 90, 80, 25);
        add(userLabel);

        usernameField = new TextField();
        usernameField.setBounds(130, 90, 150, 25);
        add(usernameField);

        Label passLabel = new Label("Password:");
        passLabel.setBounds(40, 130, 80, 25);
        add(passLabel);

        passwordField = new TextField();
        passwordField.setEchoChar('*'); // this is used hide password
        passwordField.setBounds(130, 130, 150, 25);
        add(passwordField);

        loginButton = new Button("Login");
        loginButton.setBounds(120, 180, 100, 30);
        add(loginButton);

        loginButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (users.containsKey(user) && users.get(user).equals(pass)) {// not get 1

            Dialog success = new Dialog(this, "Message", true);
            success.setLayout(new FlowLayout());
            success.setSize(250, 100);

            Label msg = new Label("Login Successful!");
            Button ok = new Button("OK");

            ok.addActionListener(ev -> success.dispose());

            success.add(msg);
            success.add(ok);

            success.setVisible(true);

            dispose();

            // Open main application
            new UserInterface();

        } else {

            Dialog fail = new Dialog(this, "Error", true);// Dialog in AWT is a small pop-up window used to show
            // messages, warnings, confirmation, etc.
            fail.setLayout(new FlowLayout());
            fail.setSize(250, 100);

            Label msg = new Label("Invalid Credentials!");
            Button ok = new Button("OK");

            ok.addActionListener(ev -> fail.dispose());

            fail.add(msg);
            fail.add(ok);

            fail.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new LoginFrameAWT();
    }
}