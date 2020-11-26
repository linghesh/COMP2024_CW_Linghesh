

package frogger;

import jig.engine.util.Vector2D;

public class LongLog extends MovingEntity {
	
	public static int LENGTH = 32*4;
	
	public LongLog (Vector2D pos, Vector2D v) {
		super(Main.SPRITE_SHEET + "#longlog");
		position = pos;
		Vector2D posSphere1 = position;
		Vector2D posSphere2 = new Vector2D(position.getX()+32*1, position.getY());
		Vector2D posSphere3 = new Vector2D(position.getX()+32*2, position.getY());
		Vector2D posSphere4 = new Vector2D(position.getX()+32*3, position.getY());
		collisionObjects.add(new CollisionObject("colSmall",posSphere1));
		collisionObjects.add(new CollisionObject("colSmall",posSphere2));
		collisionObjects.add(new CollisionObject("colSmall",posSphere3));
		collisionObjects.add(new CollisionObject("colSmall",posSphere4));
		velocity = v;
		
		if (v.getX() < 0)
			setFrame(1);
		else
			setFrame(0);
	}
}