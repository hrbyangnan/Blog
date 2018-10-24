<%@ page import="pojo.Article" %>
<%@ page import="pojo.Comment" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="pojo.CommentOnComment" %>
<%@ page import="dao.ComOnComDao" %>
<%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 11-10-2018
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>blog</title>
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


    <style>
        .navbar {min-height:32px !important}

        .toggle-content {
            display: none;
        }

        .toggle-content.is-visible {
            display: block;
        }

        .divcomments {
            border: 2px solid rgba(34,34,34,0.75);
            padding: 5px 40px 10px;
            background: #dddddd;
            width: 860px;

        }

        .innerDivcomments {
            border: 2px solid rgba(34,34,34,0.75);
            padding: 10px 40px 10px;
            background: #dddddd;
            width: 600px;

        }

        .well-searchbox label {
            color: #fff;
        }

        #respond input[type='text'],
        #respond input[type='email'] {
            margin-bottom: 10px;
            display: block;
            width: 50%;

            border: 1px solid rgba(0, 0, 0, 0.1);
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;

            border-radius: 5px;
            height: 50px;
            line-height: 1.4em;

        }

        #respond textarea {
            margin-bottom: 10px;
            display: block;
            width: 50%;
            height: 200px;
            border: 1px solid rgba(0, 0, 0, 0.1);
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;

            border-radius: 5px;
            line-height: 1.4em;

        }

        .card-body {
            margin-left: 2rem;
        }

        body {
            background-color: #eeeeee;
        }


        .gedf-wrapper {
            margin-top: 0.97rem;
        }

        @media (min-width: 992px) {
            .gedf-main {
                padding-left: 4rem;
                padding-right: 4rem;
            }

            .gedf-card {
                margin-bottom: 2.77rem;
            }
        }

    </style>
    <script>
        // =======================


        // =======================

        var toggle = function (elem) {
            elem.classList.toggle('is-visible');
        };

        document.addEventListener('click', function (event) {

            if (!event.target.classList.contains('toggle')) return;
            event.preventDefault();
            var content = document.querySelector(event.target.hash);
            if (!content) return;
            toggle(content);

        }, false);


        var modal = document.getElementById('id02');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</head>

<body>
<!----------------------------------------------Navbar header---------------------------------------------------------->

<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");
//     CommentOnComment commentOnComment = (CommentOnComment) userSession.getAttribute("comOnComContent");

%>
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

<br>
<!---------------------------------Article content--------------------------------------------------------------------->
<div class="container-fluid gedf-wrapper">
    <div class="row">
        <%Article singleArticle = (Article) request.getAttribute("SingleArticle");%>
        <%String path = (String) request.getAttribute("path");%>
        <%String path1 = (String) request.getAttribute("path1");%>

        <div class="col-md-1"></div>
        <div class="col-md-10 gedf-main">
            <div class="card gedf-card">
                <div class="card-header">
                    <div class="mr-2">
                        <%--<img class="rounded-circle" width="45" src="<%=path%>">--%>
                    </div>
                    <div class="ml-2">
                        <h2 class="card-title"><%=singleArticle.getArticleName()%>
                        </h2>
                    </div>
                </div>
                <div class="card-body">
                    <p><%=singleArticle.getArticleContent()%>
                    </p>
                    <br>
                    <div class="h5 m-0"><strong><%="Posted on " + singleArticle.getPubTime() + " by " + singleArticle.getRealName()%></strong></div>
                    <% if (singleArticle.getAllPicPaths().size() > 0) {
                        System.out.println("Trying to get pictures from getAllpicPAth");
                        for (String pic : (singleArticle.getAllPicPaths())) { %>
                    <img src="<%=pic%>" alt="Pic" height="300">

                    <%
                            }
                        }
                    %>

                    <% if (singleArticle.getMedia().size() > 0) {
                        System.out.println("Trying to get multimedia");
                        for (String item : (singleArticle.getMedia())) {
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
                </div>
                <div class="card-footer">
                    <%--<button class="toggle" href="#example1" type="submit"><span class="glyphicon glyphicon-send"></span> Summit comment</button>--%>
                    <% if (author != null) {%>
                    <a class="toggle" href="#example1" class="card-link"><i class="fa fa-comment"></i> Comment</a>
                    <%}%>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 gedf-main">
            <div class="toggle-content" id="example1">
                <div class="card gedf-card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active">Comment</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active">

                                <%---------------------%>
                                <form method="post" action="/addComment">
                                    <table>
                                        <tr>
                                            <label for="comment" class="required"></label>
                                            <textarea name="commentContent" id="comment" style="width: 100%" type ="text" rows="5" cols="90"
                                                      placeholder="What are you thinking?"></textarea>
                                            <br>
                                            <input type="hidden" name="artID"
                                                   value="<%=singleArticle.getArticleId()%>">
                                            <button class="btn btn-success btn-circle text-uppercase" type="submit"
                                                    id="submitComment"> Submit comment
                                            </button>

                                            <%--<input name="submit" type="submit" value="Submit comment"/>--%>
                                        </tr>
                                    </table>
                                </form>
                                <%-------------------------%>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!--------------------------------comment gets displayed here---------------------------------------------------------->
<br>

<div class="container">
    <div class="card-body">
        <% List<Comment> commentsByUser;
            try (CommentDao ad = new CommentDao()) {
                System.out.println("Are we getting comments");
                commentsByUser = ad.selectComByArt(singleArticle.getArticleId());
                userSession.setAttribute("userComments", commentsByUser);
            }%>
        <% for (Comment a : commentsByUser) {
            if ((a.getVisible() == 1)) { %>
        <% List<CommentOnComment> commentOnCommentByUser;
            try (ComOnComDao ad = new ComOnComDao()) {
                commentOnCommentByUser = ad.selectComByCom(a.getCommentId());
                System.out.println(commentOnCommentByUser.size() + "is the size of the comoncombyuserlist");
                userSession.setAttribute("userCommentsOnComments", commentOnCommentByUser);
             }
        %>
        <div class="row">
            <div id="commentOnCommentContent">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <%--<p><a class="toggle" href="#example">Comment on Comment</a></p>--%>
                </div>
                <div class="col-md-1"></div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <%--<img src="<%=path%>" class="img img-rounded img-fluid" style="width:30%">--%>
                <%--<p class="text-secondary text-center">Some Time Ago</p>--%>
            </div>
            <div class="col-md-10">
                <div class="clearfix"></div>
                <div class="divcomments">
                    <br>
                    <p><a><strong><%=a.getUserName()%></strong></a></p>
                    <br>
                    <p><%=a.getCommentContent()%></p>
                    <form action="/deleteComment" method="get">
                        <input type="hidden" name="cID" value="<%= a.getCommentId()%>">
                        <input type="hidden" name="superID" value="<%= singleArticle.getArticleId()%>">
                        <% if (author != null && author.getId() == a.getUserId()) {%>
                        <input type="submit" class="button" value="Delete Comment">
                        <%}%>
                    </form>
                    <br>
                    <%--<form action="/editComments" method="post">--%>
                    <%--<input type="hidden" name="commentContent" value="<%=a.getCommentContent()%>">--%>
                    <%--<input type="hidden" name="id" value="<%= a.getCommentId()%>">--%>
                    <%--<input type="submit" class="button" value="Edit Comment">--%>
                    <%--</form>--%>
                    <%--<p><a class="toggle" href="#<%= a.getCommentId()%>">Comment on Comment</a></p>--%>

                    <%-------------------------------------------------------------------------------------------------------------------%>
                    <div class="card gedf-card">
                        <div class="card-header">
                            <ul class="nav nav-tabs card-header-tabs">
                                <li class="nav-item">
                                    <a class="nav-link active">Comment</a>
                                </li>
                            </ul>
                        </div>
                    <div class="card-body">
                    <div class="tab-pane fade show active">

                    <%---------------------%>
                    <form id="#example" method="post" action="/addCommentOnComment">
                    <table>
                     <tr>
                     <% if (author != null) {%>
                     <label for="ComOnCom" class="required"></label>
                     <textarea name="ComOnCom" id="ComOnCom" type="text" rows="4" cols="80"></textarea>
                     <input type="hidden" name="cID" value="<%=a.getCommentId()%>">
                     <input type="hidden" name="superID" value="<%=a.getArticleId()%>">
                     <input name="submit" type="submit" value="Submit Comment"/>
                      <%}%>
                      </tr>
                      </table>
                      </form>
                      <%-------------------------%>
                      </div>
                      </div>
                            </div>
                    <!--------------------------------------------------------------------------------------------------------------------->
                    <br>
                    <br>
                    <br>
                    <!--------------------------------------------------------------------------------------------------------------------->
                    <!--------------------------------------------------------------------------------------------------------------------->
                    <div class="row">
                        <% System.out.println("Are we reaching...... ");%>
                        <% for (CommentOnComment ab : commentOnCommentByUser) {
                            if (ab.getVisible() == 1) {%>

                        <% System.out.println("Are we getting Comment In Comment................. ");%>
                        <%--<div class="col-md-1">--%>
                            <%--<img src="<%=path1%>"--%>
                                 <%--class="img img-rounded img-fluid" style="width:30%">--%>
                            <%--<p class="text-secondary text-center">Some Time Ago</p>--%>
                        <%--</div>--%>
                        <div class="col-md-10">
                            <div class="clearfix"></div>
                            <div class="innerDivcomments">
                                <p><a><strong><%= ab.getUserName()%>
                                </strong></a></p>

                                <%--<form action="/deleteComOnCom" method="get">--%>
                                <%--<input type="hidden" name="ComOnComID" value="<%= ab.getCommentId()%>">--%>
                                <%--<input type="submit" class="button" value="Delete Comment">--%>
                                <%--</form>--%>
                                <p><%=ab.getCommentContent()%></p>
                                <% System.out.println("does it reach here: " + ab.getCommentContent()); %>

                                <%--<form action="/editComOnCom" method="post">--%>
                                <%--<input type="hidden" name="ComOnComContent" value="<%=ab.getCommentContent()%>">--%>
                                <%--<input type="hidden" name="ComOnComID" value="<%= ab.getCommentId()%>">--%>
                                <%--<input type="submit" class="button" value="Edit Comment">--%>
                                <%--</form>--%>
                            </div>
                            <br>
                        </div>
                        <div class="col-md-2"></div>

                        <% }
                        } %>
                    </div>
                    <br>
                    <!--------------------------------------------------------------------------------------------------------------------->
                </div>
                <br>
                <br>
            </div>
            <div class="col-md-1"></div>
        </div>
        <% }
        }%>
    </div>
</div>


<!----------------------------------------------Blog Footer------------------------------------------------------------>
<div class="jumbotron text-center" id="jumbo" style="margin-top: 150px">
    <p><a href="#">About</a></p>
    <p><a href="#">FAQ</a></p>
    <p><a href="#">Contact us</a></p>
    <br>
    <br>
    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
</div>
<!----------------------------------------------Login Modal------------------------------------------------------------>
<div id="id02" class="modal">
    <div class="container">
        <div class="login-form">
            <div class="main-div">
                <div class="imgContainer">
                    <span onclick="document.getElementById('id02').style.display='none'" class="close"
                          title="Close Modal">&times;</span>
                </div>
                <div class="panel">
                    <h2>Admin Login</h2>
                    <p>Please enter your username and password</p>
                </div>
                <form id="Login" action="/login" method="post">
                    <div class="group">
                        <input type="text" name="login" class="form-control" id="inputUsername"
                               placeholder="Username">
                    </div>

                    <div class="group">
                        <input type="password" name="password" class="form-control" id="inputPassword"
                               placeholder="Password">
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
<%--------------------------------------------------------------------------------------------------------------------%>

</body>
</html>


