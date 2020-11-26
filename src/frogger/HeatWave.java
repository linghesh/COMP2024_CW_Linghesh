
package frogger;

import java.util.Random;

import jig.engine.util.Vector2D;

public class HeatWave {
	final static int PERIOD    = 2000;  //milliseconds
	final static int DURATION  = 1000;  //milliseconds
	
	Random r;
	
	private long timeMs;
	private long durationMs;
	private long heatWaveMs;
	
	public boolean isHot;
	
	public HeatWave() {
		isHot = false;
		timeMs = 0;
		heatWaveMs = 0;
		r = new Random(System.currentTimeMillis());
	}
	
	/**
	 * This is checked with every game update
	 * @param f - reference Frogger's hw_hasMoved
	 * @param deltaMs
	 */
	public void perform (Frogger f, final long deltaMs, final int level) {	
		if (!f.isAlive) {
			isHot = false;
			return;
		}
		
		if (isHot && durationMs > (DURATION - (level*10)) && !f.hw_hasMoved) {
			f.randomJump(r.nextInt(4));
			isHot = false;
		}
		
		if (f.hw_hasMoved)
			isHot = false;
	}
	
	/**
	 * Initiate the Heat Wave effect
	 * 
	 * @param f
	 * @param temp - based on the GameTemp, this effects occurs more often
	 */
	public void start (Frogger f, final int GameLevel) {
		
		if (!isHot && timeMs > PERIOD) {		
			if (r.nextInt(100) < GameLevel*10) {
				durationMs = 1;
				isHot = true;
				f.hw_hasMoved = false;
				AudioEfx.fire.play(0.2);
			}		
			timeMs = 0;
		}
	}
	
	/**
	 * Generating particles
	 * 
	 * @param f
	 * @return
	 */
	public MovingEntity genParticles(Vector2D pos) {
		if (!isHot)
			return null;
		
		if (r.nextInt(100) > 10)
			return null;
		
		// Generate particles from center of the Frogger to all directions around
		Vector2D v = new Vector2D((r.nextDouble()-0.5)*0.1,(r.nextDouble()-0.5)*0.1);
		
		return new Particle(Main.SPRITE_SHEET + "#smoke_cloud", pos,v,1000);
	}
	
	/**
	 * Increase GameTemp by 1 every 2 seconds (2000 milliseconds)
	 * @param deltaMs
	 * @return
	 */
	public int computeHeatValue(final long deltaMs, final int level, int curTemp) {
		heatWaveMs += deltaMs;
		
		if (heatWaveMs > 2000) {
			heatWaveMs = 0;
			curTemp += level;
		}
		
	    if (curTemp > 170) curTemp = 170;
	 
		return curTemp;
	}
	
	public void update(final long deltaMs) {
		timeMs += deltaMs;
		durationMs += deltaMs;
	}
}
