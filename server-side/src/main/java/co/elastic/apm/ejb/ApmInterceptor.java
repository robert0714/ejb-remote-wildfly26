package co.elastic.apm.ejb;
 
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
 
import co.elastic.apm.api.Transaction;

/**
 * https://opentelemetry.io/docs/reference/specification/compatibility/opentracing/<br/>
 * https://www.elastic.co/guide/en/apm/agent/java/current/public-api.html<br/>
 * https://www.elastic.co/guide/en/apm/guide/7.17/apm-distributed-tracing.html
 * https://www.elastic.co/guide/en/apm/agent/rum-js/master/custom-transactions.html
 * https://github.com/elastic/apm-agent-java
 * http://www.mastertheboss.com/jbossas/wildfly-8/how-to-debug-remotely-wildfly/
 * */
@Interceptor
public class ApmInterceptor implements Serializable {
	private static final Logger log = Logger.getLogger(ApmInterceptor.class.getName());

    @AroundInvoke
    public Object wrap(InvocationContext ctx) throws Exception {
        if (!ElasticApmUtils.isEnable()) {
            log.fine("elastic.apm.enabled is null or false");
            System.out.println("elastic.apm.enabled is null or false");
            return ctx.proceed();
        } 
        System.out.println("elastic.apm.enabled is true");
        // TODO:  Enable over System property
        Method method = ctx.getMethod();
        String className = ctx.getMethod().getDeclaringClass().getCanonicalName();
        String methodName = method.getName();
        
        boolean isInTransaction = ElasticApmUtils.isInTransaction();
        final Transaction transaction = ElasticApmUtils.apmTransactionCdiContext(className, methodName); 
        
        
        
        log.fine(className + "#" + methodName + " isInTransaction " + isInTransaction);
        // TODO vuru 29.04.2019: Make abstraction
         if (isInTransaction) {
             boolean errorOccured = false;
             final  co.elastic.apm.api.Span span = transaction
                     .startSpan("remote-EJB", className, methodName)
                     .setName(className + "#" + methodName);
             
             try ( co.elastic.apm.api.Scope scope = span.activate()){
                 final Object proceed = ctx.proceed();
                 return proceed;
             } catch (Exception e) {
                 span.captureException(e);
                 errorOccured = true;
                 throw e;
             } finally {
                 span.end();
                 if(errorOccured ){
                     transaction.setResult("Failed");
                 } else {
                     transaction.setResult("Ok");
                 }
             }
         } else {
             boolean errorOccured = false;
             try (co.elastic.apm.api.Scope scope = transaction.activate()) {
            	 
            	 
                 final Object proceed = ctx.proceed();
                 return proceed;
             } catch (Exception e) {
                 transaction.captureException(e);
                 errorOccured = true;
                 throw e;
             } finally {
                 transaction.end();
                 if(errorOccured ){
                     transaction.setResult("Failed");
                 } else {
                     transaction.setResult("Ok");
                 }
             }
         }
    } 
}
