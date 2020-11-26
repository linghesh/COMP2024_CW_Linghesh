
package frogger;
import jig.engine.util.Vector2D;

public class Goal extends MovingEntity {
	
	public boolean isReached = false;
	public boolean isBonus = false;
	
	public Goal(int loc) {
		super(Main.SPRITE_SHEET + "#goal");
		position = new Vector2D(32*(1+2*loc), 32);
		collisionObjects.add(new CollisionObject("colSmall", position));
		sync(position);
		setFrame(0);
	}

	public Goal(Vector2D pos) {
		super(Main.SPRITE_SHEET + "#goal");
		position = pos;
		collisionObjects.add(new CollisionObject("colSmall", position));
		sync(position);
		setFrame(0);		
	}
	
	public void reached() {
		isReached = true;
		setFrame(1);
	}
	
	public void setBonus(boolean b) {
		if (b) {
			isBonus = true;
			setFrame(2);
		} else {
			isBonus = false;
			setFrame(0);
		}
	}
	
	public void update(long deltaMs) {
		;
	}
}