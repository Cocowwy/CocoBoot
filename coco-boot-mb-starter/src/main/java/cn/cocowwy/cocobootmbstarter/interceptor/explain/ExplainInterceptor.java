package cn.cocowwy.cocobootmbstarter.interceptor.explain;

import cn.cocowwy.cocobootmbstarter.interceptor.utils.PrintUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * MySQL 执行计划拦截器
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class ExplainInterceptor implements Interceptor {

    private static final Log logger = LogFactory.getLog(ExplainInterceptor.class);

    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMATTER);

    /**
     * 订阅的 type 类型 ，如果为空则打印所有，否则打印订阅的type等级
     */
    private List<String> subscribeType = Collections.emptyList();

    /**
     * 是否打印栈帧
     */
    private Boolean printStack = true;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Executor executor = (Executor) invocation.getTarget();

        if (executor != null && invocation.getMethod().getName().equals("query")) {
            Connection connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
            MappedStatement mappedStatement = (MappedStatement) args[0];
            BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
            String sql = geneSql(ms.getConfiguration(), boundSql);
            PreparedStatement preparedStatement = connection.prepareStatement("explain " + sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 预执行explain
            preExplain(resultSet, sql);

            return invocation.proceed();
        }

        return invocation.proceed();
    }

    private void preExplain(ResultSet resultSet, String sql) throws SQLException {
        // 获取执行计划
        List<ExplainResult> er = getExplainResult(resultSet);
        StringBuilder info = new StringBuilder();
        if (er.size() > 0) {
            er.forEach(info::append);
            if (printStack) {
                String stack = PrintUtils.printStack();
                info.append("\n栈帧定位信息如下：\n").append(stack);
            }
            logger.warn("\nSQL：" + sql + "\n执行计划如下：\n" + info);
        }
    }

    private List<ExplainResult> getExplainResult(ResultSet resultSet) throws SQLException {
        List<ExplainResult> explainResult = new ArrayList<>(1);
        while (resultSet.next()) {
            ExplainResult er = new ExplainResult();
            er.setId(resultSet.getLong("id"));
            er.setSelectType(resultSet.getString("select_type"));
            er.setTable(resultSet.getString("table"));
            er.setPartitions(resultSet.getString("partitions"));
            er.setType(resultSet.getString("type"));
            er.setPossibleKeys(resultSet.getString("possible_keys"));
            er.setKey(resultSet.getString("key"));
            er.setKeyLen(resultSet.getString("key_len"));
            er.setRef(resultSet.getString("ref"));
            er.setRows(resultSet.getLong("rows"));
            er.setFiltered(resultSet.getDouble("filtered"));
            er.setExtra(resultSet.getString("extra"));

            if (subscribeType == null || subscribeType.isEmpty() || subscribeType.contains(er.getType())) {
                explainResult.add(er);
            }

        }
        return explainResult;
    }


    private String geneSql(Configuration configuration, BoundSql boundSql) {
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        if (sql == null || sql.equals(PrintUtils.EMPTY_STR) || configuration == null) {
            return PrintUtils.EMPTY_STR;
        }

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        sql = sql.replaceAll("[\\s\n ]+", " ");
        // 见 org.apache.ibatis.scripting.defaults.DefaultParameterHandler
        if (parameterMappings != null) {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    String paramValueStr;
                    if (value instanceof String || value instanceof LocalDate || value instanceof LocalTime) {
                        paramValueStr = "'" + value + "'";
                    } else if (value instanceof LocalDateTime) {
                        LocalDateTime ldt = (LocalDateTime) value;
                        paramValueStr = "'" + LOCAL_DATE_TIME_FORMATTER.format(ldt) + "'";
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        paramValueStr = "'" + DATE_FORMATTER.format(date) + "'";
                    } else {
                        paramValueStr = value + PrintUtils.EMPTY_STR;
                    }
                    sql = sql.replaceFirst("\\?", paramValueStr);
                }
            }
        }
        return sql;
    }

    static class ExplainResult {
        private Long id;
        private String selectType;
        private String table;
        private String partitions;
        private String type;
        private String possibleKeys;
        private String key;
        private String keyLen;
        private String ref;
        private Long rows;
        private Double filtered;
        private String extra;

        public void setId(Long id) {
            this.id = id;
        }

        public void setSelectType(String selectType) {
            this.selectType = selectType;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public void setPartitions(String partitions) {
            this.partitions = partitions;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setPossibleKeys(String possibleKeys) {
            this.possibleKeys = possibleKeys;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setKeyLen(String keyLen) {
            this.keyLen = keyLen;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public void setRows(Long rows) {
            this.rows = rows;
        }

        public void setFiltered(Double filtered) {
            this.filtered = filtered;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public Long getId() {
            return id;
        }

        public String getSelectType() {
            return selectType;
        }

        public String getTable() {
            return table;
        }

        public String getPartitions() {
            return partitions;
        }

        public String getType() {
            return type;
        }

        public String getPossibleKeys() {
            return possibleKeys;
        }

        public String getKey() {
            return key;
        }

        public String getKeyLen() {
            return keyLen;
        }

        public String getRef() {
            return ref;
        }

        public Long getRows() {
            return rows;
        }

        public Double getFiltered() {
            return filtered;
        }

        public String getExtra() {
            return extra;
        }

        @Override
        public String toString() {
            return
                    "｜ id=" + id +
                            " ｜ selectType='" + selectType + '\'' +
                            " ｜ table='" + table + '\'' +
                            " ｜ partitions='" + partitions + '\'' +
                            " ｜ type='" + type + '\'' +
                            " ｜ possibleKeys='" + possibleKeys + '\'' +
                            " ｜ key='" + key + '\'' +
                            " ｜ keyLen='" + keyLen + '\'' +
                            " ｜ ref='" + ref + '\'' +
                            " ｜ rows=" + rows +
                            " ｜ filtered=" + filtered +
                            " ｜ extra='" + extra + "' ｜";
        }
    }

    public void setSubscribeType(List<String> subscribeType) {
        this.subscribeType = subscribeType;
    }

    public void setPrintStack(Boolean printStack) {
        this.printStack = printStack;
    }
}
