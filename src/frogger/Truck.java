

package frogger;

import jig.engine.util.Vector2D;
  
public class Truck extends MovingEntity {
	
	public static int LENGTH = 32*2;

	public Truck (Vector2D pos, Vector2D v) {
		super(Main.SPRITE_SHEET + "#truck");
		position = pos;
		Vector2D posSphere1 = position;
		Vector2D posSphere2 = new Vector2D(position.getX()+32, position.getY());
		collisionObjects.add(new CollisionObject(posSphere1));
		collisionObjects.add(new CollisionObject(posSphere2));
		velocity = v;
		
		if (v.getX() < 0)
			setFrame(1);
		else
			setFrame(0);
	}
}