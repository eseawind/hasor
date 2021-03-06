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
package net.test.project.mgr.user.service;
import java.util.List;
import javax.inject.Inject;
import net.hasor.jdbc.core.JdbcTemplate;
import net.hasor.plugins.bean.Bean;
import net.test.project.mgr.user.entity.UserBean;
/**
 * 
 * @version : 2013-12-25
 * @author 赵永春(zyc@hasor.net)
 */
@Bean("UserService")
public class UserService {
    @Inject
    private JdbcTemplate jdbcTemplate;
    //
    /*取得用户列表*/
    public List<UserBean> getUserList() {
        List<UserBean> userList = jdbcTemplate.queryForList("select * from TB_User", UserBean.class);
        return userList;
    }
}