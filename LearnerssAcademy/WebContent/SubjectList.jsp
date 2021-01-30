<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>Subjects Management</h1>
        <h2>
            <a href="/new">Add New Subject</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Subjects</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Subjects</h2></caption>
            <tr>
                <th>ID</th>
                <th>Subject Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="subject" items="${listSubject}">
                <tr>
                    <td><c:out value="${subject.id}" /></td>
                    <td><c:out value="${subject.subjectname}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${subject.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${subject.id}' />">Delete</a>                     
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