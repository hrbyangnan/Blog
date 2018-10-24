<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);

    if(request.getQueryString()!=null)
    {
        url+="?"+request.getQueryString();
    }
    String result = url.substring(url.indexOf("=")+1,url.indexOf("&"));
    System.out.println(result);
%>

<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Reset Your Password</title>
<%@ page import="pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 16-10-2018
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forgotPassword</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" type="text/css" href="Homepage.css">
    <link rel="shortcut icon" type="image/png" href="images/favicon.ico"/>
</head>
<body>
<!----------------------------------------------Navbar header------------------------------------------->
<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand d-flex w-25 mr-auto" href="index.jsp"><img src="images/loogoo.png" style="height:50px;"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="nav navbar-nav flex-fill w-100 flex-nowrap">
            <li class="nav-item">
                <a class="nav-link" href="/getAllArticles">Article Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/gallery.jsp">Media Gallery</a>
            </li>

        </ul>
        <ul class="navbar-nav ml-auto w-100 justify-content-center">
            <li>
                <form class="form-inline" action="SearchArticle" id="searchField">
                    <div class="input-group">
                        <input type="text" name="info" class="form-control" placeholder="Search articles">
                    </div>
                </form>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto w-100 justify-content-end">

            <% if(author==null){%>
            <li class="nav-item">
                <a href = "RegistrationForm.jsp" class="nav-link"> Sign Up</a>
            </li>
            <li class="nav-item">

                <a class="nav-link" onclick="document.getElementById('id02').style.display='block'"> Login</a>
            </li>

            <%} else{%>
            <li class="nav-item">
                <a class="nav-link" href="personalpage.jsp"><%=author.getRealName()%></a>
            </li>
            <% if(author.getId()== 62){%>
            <li class="nav-item"><a class="nav-link" href = "adminPage.jsp" class= pointer> Admin Page</a></li>
            <%}%>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Sign Out</a>
            </li><%}%>
        </ul>

    </div>
</nav>
<!------------------------------------------------------------------------------------------------------>
<!--===============================================================================================-->

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form action="${pageContext.request.contextPath}/ResetPassword" method="post" role="form"
                  class="sign_up_form">
                <span class="error" style="display: block;">${errors.passwordError}</span>
                <h2 class="sign_up_title">Reset password</h2>
                <div class="form-group">
                    <p>Your Username: </p>
                    <input type="text" name="userName" value="<%=result%>" class="form-control input-lg" readonly="readonly"/>
                    <p>Enter your new password: </p>
                    <input type="password" name="password" id="pwd" class="form-control input-lg"
                           placeholder="New password" tabindex="4">
                </div>
                <p>Confirm your password: </p>
                <div class="form-group">
                    <input type="password" name="password2" id="pwdConfirm" class="form-control input-lg"
                           placeholder="Confirm password" tabindex="4">
                </div>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-12 col-md-12"><a href="#" class="btn btn-success btn-block btn-lg">Reset</a>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <button name="submit" type="submit" class="btn btn-primary">Reset
                </button>

            </form>
        </div>
    </div>
</div>
<!--===============================================================================================-->

</body>
</html>