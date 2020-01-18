package Mechanisms;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
public class SecurePW 
{

//public static String getSecurePassword() throws NoSuchAlgorithmException
// {
//        String passwordToHash = "upass";
//        byte[] salt = getSalt();
//        
//        String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
//        // System.out.println(securePassword);
//        return securePassword;
// } 

  public static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException
    {
   String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
        }
        return generatedPassword;
    }
     
//Add salt
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
}
     
   