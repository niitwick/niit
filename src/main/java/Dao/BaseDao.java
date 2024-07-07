package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Util.MysqlTools;

/**
 *
 * @author llq
 *基础dao，封装基本操作
 */
public class BaseDao {
    private MysqlTools dbUtil = new MysqlTools();
    /**
     * 及时关闭数据库连接，释放资源
     */
    public void closeCon(){
        dbUtil.closeCon();
    }

    /**
     * 基础查询，多条查询
     */
    public ResultSet query(String sql){
        try {
            PreparedStatement prepareStatement = dbUtil.getConnection().prepareStatement(sql);
            return prepareStatement.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     *增删改
     */
    public boolean update(String sql){
        try {
            return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public Connection getConnection(){
        return dbUtil.getConnection();
    }
}


