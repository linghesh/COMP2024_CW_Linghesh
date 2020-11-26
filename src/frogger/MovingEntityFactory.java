
package frogger;
import jig.engine.util.Vector2D;

import java.util.Random;

public class MovingEntityFactory {
	
	public static int  CAR   = 0;
	public static int  TRUCK = 1;
	public static int  SLOG  = 2;
	public static int  LLOG  = 3;
	
	public Vector2D position;
	public Vector2D velocity;
        private int speed=1;
	
	public Random r;
	
	private long updateMs = 0;
	private long copCarDelay = 0;
	
	private long rateMs = 1000;

	private int padding = 64; // distance between 2 objects in a traffic/river line
	
	private int[] creationRate = new int[4];	

    MovingEntityFactory() {
        }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
	
	/**
	 * Moving Entity factory
	 * 
	 * @param pos
	 * @param v
	 * @param rate
	 */
        
       
	public MovingEntityFactory(Vector2D pos, Vector2D v) {
		position = pos;
		velocity = v;
		r = new Random(System.currentTimeMillis());

		creationRate[CAR]   = (int) Math.round(((Car.LENGTH) + padding + 32) / 
				Math.abs(velocity.getX()));
		creationRate[TRUCK] = (int) Math.round(((Truck.LENGTH) + padding + 32) / 
				Math.abs(velocity.getX()));
		creationRate[SLOG]  = (int) Math.round(((ShortLog.LENGTH) + padding - 32) / 
				Math.abs(velocity.getX()));
		creationRate[LLOG]  = (int) Math.round(((LongLog.LENGTH) + padding - 32) / 
				Math.abs(velocity.getX()));
	}
	
	/**
	 * Building basic moving object {car, truck, short log, long log}
	 * @param type - {CAR, TRUCK, SLOG, LLOG}
	 * @param chance - of production (n out of 100)
	 * @return MovingEntity on chance of success, otherwise return null
	 * chance gives some holes in the production pattern, looks better.
	 */
	public MovingEntity buildBasicObject(int type, int chance) {
		if (updateMs > rateMs) {
			updateMs = 0;
                        int randSpeed = r.nextInt(20);
			
			if (r.nextInt(100) < chance)			
				switch(type) {
					case 0: // CAR
						rateMs = creationRate[CAR];
						return new Car(position, velocity.scale(speed), r.nextInt(Car.TYPES));
					case 1: // TRUCK
						rateMs = creationRate[TRUCK];
						return new Truck(position, velocity.scale(speed));
					case 2: // SLOG
						rateMs = creationRate[SLOG];
						return new ShortLog(position, velocity.scale(speed));
					case 3: // LLOG
						rateMs = creationRate[LLOG];
						return new LongLog(position, velocity.scale(speed));
					default:
						return null;
				}
		}
		
		return null;
	}
	
	public MovingEntity buildShortLogWithTurtles(int chance) {
            int randSpeed = r.nextInt(20);
		MovingEntity m = buildBasicObject(SLOG,80);
		if (m != null && r.nextInt(100) < chance)
			return new Turtles(position, velocity.scale(speed), r.nextInt(2));
		return m;
	}
	
	/**
	 * Long Tree Logs with a some chance of Crocodile!
	 * @return
	 */
	public MovingEntity buildLongLogWithCrocodile(int chance) {
            int randSpeed = r.nextInt(20);
		MovingEntity m = buildBasicObject(LLOG,80);
		if (m != null && r.nextInt(100) < chance)
			return new Crocodile(position, velocity.scale(speed));
		return m;
	}

	/**
	 * Cars appear more often than trucks
	 * If traffic line is clear, send a fast CopCar!
	 * @return
	 */
	public MovingEntity buildVehicle() {
		
		// Build slightly more cars that trucks
		MovingEntity m = r.nextInt(100) < 80 ? buildBasicObject(CAR,50) : buildBasicObject(TRUCK,50);

		if (m != null) {
			
			/* If the road line is clear, that is there are no cars or truck on it
			 * then send in a high speed cop car
			 */
			if (Math.abs(velocity.getX()*copCarDelay) > Main.WORLD_WIDTH) {
				copCarDelay = 0;
				return new CopCar(position, velocity.scale(speed+5));
			}
			copCarDelay = 0;
		}
		return m;
	}
	
	public void update(final long deltaMs) {
		updateMs += deltaMs;
		copCarDelay += deltaMs;
	}
}