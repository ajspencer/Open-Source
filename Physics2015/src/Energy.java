import jbox2d.dynamics.*;

import java.util.*;
import java.awt.*;

import javax.swing.*;

//import test.SimPanel;
import jbox2d.collision.shapes.*;
import jbox2d.common.*;
public class Energy extends JFrame implements Runnable
{
	//ArrayList<JFrame> listOfFrames= new ArrayList<>();
	Font textf = new Font("Display",Font.PLAIN,18);
	Font headerf = new Font("Display",Font.PLAIN,24);
	int baseX = 0;
	int baseY = 0;
	boolean runningSim2 = false;
	ArrayList<Float> yPositionList = new ArrayList<Float>(); 
	ArrayList<Float> xPositionList = new ArrayList<Float>();
	ArrayList<Float> yVelocityList = new ArrayList<Float>(); 
	ArrayList<Float> xVelocityList = new ArrayList<Float>();
	int posCounter;
	public Energy()
	{
		super("Energy Module");
		posCounter = 0;
	}
	
	public void run()
	{
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		//create starting text panel here
		JTextArea startTextPane = new JTextArea();
		startTextPane.setEditable(false);
		startTextPane.setLineWrap(true);
		startTextPane.setWrapStyleWord(true);
		startTextPane.setFont(textf);
		startTextPane.setText("Welcome to the Energy Module! In this module, you will learn about the ideas and concepts of energy. Press start when you are ready to go, or back if you want to try another one.");
		c.add(BorderLayout.NORTH, startTextPane);
		JButton startButton = new JButton("START");
		JButton menuButton = new JButton("BACK TO MENU");
		c.add(BorderLayout.CENTER,startButton);
		c.add(BorderLayout.SOUTH,menuButton);
		startButton.addActionListener( e-> {
			createFirstPanel();
		});
		menuButton.addActionListener( e-> {
	        	javax.swing.SwingUtilities.invokeLater(new Menu());
		});
		setVisible(true);
	}
	
	public void fixPanel(JTextArea jta, Font f) //this makes the jtextarea nicer to look at/use
	{
		jta.setFont(f);
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
	}
	
	public void createFirstPanel()
	{
		JFrame firstFrame = new JFrame("1");
		firstFrame.setSize(500,500);
		Container c = firstFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea firstTextPane = new JTextArea("The first question to ask is: What is energy?\nSimply put, energy is the ability for an object to do work.\n"
				+ "It is said that work is done upon an object when a force causes a displacement of the object.\n"
				+ "This means that the units of energy are Newtons times meters, or what is defined as the Joule.");
		fixPanel(firstTextPane, textf);
		c.add(BorderLayout.CENTER, firstTextPane);
		backButton.addActionListener(e->{
			firstFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createSecondPanel();
			firstFrame.dispose();
		});
		firstFrame.setVisible(true);
	}
	
	public void createSecondPanel()
	{
	JFrame secondFrame = new JFrame("2");
		secondFrame.setSize(500,500);
		Container c = secondFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea secondTextPane = new JTextArea("There is a very important law that is needed to understand energy."
				+ "That is the idea that regardless of what is does to an object in terms of forces, energy can neither be created nor destroyed, but it can only be transformed. The idea that energy can be transformed implies that energy has different forms.\n"
				+ "This is known as the Law of Conservation of Energy.");
			//	System.out.println(firstTextPane.getFont());
		fixPanel(secondTextPane, textf);
		c.add(BorderLayout.CENTER, secondTextPane);
		backButton.addActionListener(e->{
			createFirstPanel();
			secondFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			//createSecondPanel();
			createThirdPanel();
			secondFrame.dispose();
		});
		secondFrame.setVisible(true);
	}
	
	public void createThirdPanel()
	{
		JFrame thirdFrame = new JFrame("3");
		thirdFrame.setSize(500,500);
		Container c = thirdFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea thirdTextPane = new JTextArea("In order to work with energy, the concept of a system must be understood.\n"
				+ "A system is a collection of objects. A system that is free from the influence of some net external force is called an isolated system.\n"
				+ "This is combined with the Law of Conservation of Energy when working with energy considerations.\n"
				+ "In an isolated system, all energy put in will be given out in some way by the system.\n"
				+ "This means that when working with energy, it is necessary to define the system.\n"
				+ "In many cases, a system can also involve the entire Earth if the force of gravity is used.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(thirdTextPane, textf);
		//runSim1();
	//	c.add(new SimPanel());
		c.add(BorderLayout.CENTER, thirdTextPane);
		backButton.addActionListener(e->{
			createSecondPanel();
			thirdFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createFourthPanel();
			thirdFrame.dispose();
		});
		thirdFrame.setVisible(true);
	}
//	runSim1();
//	c.add(new SimPanel());
	public void createFourthPanel()
	{
		JFrame fourthFrame = new JFrame("4");
		fourthFrame.setSize(500,500);
		Container c = fourthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea fourthTextPane = new JTextArea("Consider the following block. Let us define the system to be the block and Earth.\n"
				+ "Now that it is stopped, does it have any energy? It must since we included Earth in our isolated system.\n"
				+ "The fact that this block has the potential to move shows that it has a type of energy called potential energy.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(fourthTextPane, textf);
		runSim1();
		c.add(new SimPanel());
		c.add(BorderLayout.NORTH, fourthTextPane);
		backButton.addActionListener(e->{
			createThirdPanel();
			fourthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createFifthPanel();
			fourthFrame.dispose();
		});
		fourthFrame.setVisible(true);
	}
	
	public void createFifthPanel()
	{
		JFrame fifthFrame = new JFrame("5");
		fifthFrame.setSize(500,500);
		Container c = fifthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea fifthTextPane = new JTextArea("More specifically, the type of potential energy related to height and gravity is called gravitational potential energy.\n"
				+ "This energy is stored in the object based on its height. The mathermatical formula for graviational potential energy, or U, is:\n"
				+ "U = m*g*h\n"
				+ "m is the mass of the object in kilograms, g is the gravitational field constant on Earth, or 9.8 N/kg, and h is the object's height in meters.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(fifthTextPane, textf);
		c.add(BorderLayout.CENTER, fifthTextPane);
		backButton.addActionListener(e->{
			createFourthPanel();
			fifthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createSixthPanel();
			fifthFrame.dispose();
		});
		fifthFrame.setVisible(true);
	}
	
	public void createSixthPanel()
	{
		JFrame sixthFrame = new JFrame("6");
		sixthFrame.setSize(500,500);
		Container c = sixthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea sixthTextPane = new JTextArea("So, what would the potential energy be of a 2 kilogram mass that is 10 meters above the ground?\n"
				+ "It would simply be m*g*h = (2kg)(9.8N/kg)(10m) = 196 J\n\n"
				+ "One last key point to remember is that the height used is relative to a certain point, so it is possible for potential energy to be negative, although uncommon.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(sixthTextPane, textf);
		c.add(BorderLayout.CENTER, sixthTextPane);
		backButton.addActionListener(e->{
			createFifthPanel();
			sixthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createSeventhPanel();
			sixthFrame.dispose();
		});
		sixthFrame.setVisible(true);
	}
	
	public void createSeventhPanel()
	{
		JFrame seventhFrame = new JFrame("7");
		seventhFrame.setSize(500,500);
		Container c = seventhFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea seventhTextPane = new JTextArea("The other big type of energy to consider would be kinetic energy. Kinetic energy is possessed by objects that move.\n"
				+ "Kinetic energy, or KE, is defined as\nKE = 0.5*m*v^2, where m is still the mass in kilograms and v is the velocity in meters per second.\n"
				+ "So, the kinetic energy of a 2 kg block moving at 5 m/s is:\n"
				+ "KE = 0.5*(2 kg)*(5m/s)^2 = 25J");
//			System.out.println(firstTextPane.getFont());
		fixPanel(seventhTextPane, textf);
		c.add(BorderLayout.CENTER, seventhTextPane);
		backButton.addActionListener(e->{
			createSixthPanel();
			seventhFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createEighthPanel();
			seventhFrame.dispose();
		});
		seventhFrame.setVisible(true);
	}
	public void createEighthPanel()
	{
		JFrame eighthFrame = new JFrame("8");
		eighthFrame.setSize(500,500);
		Container c = eighthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea eighthTextPane = new JTextArea("The important questions in energy considerations require understanding energy transformations and how to manipulate them.\n"
				+ "In a situation where there are no external forces put on a system, then the Law of Conservation of Energy takes over and says that any energy inputted is equal to any energy outputted.\n"
				+ "Consider this with the following example:\n"
				+ "A 2 kg mass shot upwards at 5 m/s will go how high before stopping and starting to drop?\n"
				+ "Hint: Consider all forms of energy before the block starts moving and at the instant it stops moving.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(eighthTextPane, textf);
		c.add(BorderLayout.CENTER, eighthTextPane);
		backButton.addActionListener(e->{
			createSeventhPanel();
			eighthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createNinthPanel();
			eighthFrame.dispose();
		});
		eighthFrame.setVisible(true);
	}
	public void createNinthPanel()
	{
		JFrame ninthFrame = new JFrame("9");
		ninthFrame.setSize(500,500);
		Container c = ninthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("NEXT");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea ninthTextPane = new JTextArea("Let us define our system to be the block and Earth. This means that any energy put in will be given out.\n"
				+ "Let us also define the starting height to be at 0 meters. Since we are trying to find the instant where the block has stopped moving, this is the point where the velocity is zero.\n"
				+ "Essentially, the kinetic energy put in before is equal to the gravitational potential energy later. So we can set these two formulas equal to each other.\n"
				+ "0.5*m*v^2 = m*g*h\n"
				+ "This equation can then be solved for the final height.\n"
				+ "0.5*v^2 = g*h\n"
				+ "h = 0.5*v^2/g = 0.5(5m/s)^2/9.8N/kg\n"
				+ "h = 1.28m");
//			System.out.println(firstTextPane.getFont());
		fixPanel(ninthTextPane, textf);
		c.add(BorderLayout.CENTER, ninthTextPane);
		backButton.addActionListener(e->{
			createEighthPanel();
			ninthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			createTenthPanel();
			ninthFrame.dispose();
		});
		ninthFrame.setVisible(true);
	}
	public void createTenthPanel()
	{
		JFrame tenthFrame = new JFrame("10");
		tenthFrame.setSize(500,500);
		Container c = tenthFrame.getContentPane();
		JButton backButton = new JButton("BACK");
		JButton nextButton = new JButton("END");
		c.add(BorderLayout.WEST, backButton);
		c.add(BorderLayout.EAST, nextButton);
		JTextArea tenthTextPane = new JTextArea("So, we have seen the basic concept of energy and energy conservations as well as some basic types of energy.\n"
				+ "Of course, there are many other types of energy, such as thermal, chemical, and elastic potential, to name a few. Even if these have different formulas, the basic concepts taught here will hold in all situations.\n");
//			System.out.println(firstTextPane.getFont());
		fixPanel(tenthTextPane, textf);
		c.add(BorderLayout.CENTER, tenthTextPane);
		backButton.addActionListener(e->{
			createNinthPanel();
			tenthFrame.dispose();
		});
		nextButton.addActionListener(e->{
			//posCounter++;
			//createtenthPanel();
			tenthFrame.dispose();
		});
		tenthFrame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Energy EN = new Energy();
		javax.swing.SwingUtilities.invokeLater(EN);
	}
	
	public void runSim1()
	{
		xVelocityList.clear();
		yVelocityList.clear();
		Vec2 rightMove = new Vec2(0.0f, 3.0f);
		//creates the world
		World world = new World(rightMove);
		//creates a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 4.0f);
		Body body = world.createBody(bodyDef);
		//Creates a polygon shape
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		float timeStep = 1.0f / 60.0f;
		//suggested default values
		int velocityIterations = 6;
		int positionIterations = 2;
		//runs the program, prints out the position of a box falling
		for (int i = 0; i < 70; ++i)
		{
		    world.step(timeStep, velocityIterations, positionIterations);
		    Vec2 position = body.getPosition();
		    Vec2 velocity = body.getLinearVelocity();
		    float angle = body.getAngle();
		    
		    //use something like the above for positions
		    xVelocityList.add(velocity.x);
		    yVelocityList.add(velocity.y);
		//    System.out.printf("%4.2f %4.2f %4.2f\n", velocity.x, velocity.y, angle);
		    //everything in the movement uses velocity to move
		    //make an arraylist of velocity values
		}
		baseX = 150;
		baseY = 150;
	}
		public void runSim2()
		{
			runningSim2 =true;
			xVelocityList.clear();
			yVelocityList.clear();
			Vec2 gravity = new Vec2(0f, -10.0f);
			//creates the world
			World world = new World(gravity);
			//creates a body
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyType.DYNAMIC;
			bodyDef.position.set(0.0f, 4.0f);
			Body body = world.createBody(bodyDef);
			//Creates a polygon shape
			PolygonShape dynamicBox = new PolygonShape();
			dynamicBox.setAsBox(1.0f, 1.0f);
			float timeStep = 1.0f / 60.0f;
			//suggested default values
			int velocityIterations = 6;
			int positionIterations = 2;
			//runs the program, prints out the position of a box falling
			for (int i = 0; i < 100; ++i)
			{
			    world.step(timeStep, velocityIterations, positionIterations);
			    Vec2 position = body.getPosition();
			    Vec2 velocity = body.getLinearVelocity();
			    float angle = body.getAngle();
			    
			    //use something like the above for positions
			    xVelocityList.add(velocity.x);
			    yVelocityList.add(velocity.y);
			 //   System.out.printf("%4.2f %4.2f %4.2f\n", velocity.x, velocity.y, angle);
			    //everything in the movement uses velocity to move
			    //make an arraylist of velocity values
			}
			baseX = 175;
			baseY = 150;
	}
	
	///*****************Inner Panel**********************/

		class SimPanel extends JComponent
		{
			private int lastY = baseY;
			private int lastX = baseX;
			private int a = 0; // this is used to keep track of the new velocities
			public SimPanel()
			{ //the trick here is to create this Thread that only tells the window to redraw
				//on each redraw, recalc the new positions and draw 
				Thread animationThread = new Thread(new Runnable() 
				{
		            public void run() 
		            {
		                while (true) 
		                {
		                    repaint();
		                    try 
		                    {
		                    Thread.sleep(10);
		                    a++; //on each execution, move in the velocity list
		                    //System.out.println()
		                    } catch (InterruptedException ex) {}
		                }
		            }
		        });

		        animationThread.start();
			}
			@Override
			public void paintComponent(Graphics g)
			{
				Graphics2D g2d = (Graphics2D) g;
				int boxSpeedY = 0 ; //initialize this here for the try block
				int boxSpeedX = 0 ; 
		        int w = getWidth();
		        int h = getHeight();
		        //int a =0;
		        
		        int boxW = 50;
		        int boxH = 50;
		        try    
		        { 
		        	boxSpeedY = Math.round(-1*yVelocityList.get(a));
		        	//adjusts the velocities since JFrame is upside down with the -1
		        	boxSpeedX = Math.round(xVelocityList.get(a));
		        }
		        catch (IndexOutOfBoundsException aex)
		        {
		        	
		        }
		        	//changes velocities
		        int y = lastY + boxSpeedY; //adjusts for the new speed
		        int x = lastX + boxSpeedX;
		        g2d.setColor(Color.BLACK);
		        g2d.fillRect(x, y, boxW, boxH);

		        lastY = y;
		        lastX = x;
		        //readjusts
		        
		        //next loop runs here
		    }
		}
}