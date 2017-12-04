import java.awt.*;
import java.util.Random;
import javax.swing.*;

// Java make a dragging checker to prevent layering of the circles?

public class Space extends JFrame {
	
	public static int xScreenSize = 600, yScreenSize = 600;
	public static int xCoord, yCoord, speed = 1;
	public static int amountOfStars = 300;
	public static int[][] starArray = new int[amountOfStars][5];
	public static int sizeOfStars = 10;
	
	
	public static void setup() {
		Random rand = new Random();
		for(int x = 0; x < amountOfStars; x++) {
			starArray[x][0] = rand.nextInt(xScreenSize) + 1; // x value
			starArray[x][1] = rand.nextInt(yScreenSize) + 1; // y value
			starArray[x][2] = rand.nextInt(4) + 1; // speed
			starArray[x][3] = (starArray[x][2] * 10) - 20; // Size of star
			starArray[x][4] = rand.nextInt(100) + 1; // Color
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
	starfield frame = new starfield();
	frame.setTitle("Colorful Star Field");
	frame.setVisible(true);
	frame.setBackground(Color.BLACK);
	frame.setSize(xScreenSize, yScreenSize);
	frame.getMaximizedBounds();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setup(); // setup of the stars;
		
		while(true) {
			xScreenSize = frame.getWidth();
			yScreenSize = frame.getHeight();
			frame.repaint();
		
			Thread.sleep(10);
		}
	}
	
	static class starfield extends JFrame{
		Random rand = new Random();
		int color = 0;
		
		public void paint(Graphics g) {
			Graphics2D g1 = (Graphics2D) g;
			RenderingHints rh = new RenderingHints(
		             RenderingHints.KEY_TEXT_ANTIALIASING,
		             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		    g1.setRenderingHints(rh);
			for(int p = 0; p < amountOfStars; p++) {
				g1.setColor(Color.BLACK);
				g1.fillOval(starArray[p][0], starArray[p][1], starArray[p][3], starArray[p][3]);
				if(starArray[p][0] <= 0) {
					g1.fillRect(starArray[p][0], starArray[p][1], starArray[p][3], starArray[p][3]);
					starArray[p][0] = xScreenSize;
					starArray[p][1] = rand.nextInt(yScreenSize + 200) + 1;
					starArray[p][2] = rand.nextInt(4) + 1;
					starArray[p][3] = (starArray[p][2] * 5);
					starArray[p][4] = rand.nextInt(100) + 1;
				}
				starArray[p][0] -= starArray[p][2];
				g1.setColor(Color.getHSBColor(((float)starArray[p][4]) / 100 , 1, 1));
				g1.fillOval(starArray[p][0], starArray[p][1], starArray[p][3],starArray[p][3]);
			}
		}
	}
}
