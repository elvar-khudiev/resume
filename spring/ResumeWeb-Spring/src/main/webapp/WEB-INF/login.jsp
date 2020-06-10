<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!--<link rel="stylesheet" href="../assets/css/login.css">-->
</head>
<body>
<div class="cont">
    <center>
        <div class="col-3">
            <h2 class="display-4" style="margin: 20px;">Login</h2>
            <form action="login" method="POST">
                <div class="form-group" style="margin: 3px 0">
                    <input type="text" class="form-control email" name="username"
                           style="border-radius: 0;" aria-describedby="emailHelp" placeholder="E-mail">
                </div>
                <div class="form-group" style="margin: 3px 0 4px 0">
                    <input type="password" class="form-control" style="border-radius: 0;"
                           name="password" placeholder="Password">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary"
                        style="width: 100%; margin-bottom: 5px; border-radius: 0;">Sign in
                </button>
            </form>
        </div>
    </center>
</div>
</body>
</html>
