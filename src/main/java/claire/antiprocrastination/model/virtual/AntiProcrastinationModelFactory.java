package claire.antiprocrastination.model.virtual;

import claire.antiprocrastination.common.Null;

/**
 * A factory for <code>AntiProcrastinationModel</code>. Only one instance may exist
 * 
 * @author Claire
 */
public abstract class AntiProcrastinationModelFactory {
	
	private static final AntiProcrastinationModelFactory instance = Null.nonNull(null);
	
	/**
	 * @return The canonical instance of this factory
	 */
	public static final AntiProcrastinationModelFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * @return A new instance of <code>AntiProcrastinationModel</code>
	 */
	public abstract AntiProcrastinationModel newInstance();

}
