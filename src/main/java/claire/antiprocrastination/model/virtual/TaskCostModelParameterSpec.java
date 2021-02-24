package claire.antiprocrastination.model.virtual;

import java.util.Map;

/**
 * A specification for <code>TaskCostModelParameters</code>
 * 
 * @author Claire
 */
public interface TaskCostModelParameterSpec {
	
	/**
	 * @return A map from parameter name to parameter type
	 */
	Map<String, Class<?>> parameterDefinition();
	
	/**
	 * @return A map with a working set of defaults for this task cost model
	 */
	Map<String, Object> parameterDefaults();
	
	/**
	 * @param parameters A map of parameters that has the names and specified types from <code>parameterDefinition()</code>
	 * @return An object that represents these parameters
	 */
	TaskCostModelParameters newInstance(Map<String, Object> parameters);
	
	/**
	 * @param parameters A parameters object to test
	 * @return Whether or not that object matches this specification
	 */
	boolean isValid(TaskCostModelParameters parameters);

}
