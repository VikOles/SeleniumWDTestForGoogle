package lib;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Log extends Logger {

	protected Log(String name) {
		super(name);
	}

	public static ArrayList<String> sessionForLog = new ArrayList<String>();

	public static String getCallerClassName() {
		StackTraceElement[] elementList = Thread.currentThread().getStackTrace();
		for (int i = 1; i < elementList.length; i++) {
			StackTraceElement element = elementList[i];
			if (element.getClassName().indexOf("java.lang.Thread") != 0
					&& !element.getClassName().equals(Log.class.getName())) {
				return element.getClassName();
			}
		}
		return null;
	}

	public static void info(String outputMessage) {
		sessionForLog.add(outputMessage);
		String className = getCallerClassName();
		Logger LOG = Logger.getLogger(className);
		LOG.info(outputMessage);
	}
}
