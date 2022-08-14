package io.opentelemetry.contib.ejb;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import io.opentelemetry.api.OpenTelemetry;

public class OpenTelemetryUtils {
	public static boolean isInTransaction() {
		//TODO
		final Transaction transaction = ElasticApm.currentTransaction();
		final String clazzName = transaction.getClass().getSimpleName();
		  
		return OpenTelemetry.noop().equals(clazzName);
	}
}
