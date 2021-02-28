package claire.antiprocrastination.model;

import java.time.Duration;
import java.time.Instant;

import claire.antiprocrastination.common.Null;

/**
 * This class is simply a proxy for Instant.now() that allows the time to change for debugging purposes
 * 
 * @author Claire
 */
public class TemporalProxy {
	
	private static Instant test_now; 
	
	static {
		test_now = Null.nonNull(Instant.now());
	}
	
	/**
	 * @return The current time, or the simulated time if currently testing.
	 */
	public static final Instant now() 
	{
		return isTest() ? test_now : Null.nonNull(Instant.now());
	}
	
	/**
	 * Initializes this construct with a new, arbitrary time
	 */
	public static final void test_init()
	{
		test_now = Null.nonNull(Instant.now());
	}
	
	/**
	 * @param duration The amount of time to fastforward by
	 */
	public static final void test_fastforward(Duration duration)
	{
		test_now = Null.nonNull(test_now.plus(duration));
	}
	
	private static boolean isTest() 
	{  
	  for(StackTraceElement element : Thread.currentThread().getStackTrace()) 
	    if(element.getClassName().startsWith("org.junit.")) 
	      return true;
	  return false;
	}

}
