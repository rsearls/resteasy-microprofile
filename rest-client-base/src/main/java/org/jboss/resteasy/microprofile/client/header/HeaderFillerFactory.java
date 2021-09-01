/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2021 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.resteasy.microprofile.client.header;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * 2020-07-10
 */
public interface HeaderFillerFactory {
    /**
     * If multiple {@link HeaderFillerFactory}'s are defined, the one with the highest priority is used
     *
     * @return the priority
     */
    int getPriority();


    /**
     * Creates {@link HeaderFiller}, will be called once for each method with
     * {@link org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam} annotation
     *
     * @param value          value of the annotation
     * @param headerName     name of the header to generate
     * @param required       if true, a failure of header computation will fail the rest client invocation
     * @param interfaceClass JAX-RS interface class
     * @param clientProxy    proxy object
     *
     * @return an object that can generate the header value
     */
    HeaderFiller createFiller(String value,
                              String headerName,
                              boolean required,
                              Class<?> interfaceClass,
                              Object clientProxy);
}
