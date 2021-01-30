<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>Teachers Management</h1>
        <h2>
            <a href="/new">Add New Teacher</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Teachers</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Teachers</h2></caption>
            <tr>
                <th>ID</th>
                <th>Teacher Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="teacher" items="${listTeacher}">
                <tr>
                    <td><c:out value="${teacher.id}" /></td>
                    <td><c:out value="${teacher.teachername}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${teacher.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${teacher.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <br><br>
		<a href="logout">Home</a>
		<br><br>
		<a href="logout">Logout</a>
    </div> 
      
</body>
</html>