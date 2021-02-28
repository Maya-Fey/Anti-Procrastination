package claire.antiprocrastination.model.virtual;

import java.time.Duration;
import java.time.Instant;

import claire.antiprocrastination.common.Null;
import claire.antiprocrastination.model.TemporalProxy;

/**
 * Virtual representation of a task being procrastinated on.
 * 
 * @author Claire
 */
public interface ProcrastinatingTask {

	/**
	 * @return A string of length 1-6 following the pattern [A-Z]+, representing the task prefix/group of this procrastinating task
	 */
	String getTaskPrefix();
	
	/**
	 * @return A positive integer that is unique to all procrastinating tasks sharing this prefix. All tasks with a number less
	 * than this value were created earlier, and the ones more later.
	 */
	int getTaskNumber();
	
	/**
	 * @return A string of english text that describes this task. Less than 150 characters.
	 */
	String getTaskTitle();
	
	/**
	 * @return A string of english text providing additional information. Can be any length
	 */
	String getTaskDescription();
	
	/**
	 * @return The exact time this task was registered. Guaranteed to be <= Instant.now()
	 */
	Instant getTaskCreationDate();
	
	/**
	 * @return How long this task has existed
	 */
	default Duration getTaskAge()
	{
		return Null.nonNull(Duration.between(this.getTaskCreationDate(), TemporalProxy.now()));
	}
	
	/**
	 * @return The cost model for this task
	 */
	TaskCostModel getTaskModel();
	
	/**
	 * @return The cost for this task in the present moment
	 */
	default double getTaskCost()
	{
		return this.isResolved() ? 0 : this.getTaskModel().computeCost(this.getTaskAge());
	}
	
	/**
	 * @param future The amount of time from now to use
	 * @return The cost of this task if it were still unresolved for at a certain time in the future 
	 */
	default double getFutureTaskCost(Duration future)
	{
		return this.getTaskModel().computeCost(Null.nonNull(this.getTaskAge().plus(future)));
	}
	
	/**
	 * @return The cost of this task if it were still unresolved exactly 24 hours from now 
	 */
	default double getTaskCostTomorrow()
	{
		return this.getFutureTaskCost(Null.nonNull(Duration.ofDays(1)));
	}
	
	/**
	 * @return Whether or not this task has been resolved
	 */
	boolean isResolved();
	
	/**
	 * Sets whether this task is resolved or not
	 * @param resolved Whether you want it to be resolved or not
	 */
	void setResolved(boolean resolved);
}
