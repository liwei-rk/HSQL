package org.ict.hsql.query;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-9
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
import java.sql.ResultSetMetaData;

import org.ict.hsql.exception.HSQLNotSupported;

import java.sql.SQLException ;

/**
 *
 * JDBC ResultSetMetaData implementation of HSQL.
 * Currently only the following methods are supported:
 * - {@link #getColumnCount()}
 * - {@link #getColumnDisplaySize(int)}
 * - {@link #getColumnLabel(int)} displays alias name if present and column name otherwise
 * - {@link #getColumnName(int)} same as {@link #getColumnLabel(int)}
 * - {@link #isCaseSensitive(int)}
 * - {@link #getColumnType(int)}
 * - {@link #getColumnTypeName(int)}
 * - {@link #getTableName(int)}
 * - {@link #getSchemaName(int)} always returns empty string
 * - {@link #getCatalogName(int)} always returns empty string
 * - {@link #isNullable(int)}
 * - {@link #isSigned(int)}
 * - {@link #isAutoIncrement(int)} always false
 * - {@link #isCurrency(int)} always false
 * - {@link #isDefinitelyWritable(int)} always false
 * - {@link #isReadOnly(int)} always true
 * - {@link #isSearchable(int)} always true
 *
 * @author jtaylor
 * @since 0.1
 */
public class HSQLResultSetMetaData implements ResultSetMetaData {
    private static final int MIN_DISPLAY_WIDTH = 3;
    private static final int MAX_DISPLAY_WIDTH = 40;
    private static final int DEFAULT_DISPLAY_WIDTH = 10;
    private final ProjectorList pl;


    public HSQLResultSetMetaData(ProjectorList pl) {
        this.pl= pl;
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        return "";
    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
         return "unimplement";
    }

    @Override
    public int getColumnCount() throws SQLException {
        return pl.getProjectors().size();
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        return 20;
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return pl.getProjectors().get(column-1).getSrcColumn();
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        // TODO: will return alias if there is one
        return getColumnLabel(column);
    }

    @Override
    public int getColumnType(int column) throws SQLException {
       return pl.getColumnType(column);
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        return pl.getColumnTypeName(column);
    }

    @Override
    public int getPrecision(int column) throws SQLException {
       return 0;
    }

    @Override
    public int getScale(int column) throws SQLException {
        return 0;
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        return ""; // TODO
    }

    @Override
    public String getTableName(int column) throws SQLException {
        return pl.getTableName();
    }

    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isCurrency(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isDefinitelyWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public int isNullable(int column) throws SQLException {
        return 0;// not null
    }

    @Override
    public boolean isReadOnly(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isSearchable(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isSigned(int column) throws SQLException {
       return true;
    }

    @Override
    public boolean isWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }

    @SuppressWarnings("unchecked")
    @Override

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new HSQLNotSupported();
    }


}
