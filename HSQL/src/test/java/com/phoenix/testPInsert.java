package com.phoenix;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-14
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 */
public class testPInsert {
    /*

    public static final String PHOENIX_JDBC_URL = "jdbc:phoenix:10.61.1.93";
    public static Connection conn;
    public final static int expectedCount=1000000;
    @BeforeClass
    public static void setUp() throws Exception {
        Class.forName("com.salesforce.phoenix.jdbc.PhoenixDriver");
        Properties props = new Properties();
        conn = DriverManager.getConnection(PHOENIX_JDBC_URL, props);
        //createTable();
    }
    public static void createTable() throws Exception{
        Statement stmt=conn.createStatement();
        stmt.execute("drop table if exists ptest");
        stmt.execute("create table if not exists ptest3 (rowkey integer not null primary key,value integer)") ;
        System.out.println("where");
        PreparedStatement upsertStmt=conn.prepareStatement("upsert into ptest3(rowkey,value) values(?,?)");
        for(int i=0;i<expectedCount;i++){
            upsertStmt.setInt(1,i);
            upsertStmt.setInt(2,i);
            upsertStmt.execute();
            if(i%50000==0)    {
                conn.commit();
                System.out.println("Insert "+i);
            }
        }
        conn.commit();
    }
    @Ignore
    @Test
    public void testCount() throws SQLException {
        Statement stmt=conn.createStatement();
        System.out.println("begin count");
        long begin=System.currentTimeMillis();
        stmt.execute("select count(*) from ptest3");
        ResultSet resultSet=stmt.getResultSet();
        resultSet.next();

        System.out.println(resultSet.getInt(1));
        // assertEquals(100000,resultSet.getInt(1));
        long end=System.currentTimeMillis();
        System.out.println("cost : "+(end-begin)/1000.0);
        //System.out.println(stmt.getResultSet().getLong(1));
    }
    */
}
