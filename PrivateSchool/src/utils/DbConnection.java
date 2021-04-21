/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ugleethyn
 */
public class DbConnection {

    /**
     * Link of SQL Connection
     */
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/school_project"
            + "?zeroDateTimeBehavior=CONVERT_TO_NULL"
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC"
            + "&allowPublicKeyRetrieval=true"
            + "&useSSL=false";

    private static String username = "ugleethyn";
    private static String password = "TasKlad123";
    
    /**
     * Creates a connection from the given link
     * @return
     * A connection ready to use on the project
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(MYSQLURL, username, password);
        return con;
    }
}
