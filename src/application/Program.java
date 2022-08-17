package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Rita Ferreira");
            st.setString(2, "rialf.ferreira@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("26/07/1999").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
            int rowAffected = st.executeUpdate();
            if (rowAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Feito! Id="+id);
                }
            } else {
                System.out.println("Nenhuma linha foi afetada");
            }
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}