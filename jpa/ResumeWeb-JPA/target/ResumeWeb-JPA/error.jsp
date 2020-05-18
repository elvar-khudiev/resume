<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 20.02.2020
  Time: 17:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why are you here?</title>
</head>
<body background="assets/images/err.png">
<center>
    <h2 style="
        font: 20px 'Microsoft YaHei UI';
        position: relative;
        top: 390px;
        right: 5px;">
        <%=request.getParameter("msg")%>
<%--    You are not allowed to access this page!--%>
    </h2>
</center>
</body>
</html>
