import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import jbox2d.collision.shapes.PolygonShape;
import jbox2d.common.Vec2;
import jbox2d.dynamics.Body;
import jbox2d.dynamics.BodyDef;
import jbox2d.dynamics.BodyType;
import jbox2d.dynamics.FixtureDef;
import jbox2d.dynamics.World;

//This example code demonstrates a body,
//a single fixture with the shape of a polygon, falling from the sky.
//see google doc if you didn't understand that.
public class kinematics extends JFrame implements Runnable{

	ArrayList<Float> positionList = new ArrayList<Float>();
	ArrayList<Float> velocityList = new ArrayList<Float>();
	//use these lists in the inner panel
	public kinematics()
	{
		super("Kinematics");
	}
	public void run()
	{
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		runSim();
		setLayout(new BorderLayout());
		getContentPane().add(new SimPanel());
		setVisible(true);
	}
	public void runSim()//method to run the simulation
	{
				//testGraphics graphics = new testGraphics();//this is the call for the graphics class
				//sets the gravity vector for the world
				Vec2 gravity = new Vec2(acceleration, 0.0f);
				//creates the world
				World world = new World(gravity);
				//creates a body
				BodyDef bodyDef = new BodyDef();
				bodyDef.type = BodyType.DYNAMIC;
				bodyDef.position.set(0.0f, 4.0f);
				bodyDef.setLinearVelocity(new Vec2(velocity, 0.0f));
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
				for (int i = 0; i < maxIterations; ++i)
				{
				    world.step(timeStep, velocityIterations, positionIterations);
				    Vec2 position = body.getPosition();
				    Vec2 velocity = body.getLinearVelocity();
				    float angle = body.getAngle();		    
				    //use something like the above for positions
				    velocityList.add(velocity.x/10);
				    positionList.add(position.x/10);
				    System.out.printf("%4.2f %4.2f\n", velocity.x, angle);
				    //everything in the movement uses velocity to move
				    //make an arraylist of velocity values
				}
	}
///*****************Inner Panel**********************/

	int maxIterations = 1000, iteration = 0;
	float velocity, acceleration;
	Label equation0;
	
	class SimPanel extends JComponent
	{
		private float lastX = 0; // this is used to keep track of the new velocities
		
		public SimPanel (){
			setSize(getWidth(),3*getHeight()/4);
			setLayout(new GridBagLayout());
			//set the constraints
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = .5;
			c.ipady = 5;
			c.gridheight = 1;
			c.gridy = 0;
			c.insets = new Insets(10,0,5,0);
			c.anchor = GridBagConstraints.LAST_LINE_END;
			//personalized settings
			c.gridy = 0;
			equation0 = new Label(positionList.get(iteration).toString());
			this.add(equation0,c);
			c.gridy=1;
			c.gridx = 0;
			Label equation1 = new Label("X = 1/2 * ");
			this.add(equation1,c);
			c.gridx = 1;
			TextField accelText = new TextField ("a");
			this.add(accelText,c);
			try{
			acceleration = Float.parseFloat(accelText.getText());
			} catch (NumberFormatException exc){
				acceleration = 4f;
				System.out.println("not a number");
			}
			c.gridx = 2;
			Label equation2 = new Label("^2 * t + ");
			this.add(equation2,c);
			c.gridx = 3;
			TextField velocityText = new TextField ("v");
			this.add(velocityText,c);
			try{
				velocity = Float.parseFloat(velocityText.getText());
				} catch (NumberFormatException exc){
					velocity = 3f;
					System.out.println("not a number");
				}
			c.gridx = 4;
			Label equation3 = new Label(" * t + X_0");
			this.add(equation3,c);
			c.gridx = 5;
			c.weightx = .2;
			c.insets = new Insets(10,10,5,10);
			Button startButton = new Button("Start");
			this.add(startButton, c);
			startButton.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when start button is pressed
	            	velocityList.clear();
	            	iteration = 0;
	            	lastX = 0;
	        		runSim();
	        		//on each redraw, recalc the new positions and draw 
	    			Thread animationThread = new Thread(new Runnable() 
	    			{
	    	            public void run() 
	    	            {
	    	                while (iteration<=maxIterations) 
	    	                {
	    	                    repaint();
	    	                    try 
	    	                    {
	    	                    Thread.sleep(10);
	    	                    iteration++; //on each execution, move in the velocity list
	    	                    equation0.setText(positionList.get(iteration).toString());
	    	                    } catch (InterruptedException ex) {}
	    	                }
	    	            }
	    	        });

	    	        animationThread.start();
	            }
	        });
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			float boxSpeed =0 ; //initialize this here for the try block
	        int w = getWidth();
	        int h = getHeight();
	        
	        int boxW = 20;
	        int boxH = 20;
	        if(iteration<maxIterations){
	        	boxSpeed = velocityList.get(iteration);
	        	//adjusts the velocities since JFrame is upside down
	        }else{
	        	//box is done following animation
	        	System.out.println("done");
	        }
	        	//changes velocities
	        float x = lastX + boxSpeed; //adjusts for the new speed

	        g2d.setColor(Color.BLACK);
	        g2d.fillRect((int) x, h/2 -boxH/2, boxW, boxH);

	        lastX = x;
	        //next loop runs here
	    }
	}
}
