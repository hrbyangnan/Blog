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

/*
* This servlet allows a user to edit their information. Similar to registration servlet, but uses existing User ID and doesn't require recaptcha
* */


public class EditUserInfoServlet extends HttpServlet {


        private File uploadsFolder;
        private File tempFolder;


        @Override
        public void init() throws ServletException {
            super.init();
            initUploadFolder();
        }
//Get folder incase user uploads new profile image
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
             DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(4 * 1024);
            factory.setRepository(tempFolder);
            ServletFileUpload upload = new ServletFileUpload(factory);

            User author = (User)request.getSession().getAttribute("userInfo");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
             String userName = null;
            String firstName = null;
            String lastName = null;


            String country = null;
            String birthday = null;
            String avatarPath = null;
            String fileName = null;
            String realName = null;
             String information = null;

            String random = String.valueOf((int) (Math.random() * (9999 - 1000 + 1)) + 1000);

//Gets data from multipart form

            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                File fullsizeImagefile = null;


                for (FileItem fi : fileItems) {
                    if (!fi.isFormField()) {
                        if(fi.getSize()>1){
                        fileName = fi.getName();
                        fullsizeImagefile = new File(uploadsFolder, random+"_"+fileName);
                        fi.write(fullsizeImagefile);
                         }

                    } else if (fi.getFieldName() != null) {
//
                        if (fi.getFieldName().equals("username")) {
                            userName = fi.getString();
                        }
                        if (fi.getFieldName().equals("firstName")) {
                            firstName = fi.getString();
                        }
                        if (fi.getFieldName().equals("lastName")) {
                            lastName = fi.getString();
                        }

                        if (fi.getFieldName().equals("country")) {
                            country = fi.getString();
                        }
                        if (fi.getFieldName().equals("birthday")) {
                            birthday = fi.getString();
                        }
                        if (fi.getFieldName().equals("avatar")){
                            avatarPath = fi.getString();
                        }

                        if (fi.getFieldName().equals("information")) {
                            information = fi.getString();
                        }


                        realName = firstName + " " + lastName;
                    }
                }


            } catch (Exception e) {
                throw new ServletException(e);
            }

//Convert birthdate into suitable format for SQL
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date d = null;
                String picPath="";
                if (fileName != null) {
                picPath = "/Uploaded_Profile/" + random+"_"+fileName;
                 }else {
                    picPath = avatarPath;
                }

                try {
                    d = format.parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                java.sql.Date date = new java.sql.Date(d.getTime());

                //Actually create a new User object then set with information taken from multipart form
                User user = new User();
                user.setId((author.getId()));
            user.setName(userName);
                user.setRealName(realName);
                 user.setCountry(country);
                user.setBirthday(date);
                user.setInfomation(information);
                 user.setProfilePhoto(picPath);

                 //Use updateProfile method in dao instance instead of creatUser method
                try {
                    UserDao ud = new UserDaoImp();

                    if (ud.updateProfile(user)) {
                        request.getSession().setAttribute("userInfo", null);

                        request.getSession().setAttribute("userInfo", user);
                        request.getRequestDispatcher("personalpage.jsp").forward(request, response);
                    } else {

                        response.sendRedirect("index.jsp");
                    }

                } catch (SQLException e) {
                    e.getMessage();
                }
            }





}