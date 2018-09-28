import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
	
	JLabel username;
	JTextField inputUsername;
	JLabel password;
	JPasswordField inputPassword;
	JButton login;
	char pass[] = {'p','a','s','s','w','o','r','d'};
	JLabel incorrect;
	String user = "Username";

	
	public static void main (String[] args) {
		Login b = new Login();
		b.setVisible(true);
		b.setTitle("Login");
		b.setPreferredSize(new Dimension(240,200));
		
		
	}
	
	public Login() {
		
		this.setLayout(new FlowLayout());
		this.setSize(240, 200);
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		username = new JLabel("Username: ");
		this.add(username);
		
		inputUsername = new JTextField(10);
		this.add(inputUsername);
		
		password = new JLabel("Password: ");
		this.add(password); //let us put a password in the README
		
		inputPassword = new JPasswordField(10);
		this.add(inputPassword);
//		pass = String.valueOf(inputPassword.getPassword());
		
		incorrect = new JLabel();
		this.add(incorrect);
		
		login = new JButton("Login");
		login.addActionListener(this);
		this.add(login);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(user);
		System.out.println(inputUsername.getText());
		if(Arrays.equals(inputPassword.getPassword(), pass) && inputUsername.getText().equalsIgnoreCase(user)) {
		App app = new App();
		app.setVisible(true);
		setVisible(false);
		} else if(!(Arrays.equals(inputPassword.getPassword(), pass))) {
			incorrect.setText("Incorrect Password. Login failed.");
		} else if (!(inputUsername.getText().equalsIgnoreCase(user))) {            
			incorrect.setText("Incorrect Username. Login failed.");
		} else if (!(Arrays.equals(inputPassword.getPassword(), pass) && !(inputUsername.getText().equalsIgnoreCase(user)))) {
			incorrect.setText("Incorrect Username and Password.");
		}
		
		}
	//Arrays.equals(inputPassword.getPassword(), pass) && 

}
