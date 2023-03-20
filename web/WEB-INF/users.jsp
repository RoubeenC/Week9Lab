<%-- 
    Document   : users
    Created on : Mar 19, 2023, 10:17:30 PM
    Author     : Roubeen Chaudhry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
<!-- First table for viewing the users-->
        <h1>Manage Users</h1>
        ${errorMessage}
        <table border="1">
            <tr>
                <td>Email</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Role</td>
                <td></td>
                <td></td> 
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.roleName}</td>
                    <td><c:url value="/users" var="editUser">
                            <c:param name="email" value="${user.email}" />
                            <c:param name="action" value="edit" />
                        </c:url>
                        <a href=${editUser}>Edit</a></td>
                    <td><c:url value="/users" var="deleteUser">
                            <c:param name="email" value="${user.email}" />
                            <c:param name="action" value="delete" />
                        </c:url>
                        <a href=${deleteUser}>Delete</a></td>
                </tr>
            </c:forEach>
        </table>      
<!-- Section to add a new user-->
        <c:if test="${selUser eq null}">
            <h2>Add User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" required> <br>
                First Name: <input type="text" name="firstName" required> <br>
                Last Name: <input type="text" name="lastName" required> <br>
                Password: <input type="text" name="password" required> <br>
                Role: <select name="role">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                      </select> <br>
                      
                      <input type="hidden" name="action" value="add">
                <input type="submit"value="Add User">
            </form>
        </c:if> 
          
<!-- Section to edit the user-->

        <c:if test="${selUser ne null}">           
            <h2>Edit Users</h2>
            <form action="users" method="post">
                <input type="hidden" name="email" value="${selUser.email}"> 
                Email: ${selUser.email} <br>
                First Name: <input type="text" name="firstName" required value="${selUser.firstName}"> <br>
                Last Name: <input type="text" name="lastName" required value="${selUser.lastName}"> <br>
                Password: <input type="text" name="password" required> <br>
                Role: <select name="role">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                      </select> <br>
                      
                    <input type="hidden" name="action" value="update">
                    <input type="submit" value="Update">
                    <a href="\users" class="button">Cancel</a>
            </form>
        </c:if>
      ${errorMessage}
    </body>
</html>
