/*
package ArticleServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddPhotosServlet extends HttpServlet {

    private File uploadsFolder;
    private File tempFolder;


    @Override
    public void init() throws ServletException {
        super.init();
        initUploadFolder();
    }

    public void initUploadFolder() {
        this.uploadsFolder = new File(getServletContext().getRealPath("/Uploaded_Photos"));
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
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println("AAA");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String articleName = null;
        String articleContent = null;
        String pubTime = null;
        String fileName = null;
        System.out.println("BBB");
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImagefile = null;

            System.out.println("CCC");
            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    fileName = fi.getName();
                    fullsizeImagefile = new File(uploadsFolder, fileName);
                    fi.write(fullsizeImagefile);
                } else if (fi.getFieldName() != null) {
                    if (fi.getFieldName().equals("articleName")) {
                        articleName = fi.getString();
                    }
                    if (fi.getFieldName().equals("articleContent")) {
                        articleContent = fi.getString();
                    }
                    if (fi.getFieldName().equals("pubTime")) {
                        pubTime = fi.getString();
                    }
                }
            }

            out.println("<img src=/Uploaded_Photos/" + fullsizeImagefile.getName() + " width=\"200\">");
            out.println("Photo is already uploaded.");

            System.out.println(fullsizeImagefile.getName());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}*/
