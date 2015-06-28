package com.tmt.toomanythoughts.utils.dbunit;

import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class AutoCommitTransactionOperation extends DatabaseOperation {

	private final DatabaseOperation	_operation;

	public AutoCommitTransactionOperation(final DatabaseOperation operation) {
		this._operation = operation;
	}

	public static final DatabaseOperation AUTO_COMMIT_TRANSACTION(final DatabaseOperation operation) {
		return new AutoCommitTransactionOperation(operation);
	}

	@Override
	public void execute(final IDatabaseConnection connection,
	                    final IDataSet dataSet) throws DatabaseUnitException, SQLException {
		final IDatabaseConnection databaseConnection = connection;
		final Connection jdbcConnection = databaseConnection.getConnection();
		jdbcConnection.setAutoCommit(false);
		final boolean autoCommit = jdbcConnection.getAutoCommit();
		try {
			this._operation.execute(databaseConnection, dataSet);
			jdbcConnection.commit();
		} catch (final Exception e) {
			jdbcConnection.rollback();
			throw e;
		} finally {
			jdbcConnection.setAutoCommit(autoCommit);
		}
	}
}
