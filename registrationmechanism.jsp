<%@page import="Mechanisms.SecurePW"%>
<%@ page import ="java.sql.*" %>
<%@page import="org.apache.commons.codec.binary.Base64" %>

<%
    String uname = request.getParameter("uname");    
    String upass = request.getParameter("upass");
    //String generatedPassword = request.getParameter("generatedPassword");
    String uemail = request.getParameter("uemail");
    
    try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
        
    Statement st = con.createStatement();
    
    byte[] salt = SecurePW.getSalt();
    String generatedPassword = SecurePW.get_SHA_512_SecurePassword(upass, salt); 
    String saltString = Base64.encodeBase64String(salt); 
    
    //int i = st.executeUpdate("insert into user_reg(uname,upass,uemail)values('"+uname+"','"+upass+"','"+uemail+"')");
    int i = st.executeUpdate("insert into user_reg(uname,upass,uemail,passSalt)values"
            + "('"+uname+"','"+generatedPassword+"','"+uemail+"','"+saltString+"')");
    
    response.sendRedirect("index.jsp");
    
    }
    catch(Exception e)
    {
    System.out.println("Invalid email address");
    e.printStackTrace();
    response.sendRedirect("index.jsp");
    }
%>

