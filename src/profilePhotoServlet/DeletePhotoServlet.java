package profilePhotoServlet;

import dao.ProfilePhotoDao;
import pojo.ProfilePhoto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeletePhotoServlet extends HttpServlet {
	ProfilePhotoDao dao=new ProfilePhotoDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ProfilePhoto photo=new ProfilePhoto(Integer.parseInt(request.getParameter("photoId").toString()));
		dao.deletePhoto(photo);
		int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		List<ProfilePhoto> photos=dao.selectByUser(userId);
		request.getSession().setAttribute("photos",photos);
		response.sendRedirect("photo/photo.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
