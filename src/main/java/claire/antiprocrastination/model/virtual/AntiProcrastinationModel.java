package claire.antiprocrastination.model.virtual;

import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import claire.antiprocrastination.common.Null;

/**
 * The core model for anti-procrastination. Contains everything required to use it as an application.
 * 
 * @author Claire
 */
public interface AntiProcrastinationModel extends Iterable<ProcrastinatingTask> {

	/**
	 * @return The total cost of all unresolved tasks in this model
	 */
	default double getTotalCost()
	{
		double total = 0.0;
		for(ProcrastinatingTask task : this)
			total += task.getTaskCost();
		return total;
	}
	
	/**
	 * @param duration The duration in the future to check for
	 * @return The total costs of all currently unresolved tasks if they're still unresolved in duration time in the future
	 */
	default double getFutureTotalCost(Duration duration)
	{
		double total = 0.0;
		for(ProcrastinatingTask task : this)
			total += task.getFutureTaskCost(duration);
		return total;
	}
	
	/**
	 * @return The level of overheat, that is to say, the amount of cost that exceeds the procrastination limit
	 */
	default double getOverheat()
	{
		return Math.max(0.0, this.getTotalCost() - this.getProcrastinationLimit());
	}
	
	/**
	 * @param duration The duration in the future to check for
	 * @return The level of overheat in duration time in the future, assuming no tasks are added or resolved
	 */
	default double getOverheat(Duration duration)
	{
		return Math.max(0.0, this.getFutureTotalCost(duration) - this.getProcrastinationLimit());
	}
	
	/**
	 * @return The limit of procrastination cost this model can take before applying penalties
	 */
	double getProcrastinationLimit();
	
	/**
	 * @param limit The desired procrastination limit
	 */
	void setProcrastinationLimit(int limit);
	
	/**
	 * @return A list of active penalties at this level of overheat
	 */
	default List<ProcrastinationPenalty> activePenalties()
	{
		List<ProcrastinationPenalty> list = this.possiblePenalties().stream().filter((penalty) -> { 
				return this.getOverheat() > penalty.getMaximumOverheat(); 
			}).collect(Collectors.<ProcrastinationPenalty>toList());
		return Null.nonNull(list);
	}
	
	/**
	 * @return A list of all possible procrastination penalties
	 */
	List<ProcrastinationPenalty> possiblePenalties();
	
	/**
	 * @param penalty The penalty to add
	 */
	void addPossiblePenalty(ProcrastinationPenalty penalty);
	
	/**
	 * @param task A task with a unique ID
	 */
	void addTask(ProcrastinatingTask task);
	
	/**
	 * @param prefix The prefix to get a new unique number for
	 * @return A new unique number for that prefix
	 */
	default int newUniqueTaskNumber(String prefix)
	{
		int highest = 0;
		for(ProcrastinatingTask task : this.getAllTasks(prefix))
			if(task.getTaskNumber() > highest)
				highest = task.getTaskNumber();
		return highest + 1;
	}
	
	/**
	 * @return A set of currently used task prefixes
	 */
	Set<String> prefixes();
	
	/**
	 * @return An iterator across all <b>unsolved</b> tasks tracked by this model
	 */
	@Override
	Iterator<ProcrastinatingTask> iterator();
	
	/**
	 * @return An unmodifiable collection of all unsolved tasks
	 */
	Collection<ProcrastinatingTask> getUnsolvedTasks();
	
	/**
	 * @return An unmodifiable collection of all solved tasks
	 */
	Collection<ProcrastinatingTask> getSolvedTasks();
	
	/**
	 * @return An unmodifiable collection of all tasks
	 */
	Collection<ProcrastinatingTask> getAllTasks();
	
	/**
	 * @param prefix The prefix to search for (exact match)
	 * @return An unmodifiable collection of all unsolved tasks with that prefix
	 */
	Collection<ProcrastinatingTask> getUnsolvedTasks(String prefix);
	
	/**
	 * @param prefix The prefix to search for (exact match)
	 * @return An unmodifiable collection of all solved tasks with that prefix
	 */
	Collection<ProcrastinatingTask> getSolvedTasks(String prefix);
	
	/**
	 * @param prefix The prefix to search for (exact match)
	 * @return An unmodifiable collection of all tasks with that prefix
	 */
	Collection<ProcrastinatingTask> getAllTasks(String prefix);
	
}
