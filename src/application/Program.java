package application;

import db.DB;
import db.DbException;

import java.sql.*;

public class Program {
    public static void main(String[] args) {
        //As transações devem ser atômicas, consistentes, isoladas e duráveis.
        Connection conn = null;
        Statement st;
        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            int rowsAffected1 = st.executeUpdate("UPDATE seller SET BaseSalary = 1090.0 WHERE DepartmentId = 1");
            int x = 1;
            if(x < 2){
                throw new SQLException("False Error");
            }
            int rowsAffected2 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090.0 WHERE DepartmentId = 2");
            conn.commit();
            System.out.println("Rows 1 --> "+rowsAffected1);
            System.out.println("Rows 2 --> "+rowsAffected2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transação não concluída. Causada por: "+e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Erro no try-rollback(). Causada por: "+e1.getMessage());
            }

        }
    }
}