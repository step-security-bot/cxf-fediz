/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cxf.fediz.core.config;

import java.io.File;
import java.io.Reader;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class FederationConfigurator {

    private FedizConfig rootConfig = null;

    private JAXBContext jaxbContext = null;

    public FedizConfig loadConfig(File f) throws JAXBException {
        rootConfig = (FedizConfig) getJaxbContext().createUnmarshaller()
                .unmarshal(f);
        return rootConfig;
    }

    public FedizConfig loadConfig(Reader reader) throws JAXBException {
        rootConfig = (FedizConfig) getJaxbContext().createUnmarshaller()
                .unmarshal(reader);
        return rootConfig;
    }

    public void saveConfiguration(File f) throws JAXBException {
        if (f.canWrite()) {
            jaxbContext.createMarshaller().marshal(rootConfig, f);
        }
    }

    public void saveConfiguration(Writer writer) throws JAXBException {
        jaxbContext.createMarshaller().marshal(rootConfig, writer);
    }

    private JAXBContext getJaxbContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(FedizConfig.class);
        }
        return jaxbContext;
    }

    public FederationContext getFederationContext(String contextName) {
        ContextConfig config = getContextConfig(contextName);
        if (config == null) {
            return null;
        }
        return new FederationContext(config);
    }

    public ContextConfig getContextConfig(String contextName)
            throws IllegalArgumentException {
        if (contextName == null || contextName.isEmpty()) {
            throw new IllegalArgumentException("Invalid Context Name '" + contextName + "'");
        }
        if (rootConfig == null) {
            throw new IllegalArgumentException("No configuration loaded");
        }

        for (ContextConfig config : rootConfig.getContextConfig()) {
            if (contextName.equals(config.getName())) {
                return config;
            }
        }
        return null;
    }

}
