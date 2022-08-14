package co.elastic.apm.ejb;

import java.util.Optional;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;

/**
 * https://qiita.com/mkyz08/items/9ab164d47ff6cb16addb
 * **/
public class ElasticApmUtils {

	
    /**
     * Returns {@code true} if and only if the system property
     * named "elastic.apm.enabled" exists and is equal to the string
     * {@code "true"}. (Beginning with version 1.0.2 of the
     * Java<small><sup>TM</sup></small> platform, the test of
     * this string is case insensitive.) A system property is accessible
     * through {@code getProperty}, a method defined by the
     * {@code System} class.
     * <p>
     * If there is no property with the specified name, or if the specified
     * name is empty or null, then {@code false} is returned.
     * <p>
     * Refernce: https://www.elastic.co/guide/en/apm/agent/java/current/config-core.html#config-enabled
     * @param   name   the system property name.
     * @return  the {@code boolean} value of the system property.
     *          {@link System#getProperty(String) System.getProperty}
     * @see     java.lang.System#getProperty(java.lang.String)
     * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
	public static boolean isEnable() {
		final String value = Optional.ofNullable(System.getenv("ELASTIC_APM_ENABLED"))
				.orElse( Optional.ofNullable(System.getProperty("elastic.apm.enabled")) 
				.orElse("false"));	
		return Boolean.valueOf(value);
	}
	public static boolean isInTransaction() {
		final Transaction transaction = ElasticApm.currentTransaction();
		final String clazzName = transaction.getClass().getSimpleName();
		  
		return !"NoopTransaction".equals(clazzName);
	}
	/***
	 * https://www.elastic.co/guide/en/apm/agent/java/master/public-api.html
	 * ***/
	public static Transaction apmTransactionCdiContext(String className, String methodName) {
		if(!isInTransaction()) {
			final Transaction  transaction = ElasticApm.startTransaction();
			String name = className+"#"+methodName;
			transaction.setName(name);
			transaction.setType("EJB invoker");
			return transaction;
		}else {
			return ElasticApm.currentTransaction();
		}
	}

}
