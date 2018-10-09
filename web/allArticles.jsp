<%@ page import="java.util.List" %>
<%@ page import="pojo.Article" %><%--
  Created by IntelliJ IDEA.
  User: kfit706
  Date: 9/10/2018
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
some html
<%
    List<Article> articleList = (List<Article>) request.getSession().getAttribute("AllArticlesPojo");
    for (Article a : articleList) {%>
<h2><%=a.getArticleName()%>
</h2>
<p><%=a.getArticleContent()%>
</p>
<%
    }
%>
</body>
</html>
