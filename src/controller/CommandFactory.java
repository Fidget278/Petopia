package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private static CommandFactory factory = null;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		map.put("/managerIndex.do", "controller.manager.ManagerCommand");
		map.put("/managerStatistics.do", "controller.statistics.statisticsFormCommand");
		
		
		map.put("/petopia.do", "controller.member.IntroCommand");
		map.put("/login.do", "controller.member.LoginCommand");
	}
	
	public static CommandFactory getInstance() {
		if(factory == null) 
			factory = new CommandFactory();
		
		return factory;
	}
	
	public Command createCommand(String commandURI) throws Exception {
		
		String commandClass = map.get(commandURI);
		System.out.println("fac : " + commandURI);
		if(commandClass == null)
			return null;
		
		System.out.println(commandClass);
		try {
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor();
			Command command = (Command)constructor.newInstance();
			
			return command;
		} catch(Exception e) {
			throw e;
		}

	}
}
