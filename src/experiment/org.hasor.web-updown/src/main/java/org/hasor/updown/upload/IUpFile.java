/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
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
package org.hasor.updown.upload;
import java.io.IOException;
import org.hasor.context.AppContext;
/**
 * 处理文件上传的接口
 * @version : 2013-3-12
 * @author 赵永春 (zyc@byshell.org)
 */
public interface IUpFile {
    /**初始化对象。*/
    public void initUpFile(AppContext appContext);
    /*** 处理文件上传。*/
    public void doUpFile(IUpInfo upData) throws IOException;
}