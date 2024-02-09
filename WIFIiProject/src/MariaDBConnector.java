/****************************************************
**                                                 **              
**        ApiWifiJavaServletProject-main                          ** 
**        MariaDBConnector                                  **
**        Made by Toji                          **
**        2024-02-07 :오전 9:17                         **
**        https://github.com/lyckabc               **
**                                                 **
****************************************************/
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDBConnector {
    public static void main(String[] args) {
        Connection con = null;

        String server_ip = "localhost";
        String db_name = "wifi";
        String db_port = "3306";
        String user_name = "root";
        String password = "1q!1q!";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mariadb://" +
                    server_ip + ":" + db_port + "/" +
                    db_name +
                    "?useSSL=false", user_name, password); // SSL 실행 확인
            System.out.println("연결 성공");
        } catch(SQLException e) {
            System.err.println("에러 내용 :" + e.getMessage());
            e.printStackTrace();
        }

        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }
}
*/