import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInterface extends Frame implements ActionListener {

    private Choice eventTypeChoice;

    private TextField venueField;
    private TextField rowField;
    private TextField seatField;
    private TextField eventNameField;

    private Checkbox standingCheckBox;
    private Checkbox backstageCheckBox;

    private TextArea ticketsDisplay;

    private Button purchaseButton;
    private Button viewTicketsButton;

    private ArrayList<Ticket> tickets;

    public UserInterface() {

        tickets = new ArrayList<>();

        setTitle("Ticket Booking System");
        setSize(700, 500);
        setLayout(null);

        Label titleLabel = new Label("Ticket Booking System", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(200, 40, 250, 30);
        add(titleLabel);

        // Event Type
        Label typeLabel = new Label("Event Type:");
        typeLabel.setBounds(50, 100, 100, 25);
        add(typeLabel);

        eventTypeChoice = new Choice();
        eventTypeChoice.add("Theater");
        eventTypeChoice.add("Concert");
        eventTypeChoice.add("Sports");
        eventTypeChoice.setBounds(200, 100, 150, 25);
        add(eventTypeChoice);

        // Venue
        Label venueLabel = new Label("Venue:");
        venueLabel.setBounds(50, 140, 100, 25);
        add(venueLabel);

        venueField = new TextField();
        venueField.setBounds(200, 140, 150, 25);
        add(venueField);

        // Row
        Label rowLabel = new Label("Row:");
        rowLabel.setBounds(50, 180, 100, 25);
        add(rowLabel);

        rowField = new TextField();
        rowField.setBounds(200, 180, 150, 25);
        add(rowField);

        // Seat
        Label seatLabel = new Label("Seat Number:");
        seatLabel.setBounds(50, 220, 100, 25);
        add(seatLabel);

        seatField = new TextField();
        seatField.setBounds(200, 220, 150, 25);
        add(seatField);

        // Event Name
        Label eventNameLabel = new Label("Event Name:");
        eventNameLabel.setBounds(50, 260, 100, 25);
        add(eventNameLabel);

        eventNameField = new TextField();
        eventNameField.setBounds(200, 260, 150, 25);
        add(eventNameField);

        // Checkboxes
        standingCheckBox = new Checkbox("Standing Ticket");
        standingCheckBox.setBounds(50, 300, 150, 25);
        add(standingCheckBox);

        backstageCheckBox = new Checkbox("Backstage Pass");
        backstageCheckBox.setBounds(220, 300, 150, 25);
        add(backstageCheckBox);

        // Buttons
        purchaseButton = new Button("Purchase Ticket");
        purchaseButton.setBounds(50, 350, 140, 35);
        add(purchaseButton);

        viewTicketsButton = new Button("View Tickets");
        viewTicketsButton.setBounds(220, 350, 140, 35);
        add(viewTicketsButton);

        purchaseButton.addActionListener(this);
        viewTicketsButton.addActionListener(this);

        // Ticket display area
        ticketsDisplay = new TextArea();
        ticketsDisplay.setBounds(400, 100, 250, 250);
        ticketsDisplay.setEditable(false);
        add(ticketsDisplay);

        // Window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == purchaseButton) {

            String eventType = eventTypeChoice.getSelectedItem();
            String venue = venueField.getText();

            char row = rowField.getText().isEmpty()
                    ? ' '
                    : rowField.getText().charAt(0);

            int seat = seatField.getText().isEmpty()
                    ? 0
                    : Integer.parseInt(seatField.getText());

            String eventName = eventNameField.getText();

            boolean standing = standingCheckBox.getState();
            boolean backstage = backstageCheckBox.getState();

            if (venue.isEmpty() || eventName.isEmpty()) {

                Dialog errorDialog = new Dialog(this, "Error", true);
                errorDialog.setLayout(new FlowLayout());
                errorDialog.setSize(250, 100);

                errorDialog.add(new Label("Please fill all required fields"));

                Button ok = new Button("OK");
                ok.addActionListener(ev -> errorDialog.dispose());

                errorDialog.add(ok);

                errorDialog.setVisible(true);

                return;
            }

            Ticket ticket;

            if (eventType.equals("Theater")) {

                ticket = new Theater(
                        venue,
                        row,
                        seat,
                        "" + row + seat,
                        standing,
                        backstage,
                        eventName);

            } else if (eventType.equals("Concert")) {

                ticket = new Concert(
                        venue,
                        row,
                        seat,
                        "" + row + seat,
                        standing,
                        backstage,
                        eventName);

            } else {

                ticket = new Sports(
                        venue,
                        row,
                        seat,
                        "" + row + seat,
                        standing,
                        eventName);
            }

            ticket.adjustPrice();
            tickets.add(ticket);

            Dialog successDialog = new Dialog(this, "Success", true);
            successDialog.setLayout(new FlowLayout());
            successDialog.setSize(250, 120);

            successDialog.add(
                    new Label("Ticket Purchased! Price: $" + ticket.getPrice()));

            Button ok = new Button("OK");
            ok.addActionListener(ev -> successDialog.dispose());

            successDialog.add(ok);

            successDialog.setVisible(true);
        }

        else if (e.getSource() == viewTicketsButton) {

            ticketsDisplay.setText("");

            for (int i = 0; i < tickets.size(); i++) {

                ticketsDisplay.append(
                        "Ticket #" + (i + 1) + "\n"
                                + tickets.get(i).getTicketDetails()
                                + "\n\n");
            }
        }
    }

    public static void main(String[] args) {
        new LoginFrameAWT();
    }
}