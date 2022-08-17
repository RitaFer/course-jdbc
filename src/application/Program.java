package application;

import db.DB;
import db.DBIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        Connection conn;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE from Department WHERE Id = ?");
            st.setInt(1, 2);
            int rowsAffected = st.executeUpdate();
            System.out.println("Feito! Linhas afetadas --> "+rowsAffected);
        } catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        }
    }
}