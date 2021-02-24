package claire.antiprocrastination.model.virtual;

import java.util.Map;

/**
 * A object that represents parameters for a task cost model
 * 
 * @author Claire
 */
public interface TaskCostModelParameters {
	
	/**
	 * @return A map of the parameter values
	 */
	Map<String, Object> values();

}
