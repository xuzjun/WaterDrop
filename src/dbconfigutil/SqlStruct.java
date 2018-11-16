package dbconfigutil;

import java.util.ArrayList;

/**
 * @author len
 */
public class SqlStruct {
    private String name;
    private String sql;
    public ArrayList<SqlField> inputs;
    public ArrayList<SqlField> outputs;

    public SqlStruct() {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
