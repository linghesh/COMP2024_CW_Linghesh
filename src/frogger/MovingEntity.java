

package frogger;

import jig.engine.physics.Body;
import jig.engine.util.Vector2D;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for moving entities in the game
 * 
 * They all have update, sync methods and underlining collision spheres
 * 
 *
 */
public abstract class MovingEntity extends Body {
	
	static final int STEP_SIZE = 32;
	
	// List that holds collision spheres
	protected List<CollisionObject> collisionObjects;
	
	
	public MovingEntity (String name) {
		super(name);
		collisionObjects = new LinkedList<CollisionObject>();
	}

	public List<CollisionObject> getCollisionObjects() {
		return collisionObjects;
	}
	
	/**
	 * Updates the collision spheres with new position
	 * 
	 * @param position
	 */
	public void sync(Vector2D position) {
		int i = 0;
		for (CollisionObject a : collisionObjects) {
			Vector2D deltaPos = new Vector2D(position.getX()+(STEP_SIZE*i), position.getY());
			a.setPosition(deltaPos);
			i++;
		}
		    
	}
	
	/**
	 * Check bounds in the game
	 * 
	 * The way this game works, we only worry about the x-axis
	 * 
	 * None of the objects (except the Frogger which has it's own collision detection) travel
	 * in y-axis
	 */
	public void update(final long deltaMs) {
		if (position.getX() > Main.WORLD_WIDTH+width || position.getX() < -(32*4))
			setActivation(false);
			
	    position = new Vector2D(
	    		position.getX()+velocity.getX()*deltaMs,
	    		position.getY()+velocity.getY()*deltaMs);
	    sync(position);
	}
}