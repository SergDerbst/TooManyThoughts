package com.tmt.toomanythoughts.utils.dbunit;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.operation.DefaultDatabaseOperationLookup;

public class AutoCommitTransactionDatabaseLookup extends DefaultDatabaseOperationLookup {

	@Override
	public org.dbunit.operation.DatabaseOperation get(final DatabaseOperation operation) {
		if (operation == DatabaseOperation.CLEAN_INSERT) {
			return AutoCommitTransactionOperation.AUTO_COMMIT_TRANSACTION(org.dbunit.operation.DatabaseOperation.CLEAN_INSERT);
		}
		return super.get(operation);
	}
}
