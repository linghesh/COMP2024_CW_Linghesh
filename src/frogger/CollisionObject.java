

package frogger;
import jig.engine.physics.vpe.VanillaSphere;
import jig.engine.util.Vector2D;


public class CollisionObject extends VanillaSphere {

	public CollisionObject(Vector2D pos) {
		super("col");
		setPosition(pos);
	}
	
	public CollisionObject(String name, Vector2D pos) {
		super(name);
		setPosition(pos);
	}
	
	/**
	 * Depending on the collision sphere, we offset it's position so
	 * that it appears in the middle of the object
	 */
	public void setPosition(Vector2D pos) {
		double dX = 16 - getRadius();
		double dY = -getRadius() +16;
		position = new Vector2D(pos.getX()+dX, pos.getY()+dY);
	}
	
	public void update(long deltaMs) {
		;
	}
}