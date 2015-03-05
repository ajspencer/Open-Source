import jbox2d.dynamics.*;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import jbox2d.collision.shapes.*;
import jbox2d.common.*;
//This example code demonstrates a body,
//a single fixture with the shape of a polygon, falling from the sky.
//see google doc if you didn't understand that.
public class test extends JFrame implements Runnable{

	/**
	 * @param args
	 */
	ArrayList<Float> yPositionList = new ArrayList<Float>(); 
	ArrayList<Float> xPositionList = new ArrayList<Float>();
	ArrayList<Float> yVelocityList = new ArrayList<Float>(); 
	ArrayList<Float> xVelocityList = new ArrayList<Float>();
	//use these lists in the inner panel
	public test()
	{
		super("Test Graphics");
	}
	public void run()
	{
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		runSim();
		getContentPane().add(new SimPanel());

		
		setVisible(true);
	}
	public void runSim()//method to run the simulation
	{
		//testGraphics graphics = new testGraphics();//this is the call for the graphics class
				//sets the gravity vector for the world
				Vec2 gravity = new Vec2(0.0f, -10.0f);
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
				//Creates a fixture and adds it to the body
				FixtureDef fixtureDef = new FixtureDef();
				//the shape of the fixture is the polygon
				fixtureDef.shape = dynamicBox;
				fixtureDef.density = 1.0f;
				fixtureDef.friction = 0.3f;
				body.createFixture(fixtureDef);
				//Newton's laws are applied at steps, this sets the rate. 
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
				    //if(position.y < 0) {
				   // world.destroyBody(body);
				   // break;
				   // }
				   /* System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
				    xpositionList.add(position.y);
				    ypositionList.add(position.y);
				    */ 
				    
				    //use something like the above for positions
				    xVelocityList.add(velocity.x);
				    yVelocityList.add(velocity.y);
				    System.out.printf("%4.2f %4.2f %4.2f\n", velocity.x, velocity.y, angle);
				    //everything in the movement uses velocity to move
				    //make an arraylist of velocity values
				}
	}
	
	public static void main(String[] args) 
	{
		test TestFrame = new test();
		javax.swing.SwingUtilities.invokeLater(TestFrame);

	}
///*****************Inner Panel**********************/

	class SimPanel extends JComponent
	{
		private int lastY = 0;
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
			int boxSpeed =0 ; //initialize this here for the try block
	        int w = getWidth();
	        int h = getHeight();
	        //int a =0;
	        
	        int boxW = 100;
	        int boxH = 10;
	        try    
	        { 
	        	boxSpeed = Math.round(-1*yVelocityList.get(a));
	        	//adjusts the velocities since JFrame is upside down
	        }
	        catch (IndexOutOfBoundsException aex)
	        {
	        	System.exit(0); //ends if the box falls out of the screen
	        }
	        	//changes velocities
	        int y = lastY + boxSpeed; //adjusts for the new speed

	        g2d.setColor(Color.BLACK);
	        g2d.fillRect(w/2 -boxW/2, y, boxW, boxH);

	        lastY = y;
	        //readjusts
	        //a++;
	        
	        //next loop runs here
	    }
	}
}
