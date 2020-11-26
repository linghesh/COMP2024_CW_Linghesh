

package frogger;

import jig.engine.util.Vector2D;
  
public class CopCar extends MovingEntity {
	
	public CopCar (Vector2D pos, Vector2D v) {
		super(Main.SPRITE_SHEET + "#copcar");
		position = pos;
		collisionObjects.add(new CollisionObject(position));
		velocity = v;
		if (v.getX() < 0)
			setFrame(1);
		else
			setFrame(0);
	}
}