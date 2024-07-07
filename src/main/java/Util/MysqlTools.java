package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author llq
 *数据库连接util
 */
public class MysqlTools {

    private String dbUrl = "jdbc:mysql://localhost:3306/niit";
    private String dbUser = "root";
    private String dbPassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";
    private Connection connection = null;
    public Connection getConnection(){
        try {
            Class.forName(jdbcName);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeCon(){
        if(connection != null)
            try {
                connection.close();
                System.out.println("数据库连接已关闭");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建一个数据库对象
        MysqlTools dbUtil = new MysqlTools();
        //连接数据库
        dbUtil.getConnection();
    }
}

