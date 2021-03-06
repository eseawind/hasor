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
package net.hasor.web.jstl.tagfun;
import net.hasor.core.AppContext;
import net.hasor.web.startup.RuntimeListener;
import org.more.util.StringUtils;
import com.google.inject.Provider;
/**
 * 
 * @version : 2013-12-24
 * @author ������(zyc@hasor.net)
 */
public class Functions {
    protected static AppContext getAppContext() {
        AppContext appContext = RuntimeListener.getLocalAppContext();
        if (appContext != null)
            return appContext;
        throw new NullPointerException("AppContext is undefined.");
    }
    //
    public static Object defineBean(String defineBean) {
        if (StringUtils.isBlank(defineBean))
            return null;
        return getAppContext().getBean(defineBean);
    }
    public static Object defineType(String className) throws ClassNotFoundException {
        Class<?> defineType = Class.forName(className);
        return getAppContext().getInstance(defineType);
    }
    public static Object defineBinding(String name, String bindingType) throws ClassNotFoundException {
        if (StringUtils.isBlank(name))
            return null;
        if (StringUtils.isBlank(bindingType))
            return null;
        Class<?> defineType = Class.forName(bindingType);
        //
        Provider<?> provider = getAppContext().findProviderByType(name, defineType);
        if (provider != null)
            return provider.get();
        return null;
    }
    public static boolean hasBean(String defineBean) {
        if (StringUtils.isBlank(defineBean))
            return false;
        String[] names = getAppContext().getBeanNames();
        for (String ns : names)
            if (ns.equals(defineBean))
                return true;
        return false;
    }
    public static boolean hasBinding(String name, String className) throws ClassNotFoundException {
        if (StringUtils.isBlank(name))
            return false;
        Class<?> defineType = Class.forName(className);
        //
        Provider<?> provider = getAppContext().findProviderByType(name, defineType);
        return provider != null;
    }
}