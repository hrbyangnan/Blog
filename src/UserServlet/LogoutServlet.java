package UserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

	public LogoutServlet() {
		super();
	}


	public void destroy() {
		super.destroy();

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String id=null;
			request.getSession().setAttribute("userId", id);
			request.getSession().setAttribute("user", id);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}


	public void init() throws ServletException {

	}

}
