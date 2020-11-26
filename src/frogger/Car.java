
package frogger;

import jig.engine.util.Vector2D;
 
public class Car extends MovingEntity {
	public final static int TYPES  = 3;
	public final static int LENGTH = 32*1;
	
	public Car (Vector2D pos, Vector2D v, int randId) {
		super(Main.SPRITE_SHEET + "#car" + randId);
		position = pos;
		collisionObjects.add(new CollisionObject(position));
		velocity = v;
		if (v.getX() < 0)
			setFrame(1);
		else
			setFrame(0);
	}
}