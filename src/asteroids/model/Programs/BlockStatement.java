package asteroids.model.Programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class BlockStatement extends Statement{
	
	private List<Statement> statements;
	
	public BlockStatement(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		setStatements(statements);
		for(Statement statement: statements){
			statement.setProgram(this.getProgram());
			statement.setFunction(this.getFunction());
		}
	}

	@Override
	public void execute() throws ClassNotFoundException {
		for (Statement statement: this.getStatements()){
//			statement.setProgram(this.getProgram());
//			statement.setFunction(this.getFunction());
			statement.execute();
		}
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
