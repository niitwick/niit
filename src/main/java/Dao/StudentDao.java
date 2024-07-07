package Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.Student;
import Util.MysqlTools;

/**
 *
 * @author llq
 *管理员数据库操作封装
 */
public class StudentDao extends BaseDao {
    //将从表单获取到的username和password作为参数传入，返回一个在数据库的user表中对应了用户名和密码的user对象
    /*
     * 1、如果成功返回一个user对象说明在数据库中找到和表单输入对应的用户名和密码，登录成功
     * 2、如果返回为空说明在数据库中没有找到和表单对应的用户名和密码，登录失败*/
    public Student login(String name , String password){
        String sql = "select * from student where Name = '" + name + "' and password = '" + password + "'";
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                return student;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
