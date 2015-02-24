import jbox2d.dynamics.*;

import jbox2d.collision.shapes.*;
import jbox2d.common.*;
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//sets the gravity vector for the world
		Vec2 gravity = new Vec2(0.0f, -10.0f);
		//creates the world
		World world = new World(gravity);
		//creates a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0.0f, 4.0f);
		Body body = world.createBody(bodyDef);
		//adds a polygon shape to the body
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		//Creates a fixture definition using the box.
		FixtureDef fixtureDef = new FixtureDef();
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
		for (int i = 0; i < 60; ++i)

		{

		    world.step(timeStep, velocityIterations, positionIterations);

		    Vec2 position = body.getPosition();

		    float angle = body.getAngle();

		    System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);

		}

	}

}
