/*
 * Copyright 2008-2009 the original author or authors.
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
package org.more.submit;
/**
 * 该类负责提供{@link ActionFilter ActionFilter接口}的ActionInvoke接口形式。
 * @version 2009-12-1
 * @author 赵永春 (zyc@byshell.org)
 */
class FilterActionInvoke implements ActionInvoke {
    //========================================================================================Field
    private FilterChain filterChain = null;
    //==================================================================================Constructor
    public FilterActionInvoke(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
    //==========================================================================================Job
    @Override
    public Object invoke(ActionStack stack) throws Throwable {
        return filterChain.doInvokeFilter(stack);
    }
}