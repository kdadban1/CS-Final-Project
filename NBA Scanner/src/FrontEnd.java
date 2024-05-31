import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class FrontEnd extends JPanel {
	private JTextField textField; // Text field for user input
	private boolean showText;
	private String playerName;
	private BufferedImage teamImage; // To hold the team logo
	private BufferedImage buttonImage; // To hold the team logo
	private HashMap<String, Rectangle> buttons; // Store buttons with their bounds
	public FrontEnd() {
		setLayout(null);
		setBackground(new Color(21, 24, 44)); // Set the background color
		textField = new JTextField();
		textField.setBounds(340, 300, 320, 40); // Set position and size of the text field
		textField.setHorizontalAlignment(JTextField.CENTER); // Center text horizontally
		// Set text color
		Color textColor = new Color(255, 255, 255);
		textField.setForeground(textColor);
		// set caret color
		textField.setCaretColor(Color.WHITE);
		// Set the background color of text box
		textField.setBackground(new Color(44, 49, 91));
		// if enter is pressed, new page
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerName = textField.getText().trim(); // Get user input from text field
				removeAll();
				showText = true;
				repaint();
			}
		});
		add(textField);
		// Initialize buttons and their bounds
		buttons = new HashMap<>();
		buttons.put("m1n", new Rectangle(60, 305, 155, 58));
		buttons.put("pts", new Rectangle(60, 380, 155, 58));
		buttons.put("fgm", new Rectangle(60, 455, 155, 58));
		buttons.put("fga", new Rectangle(60, 530, 155, 58));
		buttons.put("fg_", new Rectangle(60, 605, 155, 58));
		buttons.put("3pm", new Rectangle(60, 680, 155, 58));
		buttons.put("3pa", new Rectangle(60, 755, 155, 58));
		buttons.put("3p_", new Rectangle(240, 680, 155, 58));
		buttons.put("ftm", new Rectangle(240, 755, 155, 58));
		buttons.put("fta", new Rectangle(420, 680, 155, 58));
		buttons.put("ft_", new Rectangle(420, 755, 155, 58));
		buttons.put("gp", new Rectangle(600, 680, 155, 58));
		buttons.put("reb", new Rectangle(600, 755, 155, 58));
		buttons.put("ast", new Rectangle(780, 305, 155, 58));
		buttons.put("stl", new Rectangle(780, 380, 155, 58));
		buttons.put("blk", new Rectangle(780, 455, 155, 58));
		buttons.put("tov", new Rectangle(780, 530, 155, 58));
		buttons.put("eff", new Rectangle(780, 605, 155, 58));
		buttons.put("oreb", new Rectangle(780, 680, 155, 58));
		buttons.put("dreb", new Rectangle(780, 755, 155, 58));
		// Add mouse listener to handle button clicks
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Check if the mouse click falls within any button bounds
				for (String button : buttons.keySet()) {
					Rectangle bounds = buttons.get(button);
					if (bounds.contains(e.getPoint())) {
						// display the stat corresponding to the clicked button
						displayStat(button);
						break;
					}
				}
			}
		});
	}
	// Method to display stat corresponding to the clicked button
	private void displayStat(String button) {
		//USE A SCANNER TO FIND THE STAT
		System.out.println("Button clicked: " + button);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Call the superclass method to handle the default painting logic, including
									// clearing the background.
		Graphics2D g2 = (Graphics2D) g;
		Color c = new Color(44, 49, 91);
		g2.setColor(c);
		if (showText == false) {
			// Text
			Font font = new Font("SansSerif", Font.BOLD, 60);
			g2.setFont(font);
			g2.drawString("Enter NBA Player Here", 180, 190);
		}
		// if the user has entered a name, show it
		if (playerName != null) {
			// Text
			Font font = new Font("SansSerif", Font.BOLD, 60);
			g2.setFont(font);
			int textWidth = g2.getFontMetrics().stringWidth(playerName); // Calculate the width of the text
			int x = (getWidth() - textWidth) / 2; // Calculate the X coordinate to center the text horizontally
			g2.drawString(playerName, x, 100); // Draw the text centered horizontally
			// if that player exists, load their team and stats
			if (checkPlayer(playerName)) {
				String team = getTeam(playerName);
				loadTeamImage(team);
				g2.drawImage(teamImage, (getWidth() - teamImage.getWidth()) / 2, 200, this);
				loadButton("m1n");
				g2.drawImage(buttonImage, 60, 305, this);
				loadButton("pts");
				g2.drawImage(buttonImage, 60, 380, this);
				loadButton("fgm");
				g2.drawImage(buttonImage, 60, 455, this);
				loadButton("fga");
				g2.drawImage(buttonImage, 60, 530, this);
				loadButton("fg");
				g2.drawImage(buttonImage, 60, 605, this);
				loadButton("3pm");
				g2.drawImage(buttonImage, 60, 680, this);
				loadButton("3pa");
				g2.drawImage(buttonImage, 60, 755, this);
				loadButton("3p_");
				g2.drawImage(buttonImage, 240, 680, this);
				loadButton("ftm");
				g2.drawImage(buttonImage, 240, 755, this);
				loadButton("fta");
				g2.drawImage(buttonImage, 420, 680, this);
				loadButton("ft_");
				g2.drawImage(buttonImage, 420, 755, this);
				loadButton("gp");
				g2.drawImage(buttonImage, 600, 680, this);
				loadButton("reb");
				g2.drawImage(buttonImage, 600, 755, this);
				loadButton("ast");
				g2.drawImage(buttonImage, 780, 305, this);
				loadButton("stl");
				g2.drawImage(buttonImage, 780, 380, this);
				loadButton("blk");
				g2.drawImage(buttonImage, 780, 455, this);
				loadButton("tov");
				g2.drawImage(buttonImage, 780, 530, this);
				loadButton("eff");
				g2.drawImage(buttonImage, 780, 605, this);
				loadButton("oreb");
				g2.drawImage(buttonImage, 780, 680, this);
				loadButton("dreb");
				g2.drawImage(buttonImage, 780, 755, this);
			}
			// else, the player does not exist
			else {
				String noPlayer = "Player does not exist.";
				textWidth = g2.getFontMetrics().stringWidth(noPlayer); // Calculate the width of the text
				x = (getWidth() - textWidth) / 2;
				g2.drawString(noPlayer, x, 400);
			}
		}
	}
	private void loadTeamImage(String team) {
		try {
			teamImage = ImageIO.read(new File("src/images/" + team + ".png")); // Adjust the path and format as needed
		} catch (IOException e) {
			System.err.println("Image Unavailable");
			teamImage = null;
		}
	}
	private void loadButton(String button) {
		try {
			buttonImage = ImageIO.read(new File("src/images/" + button + ".png")); // Adjust the path and format as
																					// needed
		} catch (IOException e) {
			System.err.println("Image Unavailable");
			buttonImage = null;
		}
	}
	public boolean checkPlayer(String playerName) {
		try {
			Scanner scanner = new Scanner(new File("NBA STAT REAL - Sheet1.csv"));
			while (scanner.hasNextLine()) {
				String[] row = scanner.nextLine().split(","); // Split by commas
				// lowercase and trim name
				String player = row[1].trim().toLowerCase();
				if (player.equals(playerName.toLowerCase())) { // find the player
					scanner.close(); // Close the scanner before returning
					return true;
				}
			}
			scanner.close(); // Close the scanner if the player is not found
			return false;
		} catch (FileNotFoundException e) {
			System.err.println("Error");
			return false;
		}
	}
	public String getTeam(String playerName) {
		try {
			Scanner scanner = new Scanner(new File("NBA STAT REAL - Sheet1.csv"));
			String team = null;
			while (scanner.hasNextLine()) {
				String[] row = scanner.nextLine().split(","); // Split by commas
				// lowercase and trim name
				String player = row[1].trim().toLowerCase();
				if (player.equals(playerName.toLowerCase())) { // find the player
					team = row[2]; // get team
					return team;
				}
			}
			scanner.close(); // Close the scanner if the player is not found
			return team;
		} catch (FileNotFoundException e) {
			System.err.println("Error");
			return null;
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("NBA Stat Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000); // Adjust size as necessary
		FrontEnd panel = new FrontEnd();
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
