/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package co.elastic.apm.api;
 

enum NoopSpan implements Span {
    INSTANCE;

    
    @Override
    public Span setName(String name) {
        // noop
        return this;
    }

    
    @Override
    public Span setType(String type) {
        // noop
        return this;
    }

    
    @Deprecated
    @Override
    public Span addTag(String key, String value) {
        // noop
        return this;
    }

    
    @Deprecated
    @Override
    public Span addLabel(String key, String value) {
        return this;
    }

    
    @Deprecated
    @Override
    public Span addLabel(String key, Number value) {
        return this;
    }

    
    @Deprecated
    @Override
    public Span addLabel(String key, boolean value) {
        return this;
    }

    
    @Override
    public Span setLabel(String key, String value) {
        return this;
    }

    
    @Override
    public Span setLabel(String key, Number value) {
        return this;
    }

    
    @Override
    public Span setLabel(String key, boolean value) {
        return this;
    }

    @Override
    public void end() {
        // noop
    }

    @Override
    public void end(long epochMicros) {
        // noop
    }

    @Override
    public String captureException(Throwable throwable) {
        // co.elastic.apm.agent.pluginapi.CaptureExceptionInstrumentation
        return "";
    }

    
    @Override
    public String getId() {
        return "";
    }

    
    @Override
    public String getTraceId() {
        return "";
    }

    @Override
    public Scope activate() {
        return NoopScope.INSTANCE;
    }

    @Override
    public boolean isSampled() {
        return false;
    }

    
    @Override
    public Span createSpan() {
        // noop
        return this;
    }

    
    @Override
    public Span startSpan(String type,  String subtype,  String action) {
        return INSTANCE;
    }

    
    @Override
    public Span startExitSpan(String type, String subtype,  String action) {
        return INSTANCE;
    }

    
    @Override
    public Span startSpan() {
        // noop
        return this;
    }

    @Override
    public Span setStartTimestamp(long epochMicros) {
        // noop
        return this;
    }

    @Override
    public Span setOutcome(Outcome outcome) {
        // noop
        return this;
    }

    @Override
    public void injectTraceHeaders(HeaderInjector headerInjector) {
        // noop
    }

    
    @Override
    public Span setDestinationAddress( String address, int port) {
        return this;
    }

    
    @Override
    public Span setDestinationService( String resource) {
        return this;
    }

    
    @Override
    public Span setServiceTarget( String type,  String name) {
        return this;
    }

    @Override
    
    public Span setNonDiscardable() {
        return this;
    }
}
