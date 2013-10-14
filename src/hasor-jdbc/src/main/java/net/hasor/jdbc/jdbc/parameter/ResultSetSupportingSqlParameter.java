/*
 * Copyright 2002-2007 the original author or authors.
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
package net.hasor.jdbc.jdbc.parameter;
import net.hasor.jdbc.jdbc.ResultSetExtractor;
import net.hasor.jdbc.jdbc.RowCallbackHandler;
import net.hasor.jdbc.jdbc.RowMapper;
/**
 * 基础类型 SQL 参数，该类型的子类有{@link SqlOutParameter} 和 {@link SqlReturnResultSet}.
 * 它不是一个传入参数。
 * @author Juergen Hoeller
 * @since 1.0.2
 */
public abstract class ResultSetSupportingSqlParameter extends SqlInputParameter {
    private ResultSetExtractor resultSetExtractor;
    private RowCallbackHandler rowCallbackHandler;
    private RowMapper          rowMapper;
    /**
     * Create a new ResultSetSupportingSqlParameter.
     * @param name name of the parameter, as used in input and output maps
     * @param sqlType SQL type of the parameter according to java.sql.Types
     */
    public ResultSetSupportingSqlParameter(String name, int sqlType) {
        super(name, sqlType);
    }
    /**
     * Create a new ResultSetSupportingSqlParameter.
     * @param name name of the parameter, as used in input and output maps
     * @param sqlType SQL type of the parameter according to java.sql.Types
     * @param typeName the type name of the parameter (optional)
     */
    public ResultSetSupportingSqlParameter(String name, int sqlType, String typeName) {
        super(name, sqlType, typeName);
    }
    /**
     * Create a new ResultSetSupportingSqlParameter.
     * @param name name of the parameter, as used in input and output maps
     * @param sqlType SQL type of the parameter according to java.sql.Types
     * @param rse ResultSetExtractor to use for parsing the ResultSet
     */
    public ResultSetSupportingSqlParameter(String name, int sqlType, ResultSetExtractor rse) {
        super(name, sqlType);
        this.resultSetExtractor = rse;
    }
    /**
     * Create a new ResultSetSupportingSqlParameter.
     * @param name name of the parameter, as used in input and output maps
     * @param sqlType SQL type of the parameter according to java.sql.Types
     * @param rch RowCallbackHandler to use for parsing the ResultSet
     */
    public ResultSetSupportingSqlParameter(String name, int sqlType, RowCallbackHandler rch) {
        super(name, sqlType);
        this.rowCallbackHandler = rch;
    }
    /**
     * Create a new ResultSetSupportingSqlParameter.
     * @param name name of the parameter, as used in input and output maps
     * @param sqlType SQL type of the parameter according to java.sql.Types
     * @param rm RowMapper to use for parsing the ResultSet
     */
    public ResultSetSupportingSqlParameter(String name, int sqlType, RowMapper rm) {
        super(name, sqlType);
        this.rowMapper = rm;
    }
    /**
     * Does this parameter support a ResultSet, i.e. does it hold a
     * ResultSetExtractor, RowCallbackHandler or RowMapper?
     */
    public boolean isResultSetSupported() {
        return (this.resultSetExtractor != null || this.rowCallbackHandler != null || this.rowMapper != null);
    }
    /**Return the ResultSetExtractor held by this parameter, if any.*/
    public ResultSetExtractor getResultSetExtractor() {
        return this.resultSetExtractor;
    }
    /**Return the RowCallbackHandler held by this parameter, if any.*/
    public RowCallbackHandler getRowCallbackHandler() {
        return this.rowCallbackHandler;
    }
    /**Return the RowMapper held by this parameter, if any.*/
    public RowMapper getRowMapper() {
        return this.rowMapper;
    }
    /**非传入参数。*/
    public boolean isInputParameter() {
        return false;
    }
}