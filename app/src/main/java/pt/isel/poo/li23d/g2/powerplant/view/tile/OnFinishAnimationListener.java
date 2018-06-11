package pt.isel.poo.li23d.g2.powerplant.view.tile;

/**
 * Interface to implemented by listeners of animations.
 * @author Palex
 */
public interface OnFinishAnimationListener {

	/**
	 * When animations are finished
     * @see Animator#triggerOnFinishAnimations
	 * @param tag Tag provided in triggerOnFinishAnimations()
	 */
	public void onFinish(Object tag);
}