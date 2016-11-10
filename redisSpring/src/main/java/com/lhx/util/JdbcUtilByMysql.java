package com.lhx.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by lhx on 2016/8/3
 */
public class JdbcUtilByMysql {

    private Connection connection ;
    private PreparedStatement pstmt ;
    private ResultSet resultSet ;
    private DataSource dataSource ;

    public JdbcUtilByMysql(){
        Properties props = new Properties() ;
        InputStream is = JdbcUtilByMysql.class.getClassLoader().getResourceAsStream("mysql.properties") ;
        try {
            props.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(props) ;
        } catch (Exception e) {
            throw new ExceptionInInitializerError() ;
        }finally{
            try {
                is.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Connection getConnection(){
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection ;
    }

    public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException {
        boolean flag = false ;
        int result = -1 ;
        pstmt = connection.prepareStatement(sql);
        int index = 1 ;
        if (params != null && !params.isEmpty()){
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        flag = result > 0 ;
        return flag ;
    }

    public Map<String,Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        int index = 1 ;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()){
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next()){
            for (int i = 0; i < col_len; i++) {
                String col_name = metaData.getColumnName(i+1);
                Object col_value = resultSet.getObject(col_name);
                if (col_value == null){
                    col_value = "";
                }
                map.put(col_name,col_value);
            }
        }
        return map ;
    }

    public List<Map<String,Object>> findMoreResult(String sql, List<Object> params) throws SQLException {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1 ;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()){
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next()){
            Map<String,Object> map = new HashMap<String, Object>();
            for (int i = 0; i < col_len; i++) {
                String col_name = metaData.getColumnName(i+1);
                Object col_value = resultSet.getObject(col_name);
                if (col_value == null){
                    col_value = "";
                }
                map.put(col_name,col_value);
            }
            list.add(map);
        }
        return list ;
    }

    public void releaseConn(){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
