package claire.antiprocrastination.model.virtual;

import java.time.Duration;

/**
 * A model that computes the 'cost' of a task being unresolved
 * 
 * @author Claire
 */
public interface TaskCostModel {
	
	/**
	 * @param duration How long the task has remained unresolved
	 * @return The cost value of a task of this type this long unattended
	 */
	double computeCost(Duration duration);
	
	/**
	 * @return The simple name for this model, with no arguments
	 */
	String getSimpleName();
	
	/**
	 * @return The name for this model, plus any parameters
	 */
	String getDescription();

}
