<%@ page import="dao.ArticleDao" %>
<%@ page import="dao.ComOnComDao" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dao.UserDaoImp" %>
<%@ page import="pojo.Article" %>
<%@ page import="pojo.Comment" %>
<%@ page import="pojo.CommentOnComment" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>adminPage form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="adminPage.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <style>
        .buttonReset {
            background-color: #008CBA;
        }

        .table {
            border-radius: 12px;
        }

        .table thead tr {
            background-color: lavender;
            border: 2px solid #ddd;
        }

        .table thead tr th {
            border: 2px solid #ddd;
        }

        .table {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .table tr td {
            border: 2px solid #ddd;
            text-align: left;
        }
    </style>
    <%--<script>--%>
        <%--function myFunction0() {--%>
            <%--var x = document.getElementById("myDIV0");--%>
            <%--if (x.style.display === "none") {--%>
                <%--x.style.display = "block";--%>
            <%--} else {--%>
                <%--x.style.display = "none";--%>
            <%--}--%>
        <%--}--%>

        <%--function myFunction1() {--%>
            <%--var x = document.getElementById("myDIV1");--%>
            <%--if (x.style.display === "none") {--%>
                <%--x.style.display = "block";--%>
            <%--} else {--%>
                <%--x.style.display = "none";--%>
            <%--}--%>
        <%--}--%>

        <%--function myFunction2() {--%>
            <%--var x = document.getElementById("myDIV2");--%>
            <%--if (x.style.display === "none") {--%>
                <%--x.style.display = "block";--%>
            <%--} else {--%>
                <%--x.style.display = "none";--%>
            <%--}--%>
        <%--}--%>

        <%--function myFunction3() {--%>
            <%--var x = document.getElementById("myDIV3");--%>
            <%--if (x.style.display === "none") {--%>
                <%--x.style.display = "block";--%>
            <%--} else {--%>
                <%--x.style.display = "none";--%>
            <%--}--%>
        <%--}--%>

        <%--function myFunction4() {--%>
            <%--var x = document.getElementById("myDIV4");--%>
            <%--if (x.style.display === "none") {--%>
                <%--x.style.display = "block";--%>
            <%--} else {--%>
                <%--x.style.display = "none";--%>
            <%--}--%>
        <%--}--%>

    <%--</script>--%>

</head>


<body>
<% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");
    if ((author == null)||author.getId()!= 62) {%>You are not administrator. You should be redirected in 2 seconds.
<script>
    setTimeout(function () {
        document.location = "/index.jsp";
    }, 2000);
</script>

<!---->
<%
    System.out.println("Trying to redirect");
} else {%>
<header role="banner">
    <h1>Admin Panel</h1>
    <ul class="utilities">
        <li><a href="index.jsp" class=pointer><span class="glyphicon glyphicon-home"></span> Home</a></li>
        <%--<li><a href="/getAllArticles"><span class="glyphicon glyphicon-th-large"></span> Archives</a></li>--%>

        <li><a href="personalpage.jsp"><span class="glyphicon glyphicon-user"></span> <%=author.getRealName()%></a></li>
        <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
    </ul>
</header>
<nav role='navigation'>
    <ul class="main">
        <li class="edit" onclick="myFunction1()"><a href="#myDIV1"> Manage Articles</a></li>
        <li class="comments" onclick="myFunction2()"><a href="#myDIV2"> Manage Comments</a></li>
        <li class="users" onclick="myFunction3()"><a href="#myDIV3"> Manage Users</a></li>
        <li class="write" onclick="myFunction4()"><a href="#myDIV4"> Manage Comments on comments</a></li>
    </ul>
</nav>

<main role="main">
    <section class="panel important">
        <h2>Welcome adminName</h2>
    </section>

</main>




<!------------------------------------------------------------------------------------------------------>
<div id="myDIV0">
    <div id="myDIV3">
        <div class="container">
            <div class="row">
                <h2>Manage Users</h2>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>User ID</th>
                        <th>UserName</th>
                        <th>UserPasswd</th>
                        <th>ProfilePath</th>
                        <th>Email</th>
                        <th>Action   <a href="RegistrationForm.jsp">Add a new user</a></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        UserDaoImp udao = new UserDaoImp();
                        List<User> userList = udao.getAllUser();
                        if (userList != null) {
                            for (User u : userList) {
                                String uname=u.getName();%>
                    <tr>
                        <td><%=u.getId()%>
                        </td>
                        <td><%=u.getName()%>
                        </td>
                        <td><%=u.getPassword()%>
                        </td>
                        <td><%=u.getProfilePhoto()%>
                        </td>
                        <td><%=u.getEmail()%>
                        </td>
                        <td>

                            <a href="RemoveUserServlet?UserId=<%=u.getId()%>">Remove</a>
                            <a href="AdminForgotPassword.jsp?username=<%=uname%>">Reset password</a>
                        </td>
                        <%
                            }
                        } else {%>
                        There has been an error and the user list is empty.
                        <%
                            }
                        %>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="myDIV2">
        <div class="container">
            <div class="row">
                <h2>Manage Comments</h2>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>CommentId</th>
                        <th>ArticleId</th>
                        <th>ArticleTitle</th>
                        <th>CommentContent</th>
                        <th>UserId</th>
                        <th>UserName</th>
                        <th>Visible</th>
                        <td>Action</td>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        CommentDao cdao = new CommentDao();
                        List<Comment> commentList = cdao.getAllComments();
                        if (commentList != null) {
                            for (Comment c : commentList) {%>
                    <tr>
                        <td><%=c.getCommentId()%></td>
                        <td><%=c.getArticleId()%></td>
                        <% String title=cdao.getArticleTitle(c.getCommentId());%>
                        <td><%=title%></td>
                        <td><%=c.getCommentContent()%></td>
                        <td><%=c.getUserId()%></td>
                        <td><%=c.getUserName()%></td>
                        <td><%=c.getVisible()%></td>


                        <td>
                            <a href="HideCommentServlet?commentId=<%=c.getCommentId()%>">Hide</a>
                            <a href="ShowCommentServlet?commentId=<%=c.getCommentId()%>">Show</a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {%>
                    There has been an error and the comment list is empty.
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="myDIV4">
        <div class="container">
            <div class="row">
                <h2>Manage Comments on Comments</h2>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>CommentId</th>
                        <th>FatherCommentId</th>
                        <th>CommentContent</th>
                        <th>UserId</th>
                        <th>UserName</th>
                        <th>Visible</th>
                        <td>Action</td>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ComOnComDao cocdao = new ComOnComDao();
                        List<CommentOnComment> comoncomList = cocdao.getAllComoncom();
                        if (comoncomList != null) {
                            for (CommentOnComment cc : comoncomList) {%>
                    <tr>
                        <td><%=cc.getCommentId()%></td>
                        <td><%=cc.getFatherCommentId()%></td>
                        <td><%=cc.getCommentContent()%></td>
                        <td><%=cc.getUserId()%></td>
                        <td><%=cc.getUserName()%></td>
                        <td><%=cc.getVisible()%></td>
                        <td>
                            <a href="HideComOnComServlet?comOnComId=<%=cc.getCommentId()%>">Hide</a>
                            <a href="ShowComOnComServlet?comOnComId=<%=cc.getCommentId()%>">Show</a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {%>
                    There has been an error and the commentincomment list is empty.
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="myDIV1">
        <div class="container">
            <div class="row">
                <h2>Manage Articles</h2>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Article Id</th>
                        <th>Article Name</th>
                        <th>Author</th>
                        <th>Publish Time</th>
                        <th>Visible</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ArticleDao dao = new ArticleDao();
                        List<Article> articleList = dao.getAllArticles();
                        if (articleList != null) {
                            for (Article a : articleList) {%>
                    <tr>
                        <td><%=a.getArticleId()%>
                        </td>
                        <td><%=a.getArticleName()%>
                        </td>
                        <td><%=a.getRealName()%>
                        </td>
                        <td><%=a.getPubTime()%>
                        </td>
                        <td><%=a.getVisible()%>
                        </td>
                        <td>
                            <a href="">Edit</a>
                            <a href="HideArticleServlet?articleId=<%=a.getArticleId()%>">Hide</a>
                            <a href="ShowArticleServlet?articleId=<%=a.getArticleId()%>">Show</a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {%>
                    There has been an error and the article list is empty.
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!------------------------------------------------------------------------------------------------------>
<%
    }
%>
</body>
</html>