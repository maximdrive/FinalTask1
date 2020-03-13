package com.dao.handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Handler {
    private static Statement st;
    private static ResultSet rs;
    private static Connection dbConnection;

    public Handler (){
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Minsk&useSSL=false","root","12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDbConnection()  {
        return dbConnection;
    }


    public static int getIdFromDB(String where){
        int id = 1;
        try{
        st = getDbConnection().createStatement();
        rs = st.executeQuery("SELECT id FROM " + where + " ESC");
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(rs.next()) {
            arrayList.add(rs.getInt(1));
        }
        id = Collections.max(arrayList);
        id++;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int searchIDBy(String where, String columnName, String what, int rsIdPosition){
        int id;
        try{
            st = getDbConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM " + where + " WHERE " + columnName + " = '" + what + "'");
            rs.next();
            return id = rs.getInt(rsIdPosition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
