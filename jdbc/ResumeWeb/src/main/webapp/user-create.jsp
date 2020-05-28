<%--
    Document   : user
    Created on : Feb 15, 2020, 3:31:06 PM
    Author     : HP
--%>

<%@page import="com.company.dao.inter.CountryDaoInter" %>
<%@ page import="com.company.entity.Country" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create new</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="assets/css/user-create.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-2.2.3.min.js"
            integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
    .
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">
    <script>
        $(document).ready(function () {
            $("#from-datepicker").datepicker({
                format: 'yyyy-mm-dd'
            });
            $("#from-datepicker").on("change", function () {
                var fromdate = $(this).val();

            });
        });
    </script>
<body>
<%
        CountryDaoInter countryDao = com.company.main.Context.instanceCountryDao();
%>
<div class="dt-container">
    <form action="user-create" method="POST" style="margin-bottom: 75px;">
        <center>
            <h1 class="display-4">Create User</h1>
        </center>
        <input type="hidden" name="action" value="create"/>
        <div>
            <label for="name">Name</label>
            <input class="w3-input" type="text" name="name" value=""/>
        </div>
        <div>
            <label for="surname">Surname</label>
            <input class="w3-input" type="text" name="surname" value=""/>
        </div>
        <div>
            <label for="email">Email</label>
            <input class="w3-input" type="text" name="email" value=""/>
        </div>
        <div>
            <label for="phone">Phone</label>
            <input class="w3-input" type="text" name="phone" value=""/>
        </div>

        <div class="form-group">
            <label for="profile-description">Profile Description</label>
            <textarea class="form-control" rows="10" name="profile-description"></textarea>
        </div>

        <div>
            <label for="address">Address</label>
            <input class="w3-input" type="text" name="address" value=""/>
        </div>

        <div class="div-3">
            <div class="div-side-by-side birth-date">
                <label class="control-label" for="datepicker">Birth Date</label>
                <input style="padding-left: 5px; margin-top: 8px;" type="text" id="from-datepicker" name="datepicker"
                       value="" placeholder="Select birthdate"/>
            </div>

            <div class="div-side-by-side">
                <label for="birthPlace" class="birthPlace">Birth Place</label></br>
                <select name="birthPlace" class="input-xlarge">
                    <option value="" selected="selected">Select birthplace</option>
                    <% for (Country c : countryDao.getAll()) {%>
                    <option><%=c.getName()%>
                    </option>
                    <%}%>
                </select>
            </div>

            <div class="div-side-by-side">
                <label for="nationality" class="nationality">Nationality</label></br>
                <select name="nationality" class="input-xlarge">
                    <option value="" selected="selected">Select nationality</option>
                    <% for (Country c : countryDao.getAll()) {%>
                    <option><%=c.getNationality()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>

        <div align="center">
            <button type="submit" class="btn-save btn btn-success" name="save" value="Save">Create</button>
        </div>
    </form>
    <form action="user-create" method="POST" style="margin-bottom: -110px;">
        <button type="submit" class="btn-back btn btn-secondary" name="action" value="back">Back</button>
    </form>
</div>
</body>
</html>
