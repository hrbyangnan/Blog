<%--
  Created by IntelliJ IDEA.
  User: kfit706
  Date: 25/10/2018
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="dao.HikariConnectionPool" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.ResultSet" %>
<%@ page import="dao.UserDaoImp" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    try (UserDaoImp ud = new UserDaoImp()) {
        if (ud.nameCheck(request.getParameter("username"))
        ) {
            out.print("Username is available");
        } else {
            out.print("Username is taken");
        }
    } catch(SQLException e){e.getMessage();}
%>
</body>
</html>
