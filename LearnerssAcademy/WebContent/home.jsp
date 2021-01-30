<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin CPanel - LEARNER'S ACADEMY</title>
</head>
<body>
	<div style="text-align: center">
		<h1>Welcome to LEARNER'S ACADEMY Website Admin Panel</h1>
		<b>${user.fullname} (${user.email})</b>
		<br><br>
		<a href="/ControllerServlet1">MASTERLIST OF SUBJECTS</a>
		<br><br>
		<a href="/ControllerServlet2">MASTERLIST OF STUDENTS</a>
		<br><br>
		<a href="/ControllerServlet3">MASTERLIST OF ALL CLASSES</a>
		<br><br>
		<a href="/ControllerServlet4">ASSIGN CLASSES FOR SUBJECTS FROM THE MASTERLIST</a>
		<br><br>
		<a href="/ControllerServlet5">ASSIGN TEACHER TO A CLASS FOR A SUBJECT</a>
		<br><br>
		<a href="/ControllerServlet6">MASTERLIST OF STUDENTS MUST BE ASSIGNED CLASS</a>
		<br><br>
		<a href="/ControllerServlet7">CLASS REPORT</a>
		<br><br>
		<a href="logout">Logout</a>
	</div>
</body>
</html>