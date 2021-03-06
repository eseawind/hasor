/*
 * Copyright 2008-2009 the original ������(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hasor.test.web.restful.interceptor;
import net.hasor.plugins.restful.interceptor.RestfulInterceptor;
import net.hasor.plugins.restful.interceptor.RestfulInvocation;
/**
 * 
 * @version : 2013-9-26
 * @author ������(zyc@hasor.net)
 */
public class TestRestfulInterceptor extends RestfulInterceptor {
    public Object invoke(RestfulInvocation invocation) throws Throwable {
        try {
            System.out.println("before Restful");
            return invocation.proceed();
        } catch (Exception e) {
            System.out.println("error Restful");
            throw e;
        } finally {
            System.out.println("after Restful");
        }
    }
}