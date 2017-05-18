package asteroids.model.Programs;

import java.util.HashMap;
import java.util.List;

import asteroids.model.*;

public class Program{

	private List<Function> functions;
	private Statement body;
	private Ship ship;
	private HashMap<String, Expression> variables = new HashMap<>();
	private HashMap<String, Statement> fuctions = new HashMap<>(); 

	public Program(List<Function> functions, Statement body) {
		setFunctions(functions);
		setBody(body);
		body.setProgram(this);
		for(Function function: functions) function.setProgram(this);
	}
	
	public List<Object> execute(Double duration){
		// TODO
		return null;
	}

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

	public HashMap<String, Expression> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Expression> variables) {
		this.variables = variables;
	}

	public HashMap<String, Statement> getFuctions() {
		return fuctions;
	}

	public void setFuctions(HashMap<String, Statement> fuctions) {
		this.fuctions = fuctions;
	}
	
}
