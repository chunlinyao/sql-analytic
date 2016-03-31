package com.github.sql.analytic.transform;

import com.github.sql.analytic.expression.Expression;
import com.github.sql.analytic.schema.Table;
import com.github.sql.analytic.statement.Statement;
import com.github.sql.analytic.statement.delete.Delete;


public class DeleteTransform {

	private StatementTransform statementTransform;
	private Table table;

	public DeleteTransform(StatementTransform statementTransform) {
		super();
		this.statementTransform = statementTransform;
	}

	public Statement transform(Delete delete) {

		Delete newDelete = new Delete();
		newDelete.setTable(statementTransform.copy(delete.getTable()));	
		setTable(newDelete.getTable());
		newDelete.setWhere(transformWhere(delete.getWhere()));

		return newDelete;
	}

	protected Expression transformWhere(Expression where) {		
		if(where != null){
			return statementTransform.transform(where);
		}else {
			return null;
		}
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
