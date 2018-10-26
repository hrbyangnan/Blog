//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
package UserServlet;


import dao.UserDaoImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * This servlet allows a user to edit their information. Takes data from multipart form and passes into new User POJO to be put into database
 * */

public class RegisterUserServlet extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;
    private final String myKey = "6Le8EHUUAAAAALk08rXol2WnCXaIkCpjj-swXRIM";


    @Override
    public void init() throws ServletException {
        super.init();
        initUploadFolder();
    }
    //Get folder for user to upload new profile image
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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = null;
        String firstName = null;
        String lastName = null;

        String password = null;
        String country = null;
        String birthday = null;
        String avatarPath = null;
        String fileName = null;
        String realName = null;
        String email = null;
        String information = null;
        String reCAPTCHACode = null;
//get random number for filename
        String random = String.valueOf((int) (Math.random() * (9999 - 1000 + 1)) + 1000);

//Gets data from multipart form
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImagefile = null;


            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    if (fi.getSize() > 1) {
                        fileName = fi.getName();
                        //Introduce random number so that one user doesn;t overwrite another users profile photo with the same filename
                        fullsizeImagefile = new File(uploadsFolder, random + "_" + fileName);
                        fi.write(fullsizeImagefile);
                    }
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
                    if (fi.getFieldName().equals("avatar")) {
                        avatarPath = fi.getString();
                    }
                    if (fi.getFieldName().equals("email")) {
                        email = fi.getString();
                    }
                    if (fi.getFieldName().equals("information")) {
                        information = fi.getString();
                    }
                    if (fi.getFieldName().equals("g-recaptcha-response")) {
                        reCAPTCHACode = fi.getString();
                    }

                    realName = firstName + " " + lastName;
                }
            }


        } catch (Exception e) {
            throw new ServletException(e);
        }
//Calls recaptcha method to validate user is not a robot
        if (isCaptchaValid(myKey, reCAPTCHACode)) {
            System.out.println("rekey success");

//Convert birthdate into suitable format for SQL
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = null;
            String picPath = "";
            if (fileName != null) {
                picPath = "/Uploaded_Profile/" + random + "_" + fileName;
            } else {
                picPath = avatarPath;
            }
            System.out.println(picPath);

            try {
                d = format.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date date = new java.sql.Date(d.getTime());

//Create a new user object then set with information taken from multipart form
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
                UserDaoImp ud = new UserDaoImp();
                if (ud.nameNotTaken(userName)) {
                    if (ud.register(user)) {
                        request.setAttribute("username", realName);
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (UnsupportedLookAndFeelException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "Success!");
                        request.getRequestDispatcher("personalpage.jsp").forward(request, response);
                    }
                } else {

                    response.sendRedirect("RegistrationForm.jsp");
                }

            } catch (SQLException e) {
                e.getMessage();
            }
        } else {
            response.sendRedirect("recaptchaIsRequired.jsp");
        }

    }

//Method for google recaptcha
    public static boolean isCaptchaValid(String secretKey, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + secretKey
                    + "&response=" + response;
            InputStream res = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            res.close();

            JSONObject json = new JSONObject(jsonText);
            return json.getBoolean("success");
        } catch (Exception e) {
            return false;
        }
    }
}
