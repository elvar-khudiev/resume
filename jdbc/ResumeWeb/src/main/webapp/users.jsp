<%--
    Document   : user
    Created on : Feb 15, 2020, 3:31:06 PM
    Author     : HP
--%>

<%@page import="com.company.entity.User" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/users.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/9bfc143a49.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="assets/js/users.js"></script>
    <title>Users</title>
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<div class="user_container">
    <center style="margin-bottom: 170px;">
        <h1 class="display-4">Users</h1>
    </center>
    <div class="col-6" style="margin-bottom: 235px">
        <div style="float: left; margin-top: -172px;">
            <form action="users" method="GET">
                <div class="form-group">
                    <label for="name">Name: </label>
                    <input class="form-control" type="text" name="name" id="name" placeholder="Enter name" value=""/>
                </div>
                <div class="form-group">
                    <label for="surname">Surname: </label>
                    <input class="form-control" type="text" name="surname" id="surname" placeholder="Enter surname"
                           value=""/>
                </div>

                <input class="btn btn-primary" type="submit" name="search" value="Search"/>
            </form>
        </div>
        <div style="float: left;">
            <%
                if (request.getSession().getAttribute("loggedInUser") == null) {
            %>

            <div class="div-2" style="padding-left: 495px; bottom: 16px;">
                <form action="users" method="POST">
                    <input type="hidden" name="action" value="signin"/>
                    <input class="btn btn-info" type="submit" style="width: 105px" value="Sign in"/>
                </form>
            </div>

            <%} else {%>

            <div class="div-2" style="padding-left: 495px; bottom: 16px;">
                <form action="users" method="POST">
                    <input type="hidden" name="action" value="logout"/>
                    <input class="btn btn-danger" type="submit" style="width: 105px" value="Log out"/>
                </form>
            </div>

            <%
                }
                if (request.getSession().getAttribute("loggedInUser") != null) {
            %>
            <div class="div-2" style="bottom: -38px; left: 780px">
                <form action="user-create" method="GET">
                    <input class="btn btn-success" type="submit" name="create" value="Create new"/>
                </form>
            </div>
            <%}%>
        </div>
    </div>
    <div class="table_user">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Nationality</th>
                <%
                    if (request.getSession().getAttribute("loggedInUser") != null) {
                %>
                <th></th>
                <th></th>
                <%}%>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                for (User u : users) {
            %>
            <tr>
                <td><%=u.getName()%>
                </td>
                <td><%=u.getSurname()%>
                </td>
                <td><%=u.getNationality().getNationality() == null ? "N/A" : u.getNationality().getNationality()%>
                </td>
                <%
                    if (request.getSession().getAttribute("loggedInUser") != null) {
                %>
                <td style="width: 1px">
                    <form action="user-details-edit" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>">
                        <input type="hidden" name="action" value="update">

                        <button class="btn_table btn_update btn-secondary" type="submit" value="update">
                            <i class="fas fa-pen-square"></i>
                        </button>
                    </form>
                </td>
                <td style="width: 1px">
                    <button class="btn_table btn_delete btn-danger" type="button" value="delete"
                            data-toggle="modal" data-target="#exampleModal" onclick="setIdForDelete(<%=u.getId()%>)">
                        <i class="far fa-trash-alt"></i>
                    </button>
                </td>
                <%}%>
                <td style="width: 1px">
                    <form action="user-details" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>">

                        <button class="btn_table btn-info" style="border-radius: 20px; font-size: 20px;" type="submit"
                                value="info">
                            <i class="fas fa-question-circle"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure ?
            </div>
            <div class="modal-footer">
                <form action="user-details-edit" method="POST">
                    <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
