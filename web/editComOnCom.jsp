<%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 21-10-2018
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pojo.Comment" %>
<%@ page import="pojo.CommentOnComment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editComment</title>
</head>
<body>
<div class="container">
    <% CommentOnComment oldComment = (CommentOnComment) request.getAttribute("ComOnComID");
        System.out.println("editArticle.jsp comment ID is " + oldComment.getCommentId());%>

    <div class="col-sm-8">
        <div class="divform">


            <form id="commentForm" action="<c:url value="/updateComOnCom2"/>" method="post">
                <input type="hidden" name="action" value="">
                <input type="hidden" name="oldComOnComID" value="<%=oldComment.getCommentId()%>">


                <label for="content">Content:</label>
                <textarea id="content" name="oldComOnCom"
                          placeholder="Article Content"><%=oldComment.getCommentContent()%></textarea>

                <input type="submit" class="button"
                       value="Submit">

            </form>
        </div>
    </div>
</div>
</body>
</html>

