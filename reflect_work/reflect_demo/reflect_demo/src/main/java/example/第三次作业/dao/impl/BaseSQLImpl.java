package example.第三次作业.dao.impl;

import com.mysql.cj.util.StringUtils;
import example.第三次作业.Entity.Condition;
import example.第三次作业.annotation.dao.Column;
import example.第三次作业.annotation.dao.Table;
import example.第三次作业.dao.BaseSQL;

import java.lang.reflect.Field;

/**
 * @author LIXIN
 */
public class BaseSQLImpl<T> implements BaseSQL<T> {

    @Override
    public String deleteOne(Class<T> clazz, String conditions) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("delete from ");
        StringBuilder body = buildSqlBody(clazz, conditions);
        sqlBuilder.append(body);
        return sqlBuilder.toString();
    }

    @Override
    public String query(Class<T> clazz, String conditions) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        sqlBuilder.append(getFiledSql(clazz));
        sqlBuilder.append(" from ");
        Table table = clazz.getAnnotation(Table.class);
        String value = table.value();
        sqlBuilder.append(value);
        sqlBuilder.append(" where ").append(conditions);
        return sqlBuilder.toString();
    }

    @Override
    public String createTable(Class<?> clazz) throws Exception {
        Table annotation = clazz.getAnnotation(Table.class);
        if (annotation == null) {
            throw new Exception(clazz.getName() + ":不存在表名注解");
        }
        String tableName = annotation.value();
        StringBuilder builder = new StringBuilder();
        StringBuilder idColumn = new StringBuilder();

        builder.append("CREATE TABLE ").append(tableName).append("(\n");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Column column = declaredField.getAnnotation(Column.class);
            if (column != null) {
                boolean id = column.isId();
                boolean nullable = column.nullable();
                String columnName = column.value();
                if (StringUtils.isNullOrEmpty(columnName)) {
                    columnName = declaredField.getName();
                }
                String typename = "";
                String isNull = "";
                if (!nullable) {
                    isNull = "not null";
                }
                if (declaredField.getType().equals(String.class)) {
                    typename = "varchar(15)";
                } else if ("int".equals(declaredField.getType().getName())) {
                    typename = "int";
                } else if ("boolean".equals(declaredField.getType().getName())) {
                    typename = "tinyint(1)";
                }
                builder.append(columnName).append(" ").append(typename).append(" ").append(isNull).append(",\n");
                if (id) {
//                    PRIMARY KEY ( `runoob_id` )

                    idColumn.append(", PRIMARY KEY (`").append(columnName).append("`)\n");
                }
            }
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append(idColumn);
        builder.append("\n);\n");
        return builder.toString();
    }

    private String getFiledSql(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field field : declaredFields) {
            Column column = field.getAnnotation(Column.class);
            String value = column.value();
            if (value.length() == 0) {
                value = field.getName();
            }
            builder.append(value).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private String buildId(Class<?> clazz) {
        StringBuilder sqlBuilder = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        sqlBuilder.append(" where ");
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null && column.isId()) {
                String idName = column.value();
                if (idName.length() == 0) {
                    idName = field.getName();
                }
                sqlBuilder.append(idName).append("=?");
                break;
            }
        }
        return sqlBuilder.toString();
    }

    private StringBuilder buildSqlBody(Class<?> clazz, String condition) {
        StringBuilder builder = new StringBuilder();
        Table table = clazz.getAnnotation(Table.class);
        String value = table.value();
        //... table where idname=?
        builder.append(value).append(" where ");
        builder.append(condition);
        return builder;
    }

    @Override
    public String updateOne(T entity, Condition condition) throws Exception {
        StringBuilder builder = new StringBuilder();
//          UPDATE course SET course.ccredit=3 WHERE course.cno=1;
        builder.append("update ");
        Class<?> clazz = entity.getClass();
        Table table = clazz.getAnnotation(Table.class);
        String value = table.value();
        builder.append(value).append(" set ");
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            String s = column.value();
            if (s.length() == 0) {
                s = field.getName();
            }
            field.setAccessible(true);
            Object o = null;
            try {
                o = field.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (o != null && !"-1".equals(String.valueOf(o))) {
                builder.append(s).append("=?,");
                count++;
            }
        }
        if (count == 0) {
            throw  new Exception("没有任何更新");

        } builder.deleteCharAt(builder.length() - 1);
        builder.append(" where");
        builder.append(condition.getPrepared());
        return builder.toString();
    }

    @Override
    public String saveOne(Class<T> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into ");
        Table table = clazz.getAnnotation(Table.class);
        String value = table.value();
        builder.append(value);
        builder.append(("("));
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Column column = field.getAnnotation(Column.class);
            String s = column.value();//stu_id
            if (s.length() == 0) {
                s = field.getName();
            }
            builder.append(s).append(",");
        }
        builder.deleteCharAt(builder.length() - 1).append(")");
        builder.append(" values(");
        for (Field ignored : declaredFields) {
            builder.append("?,");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        return builder.toString();
    }
}
