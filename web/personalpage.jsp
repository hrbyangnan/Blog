<%@ page import="pojo.Article" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ArticleDao" %><%--
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
    <link rel="stylesheet" type="text/css" href="Test.css">
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

        #textarea {
            height: 205px;
        }

    </style>
</head>
<body>
<!----------------------------------------------Navbar header------------------------------------------->
<% HttpSession userSession = request.getSession();
User author = (User) userSession.getAttribute("userInfo"); %>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Blog Name</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="Test.html" class=pointer><span class="glyphicon glyphicon-home"></span> Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropbtn" onclick="myFunction()">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= author.getRealName()%></a></li>
                <ul class="dropdown-menu">
                    <li><a href="#">Page1</a></li>
                    <li><a href="#">Page2</a></li>
                    <li><a href="#">Page3</a></li>
                </ul>
                </li>
                <li><a href="Test.html"><span class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
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
    }
    //List<Article> articles = (List) userSession.getAttribute("userArticles");
for(Article a: articlesByUser){ %>
        <div class="col-sm-8">
            <h2><%=a.getArticleName()%></h2>
            <h5><%=a.getPubTime()%></h5>
            <div class="fakeimg">Fake Image</div>

            <p><%=a.getArticleContent()%></p>
            <br>
            <p> test loop</p>
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
            <div class="list-group " style="padding-top: 73px">
                <a href="#" class="list-group-item list-group-item-action">Media</a>
                <a href="#" class="list-group-item list-group-item-action">Post</a>
                <a href="#" class="list-group-item list-group-item-action">Comments</a>
                <a href="#" class="list-group-item list-group-item-action">Appearance</a>
                <a href="#" class="list-group-item list-group-item-action">Settings</a>
            </div>
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
<!------------------------------------------------------------------------------------------------------>

</body>
</html>