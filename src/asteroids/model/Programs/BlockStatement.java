package asteroids.model.Programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class BlockStatement extends Statement{
	
	private List<Statement> statements;
	
	public BlockStatement(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setStatements(statements);
	}

	@Override
	public void execute() {
		for (Statement statement: this.getStatements())
			statement.execute();
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
