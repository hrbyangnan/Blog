<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);

    if(request.getQueryString()!=null)
    {
        url+="?"+request.getQueryString();
    }
    String result = url.substring(url.indexOf("=")+1,url.length());
    System.out.println(result);
%>
<%@ page import="pojo.User" %>
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
                <form class="form-inline" action="/searchResult.jsp" id="searchField">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search articles">
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
<!--==================================================================================================-->

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h4 style="border-bottom: 1px solid white;"><i class="glyphicon glyphicon-user"></i>Account Access</h4>
            <h4 class="">Forgot your password?</h4>
            <form action="/ForgotPwdServlet" role="form" id="login" method="post">
                <fieldset>
                    <span class="help-block">Username you use to log in to your account<br>
                        We'll send you an email with instructions to choose a new password.</span>
                    <div class="form-group input-group"><span class="input-group-addon">Username</span>
                        <input class="form-control" placeholder="Input your username" value="<%=result%>" name="username" type="text" required="required">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block" id="button">Continue</button>
                    <p class="help-block"><a class="text-muted" href="#" id="access"><small>Account Access</small></a></p>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<!--===============================================================================================-->
<div class="jumbotron text-center" id="jumbo" style="margin-top: 500px">
    <p><a href="#">About</a></p>
    <p><a href="#">FAQ</a></p>
    <p><a href="#">Contact us</a></p>
    <br>
    <br>
    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
</div>
</body>
</html>
