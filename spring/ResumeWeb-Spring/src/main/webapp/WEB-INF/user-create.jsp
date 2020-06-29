<%--
    Document   : user
    Created on : Feb 15, 2020, 3:31:06 PM
    Author     : HP
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create new</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="assets/css/user-create.css">

    <script src="https://code.jquery.com/jquery-2.2.3.min.js"
            integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">
    <%--    <script>--%>
    <%--        $(document).ready(function () {--%>
    <%--            $("#from-datepicker").datepicker({--%>
    <%--                format: 'yyyy-mm-dd'--%>
    <%--            });--%>
    <%--            $("#from-datepicker").on("change", function () {--%>
    <%--                var fromdate = $(this).val();--%>

    <%--            });--%>
    <%--        });--%>
    <%--    </script>--%>
<body>
<form:form action="user-create" method="POST" modelAttribute="user">
<center>
    <h1 class="display-4">Create User</h1>
</center>
<div class="form-group">
    <label for="name">Name: </label>
    <form:input path="name" placeolder="Enter name" class="form-control"/>
</div>
<div class="form-group">
    <label for="surname">Surname: </label>
    <form:input path="surname" placeolder="Enter surname" class="form-control"/>
</div>
<div class="form-group">
    <label for="email">Email: </label>
    <form:input path="email" placeolder="Enter email" class="form-control"/>
</div>
<div class="form-group">
    <label for="password">Password: </label>
    <form:input path="password" placeolder="Enter password" class="form-control"/>
</div>
<div class="form-group">
    <label for="phone">Phone: </label>
    <form:input path="phone" placeolder="Enter phone" class="form-control"/>
</div>
<div class="form-group">
    <label for="profile_description">Profile description: </label>
    <form:input path="profileDescription" placeolder="Enter profile description" class="form-control"/>
</div>
<div class="form-group">
    <label for="address">Address: </label>
    <form:input path="address" placeolder="Enter address" class="form-control"/>
</div>
<div class="form-group">
<%--    <label for="birth_date">Birthdate: </label>--%>
<%--    <form:input path="birthdate" placeolder="Enter birthdate" class="form-control"/>--%>
</div>
<div class="div-3">
<%--    <div class="div-side-by-side birth-date">--%>
<%--        <label class="control-label" for="datepicker">Birth Date</label>--%>
<%--        <input style="padding-left: 5px; margin-top: 8px;" type="text" id="from-datepicker" name="datepicker"--%>
<%--               value="" placeholder="Select birthdate"/>--%>
<%--    </div>--%>

    <div class="div-side-by-side">
        <label for="birthPlace" class="birthPlace">Birth Place</label></br>
        <select name="birthPlace" class="input-xlarge">
            <option value="" selected="selected">Select birthplace</option>
            <c:forEach items="${countries}" var="c">
                <option>${c.getName()}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="div-side-by-side">
        <label for="birthPlace" class="nationality">Nationality</label></br>
        <select name="nationality" class="input-xlarge">
            <option value="" selected="selected">Select nationality</option>
            <c:forEach items="${countries}" var="c">
                <option>${c.getNationality()}
                </option>
            </c:forEach>
        </select>
    </div>

    <form:button type="submit" class="btn btn-primary" name="action" value="create">
        Create
    </form:button>
    </form:form>
    <form:form action="users" method="GET" modelAttribute="user">
        <form:button type="submit" class="btn-back btn btn-secondary" name="action" value="back">
            Back
        </form:button>
    </form:form>
</div>
</body>
</html>
