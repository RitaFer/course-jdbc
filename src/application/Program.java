package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)");
            st.setString(1, "Rita Ferreira");
            st.setString(2, "rialf.ferreira@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("26/07/1999").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
            int rowAffected = st.executeUpdate();
            System.out.println("Foram afetadas "+rowAffected+" linhas.");
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}