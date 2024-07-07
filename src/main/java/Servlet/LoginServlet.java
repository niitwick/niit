package Servlet;
import Dao.AdminDao;
import Dao.StudentDao;
import Dao.TeacherDao;
import Entity.Admin;
import Entity.Student;
import Entity.Teacher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String Name= request.getParameter("name");
        String password = request.getParameter("password");
        //获取选择登陆对象的类型参数
        int type = Integer.parseInt(request.getParameter("type"));
        //验证码验证
        String imageText = request.getParameter("image");
        // 图片的验证码
        String text = (String) request.getSession().getAttribute("text");
        if (!text.equalsIgnoreCase(imageText)) {
            request.setAttribute("imageMess", "验证码输入错误!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        //验证码验证通过，对比用户名密码是否正确
        switch (type) {
            case 1:{
                StudentDao studentDao = new StudentDao();
                Student student = studentDao.login(Name,password);
                studentDao.closeCon();
                if (student == null)
                {
                    response.getWriter().write("学生登录失败");
                    return;
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", student);
                    session.setAttribute("userType", type);
                    response.getWriter().write("学生登录成功");
                }
                break;
            }
            case 2:{
                TeacherDao teacherDao = new TeacherDao();
                Teacher teacher = teacherDao.login(Name,password);
                teacherDao.closeCon();
                if(teacher == null){
                    response.getWriter().write("教师登录失败");
                    return;
                }else{
                    HttpSession session = request.getSession();
                    session.setAttribute("user", teacher);
                    session.setAttribute("userType", type);
                    response.getWriter().write("教师登录成功");
                }
                break;
            }
            case 3:{
                AdminDao adminDao = new AdminDao();
                Admin admin = adminDao.login(Name, password);
                adminDao.closeCon();
                if(admin == null){
                    response.getWriter().write("管理员登录失败");
                    return;
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", admin);
                    session.setAttribute("userType", type);
                    response.getWriter().write("管理员登录成功");
                }
                break;
            }
            default:
                break;
        }
    }
    //注销登录  退出
    private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.jsp");
    }
}


