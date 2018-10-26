package Administrative;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* This forwards the user to th reset password.jsp page
* */

public class ResetPasswordUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ResetPasswordUIServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		
		request.setAttribute("userName", userName);
		
		request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
