
package frogger;
import jig.engine.util.Vector2D;
  
public class ShortLog extends MovingEntity {
	
	public static int LENGTH = 32*3;
	
	public ShortLog (Vector2D pos, Vector2D v) {
		super(Main.SPRITE_SHEET + "#shortlog");
		position = pos;
		Vector2D posSphere1 = position;
		Vector2D posSphere2 = new Vector2D(position.getX()+32, position.getY());
		Vector2D posSphere3 = new Vector2D(position.getX()+64, position.getY());
		collisionObjects.add(new CollisionObject("colSmall", posSphere1));
		collisionObjects.add(new CollisionObject("colSmall", posSphere2));
		collisionObjects.add(new CollisionObject("colSmall", posSphere3));
		velocity = v;
		if (v.getX() < 0)
			setFrame(1);
		else
			setFrame(0);
	}

}