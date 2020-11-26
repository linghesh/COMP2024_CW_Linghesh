
package frogger;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import jig.engine.util.Vector2D;


public class GoalManager {
	
	final static int MAX_NUM_OF_GOALS = 6;
	
	private List<Goal> goals;
	private Random r;

	protected boolean showingBonus = false;
	
	private int bonusRateMs = 5000;
	private int bonusShowMs = 5000;
	private int dRMs = 0;
	private int dSMs = 0;
       
	public GoalManager() {
		goals = new LinkedList<Goal>();
		r = new Random(System.currentTimeMillis());
		init(1);
	}
	
	
	/**
	 * First level only has 2 goals
	 * Second - 4 goals
	 * All others have standard 6 goals
	 * @param level
	 */
	public void init(final int level) {
		
		goals.clear();
		
		//if (level < 3) {
			switch(level){
				case 1:
					goals.add(new Goal(new Vector2D(5*32,32)));
                                        break;
				case 2:
				default:
					goals.add(new Goal(new Vector2D(5*32,32)));
					goals.add(new Goal(new Vector2D(7*32,32)));
                                        
					break;
                                case 3:
                                        goals.add(new Goal(new Vector2D(5*32,32)));
					goals.add(new Goal(new Vector2D(7*32,32)));
					goals.add(new Goal(new Vector2D(3*32,32)));
                                        
					break;
                                case 4:
                                        goals.add(new Goal(new Vector2D(5*32,32)));
					goals.add(new Goal(new Vector2D(7*32,32)));
					goals.add(new Goal(new Vector2D(3*32,32)));
					goals.add(new Goal(new Vector2D(9*32,32)));
                                        
                                    break;
                                case 5:
                                        goals.add(new Goal(new Vector2D(5*32,32)));
					goals.add(new Goal(new Vector2D(7*32,32)));
					goals.add(new Goal(new Vector2D(3*32,32)));
					goals.add(new Goal(new Vector2D(9*32,32)));
                                        goals.add(new Goal(new Vector2D(1*32,32)));
                                     
                                    break;
                                case 6:
                                        goals.add(new Goal(new Vector2D(5*32,32)));
					goals.add(new Goal(new Vector2D(7*32,32)));
					goals.add(new Goal(new Vector2D(3*32,32)));
					goals.add(new Goal(new Vector2D(9*32,32)));
                                        goals.add(new Goal(new Vector2D(1*32,32)));
					goals.add(new Goal(new Vector2D(11*32,32)));
                                    break;
			}
			return;
		//}
		
		//for (int i=0; i<MAX_NUM_OF_GOALS; i++)
		//	goals.add(new Goal(i));		
	}
	
	/**
	 * 'goals' is a protected
	 * @return
	 */
	public List<Goal> get() {
		return goals;
	}
	
	/**
	 * getUnreached
	 * @return - list of goals currently haven't been reached
	 */
	public List<Goal> getUnreached() {
		List<Goal> l = new LinkedList<Goal>();
		for (Goal g : goals)
			if (!g.isReached)
				l.add(g);
		
		return l;		
	}
	
	/**
	 * Based on internal timer, display bonus at a goal
	 * that hasn't been reached for a duration specified by bonusShowMs
	 */
	public void doBonusCheck() {
		if (!showingBonus && dRMs > bonusRateMs) {
			dSMs = 0;
			showingBonus = true;
			List<Goal> l = getUnreached();
			l.get(r.nextInt(l.size())).setBonus(true);
		}
		
		if (showingBonus && dSMs > bonusShowMs) {
			dRMs = 0;
			showingBonus = false;
			for (Goal g : goals)
				if (!g.isReached)
					g.setBonus(false);		
		}		
	}
	
	public void update(long deltaMs) {
		dRMs += deltaMs;
		dSMs += deltaMs;
		doBonusCheck();
	}
}
