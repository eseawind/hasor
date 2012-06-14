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
package org.more.webui.render;
import java.io.IOException;
import java.io.Writer;
import org.more.webui.context.ViewContext;
import org.more.webui.support.UIComponent;
import org.more.webui.tag.TemplateBody;
import freemarker.template.TemplateException;
/**
 * 不进行渲染
 * @version : 2012-5-18
 * @author 赵永春 (zyc@byshell.org)
 */
public class NoRender<T extends UIComponent> implements Render<T> {
    /**开始渲染组建*/
    @Override
    public void beginRender(ViewContext viewContext, T component, TemplateBody arg3, Writer writer) throws IOException {
        // TODO Auto-generated method stub
    }
    /**进行渲染*/
    @Override
    public void render(ViewContext viewContext, T component, TemplateBody arg3, Writer writer) throws IOException, TemplateException {
        // TODO Auto-generated method stub
    }
    /**组建渲染结束*/
    @Override
    public void endRender(ViewContext viewContext, T component, TemplateBody arg3, Writer writer) throws IOException {
        // TODO Auto-generated method stub
    }
}