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
 

enum NoopTransaction implements Transaction {

    INSTANCE;

    
    @Override
    public Transaction setName(String name) {
        // noop
        return this;
    }



    
    @Override
    public Transaction setType(String type) {
        // noop
        return this;
    }

    
    @Override
    public Transaction setFrameworkName(String frameworkName) {
        // noop
        return this;
    }

    
    @Override
    public Transaction setServiceInfo(String serviceName, String serviceVersion) {
        // noop
        return this;
    }

    
    @Override
    public Transaction useServiceInfoForClassLoader(ClassLoader classLoader) {
        // noop
        return this;
    }

    
    @Deprecated
    @Override
    public Transaction addTag(String key, String value) {
        // noop
        return this;
    }

    
    @Deprecated
    @Override
    public Transaction addLabel(String key, String value) {
        return this;
    }

    
    @Deprecated
    @Override
    public Transaction addLabel(String key, Number value) {
        return this;
    }

    
    @Deprecated
    @Override
    public Transaction addLabel(String key, boolean value) {
        return this;
    }

    
    @Override
    public Transaction setLabel(String key, String value) {
        return this;
    }

    
    @Override
    public Transaction setLabel(String key, Number value) {
        return this;
    }

    
    @Override
    public Transaction setLabel(String key, boolean value) {
        return this;
    }

    
    @Override
    public Transaction addCustomContext(String key, String value) {
        return this;
    }

    
    @Override
    public Transaction addCustomContext(String key, Number value) {
        return this;
    }

    
    @Override
    public Transaction addCustomContext(String key, boolean value) {
        return this;
    }

    @Override
    public Transaction setUser(String id, String email, String username) {
        // noop
        return this;
    }

    @Override
    public Transaction setUser(String id, String email, String username, String domain) {
        // noop
        return this;
    }

    @Override
    public Transaction setResult(String result) {
        // noop
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
    public String ensureParentId() {
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
        return NoopSpan.INSTANCE;
    }

    
    @Override
    public Span startSpan(String type,  String subtype,  String action) {
        return NoopSpan.INSTANCE;
    }

    
    @Override
    public Span startExitSpan(String type, String subtype,  String action) {
        return NoopSpan.INSTANCE;
    }

    
    @Override
    public Span startSpan() {
        return NoopSpan.INSTANCE;
    }

    @Override
    public Transaction setStartTimestamp(long epochMicros) {
        return this;
    }

    @Override
    public Transaction setOutcome(Outcome outcome) {

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

    
    public Span setNonDiscardable() {
        return this;
    }
}
