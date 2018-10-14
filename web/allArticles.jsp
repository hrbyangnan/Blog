<%@ page import="pojo.Article" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kfit706
  Date: 9/10/2018
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Articles</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Homepage.css">

    <script>
        var modal = document.getElementById('id02');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</head>

<body>
<!----------------------------------------------Navbar header------------------------------------------->
<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");%>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Blog Name</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href = "index.jsp" class= pointer><span class="glyphicon glyphicon-home"></span> Home</a></li>

                <li><a href="/getAllArticles"><span class="glyphicon glyphicon-th-large"></span> Archives</a></li>

                <li><a href="personalpage.jsp"><span class="glyphicon glyphicon-user"></span> Author Page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <% if(author==null){%>
                <li><a href = "RegistrationForm.html" class= pointer><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a class = pointer onclick="document.getElementById('id02').style.display='block'"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                <%} else{%><li><a href="#"><%=author.getRealName()%></a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li><%}%>
            </ul>
        </div>
    </div>
</nav>
<br>
<br>
<%
    List<Article> articleList = (List<Article>) request.getAttribute("AllArticlesPojo");
    if(articleList!=null){
    for (Article a : articleList) {%>
<section id="blogContent">
    <div class="container">
        <div class="row">
            <div class="col-md-8">

                <div class="card mb-4">
                    <img class="card-img-top" src="./images/MichaelSalvage%20%20.jpg" alt="Card image cap">
                    <div class="card-body" >
                        <h2 class="card-title"><%=a.getArticleName()%></h2>




                        <p class="card-text"><%=a.getArticleContent()%></p>
                    </div>
                    <div class="card-footer text-muted">
                        Posted on <%if(a.getPubTime()!= null){%><%=a.getPubTime()%><% }else{%>Once upon a time<%}%>  by
                        <a href="#"><%=a.getRealName()%></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%
    }}else{%>
      There has been an error and the article list is empty.
    <%}
%>
<!------------------------------------------------------------------------------------------------------>
<!----------------------------------------------Blog Footer--------------------------------------------->
<section id="blogFooter">
    <footer class="area">
        <div class="container">
            <div class="row">
                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 col-6">
                    <div class="footer-title"><strong>Team</strong></div>
                    <br>
                    <p><a href="#">About</a></p>
                    <p><a href="#">FAQ</a></p>
                    <p><a href="#">Legal & Privacy</a></p>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 col-6">
                    <div class="footer-title"><strong>Quick Links</strong></div>
                    <br>
                    <p><a href="#">News</a></p>
                    <p><a href="#">Contact us</a></p>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 text-center">
                    <br>
                    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
                </div>
            </div>
        </div>
    </footer>
</section>
<!----------------------------------------------Login Modal--------------------------------------------->
<div id="id02" class="modal">
    <div class="container">
        <div class="login-form">
            <div class="main-div">
                <div class="imgContainer">
                    <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
                </div>
                <div class="panel">
                    <h2>Admin Login</h2>
                    <p>Please enter your username and password</p>
                </div>
                <form id="Login" action="/login" method="post">
                    <div class="group">
                        <input type="text" name="login" class="form-control" id="inputUsername" placeholder="Username">
                    </div>

                    <div class="group">
                        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
                    </div>
                    <div class="forgot">
                        <a href=#>Forgot password?</a>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
