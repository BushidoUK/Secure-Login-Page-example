package Mechanisms;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
/**
 * @see HttpServlet#HttpServlet()
 */
public UserProfile() 
{
    super();
    // TODO Auto-generated constructor stub
}
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at:  ").append(request.getContextPath());
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    // TODO Auto-generated method stub
    doGet(request, response);
    PrintWriter out = response.getWriter();
    String uname = request.getParameter("uname");
    String upass = request.getParameter("upass");
    String admin = request.getParameter("uname");
       
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select uname, upass, passSalt from user_reg where uname='"+uname+"'");
      
    if(rs.next()) 
    {       
        String saltString = rs.getString("passSalt");
        byte[] salt = Base64.decodeBase64(saltString);
        
        String validPass = rs.getString("upass");
        String tryPass = SecurePW.get_SHA_512_SecurePassword(upass, salt);
        
        if(validPass.equals(tryPass)) {
        
        Cookie loginCookie = new Cookie("uname",uname);
        loginCookie.setMaxAge(30*60);
        response.addCookie(loginCookie);
        if(uname.equals("admin")) {
            response.sendRedirect("http://localhost:8080/BCFT_v3/GenesisBlock.jsp?name="+rs.getString("uname"));
        } else {
        response.sendRedirect("http://localhost:8080/BCFT_v3/WelcomeUser.jsp?name="+rs.getString("uname"));
        }
        
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5*60);
        session.setAttribute("uname", uname);    
                 
    }
    }
    
    else 
    {
        out.println(" Error: Wrong Username and Password");
    }   
    }
    
    catch (ClassNotFoundException e) 
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    catch (SQLException e) 
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchAlgorithmException ex) {
          Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
      }
    
}
}