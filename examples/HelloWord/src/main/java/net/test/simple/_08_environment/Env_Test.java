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
package net.test.simple._08_environment;
import net.hasor.core.AppContext;
import net.hasor.core.Environment;
import net.hasor.core.context.StandardAppContext;
import org.junit.Test;
/**
 * ��������������ʾ
 * @version : 2013-8-11
 * @author ������ (zyc@hasor.net)
 */
public class Env_Test {
    private static String config = "net/test/simple/_08_environment/env-config.xml";
    @Test
    public void testStandardEnvironmentVar() throws Exception {
        System.out.println("--->>testSimpleEnvironment<<--");
        AppContext app = new StandardAppContext(config);
        Environment env = app.getEnvironment();
        //
        //JAVA_HOME
        System.out.println(env.getEnvVar("JAVA_HOME"));
        //HASOR_WORK_HOME
        System.out.println(env.getEnvVar("HASOR_WORK_HOME"));
        //javac.exe
        System.out.println(env.evalString("%JAVA_HOME%/bin/javac.exe"));
        //notepad
        System.out.println(env.evalEnvVar("notepad"));
    }
}