package profilePhotoServlet;

import dao.ProfilePhotoDao;
import pojo.ProfilePhoto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ShowPhotosServlet extends HttpServlet {
	ProfilePhotoDao dao=new ProfilePhotoDao();


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
		int loginUserId=Integer.parseInt(request.getSession().getAttribute("userId").toString());//传递参数值
		List<ProfilePhoto> photos=dao.selectByUser(loginUserId);
		request.getSession().setAttribute("photos",photos);
		response.sendRedirect("photo/photo.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
