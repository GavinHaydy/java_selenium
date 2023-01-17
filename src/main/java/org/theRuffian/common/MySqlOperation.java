package org.theRuffian.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySqlOperation {
    /**
     * 创建数据库连接
     * @return
     * con 数据库连接
     */
    public static Connection getConnection(){
        Connection con = null;
        try {
            Properties pro = new Properties();
            try {
                InputStream in = MySqlOperation.class.getClassLoader().getResourceAsStream("mysql.properties");
                pro.load(in);
            }catch (IOException e){
                System.out.println("配置文件不存在");
            }
            String url = pro.getProperty("url");
            String username = pro.getProperty("username");
            String password = pro.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("驱动类找不到");
        }
        return con;
    }


    /**
     * 关闭数据库
     * @param con Connection
     * @param stm Statement
     * @param rs ResultSet
     */
    public static void close(Connection con, Statement stm, ResultSet rs) {
        close(stm, rs);
        close(con);
    }

    public static void close(Connection con, PreparedStatement pst){
        close(pst);
        close(con);
    }

    public static void close(PreparedStatement pst){
        if (pst != null){
            try {
                pst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con){
        if (con != null){
            try {
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stm,ResultSet rs){
        if (stm != null){
            try {
                stm.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static JSON select(String sql){
        Statement stm =null;
        Connection con = null;
        ResultSet rs = null;
        JSON results = null;
        try {
            con = getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            results = resultSetToJSON(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(con,stm,rs);
        }
        return results;
    }

    public static JSON resultSetToJSON(ResultSet resultSet) {
        JSONObject obj = new JSONObject();
//        数据集JSON格式
        JSONArray jsonArray = new JSONArray();
//        数据库中每行的数据对象
        JSONObject rowObj = null;
        int total = 0;
        try {
//          ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
            ResultSetMetaData rsmd = resultSet.getMetaData();
//        遍历数据集
            while (resultSet.next()) {
                total++;
//          每读取一行，新建对象
                rowObj = new JSONObject();
//              获取表列数
                int columnCount = rsmd.getColumnCount();
//            列从1开始，要等于
                for (int i = 1; i <= columnCount; i++) {
//                获取列名
                    String columnName = rsmd.getColumnName(i);
//                取数据
                    String value = resultSet.getString(columnName);
//                添加到rowObj中
                    rowObj.put(columnName, value);
                }
//                添加到数据集Json
                jsonArray.add(rowObj);
            }
            obj.put("total",total);
            obj.put("result",jsonArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
