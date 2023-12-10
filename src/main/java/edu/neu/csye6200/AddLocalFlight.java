package edu.neu.csye6200;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddLocalFlight extends JFrame {

	private JPanel contentPane;
	private JTextField flightID;
	private JTextField flightTime;
	private JTextField landTime;
	private JTextField departCity;
	private JTextField landCity;
	private JTextField economySeats;
	private JTextField businessSeats;
	private JTextField distance;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddLocalFlight frame = new AddLocalFlight();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AddLocalFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Local Flight");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Flight ID");
		lblNewLabel_1.setBounds(20, 36, 101, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Time");
		lblNewLabel_2.setBounds(20, 65, 101, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setBounds(20, 90, 101, 14);
		contentPane.add(lblNewLabel_3);

		flightID = createTextField(200, 33, 86, 20);
		flightTime = createTextField(200, 62, 86, 20);
		landTime = createTextField(200, 90, 86, 20);
		departCity = createTextField(200, 121, 86, 20);
		landCity = createTextField(200, 152, 86, 20);
		economySeats = createTextField(200, 183, 86, 20);
		businessSeats = createTextField(200, 214, 86, 20);
		distance = createTextField(200, 239, 86, 20);

		JTextField[] textFields = {flightID, flightTime, landTime, departCity, landCity, economySeats, businessSeats, distance};
		for (JTextField textField : textFields) {
			contentPane.add(textField);
		}

		JLabel[] labels = {
				new JLabel("City of Departure"),
				new JLabel("City of Arrival"),
				new JLabel("No. of Economy Seats"),
				new JLabel("No. of Business Seats"),
				new JLabel("Time Duration (in hours)")
		};

		for (int i = 0; i < labels.length; i++) {
			labels[i].setBounds(20, 124 + i * 29, 200, 14);
			contentPane.add(labels[i]);
		}

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(e -> {
			if (validateInput()) {
				addLocalFlight();
				JOptionPane.showMessageDialog(null, "Added Successfully");
			}
		});
		btnNewButton.setBounds(330, 179, 89, 23);
		contentPane.add(btnNewButton);

	}

	private boolean validateInput() {
		return validateNumericTextField(flightID, "Flight ID", 0, Integer.MAX_VALUE)
				&& validateTextField(flightTime, "Time")
				&& validateTextField(landTime, "Date")
				&& validateCityTextField(departCity, "City of Departure")
				&& validateCityTextField(landCity, "City of Arrival")
				&& validateNumericTextField(economySeats, "Economy Seats", 0, 50)
				&& validateNumericTextField(businessSeats, "Business Seats", 0, 10)
				&& validateNumericTextField(distance, "Time Duration", 0, Integer.MAX_VALUE);
	}

	private void addLocalFlight() {
		Flight obj = new Flight(
				flightID.getText(),
				flightTime.getText(),
				landTime.getText(),
				departCity.getText(),
				landCity.getText(),
				economySeats.getText(),
				businessSeats.getText(),
				distance.getText()
		);
	}

	private JTextField createTextField(int x, int y, int width, int height) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		return textField;
	}

	private boolean validateTextField(JTextField textField, String fieldName) {
		if (textField.getText().isEmpty()) {
			showError(fieldName + " cannot be empty.");
			return false;
		}
		return true;
	}

	private boolean containsOnlyLetters(String text) {
		return text.matches("^[a-zA-Z\\s]+$");
	}

	private boolean validateCityTextField(JTextField textField, String fieldName) {
		if (textField.getText().isEmpty()) {
			showError(fieldName + " cannot be empty.");
			return false;
		} else if (!containsOnlyLetters(textField.getText())) {
			showError(fieldName + " should contain only letters.");
			return false;
		}
		return true;
	}

	private boolean validateNumericTextField(JTextField textField, String fieldName, int minValue, int maxValue) {
		if (!textField.getText().matches("^\\d+$")) {
			showError("Please enter a valid integer for " + fieldName + ".");
			return false;
		}
		int value = Integer.parseInt(textField.getText());
		if (value < minValue || value > maxValue) {
			showError(fieldName + " should be between " + minValue + " and " + maxValue + ".");
			return false;
		}
		return true;
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}