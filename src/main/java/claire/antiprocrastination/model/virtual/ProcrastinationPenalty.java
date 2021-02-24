package claire.antiprocrastination.model.virtual;

/**
 * A penalty for procrastinating too much
 * 
 * @author Claire
 */
public interface ProcrastinationPenalty {

	/**
	 * @return A short description for this penalty
	 */
	String getPenaltyDescription();
	
	/**
	 * @return The maximum overheat this penalty can stand before triggering.
	 */
	double getMaximumOverheat();
	
}
