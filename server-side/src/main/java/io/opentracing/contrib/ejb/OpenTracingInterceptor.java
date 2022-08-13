package io.opentracing.contrib.ejb;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * https://www.elastic.co/blog/distributed-tracing-opentracing-and-elastic-apm<br/>
 * https://opentelemetry.io/docs/reference/specification/compatibility/opentracing/<br/>
 * https://www.elastic.co/guide/en/apm/agent/java/current/public-api.html<br/>
 * https://www.elastic.co/guide/en/apm/guide/7.17/apm-distributed-tracing.html<br/>
 * https://www.elastic.co/guide/en/apm/agent/rum-js/master/custom-transactions.html<br/>
 * */
@Interceptor
public class OpenTracingInterceptor implements Serializable {

    public static final String SPAN_CONTEXT = "__opentracing_span_context";
    private static final Logger log = Logger.getLogger(OpenTracingInterceptor.class.getName());

    @AroundInvoke
    public Object wrap(InvocationContext ctx) throws Exception {
        if (!GlobalTracer.isRegistered()) { 
            log.fine("GlobalTracer is not registered. Skipping.");
            return ctx.proceed();
        } 
        Tracer tracer = GlobalTracer.get();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan(ctx.getMethod().getName());
        spanBuilder.withTag(Tags.COMPONENT.getKey(), getComponent());
        spanBuilder.withTag(getBeanTagName(), ctx.getTarget().getClass().getName());
 
        int contextParameterIndex = -1;
        for (int i = 0; i < ctx.getParameters().length; i++) { 
            Object parameter = ctx.getParameters()[i];
            if (parameter instanceof SpanContext) {
                log.fine("Found parameter as span context. Using it as the parent of this new span");
                spanBuilder.asChildOf((SpanContext) parameter);
                contextParameterIndex = i;
                break;
            }

            if (parameter instanceof Span) {
                log.fine("Found parameter as span. Using it as the parent of this new span");
                spanBuilder.asChildOf((Span) parameter);
                contextParameterIndex = i;
                break;
            }
        } 
        if (contextParameterIndex < 0) {
            log.fine("No parent found. Trying to get span context from context data");
            Object ctxParentSpan = ctx.getContextData().get(SPAN_CONTEXT);
            if (ctxParentSpan != null && ctxParentSpan instanceof SpanContext) {
                log.fine("Found span context from context data.");
                SpanContext parentSpan = (SpanContext) ctxParentSpan;
                spanBuilder.asChildOf(parentSpan);
            }
        } 
        Span span = spanBuilder.start();
        try (Scope scope = tracer.activateSpan(span)) {
            log.fine("Adding span context into the invocation context.");
            ctx.getContextData().put(SPAN_CONTEXT, span.context());

            if (contextParameterIndex >= 0) {
                log.fine("Overriding the original span context with our new context.");
                ctx.getParameters()[contextParameterIndex] = span.context();
            }

            return ctx.proceed();
        }
        finally {
            span.finish();
        }
    }

    public String getComponent() {
        return "ejb";
    }

    public String getBeanTagName() {
        return getComponent() + ".bean";
    }
}
