 /*Renata Gabdrakhmanova 
	 * Pd 2 
	 * 4/21/21
	 * 
	 * Sliding Totoro-
	 * Creates a 4X4 panel that has numbers placed randomly including totoro
	 * using arrow keys you are able to move totoro up,down, left or right
	 * when you press the arrow key the number in that spot you are moving to swaps with totoro
	 * the goal is to make all numbers be in order and totoro's location has to be at the bottom right corner
	 */
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GabdrakhmanovaRenata_TotoroSlider extends JFrame implements KeyListener {

	private BufferedImage image;
	private PicPanel[][] allPanels;
	private int totRow;// location of Totoro
	private int totCol;
	ArrayList<Integer> list = new ArrayList<Integer>();

	public GabdrakhmanovaRenata_TotoroSlider() {

		setSize(375, 375);
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tile Slider");

		try {
			image = ImageIO.read(new File("totoro.jpg"));

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Could not read in the pic");
			System.exit(0);
		}

		this.addKeyListener(this);

		allPanels = new PicPanel[4][4];

		setLayout(new GridLayout(4, 4, 2, 2));

		// creates an array with random number placed at each spot
		// contains 0, thats why the array should be size 16
		while (list.size() != 16) {
			int rand = (int) (Math.random() * (16));
			if (list.indexOf(rand) == -1) {
				list.add(rand);
			}
		}

		// places numbers in planes
		int num = 0;
		for (int row = 0; row < allPanels.length; row++) {
			for (int col = 0; col < allPanels[row].length; col++) {

				allPanels[row][col] = new PicPanel();
				allPanels[row][col].setNumber(list.get(num));// adds numbers to the planes from the array
				add(allPanels[row][col]);

				// if the number form array is 0 then it should be the spot for totoro
				if (list.get(num) == 0) {
					totRow = row;
					totCol = col;
					allPanels[row][col].removeNumber();
				}
				num++;
			}
		}

		setBackground(Color.black);

		setVisible(true);
	}

	class PicPanel extends JPanel {
		private int width = 76;
		private int height = 80;// dimensions of the Panel

		private int number = -1;// -1 when Totoro is at that position.
		private JLabel text;

		public PicPanel() {
			setBackground(Color.white);
			setLayout(null);
		}

		// changes the panel to have the given number
		public void setNumber(int num) {
			number = num;
			text = new JLabel("" + number, SwingConstants.CENTER);
			text.setFont(new Font("Calibri", Font.PLAIN, 55));
			text.setBounds(0, 35, 70, 50);
			this.add(text);
			repaint();
		}

//replaces the number with Totoro
		public void removeNumber() {
			this.remove(text);
			number = -1;
			repaint();
		}

		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}

		// this will draw the image or the number// called by repaint and when the panel
		// is initially drawn
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (number == -1)
				g.drawImage(image, 8, 0, this);
		}
	}

	public void keyPressed(KeyEvent arg0) {
		int arrowKey = arg0.getKeyCode();
		int loc = list.indexOf(0);// the location of totoro in the array

		if (arrowKey == KeyEvent.VK_UP) {
			int newLoc = list.set(loc - 4, 0);// sets 0 at the location above totoro, also returns the number that was
												// in that spot

			// swap locations
			allPanels[totRow - 1][totCol].removeNumber();// removes the number above totoro and sets totoro there
			allPanels[totRow][totCol].setNumber(newLoc);// sets the number in the previous spot of totoro

			setVisible(true);
			totRow = totRow - 1;

			list.set(loc, newLoc);// sets the number in the array to be in the previous spot of totoro

		}

		else if (arrowKey == KeyEvent.VK_DOWN) {
			int newLoc = list.set(loc + 4, 0);// sets 0 at the location below totoro, also returns the number that was
												// in that spot

			// swap locations
			allPanels[totRow + 1][totCol].removeNumber();// removes the number below totoro and sets totoro there
			allPanels[totRow][totCol].setNumber(newLoc);// sets the number in the previous spot of totoro

			setVisible(true);
			totRow = totRow + 1;

			list.set(loc, newLoc);// sets the number in the array to be in the previous spot of totoro
		}

		else if (arrowKey == KeyEvent.VK_LEFT) {

			int newLoc = list.set(loc - 1, 0);// sets 0 at the location left of the totoro, also returns the number that
												// was in that spot

			// swap locations
			allPanels[totRow][totCol - 1].removeNumber();// removes the number on the left of totoro and sets totoro
															// there
			allPanels[totRow][totCol].setNumber(newLoc);// sets the number in the previous spot of totoro

			setVisible(true);
			totCol = totCol - 1;

			list.set(loc, newLoc);// sets the number in the array to be in the previous spot of totoro
		}

		else if (arrowKey == KeyEvent.VK_RIGHT) {
			int newLoc = list.set(loc + 1, 0);// sets 0 at the location to the right of totoro, also returns the number
												// that was in that spot

			// swap locations
			allPanels[totRow][totCol + 1].removeNumber();// removes the number to the right of totoro and sets totoro
															// there
			allPanels[totRow][totCol].setNumber(newLoc);// sets the number in the previous spot of totoro

			setVisible(true);
			totCol = totCol + 1;

			list.set(loc, newLoc);// sets the number in the array to be in the previous spot of totoro
		}
		
		
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public static void main(String[] args) {
		GabdrakhmanovaRenata_TotoroSlider slider = new GabdrakhmanovaRenata_TotoroSlider();
	}
}
