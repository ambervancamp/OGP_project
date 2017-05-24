package asteroids.model;

import java.util.List;

import asteroids.model.Programs.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Function, Program> {
		
	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body,
			SourceLocation sourceLocation) {
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, (ConstantExpression)value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression)condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression<?> value, SourceLocation sourceLocation) {
		return new ReturnStatement(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfThenElseStatement((BooleanExpression) condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement((ConstantExpression)value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new BlockStatement(statements, sourceLocation);
	}

	@Override
	public Expression<?> createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new VariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression<?> createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public Expression<?> createFunctionCallExpression(String functionName, List<Expression<?>> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionInvocationExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression<?> createChangeSignExpression(Expression<?> expression, SourceLocation sourceLocation) {
		return new ChangeSignExpression((ConstantExpression)expression, sourceLocation);
	}

	@Override
	public Expression<?> createNotExpression(Expression<?> expression, SourceLocation sourceLocation) {
		return new LogicalNegationExpression((BooleanExpression) expression, sourceLocation);
	}

	@Override
	public Expression<?> createDoubleLiteralExpression(double value, SourceLocation location) {
		return new ConstantExpression(value, location);
	}

	@Override
	public Expression<?> createNullExpression(SourceLocation location) {
		return new NullExpression(location);
	}

	@Override
	public Expression<?> createSelfExpression(SourceLocation location) {
		return new SelfExpression(location);
	}

	@Override
	public Expression<?> createShipExpression(SourceLocation location) {
		return new ShipExpression(location);
	}

	@Override
	public Expression<?> createAsteroidExpression(SourceLocation location) {
		return new AsteroidExpression(location);
	}

	@Override
	public Expression<?> createPlanetoidExpression(SourceLocation location) {
		return new PlanetoidExpression(location);
	}

	@Override
	public Expression<?> createBulletExpression(SourceLocation location) {
		return new BulletExpression(location);
	}

	@Override
	public Expression<?> createPlanetExpression(SourceLocation location) {
		return new PlanetExpression(location);
	}

	@Override
	public Expression<?> createAnyExpression(SourceLocation location) {
		return new AnyExpression(location);
	}

	@Override
	public Expression<?> createGetXExpression(Expression<?> e, SourceLocation location) {
		return new GetXExpression((EntityExpression<?>)e, location);
	}

	@Override
	public Expression<?> createGetVXExpression(Expression<?> e, SourceLocation location) {
		return new GetVXExpression((EntityExpression<?>) e, location);
	}

	@Override
	public Expression<?> createGetVYExpression(Expression<?> e, SourceLocation location) {
		return new GetVYExpression((EntityExpression<?>) e, location);
	}

	@Override
	public Expression<?> createGetRadiusExpression(Expression<?> e, SourceLocation location) {
		return new GetRadiusExpression((EntityExpression<?>) e, location);
	}

	@Override
	public Expression<?> createLessThanExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new LessThanExpression((ConstantExpression)e1, (ConstantExpression)e2, location);
	}
	
	@Override
	public Expression<?> createEqualityExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new EqualToExpression(e1, e2, location);
	}

	@Override
	public Expression<?> createGetYExpression(Expression<?> e, SourceLocation location) {
		return new GetYExpression((EntityExpression<?>) e, location);
	}

	@Override
	public Expression<?> createAdditionExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new AdditionExpression((ConstantExpression)e1, (ConstantExpression)e2, location);
	}

	@Override
	public Expression<?> createMultiplicationExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new MultiplicationExpression((ConstantExpression)e1, (ConstantExpression)e2, location);
	}

	@Override
	public Expression<?> createSqrtExpression(Expression<?> e, SourceLocation location) {
		return new SqrtExpression((ConstantExpression)e, location);
	}

	@Override
	public Expression<?> createGetDirectionExpression(SourceLocation location) {
		return null;
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ThrustOnStatement(location);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ThrustOffStatement(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new ShootStatement(location);
	}

	@Override
	public Statement createTurnStatement(Expression<?> angle, SourceLocation location) {
		return new TurnStatement((ConstantExpression)angle, location);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new SkipStatement(location);
	}

}
