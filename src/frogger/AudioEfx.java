
package frogger;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import jig.engine.ResourceFactory;
import jig.engine.audio.AudioState;
import jig.engine.audio.jsound.AudioClip;
import jig.engine.audio.jsound.AudioStream;

/**
 * Controls the audio effects
 *
 */
public class AudioEfx {

	// These are referenced as to when to play the sound effects
	FroggerCollisionDetection fcd;
	Frogger frog;
	
	public Random random = new Random(System.currentTimeMillis());
	
	// Background sounds
	private AudioStream music;
	
	public static final String A_FX_PATH = Main.RSC_PATH + "ambient_fx/";
	
	public static AudioClip jump = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "jump.wav");
	
	public static AudioClip die = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "frog_die.ogg");	

	public static AudioClip goal = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "goal.ogg");
	
	public static AudioClip levGoal = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "level_goal.ogg");

	public static AudioClip wind = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "wind.ogg");
	
	public static AudioClip fire = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "match.ogg");
	
	public static AudioClip bonus = ResourceFactory.getFactory().getAudioClip(
			Main.RSC_PATH + "bonus.ogg");	
	
	public static AudioClip siren = ResourceFactory.getFactory().getAudioClip(
			A_FX_PATH + "siren.ogg");
	
	// one effect is randomly picked from road_effects or water_effects every couple of seconds
	private List<AudioClip> road_effects = new LinkedList<AudioClip>();
	private List<AudioClip> water_effects = new LinkedList<AudioClip>();
	
	private int effectsDelay = 3000;
	private int deltaT = 0;
	
	/**
	 * In order to know when to play-back certain effects, we track the state of 
	 * collision detector and Frogger
	 * @param f - Collision Dectection
	 * @param frg - Frogger
	 */
	public AudioEfx(FroggerCollisionDetection f, Frogger frg) {
		fcd = f;
		frog = frg;
		
		road_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "long-horn.ogg"));
	    road_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "car-pass.ogg"));
		road_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "siren.ogg"));

		water_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "water-splash.ogg"));
	    water_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "splash.ogg"));
		water_effects.add(ResourceFactory.getFactory().getAudioClip(A_FX_PATH + "frog.ogg"));

		music = new AudioStream(Main.RSC_PATH + "bg_music.ogg");
	}
	
	public void playGameMusic() {
	    music.loop(0.2, 0);
	}
	
	public void playCompleteLevel() {
		music.pause();
		levGoal.play(2.0);
	}
	
	public void playRandomAmbientSound(final long deltaMs) {
		deltaT += deltaMs;
		
		if (deltaT > effectsDelay && fcd.isOnRoad()) {
			deltaT = 0;
			road_effects.get(random.nextInt(road_effects.size())).play(0.2);
		}
		
		if (deltaT > effectsDelay && fcd.isInRiver()) {
			deltaT = 0;
			water_effects.get(random.nextInt(road_effects.size())).play(0.2);
		}
	}
	
	public void update(final long deltaMs) {
		playRandomAmbientSound(deltaMs);
		
		if (frog.isAlive && (music.getState() == AudioState.PAUSED))
			music.resume();
		
		if (!frog.isAlive && (music.getState() == AudioState.PLAYING))
			music.pause();	

	}

}
