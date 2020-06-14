<%--
    Document   : user
    Created on : Feb 15, 2020, 3:31:06 PM
    Author     : HP
--%>

<%@page import="com.company.entity.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="assets/css/user-details.css">

<body>
<div class="dt-container">
    <form:form action="users" method="GET" modelAttribute="user">
        <center>
            <h1 class="display-4">Details</h1>
        </center>
        <div>
            <label for="name">Name</label>
            <h5 type="text" name="name" class="col-4">${user.getName()}</h5>
        </div>
        <div>
            <label for="surname">Surname</label>
            <h5 type="text" name="surname" class="col-4">${user.getSurname()}</h5>
        </div>
        <div>
            <label for="email">Email</label>
            <h5 type="text" name="email" class="col-4">${user.getEmail()}</h5>
        </div>
        <div>
            <label for="phone">Phone</label>
            <h5 type="text" name="phone" class="col-4">${user.getPhone()}</h5>
        </div>

        <div class="div-image">
        </div>

        <div>
            <label for="profile-description">Profile Description</label>
            <h5>
                <textarea disabled class="form-control" rows="10" name="profile-description"
                          style="font-size: 18px; border: none; background-color: #f5eded;">${user.getProfileDescription()}</textarea>
            </h5>
        </div>

        <div class="div-3">
            <div class="div-side-by-side">
                <label for="address">Address</label>
                <h5 type="text" name="address" class="col-8">${user.getAddress()}</h5>
            </div>
                <%--                <%--%>
                <%--                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");--%>
                <%--                    Date utilDate = new Date(u.getBirthDate().getTime());--%>
                <%--                    String dateStr = format.format(utilDate);--%>
                <%--                %>--%>
            <div class="div-side-by-side birth-date">
                <label class="control-label" for="datepicker">Birth Date</label>
                <h5 type="text" name="datepicker" class="col-8">${user.getBirthdate()}</h5>
            </div>

            <div class="div-side-by-side">
                <label for="birthPlace">Birth Place</label></br>
                <h5 type="text" name="birthplace" class="col-8">${user.getBirthplaceId().getName()}</h5>
            </div>

            <div class="div-side-by-side">
                <label for="nationality">Nationality</label></br>
                <h5 type="text" name="nationality" class="col-8">${user.getNationalityId().getNationality()}</h5>
            </div>
        </div>
        <div align="center">
            <form:button type="submit" class="btn btn-secondary" style="width: 100px;" name="action" value="back">
                Back
            </form:button>
        </div>
    </form:form>
</div>
</body>
</html>
