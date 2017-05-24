package asteroids.model.Programs;

import java.util.HashMap;
import java.util.List;

import asteroids.model.*;

public class Program{

	private List<Function> functions;
	private Statement body;
	private Ship ship;
	
	private HashMap<String, Expression<?>> variables = new HashMap<>();
	// Variables can be of any type
	// How to keep the type of a variable when first assigned?
	private HashMap<String, Statement> functionsMap = new HashMap<>();
	private HashMap <String, Expression<?>> parameters = new HashMap<>();
	// extra map met parameters
	
	public Program(List<Function> functions, Statement body) {
		setFunctions(functions);
		setBody(body);
		body.setProgram(this);
		for(Function function: functions) {
			function.setProgram(this);
			functionsMap.put(function.getFunctionname(), function.getBody());
		}
	}
	
	public List<Object> execute(Double duration) throws ClassNotFoundException{
		body.execute();
		// functions moeten pas uitgevoerd worden wanneer deze worden opgeroepen
	}
	// Met tijd uitvoeren

	public List<Function> getFunctions(){
		return this.functions;
	}
	
	public void setFunctions(List<Function> functions){
		this.functions = functions;
	}
	
	public Statement getBody(){
		return this.body;
	}
	
	public void setBody(Statement body){
		this.body = body;
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}

	public HashMap<String, Expression<?>> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Expression<?>> variables) {
		this.variables = variables;
	}

	public HashMap<String, Statement> getFunctionsMap() {
		return functionsMap;
	}

	public void setFunctionsMap(HashMap<String, Statement> fuctions) {
		this.functionsMap = fuctions;
	}

	public HashMap <String, Expression<?>> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap <String, Expression<?>> parameters) {
		this.parameters = parameters;
	}
	
}
