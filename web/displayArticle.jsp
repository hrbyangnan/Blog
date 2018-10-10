<%@ page import="pojo.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kfit706
  Date: 10/10/2018
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>oh hey I didn't see you there</title>
</head>
<body>
<div id="articleContent">
    <%
        Article singleArticle = (Article) request.getSession().getAttribute("SingleArticle");
         %>
    <h2><%=singleArticle.getArticleName()%>
    </h2>
    <p><%=singleArticle.getArticleContent()%>
    </p>

</div>


</body>
</html>
