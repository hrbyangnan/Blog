<!DOCTYPE html>
<html>
<%@ page import="dao.ArticleDao" %>
<%@ page import="pojo.Article" %>
<%@ page import="pojo.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ArticleDao" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 12-10-2018
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date nowDate = new Date();
%>
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

        function wol() {
            var now = new Date();
            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);
            var today = now.getFullYear() + "/" + (month) + "/" + (day);
            mydateInput.value = dateString;
            $('#nowDate').val(today);
        }


    </script>
    <style>
        .list-group-item-action {
            cursor: pointer;
        }

        .deleteBlock {
            color: white;
            font-family: "Open Sans", arial, sans-serif;
            font-size: larger;
        }

        .button:hover {
            border-top-color: silver;
            color: #ccc;
            background: silver;

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
            border: 1px solid #e3e3e3;
            padding: 19px;
            position: absolute;
            z-index: 80;
            top: 60px;
            left: 850px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
            z-index: 80;
            background: rgba(0, 0, 0, 0.6);
            margin-bottom: 20px;
            border: 1px solid #e3e3e3;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
            position: absolute;

        }

        #deleteUserAccount {

            border: #34495E;
            margin: 10px auto 10px;
            background: whitesmoke none repeat scroll 0 0;
            border-radius: 5px;
            padding: 50px 70px 70px 71px;
            max-width: 30%;
            height: 40%;

        }

        span.a {
            display: inline-block;
            width: 100px;
            height: 100px;
            padding: 5px;
        }

        #deleteUserAccount {
            z-index: 20;
            top: 420px;
            background: rgba(0, 0, 0, 0.6);
            padding: 19px;
            position: absolute;
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
    <%--TINY MCE--%>
    <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=938z2jiw3kbkqfpu64elzhqarczlyjoaovifhvug2qkzg1j5"></script>
    <script type="text/javascript">
        tinymce.init({
            selector: '#content',
            height: 200,
            plugins: 'fullscreen  link  powerpaste',
            menubar: false,
            toolbar: 'formatselect | bold italic underline strikethrough forecolor | link |  alignleft aligncenter alignright alignjustify' +
                ' | numlist bullist | removeformat'
        });


    </script>

</head>
<body onload="wol()">
<!----------------------------------------------Navbar header------------------------------------------->
<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");
    if (author == null) {%>You have not signed in yet. Please login or register. You should be redirected in 2 seconds.
<script>
    setTimeout(function () {
        document.location = "/index.jsp";
    }, 2000);
</script>

<!---->
<%
    System.out.println("Trying to redirect");
} else {%>

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
                <a class="nav-link" href="/gallery">Media Gallery</a>
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


<!---------------------------------------Page content--------------------------------------------------->
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h2>About Me</h2>
            <h5>Photo of me:</h5>
            <img src="<%=author.getProfilePhoto()%>" height="150">
            <p><%= author.getInfomation() %>
            </p>
            <hr class="hidden-sm hidden-md hidden-lg">
            <div class="list-group " style="padding-top: 73px">
                <a href="/galleryForOne?paramUserID=<%=author.getId()%>" class="list-group-item list-group-item-action"><span
                        class="glyphicon glyphicon-object-align-vertical"></span> Media</a>
                <%--<a href="#" class="list-group-item list-group-item-action"><span--%>
                        <%--class="glyphicon glyphicon-send"></span> Post</a>--%>
                <a class="list-group-item list-group-item-action" class=pointer onclick="document.getElementById
                ('deleteUserAccount').style.display='block'"><span class="glyphicon glyphicon-trash"></span> Delete
                    Account</a>
                <a href="editUserInformation.jsp" class="list-group-item list-group-item-action"><span
                        class="glyphicon glyphicon-edit"></span>Edit Profile</a>
            </div>
        </div>

        <% List<Article> articlesByUser;
            try (ArticleDao ad = new ArticleDao()) {
                System.out.println("Are we getting articles");

                articlesByUser = ad.selectArtByUser(author.getId());
                System.out.printf("If we are the length is " + articlesByUser.size());


                //userSession.setAttribute("userArticles", articlesByUser);
            }
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String date = formatter.format(currentTime);%>


        <div class="col-sm-8">
            <div class="divform">
                <form ENCTYPE="multipart/form-data" id="articleForm" action="<c:url value="/createArticles"/>"
                      method="post">
                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="id" value="">

                    <label for="datetimepicker">Article Date:</label>
                    <input type="date" id="datetimepicker" name="pubTime" id="nowDate"
                           value=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>><br><br>

                    <label for="titleInput">Title:</label>
                    <input id="titleInput" name="articleName" placeholder="Article Title" required="required"><br><br>

                    <label for="content">Content:</label>
                    <textarea id="content" name="articleContent" placeholder="Article Content"></textarea>
                    <br>
                    <label for="avatar">Select multimedia:</label>
                    <input type="file" id="avatar" name="avatar"
                           accept="image/png, image/jpeg, video/webm, video/mp4 audio/mpeg" multiple size="10"><br><br>
                    <input type="submit" class="button"
                           value="Submit">

                </form>
            </div>
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

        <div class="col-sm-8">
            <hr>
            <% for (Article a : articlesByUser) {
                if (a.getPubTime().getTime() < nowDate.getTime() && (a.getVisible() == 1)) { %>
            <div class="divarticle">

                <h2><%=a.getArticleName()%>
                </h2>
                <h5><%=a.getPubTime()%>
                </h5>
                <% if (a.getAllPicPaths().size() > 0) {
                    System.out.println("Trying to get pictures from getAllpicPAth");
                    for (String pic : (a.getAllPicPaths())) { %>

                <img src="<%=pic%>" alt="Pic" height="300">

                <%
                        }
                    }
                %>

                <% if (a.getMedia().size() > 0) {
                    System.out.println("Trying to get multimedia");
                    for (String item : (a.getMedia())) {
                        if (item.toLowerCase().endsWith(".mp3")) {
                            System.out.println("Trying to find audio");%>

                <audio controls>
                    <source src="<%=item%>" type="audio/mpeg">
                    This audio type is not supported.
                </audio>
                <%
                } else if (item.toLowerCase().endsWith(".mp4")) {
                    System.out.println("trying to find video");
                %>

                <video width="320" height="240" controls>
                    <source src="<%=item%>" type="video/mp4 ">
                    That video type is currently not supported
                </video>
                <%
                            }
                        }
                    }
                %>


                <p><%=a.getArticleContent()%>
                </p>
                <br>
                <div class='is-grouped'>
                 <span class="a">
                <form action="/edit" method="post">
                    <input type="hidden" name="scooby" value="<%=a.getArticleId()%>"> <input
                        type="hidden" name="authorID" value="<%= author.getId()%>"> <input
                        type="submit" class="button" value="Edit Article">
                </form>
                 </span>
                    <span class="a">
                <form action="/delete" method="get">
                    <input type="hidden" name="articleID" value="<%= a.getArticleId()%>">
                    <input type="submit" class="button" value="Delete Article">
                </form>
                    </span>
                </div>
            </div>
            <br>
            <%
                    }
                }
            %>
        </div>

<!------------------------------------------------------------------------------------------------------>
<!----------------------------------------Post New Entry------------------------------------------------>

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
<!--------------------------------------delete modal---------------------------------------------------->
<div id="deleteUserAccount" class="modal">
    <form id="navbardelete" method="get" action="/deleteUser">

        <div class="searchContainer">
                    <span onclick="document.getElementById('deleteUserAccount').style.display='none'" class="close"
                          title="Close Modal">&times;</span>
        </div>
        <div class="deleteBox">
            <fieldset>
                <input type="hidden" name="userId" value="<%= author.getId()%>">
                <span class="deleteBlock">Are you sure you want to delete your account<br>
                       you are deleting your account all your files will be deleted</span>
                <hr>
                <br>

                <button type="submit" href="/deleteUser?userId=<%=author.getId()%>" value="<%=author.getId()%>"
                        class="btn btn-primary btn-block" id="button">Delete Account
                </button>
            </fieldset>
        </div>
    </form>
</div>
<!------------------------------------------------------------------------------------------------------>
<!---------------------------------------Blog Footer---------------------------------------------------->
<div class="jumbotron text-center" id="jumbo" style="margin-top: 150px">
    <p><a href="#">About</a></p>
    <p><a href="#">FAQ</a></p>
    <p><a href="#">Contact us</a></p>
    <br>
    <br>
    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
</div>
<!------------------------------------------------------------------------------------------------------>
<%}%>
</body>
</html><%--<%}%>--%>

