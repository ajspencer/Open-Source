import jbox2d.dynamics.*;

import java.util.*;
import java.awt.*;

import javax.swing.*;

//import test.SimPanel;
import jbox2d.collision.shapes.*;
import jbox2d.common.*;
public class kinematics extends JFrame implements Runnable
{
	//ArrayList<JFrame> listOfFrames= new ArrayList<>();
	Font textf = new Font("Display",Font.PLAIN,18);
	Font headerf = new Font("Display",Font.PLAIN,24);
	int baseX = 0;
	int baseY = 0;
	ArrayList<Float> yPositionList = new ArrayList<Float>(); 
	ArrayList<Float> xPositionList = new ArrayList<Float>();
	ArrayList<Float> yVelocityList = new ArrayList<Float>(); 
	ArrayList<Float> xVelocityList = new ArrayList<Float>();
	int posCounter;
	public kinematics()
	{
		super("Kinematics Module");
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
		startTextPane.setText("Welcome to the Kinematics Module! In this module, you will learn about the ideas and concepts of kinematics. Press start when you are ready to go, or back if you want to try another one.");
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
		JTextArea firstTextPane = new JTextArea("What is Kinematics?\nKinematics deals with the motion of objects without considering what forces act upon the objects."
				+ "In this lesson, we will only be focusing on the position, velocity, and acceleration of objects.\n"
				+ "Position will be in meters, velocity in meters per second, and acceleration in meters per second per second.");
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
		JTextArea secondTextPane = new JTextArea("Position is defined as the specific location in space of an object.\nVelocity is the change in position over time, also denoted as dX/dt."+
		"\nAcceleration is defined as the change in velocity over time, also seen as dV/dt."+
				"These quantities can be related in the equation:\n"
				+ "x_final = x_initial+v*t+.5*a^2*t");
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
		JTextArea thirdTextPane = new JTextArea("In the animation below, a box slides to the right at a constant velocity of 3.0 m/s. "
				+ "Notice how it doesn't speed up, showing that there is no acceleration.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(thirdTextPane, textf);
		runSim1();
		c.add(new SimPanel());
		c.add(BorderLayout.NORTH, thirdTextPane);
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
		JTextArea fourthTextPane = new JTextArea("Given the following equation:\nx_final = x_initial+v*t+.5*a^2*t\n"
				+ "How far did the block go after 3 seconds if it was going 3.0 m/s?\n"
				+ "To solve this, remember that dX is the change in position, and so we want to find dX, or x_final - x_initial."
				+ "This means that \ndX = v*t+.5*a^2*t. Since acceleration is 0, \ndX = (3.0 m/s) * 3s + .5*(0m/s^2)^2*3s=9m.\n"
				+ "The block went 9 meters in 3 seconds.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(fourthTextPane, textf);
		//runSim1();
		//c.add(new SimPanel());
		c.add(BorderLayout.CENTER, fourthTextPane);
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
		JTextArea fifthTextPane = new JTextArea("In this animation, the initial velocity is 0 m/s but there is a constant acceleration to the right of 5.0 m/s^2.\n"
				+ "Watch how the object speeds up, letting you know that there is an acceleration. How is the change in position when there is an acceleration different from when there is only a velocity?");
//			System.out.println(firstTextPane.getFont());
		fixPanel(fifthTextPane, textf);	
		runSim2();
		c.add(new SimPanel());
		c.add(BorderLayout.NORTH, fifthTextPane);
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
		JTextArea sixthTextPane = new JTextArea("When there is only a velocity, position changes linearly. This is true because the dX equation becomes dX = v*t.\n"
				+ "However, when there is an acceleation, position changes exponentially. The equation then becomes dX = .5*a^2*t.\n"
				+ "So, with this equation, what would the acceleration need to be so that the change in position is 8 meters over 4 seconds?\n"
				+ "We can set dX=8 and t=4, and so 8 =.5*a^2*4. Thus a = 2 m/s^2.");
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
		JTextArea seventhTextPane = new JTextArea("Now that we have looked at 1 dimensional kinematics, let's look at 2D kinematics. In 2D kinematics, the horizontal component (x) and the vertical component(y) are completely separate from each other.\n"
				+ "So, position, velocity, or accleration in x does not affect y in any way, and vice versa. To look at these problems, we must create two equations: one for x and one for y.\n"
				+ "The equation for x is x_final = x_initial+v_x*t+.5*(a_x)^2*t and the equation for y is y_final = y_initial+v_y*t+.5*(a_y)^2*t.");
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
		JTextArea eighthTextPane = new JTextArea("In this animation, a ball is thrown upwards at 7.0 m/s and to the left 3.0 m/s. However, the only acceleration acting on the ball is downwards at the acceleration of gravity, or 9.81 m/s^2.\n"
				+ "Notice how the ball changes speed vertically, but it doesn't change speed horizontally. If you just look at the horizontal movement, it follows the exact same motion as the 1D block animation with no acceleration shown earlier in this lesson.");
//			System.out.println(firstTextPane.getFont());
		fixPanel(eighthTextPane, textf);
		runSim3();
		c.add(new SimPanel());
		c.add(BorderLayout.NORTH, eighthTextPane);
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
		JTextArea ninthTextPane = new JTextArea("In the last animation of the ball being thrown up, the ball rose to a maximum height before falling back down. At this maximum height, the velocity was 0 m/s.\n"
				+ "So, how much time did it take for the ball to reach maximum after being thrown up at 7.0 m/s?\n"
				+ "To understand, we must remember that acceleration is the change in velocity over time. This means that velocity is linearly changed with the slope being the acceleration.\n"
				+ "So, we can create the equation:\n"
				+ "v_final = a*t + v_initial."
				+ "If v_intial = 7.0 m/s and acceleration is gravity, then time can be calculated:\n"
				+ "0 m/s = (-9.81 m/s^2)*t + 7.0 m/s. t = .714 seconds.");
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
		JTextArea tenthTextPane = new JTextArea("So, we have seen the basic concept of kinematics in both 1D and 2D.\n"
				+ "Kinematics is the basis for all of classical mechanics and thus must be understood. With the introduction of forces, the reason for the acceleration and motion of these objects can be explained and calculated.\n");
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
	
	public void runSim1()
	{
		xVelocityList.clear();
		yVelocityList.clear();
		Vec2 rightMove = new Vec2(0.0f, 0.0f);
		//creates the world
		World world = new World(rightMove);
		//creates a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 0.0f);
		bodyDef.setLinearVelocity(new Vec2(3.0f, 0.0f));
		Body body = world.createBody(bodyDef);
		//Creates a polygon shape
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		float timeStep = 1.0f / 60.0f;		//suggested default values
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
		//    System.out.printf("%4.2f %4.2f %4.2f\n", velocity.x, velocity.y, angle);
		    //everything in the movement uses velocity to move
		    //make an arraylist of velocity values
		}
		baseX = 150;
		baseY = 150;
	}
	public void runSim2()
	{
		xVelocityList.clear();
		yVelocityList.clear();
		Vec2 gravity = new Vec2(5.0f, 0.0f);
		//creates the world
		World world = new World(gravity);
		//creates a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 0.0f);
		bodyDef.setLinearVelocity(new Vec2(0.0f, 0.0f));
		Body body = world.createBody(bodyDef);
		//Creates a polygon shape
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		float timeStep = 1.0f / 60.0f;
		//suggested default values
		int velocityIterations = 6;
		int positionIterations = 2;
		//runs the program, prints out the position of a box falling
		for (int i = 0; i < 180; ++i)
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
	
	public void runSim3()
	{
		xVelocityList.clear();
		yVelocityList.clear();
		Vec2 gravity = new Vec2(0.0f, -9.81f);
		//creates the world
		World world = new World(gravity);
		//creates a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 0.0f);
		bodyDef.setLinearVelocity(new Vec2(3.0f, 7.0f));
		Body body = world.createBody(bodyDef);
		//Creates a polygon shape
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		float timeStep = 1.0f / 60.0f;
		//suggested default values
		int velocityIterations = 6;
		int positionIterations = 2;
		//runs the program, prints out the position of a box falling
		for (int i = 0; i < 200; ++i)
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
		        g2d.fillRect(x-100, y, boxW, boxH);

		        lastY = y;
		        lastX = x;
		        //readjusts
		        
		        //next loop runs here
		    }
		}
}