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
package org.more.webui.lifestyle.phase;
import org.more.webui.context.ViewContext;
import org.more.webui.lifestyle.Phase;
import org.more.webui.lifestyle.PhaseID;
/**
 * ��6�׶Σ�����webui�¼���
 * @version : 2011-8-4
 * @author ������ (zyc@byshell.org)
 */
public class InvokeApplication_Phase extends Phase {
    public static class InvokeApplication_PhaseID extends PhaseID {
        public String getPhaseID() {
            return "InvokeApplication";
        };
    };
    public final static InvokeApplication_PhaseID PhaseID = new InvokeApplication_PhaseID();
    public PhaseID getPhaseID() {
        return PhaseID;
    };
    public void execute(ViewContext uiContext) throws Throwable {
        uiContext.getViewRoot().processApplication(uiContext);
    };
};