<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LEARNER'S ACADEMY Application</title>
</head>
<body>
    <center>
        <h1>CLASS REPORT</h1>
        <h2>
            <a href="/new">Add New Record</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Records</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>CLASS REPORT</h2></caption>
            <tr>
                <th>ID</th>
                <th>Class</th>
                <th>Subject</th>
                <th>Teacher</th>
                <th>Student</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="record" items="${listRecord}">
                <tr>
                    <td><c:out value="${record.id}" /></td>
                    <td><c:out value="${record.classname}" /></td>
                    <td><c:out value="${record.subjectname}" /></td>
                    <td><c:out value="${record.teachername}" /></td>
                    <td><c:out value="${record.studentname}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${record.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${record.id}' />">Delete</a>                     
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