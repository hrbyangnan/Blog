package Administrative;

import dao.UserDaoImp;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * send link of resetting password
 */
public class ForgotPwdServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDaoImp userDao = null;
		try {
			userDao = new UserDaoImp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		User user =null;
		try {
			user = userDao.findUserByName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user == null) {
			request.setAttribute("errorMsg", username + "is not exist!");
			request.getRequestDispatcher("/forgotPwdUI").forward(request, response);
			return;
		}
		

		EmailUtils.sendResetPasswordEmail(user);
		
		request.setAttribute("sendMailMsg", "Please check your email: "+user.getEmail());
		
		request.getRequestDispatcher("forgotpass.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
