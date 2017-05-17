package asteroids.model.Programs;

import java.util.List;

import asteroids.model.*;

public class Program<F,S>{

	private List<F> functions;
	private S body;
	private Ship ship;

	public Program(List<F> functions, S body) {
		this.functions = functions;
		this.body = body;
	}
	
	public List<Object> execute(Double duration){
		// TODO
		return null;
	}

	public List<F> getFunctions(){
		return this.functions;
	}
	
	public void setFunctions(List<F> functions){
		this.functions = functions;
	}
	
	public S getBody(){
		return this.body;
	}
	
	public void setBody(S body){
		this.body = body;
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
}
