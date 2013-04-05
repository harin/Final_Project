package worldview;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginNew extends JPanel {
	private JTextField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginNew() {
		setLayout(null);
		
		passwordField = new JTextField();
		passwordField.setBounds(206, 123, 134, 28);
		add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(122, 88, 72, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(122, 129, 61, 16);
		add(lblPassword);
		
		JComboBox usernameBox = new JComboBox();
		usernameBox.setBounds(206, 84, 134, 27);
		add(usernameBox);
		
		JButton LoginBut = new JButton("Login");
		LoginBut.setBounds(216, 166, 117, 29);
		add(LoginBut);
		
		JButton LoginAlienBut = new JButton("Alien Login");
		LoginAlienBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LoginAlienBut.setBounds(98, 166, 117, 29);
		add(LoginAlienBut);

	}
}
