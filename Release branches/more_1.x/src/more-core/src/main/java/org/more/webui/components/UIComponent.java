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
package org.more.webui.components;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.more.core.error.MoreDataException;
import org.more.core.event.AbstractEventManager;
import org.more.core.event.Event;
import org.more.core.event.EventListener;
import org.more.core.event.EventManager;
import org.more.webui.PropertyHolder;
import org.more.webui.ValueHolder;
import org.more.webui.ViewContext;
import org.more.webui.event.ActionEvent;
import org.more.webui.event.InitInvokeEvent;
/**
 * 所有组件的根
 * @version : 2011-8-4
 * @author 赵永春 (zyc@byshell.org)
 */
public abstract class UIComponent implements StateHolder {
    private String                   componentID         = null;
    private List<UIComponent>        components          = null;
    private boolean                  isRender            = true;
    /**私有事件管理器，该事件时间线不会受到其他组件影响*/
    private EventManager             privateEventManager = new AbstractEventManager() {};
    private Map<String, ValueHolder> propertys           = new HashMap<String, ValueHolder>();
    //
    /**通用属性表*/
    public enum Propertys {
        /**组件ID*/
        componentID, //
        /**组件使用的属性配置文件*/
        profile//
    };
    //
    /**获取组件类型名称。*/
    public abstract String getComponentType();
    /**返回组件的ID*/
    public String getComponentID() {
        return this.componentID;
    };
    /**设置属性ID*/
    public void setComponentID(String componentID) {
        this.componentID = componentID;
    };
    /**在当前组件的子级中寻找某个特定ID的组件*/
    public UIComponent getChildByID(String componentID) {
        if (this.componentID.equals(componentID) == true)
            return this;
        for (UIComponent component : this.getChildren()) {
            UIComponent com = component.getChildByID(componentID);
            if (com != null)
                return com;
        }
        return null;
    };
    /**获取一个int，该值表明当前组件中共有多少个子元素*/
    public int getChildCount() {
        return this.getChildren().size();
    };
    /**获取一个元素集合，该集合是存放子组件的场所*/
    public List<UIComponent> getChildren() {
        if (this.components == null)
            this.components = new ArrayList<UIComponent>();
        return this.components;
    };
    /**子类可以通过该方法初始化该组件。*/
    protected void initUIComponent(ViewContext viewContext) {};
    /**获取保存属性的集合。*/
    public Map<String, ValueHolder> getPropertys() {
        return this.propertys;
    };
    /**设置用于表示组件属性的字符串。*/
    public void setPropertyText(String propertyName, String propertyText) {
        ValueHolder value = this.getProperty(propertyName);
        if (value.equalsText(propertyText) == true)
            return;
        /*创建一个新的属性对象放入属性集合*/
        value = PropertyHolder.createPropertyHolder(propertyText);
        this.getPropertys().put(propertyName, value);
    };
    /**获取用于表示组件属性的字符串。*/
    public String getPropertyText(String propertyName, String propertyText) {
        ValueHolder value = this.getProperty(propertyName);
        if (value != null)
            return value.getPropertyText();
        return null;
    };
    /**获取用于表示组件属性对象。*/
    public ValueHolder getProperty(String propertyName) {
        return this.getPropertys().get(propertyName);
    };
    /**将新值设置到属性中。*/
    public void setProperty(String propertyName, Object newValue) {
        ValueHolder value = this.getProperty(propertyName);
        if (value != null)
            value.value(newValue);
    };
    /**返回一个boolean值，该值决定是否渲染该组件*/
    public boolean isRender() {
        return this.isRender;
    };
    /**设置一个boolean值，该值决定是否渲染该组件*/
    public void setRender(boolean isRender) {
        this.isRender = isRender;
    };
    /*-------------------------------------------------------------------------------*/
    /**第1阶段，处理初始化阶段，该阶段负责初始化组件。*/
    public void processInit(ViewContext viewContext) {
        /*弹出所有初始化调用事件*/
        this.privateEventManager.popEvent(Event.getEvent(InitInvokeEvent.class));
        this.initUIComponent(viewContext);
        for (UIComponent com : this.getChildren())
            com.processInit(viewContext);
    };
    /**第3阶段，将请求参数中与属性名一致的属性灌入属性上。*/
    public void processApplyRequest(ViewContext viewContext) {
        /*将请求参数中要求灌入的属性值灌入到属性上*/
        for (String key : this.propertys.keySet()) {
            /*被灌入的属性名，请求参数中必须是“componentID:attName”*/
            String newValue = viewContext.getHttpRequest().getParameter(this.componentID + ":" + key);
            if (newValue != null)
                this.propertys.get(key).value(newValue);
        }
        for (UIComponent com : this.getChildren())
            com.processApplyRequest(viewContext);
    };
    /**第4阶段，该阶段用于提供一组验证数据的合法性。*/
    public void processValidate(ViewContext viewContext) {
        for (UIComponent com : this.getChildren())
            com.processValidate(viewContext);
    };
    /**第5阶段，将组件模型中的新值应用到，Bean*/
    public void processUpdate(ViewContext viewContext) {
        /*更新所有注册到propertys中的属性值*/
        for (String key : this.propertys.keySet()) {
            ValueHolder vh = this.propertys.get(key);
            vh.updateModule();
        }
        for (UIComponent com : this.getChildren())
            com.processUpdate(viewContext);
    };
    /**第6阶段，处理Action动作和客户端回传的消息*/
    public void processApplication(ViewContext viewContext) {
        /*弹出所有动作事件*/
        this.privateEventManager.popEvent(Event.getEvent(ActionEvent.class));
        for (UIComponent com : this.getChildren())
            com.processApplication(viewContext);
    };
    /*-------------------------------------------------------------------------------*/
    /**发出事件，该事件会被发送到私有事件管理器中。*/
    protected void pushEvent(Event eventType, Object... objects) {
        this.privateEventManager.pushEvent(eventType, objects);
    };
    /**通知事件管理器抛出某一个类型的事件。*/
    protected void popEvent(Event event) {
        this.privateEventManager.popEvent(event);
    };
    /**添加一种类型事件的事件监听器。*/
    public void addEventListener(Event eventType, EventListener<?> listener) {
        this.privateEventManager.addEventListener(eventType, listener);
    };
    /**获取私有事件管理器。*/
    protected EventManager getEventManager() {
        return this.privateEventManager;
    };
    /*-------------------------------------------------------------------------------*/
    public void restoreState(Object[] stateData) {
        //1.数据检查
        if (stateData == null)
            return;
        if (stateData.length != 2)
            throw new MoreDataException("WebUI无法重塑组件状态，在重塑组件[" + this.componentID + "]组件发生数据丢失");
        //2.恢复自身数据
        Map<String, Object> mineState = (Map<String, Object>) stateData[0];
        for (String propName : mineState.keySet()) {
            ValueHolder vh = this.propertys.get(propName);
            if (vh != null && vh.isReadOnly() == false)
                vh.value(mineState.get(propName));
        }
        //3.恢复子组件
        Map<String, Object> childrenState = (Map<String, Object>) stateData[1];
        List<UIComponent> uic = getChildren();
        for (UIComponent com : uic)
            com.restoreState((Object[]) childrenState.get(com.getComponentID()));
    };
    public Object[] saveState() {
        //1.持久化自身的状态
        HashMap<String, Object> mineState = new HashMap<String, Object>();
        for (String propName : this.propertys.keySet()) {
            ValueHolder vh = this.propertys.get(propName);
            if (vh.isReadOnly() == false)
                mineState.put(propName, vh.value());
        }
        //2.持久化子组件的状态
        HashMap<String, Object> childrenState = new HashMap<String, Object>();
        List<UIComponent> uic = getChildren();
        for (UIComponent com : uic)
            childrenState.put(com.getComponentID(), com.saveState());
        //3.返回持久化状态
        Object[] thisState = new Object[2];
        thisState[0] = mineState;
        thisState[1] = childrenState;
        return thisState;
    }
};