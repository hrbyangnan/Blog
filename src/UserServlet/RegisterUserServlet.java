//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
package UserServlet;


import dao.UserDao;
import dao.UserDaoImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class RegisterUserServlet extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;


    @Override
    public void init() throws ServletException {
        super.init();
        initUploadFolder();
    }

    public void initUploadFolder() {
        this.uploadsFolder = new File(getServletContext().getRealPath("/Uploaded_Profile"));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }

        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Check that servlet doPost is loading");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = null;
        String firstName = null;
        String lastName = null;

        String password = null;
        String country = null;
        String birthday = null;
        String fileName = null;
        String realName = null;

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImagefile = null;


            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    fileName = fi.getName();
                    fullsizeImagefile = new File(uploadsFolder, fileName);
                    fi.write(fullsizeImagefile);
                } else if (fi.getFieldName() != null) {
                    if (fi.getFieldName().equals("username")) {
                        userName = fi.getString();
                    }
                    if (fi.getFieldName().equals("firstName")) {
                        firstName = fi.getString();
                    }
                    if (fi.getFieldName().equals("lastName")) {
                        lastName = fi.getString();
                    }
                    if (fi.getFieldName().equals("password")) {
                        password = fi.getString();
                    }
                    if (fi.getFieldName().equals("country")) {
                        country = fi.getString();
                    }
                    if (fi.getFieldName().equals("birthday")) {
                        birthday = fi.getString();
                    }
                    realName = firstName + " " + lastName;
                }
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;

        String picPath = "/Uploaded_Profile/" + fileName;

        try {
            d = format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date date = new java.sql.Date(d.getTime());


        String email = request.getParameter("email");
        String information = request.getParameter("publicinfo");

        User user = new User();
        user.setName(userName);
        user.setRealName(realName);
        user.setPassword(password);
        user.setCountry(country);
        user.setBirthday(date);
        user.setInfomation(information);
        user.setEmail(email);
        user.setProfilePhoto(picPath);

        try {
            UserDao ud = new UserDaoImp();

            if (ud.register(user)) {
                request.setAttribute("username", realName);
                request.getRequestDispatcher("personalpage.jsp").forward(request, response);
            } else {

                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
