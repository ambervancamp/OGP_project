package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asteroids.model.*;
import asteroids.model.Programs.*;

public class Program{

	private List<Function> functions;
	private Statement body;
	private Ship ship;
	private Double executeTime = 0.0;
	private List<Object> printResults = new ArrayList<Object>();	
	private HashMap<String, Expression<?>> variables = new HashMap<>();
	private Boolean isBreaking = false;
	// Variables can be of any type
	// How to keep the type of a variable when first assigned?
	// How to define global/local variables, gets difficult when invoking functions and their
	// reachable variables.. See assignement
	
	//private HashMap<String, Statement> functionsMap = new HashMap<>();

	
	public Program(List<Function> functions, Statement body) {
		setFunctions(functions);
		setBody(body);
		body.setProgram(this);
		// Verwijzen nu alle statements in deze body ook naar deze program? -> in blockstatement ook verwijzingen
		for(Function function: functions) {
			function.setProgram(this);
			//functionsMap.put(function.getFunctionname(), function.getBody());
		}
	}
	
	public List<Object> execute(Double duration) throws ClassNotFoundException{
		this.setExecuteTime(this.getExecuteTime() + duration);
		body.execute();
		
		return printResults;
	}
	// functions moeten pas uitgevoerd worden wanneer deze worden opgeroepen
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

	public Double getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Double executeTime) {
		this.executeTime = executeTime;
	}

	public List<Object> getPrintResults() {
		return printResults;
	}

	public void setPrintResults(List<Object> printResults) {
		this.printResults = printResults;
	}

	public Boolean getIsBreaking() {
		return isBreaking;
	}

	public void setIsBreaking(Boolean isBreaking) {
		this.isBreaking = isBreaking;
	}

//	public HashMap<String, Statement> getFunctionsMap() {
//		return functionsMap;
//	}
//
//	public void setFunctionsMap(HashMap<String, Statement> fuctions) {
//		this.functionsMap = fuctions;
//	}
	
}
