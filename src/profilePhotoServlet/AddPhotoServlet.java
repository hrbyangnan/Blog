package profilePhotoServlet;



import dao.ProfilePhotoDao;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import pojo.ProfilePhoto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class AddPhotoServlet extends HttpServlet {
	ProfilePhotoDao dao = new ProfilePhotoDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		System.out.println("----------------AddPhotoServlet.doGet()----------------------");
		int loginUserId=Integer.parseInt(request.getSession().getAttribute("userId").toString());

		String pathInfo = "/upload";
		System.out.println("AddPhotoServlet.doGet()"+pathInfo);
		request.setCharacterEncoding("utf-8");

		String path = getServletContext().getRealPath("/photoLibrary");
		System.out.println("ApacheOperation.doPost()----------------------"
				+ path + "----------------------");

		if (pathInfo.equals("/upload")) {

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println(isMultipart);

			if (isMultipart) {
				System.out.println("isMultipart");

				ServletFileUpload fileUpload = new ServletFileUpload();
				try {
					String flag=null;
					// List<FileItem> items=fileUpload.parseRequest(request);
					FileItemIterator iter = fileUpload.getItemIterator(request);
					while (iter.hasNext()) {
						System.out.println("aaa");
						FileItemStream stream = iter.next();
						InputStream in = stream.openStream();
						String name = stream.getFieldName();

						if (stream.isFormField()) {
							String value = Streams.asString(in, "utf-8");
							System.out.println(name + "---" + value);
						} else {
							

							System.out.println("AddPhotoServlet.doGet()"+stream.getName());
							if(stream.getName()!=""){
								File f=new File(stream.getName());
								String url=f.getName();
								String upPath = path + "\\" + url;
								System.out.println("ApacheOperation.doPost()-------------fileName--------------------");
								System.out.println(url);							
								ProfilePhoto photo=new ProfilePhoto(loginUserId,url);
								dao.addPhoto(photo);
								flag="OK";
								OutputStream os = new FileOutputStream(upPath);
								byte[] buffer = new byte[1024];
								int len = -1;
								while ((len = in.read(buffer)) != -1) {
									os.write(buffer, 0, len);
								}
								os.close();
							}else{
								flag=null;
								request.getSession().setAttribute("flag",flag);
							}
							
						}
					}
					
					List<ProfilePhoto> photos=dao.selectByUser(loginUserId);
					request.getSession().setAttribute("photos",photos);
					response.sendRedirect("/BokeProject/photo/photo.jsp");
				} catch (FileUploadException e) {
					e.printStackTrace();
					// response.sendRedirect(request.getContextPath()
					// + "/pages/failure.jsp");
				}
			}
		}else {

			response.sendError(HttpServletResponse.SC_NOT_FOUND, "invaild path");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}



}
