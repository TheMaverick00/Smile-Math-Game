package com.perisic.smile.peripherals;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import com.perisic.smile.engine.GameEngine;

/**
 * A Simple Graphical User Interface for the Six Equation Game.
 * 
 * @author Joshua Onoja
 *
 */
public class GameGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -107785653906635L;
	

	/**
	 * Method that is called when a button has been pressed.
	 */
	@Override
	
	public void actionPerformed(ActionEvent e) {	
		int solution = Integer.parseInt(e.getActionCommand());
		boolean correct = myGame.checkSolution(solution);
		int score = myGame.getScore(); 
		if (correct) {
			System.out.println("Correct Answer!");
			currentGame = myGame.nextGame(); 			
			ImageIcon ii = new ImageIcon(currentGame);
			questArea.setIcon(ii);
			infoArea.setText("Good!  Score: "+score);
			ans.setText("");
		} else { 
			System.out.println("Incorrect Answer"); 
			infoArea.setText("Oops. Try again!  Score: "+score);
			ans.setText("");
		}
	}

	JLabel questArea = null;
	GameEngine myGame = null;
	BufferedImage currentGame = null;
	JTextArea infoArea = null;
	
	JTextField ans = new JTextField(10);
	Timer timer = new Timer();
	
	
		
/**
 * Initializes the game. 
 * @param player
 */
	private void initGame(String player) {
		setSize(690, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Smile Game");
		JPanel panel = new JPanel();
		
		myGame = new GameEngine(player);
		currentGame = myGame.nextGame();

		infoArea = new JTextArea(1, 15);

	
		
		TimerTask task = new TimerTask() {
			
			int counter = 60;
			@Override
			public void run() {
				if(counter>0) {
					infoArea.setText(Integer.toString(counter));
					counter--;
				}
				else {
					System.out.println("Time Up");
					timer.cancel();
					System.exit(1);
				}
			}		
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
		
		infoArea.setEditable(false);
		infoArea.setText("Score: 0");
		
		JScrollPane infoPane = new JScrollPane(infoArea);
		panel.add(infoPane);

		ImageIcon ii = new ImageIcon(currentGame);
		questArea = new JLabel(ii);
	    questArea.setSize(330, 600);
	    
		JScrollPane questPane = new JScrollPane(questArea);
		panel.add(questPane);

		/*for (int i = 0; i < 10; i++) {
			JButton btn = new JButton(String.valueOf(i));
			panel.add(btn);
			btn.addActionListener(this);
		}*/
		JLabel quest = new JLabel("What is the Missing Number? ");
		quest.setBounds(100,90,50,40);
		
		ans.setBounds(200, 95, 100, 40);
		panel.add(quest);
		panel.add(ans);
		ans.addActionListener(this);

		panel.setBackground(Color.yellow);
		

		getContentPane().add(panel);
		panel.repaint();

	}
/**
 * Default player is null. 
 */
	public GameGUI() {
		super();
		initGame(null);
	}

	/**
	 * Use this to start GUI, e.g., after login.
	 * 
	 * @param player
	 */
	public GameGUI(String player) {
		super();
		initGame(player);
	}

	/**
	 * Main entry point into the equation game. Can be used without login for testing. 
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		GameGUI myGUI = new GameGUI();
		myGUI.setVisible(true);

	}
}