

package frogger;

import jig.engine.util.Vector2D;

/**
 * Simple tiny particle
 * 
 * Used to show some weather effects
 *
 */
public class Particle extends MovingEntity {
	
	private int timeExpire = 1;
	private int timeAlive = 1;
	
	public Particle(String sprite, Vector2D pos, Vector2D v) {
		super(sprite);
		position = pos;
		velocity = v;
		setActivation(true);
		timeExpire = 0;
	}
	
	/**
	 * Build particle with expiration timer
	 * 
	 * @param pos - position
	 * @param v - velocity
	 * @param te - expiration timer in milliseconds
	 */
	public Particle(String sprite, Vector2D pos, Vector2D v, int te) {
		super(sprite);
		position = pos;
		velocity = v;
		setActivation(true);
		timeExpire = te;
	}
	
	public void update(final long deltaMs) {
		super.update(deltaMs);
		
		// Check the expiration
		if (timeExpire != 0) {
			timeAlive += deltaMs;
			if (timeAlive > timeExpire)
				setActivation(false);
		}
	}
}
