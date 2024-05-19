package com.perisic.smile.peripherals;
/*
 * Code adapted from from https://best-programming-tricks.blogspot.com/2011/07/how-to-make-login-form-with-java-gui.html
 */

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921462126880570161L;

	public static void main(String[] args) {
		LoginGUI frameTabel = new LoginGUI();
	}

	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	
	
	JLabel userLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	
	LoginData ldata = new LoginData(); 
	

	LoginGUI() {
		super("Smile Game Login");
		//setSize(300, 200);
		setSize(500, 400);
		setLocation(500, 280);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);

		userLabel.setBounds(100,60,70,20);
		passwordLabel.setBounds(100,95,70,20);
		
		txuser.setBounds(200, 65, 150, 20);
		pass.setBounds(200, 95, 150, 20);
		blogin.setBounds(220, 130, 80, 20);

		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(userLabel);
		panel.add(passwordLabel);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = String.valueOf(pass.getPassword()); // https://stackoverflow.com/questions/10443308/why-gettext-in-jpasswordfield-was-deprecated
				if( ldata.checkPassword(puname, ppaswd)) { 
					GameGUI theGame = new GameGUI(puname); 
					theGame.setVisible(true); 
					dispose();
				} else {

					JOptionPane.showMessageDialog(null, "Wrong Password / Username");
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}

			}
		});
	}
}
