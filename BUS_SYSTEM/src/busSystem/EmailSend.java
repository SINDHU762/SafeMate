/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author ELCOT
 */
public class EmailSend {
    public static void sendEmail(String to,String name,String type,String amount,String con){
        //String from="deeparajendran54@gmail.com";
        //String host="localhost";
        //String port="25";
        
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	 
	         Properties props = System.getProperties();
	         props.setProperty("mail.smtp.host", "smtp.gmail.com");
	         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	         props.setProperty("mail.smtp.socketFactory.fallback", "false");
	         props.setProperty("mail.smtp.port", "465");
	         props.setProperty("mail.smtp.socketFactory.port", "465");
	         props.put("mail.smtp.auth", "true");
	         props.put("mail.debug", "true");
	         props.put("mail.store.protocol", "pop3");
	         props.put("mail.transport.protocol", "smtp");
	         final String username = "deepamalathi54@gmail.com";
	         final String password = "ykxhfxnliwpwroqr";
	         
                 try{
	         Session session = Session.getInstance(props, 
	                              new Authenticator(){
	                                 protected PasswordAuthentication getPasswordAuthentication() {
	                                    return new PasswordAuthentication(username, password);
	                                 }});
	     
	         Message msg = new MimeMessage(session);

                 String sub="SAFE MATE";
                 if(type.equals("Child Login")){
                 String message="Your child "+ name +" is safe with us...!\nDon't worry.\nLet your child to have happy journey!!!";
	         msg.setFrom(new InternetAddress(to));
	         msg.setRecipients(Message.RecipientType.TO, 
	                          InternetAddress.parse(to,false));
	         msg.setSubject(sub);
	         msg.setText(message);
	         
	         msg.setSentDate(new java.util.Date());
	         Transport.send(msg);
                 }else if(type.equals("Ticket Issued")){
                     String conName="",Phone="";
                    try{
                    String query="SELECT * FROM CONDUCTOR WHERE CID=?";
                    PreparedStatement ps=(PreparedStatement)DbConnect.getConnection().prepareStatement(query);
                    ps.setString(1,con);
                    //System.out.println(con);
                    ResultSet rs=ps.executeQuery();
                       
                    if(rs.next()){
                        conName = rs.getString("CNAME");
                        Phone=rs.getString("PHONE");
                    }
                    
                }catch(Exception e){
                    System.out.println(e);
                }
                    String message="Hi, "+ name +", your ticket price is Rs. "+amount+",\n"+"\n\nContact : \nConductor : "+conName+" - "+Phone+"\n\nHappy Journey with SAFEMATE!!";
                    msg.setFrom(new InternetAddress(to));
                    msg.setRecipients(Message.RecipientType.TO, 
                                      InternetAddress.parse(to,false));
                    msg.setSubject(sub);
                    msg.setText(message);

                    msg.setSentDate(new java.util.Date());
                    Transport.send(msg);
                 }
                 else if(type.equals("Register")){
                     
                 }
                     
	      }catch (MessagingException e){ System.out.println(e);}
    }
}
