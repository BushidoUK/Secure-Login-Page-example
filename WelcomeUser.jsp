<%@page import="java.util.UUID"%>
<%@page session="true" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome User!</title>
</head>

<% 
//check the session to see if user is logged in and if the "uname" is in the database
String uname=(String)session.getAttribute("uname"); 
//redirect user to login page if not logged in
if(uname==null)
{
    //when there is no user name then redirect to index
    response.sendRedirect("index.jsp");
} 
%>    

<div class="limiter">
<div class="container-login100">
<div class="wrap-login100">
<div class="login100-pic">
<img src="images/filecubelogo.png" alt="IMG">
</div>

<%
out.println("Welcome to the Main Menu for File Cube's Block Chain File Transfer System!");
out.println(String.format("&#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; &#160; Welcome, %s" ,(String)session.getAttribute("uname")));
%>

<form method="post" enctype="multipart/form-data" action="FileUpload">
<centre>
<table>
<tbody>

<tr>
    <td>
    File to upload: <input class="input100" type="file" name="file">
    </td>
</tr>

<tr>
    <td>
        Description: <input class="input100" type="text" name="description">
    </td>
</tr>

<tr>
    <td>
        <br>
    </td>
</tr>

<tr>
    <td>
        <input class="login100-form-btn" type="submit" value="Submit" />
    </td>
</tr>

<tr>
    <td>
        <br>
    </td>
</tr>

<tr>
    <td>
         <a class="login100-form-btn"  href="http://localhost:8080/BCFT_v3/FileDownload.jsp"> File Download</a>
    </td>
</tr>

<tr>
    <td>
        <br>
    </td>
</tr>

<tr>
    <td>
        <br>
    </td>
</tr>

<tr>
    <td>
        <a class="login100-form-btn"  href="http://localhost:8080/BCFT_v3/NewNode.jsp"> New Node Creation</a>
    </td>
</tr>

<tr>
    <td>
        <br>
    </td>
</tr>

<tr>
    <td>
         <a class="login100-form-btn" href="logout.jsp"> Logout</a>
    </td>
</tr>

</tbody>
</table>
</centre>

</form>

<br><br>