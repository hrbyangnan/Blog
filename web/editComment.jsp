<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pojo.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 20-10-2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editComment</title>
</head>
<body>
<div class="container">
    <% Comment oldComment = (Comment) request.getAttribute("id");
    System.out.println("editArticle.jsp comment ID is " + oldComment.getCommentId());%>

    <div class="col-sm-8">
        <div class="divform">


            <form id="commentForm" action="<c:url value="/updateComment2"/>" method="post">
                <input type="hidden" name="action" value="">
                <input type="hidden" name="oldCommentID" value="<%=oldComment.getCommentId()%>">


                <label for="content">Content:</label>
                <textarea id="content" name="oldContent"
                          placeholder="Article Content"><%=oldComment.getCommentContent()%></textarea>

                <input type="submit" class="button"
                       value="Submit">

            </form>
        </div>
    </div>
</div>
</body>
</html>
