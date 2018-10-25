
<!DOCTYPE html>
<html>
<%@ page import="pojo.Article" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ArticleDao" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 12-10-2018
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>personal page</title>
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
    <script>
        $('#datetimepicker').datetimepicker({
            format: 'yyyy-mm-dd hh:ii'
        });
        var modal = document.getElementById('search');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

        window.onclick = function (e) {
            if (!e.target.matches('.dropbtn')) {
                var myDropdown = document.getElementById("myDropdown");
                if (myDropdown.classList.contains('show')) {
                    myDropdown.classList.remove('show');
                }
            }
        }

    </script>
    <style>

        .button:hover {
            border-top-color: #28597a;
            background: #28597a;
            color: #ccc;
        }

        .button:active {
            border-top-color: #1b435e;
            background: #1b435e;
        }

        .divform {
            background: #dddddd;
            border: 2px solid #a1a1a1;
            padding: 10px 40px;
            border-radius: 25px;
            width: 800px;

        }
        .divarticle {
            border: 2px solid #a1a1a1;
            padding: 10px 40px;
            background: #dddddd;
            width: 800px;
        }

        #search {
            background: whitesmoke none repeat scroll 0 0;
            border-radius: 5px;
            border: #34495E;
            margin: 10px auto 10px;
            padding: 50px 70px 70px 71px;
            max-width: 30%;
            max-height: 15%;

        }

        #search {

            padding: 19px;
            position: absolute;
            z-index: 80;
            top: 60px;
            left: 850px;
            background: rgba(0, 0, 0, 0.6);
            margin-bottom: 20px;
            border: 1px solid #e3e3e3;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
        }

        .well-searchbox label {
            color: #fff;
        }

        #content {
            height: 205px;
        }

    </style>
    <%--TINY MCE--%>
    <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=938z2jiw3kbkqfpu64elzhqarczlyjoaovifhvug2qkzg1j5"></script>    <script type="text/javascript">
    tinymce.init({
        selector: '#content',
        height: 200,
        plugins: 'fullscreen link powerpaste',
        menubar: false,
        toolbar: 'formatselect | bold italic underline strikethrough forecolor | link | alignleft aligncenter alignright alignjustify' +
        ' | numlist bullist | removeformat'
    });



</script>

</head>
<body>
<!----------------------------------------------Navbar header------------------------------------------->
<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");
    if(author==null){%>You have not signed in yet. Please login or register. You should be redirected in 2 seconds.
<script>
    setTimeout(function(){document.location="/index.jsp";}, 2000);
</script>

<!---->
<%
    System.out.println("Trying to redirect");
}else{%>

<%--<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Blog Name</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp" class=pointer><span class="glyphicon glyphicon-home"></span> Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropbtn" onclick="myFunction()">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= author.getRealName()%></a></li>


                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
                <li><a class=pointer onclick="document.getElementById('search').style.display='block'"><span
                        class="glyphicon glyphicon-search"></span></a></li>
            </ul>
        </div>
    </div>
</nav>--%>

<!------------------------------------------------------------------------------------------------------>
<nav class="navbar navbar-expand-md navbar-dark" id="navbar">
    <a class="navbar-brand" href="index.jsp"><img src="images/loogoo.png" style="height:50px;"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="nav navbar-nav flex-fill w-100 flex-nowrap">
            <li class="nav-item">
                <a class="nav-link" href="/getAllArticles">Article Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/gallery">Media Gallery</a>
            </li>

        </ul>
        <ul class="navbar-nav ml-auto w-100 justify-content-center">
            <li>
                <form class="form-inline" action="SearchArticle" id="searchField">
                    <div class="input-group" id="searchbox">
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


<!---------------------------------------Page content--------------------------------------------------->
<div class="container">
    <% Article oldArticle = (Article) request.getAttribute("scooby");
        System.out.println("editArticle.jsp article ID is " + oldArticle.getArticleId());%>


    <div class="col-sm-8">
        <div class="divform">
            <form ENCTYPE="multipart/form-data" id="articleForm" action="<c:url value="/updatePart2"/>" method="post">
                <%-- <form ENCTYPE="multipart/form-data" id="articleForm" action="<c:url value="/updatePart2"/>" method="post">--%>
                <input type="hidden" name="action" value="">
                <input type="hidden" name="oldArticleID" value="<%=oldArticle.getArticleId()%>">

                <label for="datetimepicker">Article Date</label>
                <input type="date" name="pubTime" id="datetimepicker" value="<%= oldArticle.getPubTime()%>"><br><br>

                <label for="titleInput">Title:</label>
                <input id="titleInput" name="articleName" placeholder="Article Title" required="required" value="<%=oldArticle.getArticleName()%>"><br><br>

                <label for="content">Content:</label>
                <textarea id="content" name="articleContent" placeholder="Article Content"><%=oldArticle.getArticleContent()%></textarea>

                <label for="avatar">Select multimedia</label>
                <input type="file" id="avatar" name="avatar"
                       accept="image/png, image/jpeg, video/webm, video/mp4 audio/mpeg" multiple size="10"><br><br>
                <input type="submit" class="button"
                       value="Submit">

            </form>
            <%--<form id="articleForm" action="<c:url value="/updatePart2"/>" method="post">
                &lt;%&ndash; <form ENCTYPE="multipart/form-data" id="articleForm" action="<c:url value="/updatePart2"/>" method="post">&ndash;%&gt;
                <input type="hidden" name="action" value="">
                <input type="hidden" name="oldArticleID" value="<%=oldArticle.getArticleId()%>">

                <label for="datetimepicker">Article Date</label>
                <input type="date" name="pubTime" id="datetimepicker" value="<%= oldArticle.getPubTime()%>"><br><br>

                <label for="titleInput">Title:</label>
                <input id="titleInput" name="articleName" placeholder="Article Title" required="required" value="<%=oldArticle.getArticleName()%>"><br><br>

                <label for="content">Content:</label>
                <textarea id="content" name="articleContent" placeholder="Article Content"><%=oldArticle.getArticleContent()%></textarea>

                <input type="submit" class="button"
                       value="Submit">

            </form>--%>
        </div>
    </div>

</div>
</div>
<!------------------------------------------------------------------------------------------------------>
<!---------------------------------------side bar------------------------------------------------------->

<!------------------------------------------------------------------------------------------------------>
<!---------------------------------------Blog Footer---------------------------------------------------->
<div class="jumbotron text-center" id="jumbo" style="margin-top: 500px">
    <p><a href="#">About</a></p>
    <p><a href="#">FAQ</a></p>
    <p><a href="#">Contact us</a></p>
    <br>
    <br>
    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
</div>
<!------------------------------------------------------------------------------------------------------>

</body>
</html><%}%>