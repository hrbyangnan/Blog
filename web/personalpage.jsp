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

<!DOCTYPE html>
<html>
<head>
    <title>personal page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

        #search {
            background: white none repeat scroll 0 0;
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

        #textarea {
            height: 205px;
        }

    </style>
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

<nav class="navbar navbar-inverse">
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
</nav>

<!------------------------------------------------------------------------------------------------------>


<!---------------------------------------Page content--------------------------------------------------->
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h2>About Me</h2>
            <h5>Photo of me:</h5>
            <div class="fakeimg">Fake Image</div>
            <p><%= author.getInfomation() %></p>
            <p>The user ID we are trying to use is... <%= author.getId()%>%></p>

            <hr class="hidden-sm hidden-md hidden-lg">
        </div>
<% List<Article> articlesByUser;
    try(ArticleDao ad = new ArticleDao()) {
        System.out.println("Are we getting articles");

        articlesByUser = ad.selectArtByUser(author.getId());
        System.out.printf("If we are the length is " + articlesByUser.size());
        userSession.setAttribute("userArticles", articlesByUser);
    }%>

    <div class="col-sm-8">
   <% for(Article a: articlesByUser){ %>

            <h2><%=a.getArticleName()%></h2>
            <h5><%=a.getPubTime()%></h5>
            <div class="fakeimg">Fake Image</div>

            <p><%=a.getArticleContent()%></p>
            <br>

<% } %>
        </div>
    </div>
</div>
<!------------------------------------------------------------------------------------------------------>
<!---------------------------------------side bar------------------------------------------------------->
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-4">

        </div>
        <!------------------------------------------------------------------------------------------------------>
        <!----------------------------------------Post New Entry------------------------------------------------>
        <div class="col-sm-8">
            <hr>
            <a href="#"><h4><span class="glyphicon glyphicon-send"></span> POST NEW ENTRY</h4></a>
            <form id="input" method="post" action="/createArticles">
                <label for="datetimepicker" class="col-12 col-form-label">publication date and time</label>
                <input type="datetime-local" name="pubTime" value="2012-05-15 21:05" id="datetimepicker">
                <br>
                <br>
                <label for="text" class="col-12 col-form-label">Enter Title here</label>
                <input id="text" name="articleName" placeholder="Enter Title here" class="form-control here"
                       required="required" type="text">
                <br>
                <label for="textarea" class="col-12 col-form-label">Visual Editor</label>
                <br>
                <textarea id="textarea" name="articleContent" cols="30" rows="9" class="form-control"></textarea>
                <br>

                <label for="avatar"></label>
                <input type="file" id="avatar" name="avatar"
                       accept="image/png, image/jpeg, video/webm, video/mp4">
                <br>
                <button type="submit" class="btn btn-primary btn-sm">Publish</button>
                <br>

            </form>
        </div>
    </div>
</div>
<!--------------------------------------search modal---------------------------------------------------->
<div id="search" class="modal">
    <form id="navbarSearch">
        <div class="searchContainer">
                    <span onclick="document.getElementById('search').style.display='none'" class="close"
                          title="Close Modal">&times;</span>
        </div>
        <div class="searchBox">
            <input type="text" class="form-control" placeholder="Search">

            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">search</button>
            </div>
        </div>
    </form>
</div>
<!------------------------------------------------------------------------------------------------------>
<!---------------------------------------Blog Footer---------------------------------------------------->
<section id="blogFooter">
    <footer class="area">

                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 text-center">
                    <br>
                    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
                </div>

    </footer>
</section>
<!------------------------------------------------------------------------------------------------------>

</body>
</html><%}%>