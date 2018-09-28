
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Random;

import javax.annotation.Resources;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class App extends JFrame implements ActionListener, ChangeListener{
	
	// instance variables
	JLabel companyName;
	JButton launch;
	JButton refresh;
	JLabel showAngle;
	JLabel showSpeed;
	JSlider launchAngle;
	JSlider launchSpeed;
	JComboBox setColor;
	JComboBox setColor2;
	JComboBox fireworkType;
	JLabel timeToDetonation;
	JSlider timeDetonation;
	JLabel angleValue;
	JLabel speedValue;
	JLabel inputTime;
	Color color;
	Color color2;
	JPanel center = new JPanel();
	
	int v0;
	int theta;
	int firework;
	int X;
	int Y;
	int x;
	int y;
	int i = 0;
	
	public static void main(String[] args) {
		App a = new App();
		a.setVisible(true);
		a.setTitle("Acme Fireworks Simulator");
		a.setMinimumSize(new Dimension(1000, 600));
		//a.setMaximumSize(new Dimension(1000, 600));;
		a.pack();
		
		}
	
	//constructor
	public App() {
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(1000, 600);
		this.pack();
		
		center.setBackground(Color.BLACK); // center panel for making the fireworks
		add(center, BorderLayout.CENTER);
		
		JPanel topBar1 = new JPanel(); // bar on top displaying name
		topBar1.setBackground(Color.GRAY);
		topBar1.setOpaque(true);
		companyName = new JLabel("Welcome to Acme Fireworks!");
		companyName.setForeground(Color.WHITE);
		topBar1.add(companyName);
		
		JPanel colorAndType = new JPanel();
		colorAndType.setLayout(new BoxLayout(colorAndType, BoxLayout.X_AXIS));
		colorAndType.add(Box.createHorizontalGlue()); // first panel on top
		
		String colorForFirework[] = {"Primary Color", "White","Yellow", "Orange", "Red", "Pink", "Blue", "Green", "Cyan", "Magenta", "Gray"};
	    setColor = new JComboBox(colorForFirework);
	    setColor.setSize(new Dimension(40,30));
	    setColor.addActionListener(this);
	    
	    String colorForFirework2[] = {"Secondary Color","White","Yellow", "Orange", "Red", "Pink", "Blue", "Green", "Cyan", "Magenta", "Gray"};
	    setColor2 = new JComboBox(colorForFirework2); 
	    setColor2.setSize(new Dimension(40,30));
	    setColor2.addActionListener(this);
	    
	    colorAndType.add(setColor);
	    colorAndType.add(setColor2);
	    
	    String chooseFirework[] = {"Chose Firework", "Ripples", "Serendipity", "Tangled", "Midnight Dreams", "Classic", "Lost Stars","Vintage", "Joyful Celebration", "Love"}; // fireworks can be renamed later according to shape
		fireworkType = new JComboBox(chooseFirework);
		fireworkType.setSize(new Dimension(70,30));
		fireworkType.addActionListener(this);
		
		colorAndType.add(fireworkType);
		launch = new JButton("Launch");
		colorAndType.add(launch);
		launch.setSize(new Dimension(100,30));
		colorAndType.add(Box.createHorizontalGlue());
		launch.addActionListener(this);
		
		refresh = new JButton("Refresh");
		refresh.setSize(new Dimension(100,30));
		refresh.addActionListener(this);
		colorAndType.add(refresh);
	
		
		JPanel topBar = new JPanel();
		topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
		topBar.add(topBar1);
		topBar.add(colorAndType);
		
		add(topBar, BorderLayout.NORTH);
		
		
		JPanel bottomBar = new JPanel();
		bottomBar.setBackground(Color.WHITE);
		bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.X_AXIS));
		
		bottomBar.add(Box.createGlue());
		showAngle = new JLabel("Launch Angle: ");
		bottomBar.add(showAngle);
		angleValue = new JLabel("0");
		bottomBar.add(angleValue);
		launchAngle = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		bottomBar.add(launchAngle);
		launchAngle.addChangeListener(this);
		//table for custom slider
		Hashtable labelAngle = new Hashtable(); // hashtable for taking intervals on the slider
		labelAngle.put( new Integer( 0 ), new JLabel("0") );
		labelAngle.put( new Integer(30 ), new JLabel("30") );
		labelAngle.put( new Integer(60 ), new JLabel("60") );
		labelAngle.put( new Integer(90 ), new JLabel("90") );
		launchAngle.setLabelTable( labelAngle );
		launchAngle.setPaintLabels(true);
		 //for ticks on slider
		launchAngle.setMinorTickSpacing(15);
		launchAngle.setMajorTickSpacing(30);
		launchAngle.setPaintTicks(true);
		
		launchAngle.setPreferredSize(new Dimension(100, 55));
		
		bottomBar.add(Box.createGlue());
		showSpeed = new JLabel("Launch Speed: ");
		bottomBar.add(showSpeed);
		speedValue = new JLabel("0");
		bottomBar.add(speedValue);
		launchSpeed = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		bottomBar.add(launchSpeed);
		launchSpeed.addChangeListener(this);
		 //table for custom slider
		Hashtable labelSpeed = new Hashtable(); // hashtable for taking intervals on the slider
		labelSpeed.put(new Integer(0), new JLabel("0"));
		labelSpeed.put(new Integer(75), new JLabel("75"));
		labelSpeed.put(new Integer(150), new JLabel("150"));
		launchSpeed.setLabelTable(labelSpeed);
		launchSpeed.setPaintLabels(true);
		 //for ticks on slider
		launchSpeed.setMinorTickSpacing(25);
		launchSpeed.setMajorTickSpacing(75);
		launchSpeed.setPaintTicks(true);
		launchSpeed.setPreferredSize(new Dimension(100, 55));
		
		bottomBar.add(Box.createGlue());
		timeToDetonation = new JLabel("Time to Detonation: ");
		bottomBar.add(timeToDetonation);
		inputTime = new JLabel("0");
		bottomBar.add(inputTime);
		timeDetonation = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		bottomBar.add(timeDetonation);
		timeDetonation.addChangeListener(this);
		 //table for custom slider
		Hashtable labelTime = new Hashtable();
		labelTime.put(new Integer(0), new JLabel("0"));
		labelTime.put(new Integer(5), new JLabel("5"));
		labelTime.put(new Integer(10), new JLabel("10"));
		labelTime.put(new Integer(15), new JLabel("15"));
		timeDetonation.setLabelTable(labelTime);
		timeDetonation.setPaintLabels(true);
		 //for ticks on slider
		timeDetonation.setMajorTickSpacing(10);
		timeDetonation.setMinorTickSpacing(5);
		timeDetonation.setPaintTicks(true);
		timeDetonation.setPreferredSize(new Dimension(100, 55));
		bottomBar.add(Box.createGlue());
		
		add(bottomBar, BorderLayout.SOUTH);
		
	}
	
	

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == launchAngle) {
			angleValue.setText(Integer.toString(launchAngle.getValue()));
			theta = launchAngle.getValue();
			
		}
		if (e.getSource() == launchSpeed) {
			speedValue.setText(Integer.toString(launchSpeed.getValue()));
			v0 = launchSpeed.getValue();
			
		}
		if (e.getSource() == timeDetonation) {
			inputTime.setText(Integer.toString(timeDetonation.getValue()));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == setColor) { // setting primary color for fireworks
			if(setColor.getSelectedItem() == "Red") {
				color = Color.RED;
			} else if(setColor.getSelectedItem() == "Blue") {
				color = Color.BLUE;
			} else if(setColor.getSelectedItem() == "Green") {
				color = Color.GREEN;
			} else if(setColor.getSelectedItem() == "Pink") {
				color = Color.PINK;
			}else if(setColor.getSelectedItem() == "Orange") {
				color = Color.ORANGE;
			}else if(setColor.getSelectedItem() == "Yellow") {
				color = Color.YELLOW;
			}else if(setColor.getSelectedItem() == "White") {
				color = Color.WHITE;
			} else if(setColor.getSelectedItem() == "Primary Color") {
				color = Color.BLACK;
			} else if(setColor.getSelectedItem() == "Cyan") {
				color = Color.CYAN;
			} else if(setColor.getSelectedItem() == "Magenta") {
				color = Color.MAGENTA;
			} else if(setColor.getSelectedItem() == "Gray") {
				color = Color.GRAY;
			}
			
		} if(e.getSource() == setColor2) { // setting secondary color for fireworks
			if(setColor2.getSelectedItem() == "Red") {
				color2 = Color.RED;
			} else if(setColor2.getSelectedItem() == "Blue") {
				color2 = Color.BLUE;
			} else if(setColor2.getSelectedItem() == "Green") {
				color2 = Color.GREEN;
			} else if(setColor2.getSelectedItem() == "Pink") {
				color2 = Color.PINK;
			}else if(setColor2.getSelectedItem() == "Orange") {
				color2 = Color.ORANGE;
			}else if(setColor2.getSelectedItem() == "Yellow") {
				color2 = Color.YELLOW;
			}else if(setColor2.getSelectedItem() == "White") {
				color2 = Color.WHITE;
			}else if(setColor2.getSelectedItem() == "Secondary Color") {
				color2 = Color.BLACK;
			}else if(setColor2.getSelectedItem() == "Cyan") {
				color2 = Color.CYAN;
			}else if(setColor2.getSelectedItem() == "Magenta") {
				color2 = Color.MAGENTA;
			}else if(setColor2.getSelectedItem() == "Gray") {
				color2 = Color.GRAY;
			}
		
		} else if (e.getSource() == fireworkType) { // setting firework design/type
			
			if(fireworkType.getSelectedItem() == "Ripples") {
				firework = 1;
				System.out.println("F1 selected");
			} else if(fireworkType.getSelectedItem() == "Serendipity") {
				firework = 2;
				System.out.println("F2 selected");
			} else if(fireworkType.getSelectedItem() == "Tangled") {
				firework = 3;
				System.out.println("F3 selected");
			} else if(fireworkType.getSelectedItem() == "Midnight Dreams") {
				firework = 4;
				System.out.println("F4 selected");
			} else if(fireworkType.getSelectedItem() == "Classic") {
				firework = 5;
				System.out.println("F5 selected");
			} else if(fireworkType.getSelectedItem() == "Lost Stars") {
				firework = 6;
				System.out.println("F6 selected");
			} else if(fireworkType.getSelectedItem() == "Vintage") {
				firework = 7;
				System.out.println("F7 selected");

			} else if(fireworkType.getSelectedItem() == "Joyful Celebration") {
				firework = 8;
				System.out.println("F8 selected");

			} else if(fireworkType.getSelectedItem() == "Love") {
				firework = 9;
				System.out.println("F9 selected");

			} else if(fireworkType.getSelectedItem() == "Chose Firework") {
				firework = 0;
				System.out.println("None selected");
			}
		
		} else if(e.getSource() == refresh) { // repainting the whole canvas when refresh button is pressed
		center.repaint();
		repaint();
		
		} else if((e.getSource() == launch) && (color != Color.BLACK)) { // action performed when user presses launch
			// color != black is to confirm that the user has selected a primary color at least
			
			Graphics g = getGraphics();
			g.setColor(color);
			for (double time = 0; time <= Integer.valueOf(inputTime.getText()); time = time + 0.25) { // loop for making arc
				int x = (int) (v0*Math.cos(Math.toRadians(theta))*time);
				int y = (int) ((v0*Math.sin(Math.toRadians(theta))*time) - (.5*9.8*time*time));
				X = x;
				Y = (getHeight() - y - 57);
				System.out.print(X + " ");
				System.out.println(Y);
				g.drawOval(X, Y, 3, 3);
	
			} 
			
			if(!((Y < 140) || (Y > getHeight() - 160))) { // for checking if the fireworks are out of bounds
				companyName.setText("Welcome to Acme Fireworks!"); // Setting the text back to normal after the fireworks go out of bounds
				companyName.setForeground(Color.WHITE);
			
			if(firework == 1) { // ripples
				int i;
				int x = X;
				int y = Y;
				for(i = 0; i < 150; i = i + 20) {
					if (i%3 == 0) {
						g.setColor(color2);
					} else {
						g.setColor(color);
					}
					g.drawOval(x, y, i, i);
					x = x - 10;
					y = y - 10;
					}
				
			} else if (firework == 2) {  // serendipity
				int th = 0;
				int x;
				int y;
				int x2;
				int y2;
				int x3;
				int y3;
				
				for(th = 0; th <= 360; th = th + 72) {
					x = (int)(Math.cos((th)*0.0173)*45 + X);
					y = (int)(Math.sin((th)*0.0173)*45 + Y);
					x2 = (int)(Math.cos((th)*0.0173)*30 + X);
					y2 = (int)(Math.sin((th)*0.0173)*30 + Y);
					x3 = (int)(Math.cos((th)*0.0173)*15 + X);
					y3 = (int)(Math.sin((th)*0.0173)*15 + Y);
					g.setColor(color);
					g.fillOval(x, y, 20, 20);
					g.setColor(color2);
					g.fillOval(x2, y2, 20, 20);
					g.setColor(color);
					g.fillOval(x3, y3, 15, 15);
					g.setColor(Color.WHITE);
					g.fillOval(X, Y, 15, 15);
				}
				
			} else if (firework == 3) { // tangled
				int x;
				int y;
				int x2;
				int y2;
				int th = 0;
				
				for(th = 0; th <= 360; th = th + 15) {
					x = (int)(Math.cos((th)*0.0173)*60 + X);
					y = (int)(Math.sin((th)*0.0173)*60 + Y);
					x2 = (int)(Math.cos((th)*0.0173)*50 + X);
					y2 = (int)(Math.sin((th)*0.0173)*50 + Y);
					g.setColor(color);
					g.drawOval(x, y, 20, 20);
					g.setColor(color2);
					g.drawOval(x2, y2, 15, 15);
					
				}
				
				
			}else if (firework == 4) { //midnight dreams
				
				for(int i = 0; i < 40; i ++) {
					Random r = new Random();
					int s = r.nextInt(1200);
					int t = r.nextInt((getHeight()-100)-140)+140;
					int th = 0;
					int x = X; //creates the illusion of lines
					int y = Y;
					
						for(th = 0; th <= 360; th = th + 15) {
							if(th% 10 == 0) {
							g.setColor(color);
							} else {
							g.setColor(color2);
							}
							g.drawLine(s, t, x, y);
							x = (int)(Math.cos((th)*0.0173)*15 + s);
							y = (int)(Math.sin((th)*0.0173)*15 + t);	
							
						}
				} 
	
			}else if (firework == 5) { // classic
				int x;
				int y;
				int th = 0;
				for(th = 0; th <= 360; th = th + 15) {
					x = (int)(Math.cos((th)*0.0173)*45 + X);
					y = (int)(Math.sin((th)*0.0173)*45 + Y);	
					g.setColor(color);
					g.drawLine(X, Y, x, y);
					
				}
				
			}else if (firework == 6) { // lost stars
				int x0 = X;
				int y0 = Y;
				
				for(int i = 0; i < 4; i++) {
				int[] xPoints = {(x0-50), (x0-10), x0, (x0+10), (x0+50), (x0+10), x0, (x0-10)};
			    int[] yPoints = {y0, (y0-10), (y0-50), (y0-10), y0, (y0+10), (y0+50), (y0+10)};
			    int nPoints = 8;
			    if(i%2 == 0) {
			    	g.setColor(color);
			    } else {
			    	g.setColor(color2);
			    }
			    Polygon star = new Polygon (xPoints, yPoints, nPoints);
			    g.drawPolygon (star);
			    x0 = x0 + 10;
			    y0 = y0 + 5;
				}
				
			} else if(firework == 7) { // vintage 
				int i = 0;
				int x;
				int y;
				int x2;
				int y2;
				int x3;
				int y3;
				
				for(i = 0; i < 360; i = i + 15) {
					x = (int)(Math.cos((i)*0.0173)*63 + X);
					y = (int)(Math.sin((i)*0.0173)*63 + Y);
					x2 = (int)(Math.cos((i)*0.0173)*35 + X);
					y2 = (int)(Math.sin((i)*0.0173)*35 + Y);
					x3 = (int)(Math.cos((i)*0.0173)*47 + X);
					y3 = (int)(Math.sin((i)*0.0173)*47 + Y);
					if(i % 10 == 0) {
						g.setColor(color);
					} else {
						g.setColor(color2);
					}
					g.fillOval(x, y, 5, 5);
					g.fillOval(x2, y2, 5, 5);
					g.fillOval(x3, y3, 5, 5);
				}
				
			} else if(firework == 8) { // joyful celebration
				
				for(int j = 0; j < 25; j ++) {
					Random r = new Random();
					int s = r.nextInt(1200);
					int t = r.nextInt((getHeight()-100)-140)+140; //(max - min) + 1) + min
					int th = 0;
					int x = (int)(Math.cos((i)*0.0173)*40 + X); //creates the illusion of lines
					int y = (int)(Math.sin((i)*0.0173)*40 + Y);
					int x2;
					int y2;
					int x3;
					int y3;
					
					for(int i = 0; i < 360; i = i + 15) {
						g.drawLine(X, Y, s, t);
						x = (int)(Math.cos((i)*0.0173)*40 + s);
						y = (int)(Math.sin((i)*0.0173)*40 + t);
						x2 = (int)(Math.cos((i)*0.0173)*10 + s);
						y2 = (int)(Math.sin((i)*0.0173)*10 + t);
						x3 = (int)(Math.cos((i)*0.0173)*25 + s);
						y3 = (int)(Math.sin((i)*0.0173)*25 + t);
						if(i % 10 == 0) {
							g.setColor(color);
						} else {
							g.setColor(color2);
						}
						g.fillOval(x, y, 5, 5);
						g.fillOval(x2, y2, 5, 5);
						g.fillOval(x3, y3, 5, 5);
						
						}
					} 
				}else if(firework == 9) { // love
					for(int count = 0; count < 20; count ++) {
					Random r = new Random();
					int x0 = r.nextInt(1200);
					int y0 = r.nextInt((getHeight()-100)-140)+140;
					
					if(count%2==0) {
						g.setColor(color);
					} else {
						g.setColor(color2);
					}
					
					g.drawLine(X, Y, x0, y0);
					int[] xPoints = {(x0-12), (x0), x0 + 12, (x0)};
				    int[] yPoints = {y0, (y0-16), (y0), (y0+10)};
				    int nPoints = 4;
				    Polygon heart = new Polygon (xPoints, yPoints, nPoints);
				    g.fillPolygon (heart);
				    g.fillOval(x0-20, y0-20, 20, 20);
				    g.fillOval(x0, y0-20, 20, 20);
					}
				}
		} else { // main else for out of bounds argument
			repaint();
			companyName.setText("OUT OF BOUNDS"); // Changing label to out of bounds
			companyName.setForeground(Color.RED);
		}
			
		
	}

	}
}
