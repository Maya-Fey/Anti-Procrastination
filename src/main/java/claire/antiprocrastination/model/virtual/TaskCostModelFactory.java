/**
 * 
 */
package claire.antiprocrastination.model.virtual;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import claire.antiprocrastination.common.Null;

/**
 * An abstract factory definition for TaskCostModel
 * 
 * @author Claire
 */
public abstract class TaskCostModelFactory {
	
	private static final Map<String, TaskCostModelFactory> factories;
	
	static {
		factories = new HashMap<>();
		//TODO: Implement service loading
	}
	
	/**
	 * @param name The name of the task cost model to get a factory instance of
	 * @return The factory instance
	 * @throws Exception If no such model exists with that name
	 */
	public static final TaskCostModelFactory getInstance(String name) throws Exception
	{
		if(factories.containsKey(name))
			return factories.get(name);
		throw new Exception("placeholder");
	}
	
	/**
	 * @return An exhaustive set of all the model types accessible with <code>getInstance()</code>
	 */
	public static final Set<String> getModelTypes()
	{
		return Null.nonNull(factories.keySet());
	}
	
	/**
	 * @return The name of this task cost model type
	 */
	public abstract String getName();
	
	/**
	 * @return The parameter spec for this type of TaskCostModel
	 */
	public abstract TaskCostModelParameterSpec getParameterSpec();

	/**
	 * @param parameters The parameters for this task cost model instance
	 * @return A task cost model of this type and instance
	 */
	public abstract TaskCostModel newInstance(TaskCostModelParameters parameters);

}
