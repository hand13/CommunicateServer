package com.hand13;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hd110 on 2017/7/19.
 */
public class Confirm {
    public static boolean update(String fname,String tname,String message)throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false","hand13","2390129416");
        PreparedStatement statement=connection.prepareStatement("INSERT INTO users VALUES (NULL,?,?,?)");
        statement.setString(1,fname);
        statement.setString(2,tname);
        statement.setString(3,message);
        statement.execute();
        connection.close();
        return true;
    }
    public static ArrayList<String> down(String tname)throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false","hand13","2390129416");
        PreparedStatement statement=connection.prepareStatement("SELECT fname,message FROM users WHERE tname=?");
        statement.setString(1,tname);
        ResultSet resultSet=statement.executeQuery();
        ArrayList<String> outString=new ArrayList<>();
        while (resultSet.next()){
            String message=resultSet.getString("fname")+"\0"+resultSet.getString("message");
            outString.add(message);
        }
        return outString;
    }
}
