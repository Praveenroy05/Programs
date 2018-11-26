package Com.MBSWrapper;

import java.sql.*;

public class DatabaseConnection
{ 
	private String connection;
	private Connection con;
	private ResultSet rs = null;
       public void databaseConnection(){  
       try{
    	      connection="jdbc:mysql://ratematch-db.cluster-cdxmeh9zn3o2.us-east-1.rds.amazonaws.com:3306/rate_match_qa?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
              //Class.forName("com.mysql.cj.jdbc.Driver"); 
              con=DriverManager.getConnection(connection,"ratematch","NYCTeam333");
          }
          catch(Exception e)
          {
        	  System.out.println(e);
          }
       }
       
       public ResultSet queryExecution(String Query)
       {
    	   
    	   try {
    	   databaseConnection();       
 	      //here sonoo is database name, root is username and password  
           Statement stmt=con.createStatement();  
           rs=stmt.executeQuery(Query);
           
    	   }
    	   catch(Exception e)
    	   {
    		   System.out.println(e);
    	   }
    	   return rs;
       }
       
       public void printColumns(String Query)
       {      
    	   try {
    	      ResultSet rs=queryExecution(Query);
              ResultSetMetaData metaData = rs.getMetaData();
              int count = metaData.getColumnCount(); //number of column
              String columnName[] = new String[count];

              for (int i = 1; i <= count; i++)
              {
                 columnName[i-1] = metaData.getColumnLabel(i);
                 System.out.println(columnName[i-1]);
              }
              con.close(); 
    	   }
    	   
    	   catch(Exception e)
    	   {
    		   System.out.println(e);
    	   }
       }
       
       public String getColumnData(String Query,String Coulumnname)
       {
    	   String columndata=null;
    	   try 
    	   {
    		  ResultSet rs=queryExecution(Query);
    		  rs.next();
    		  columndata=rs.getString(Coulumnname);
    		  con.close(); 
    		  /*while(rs.next())  {
              //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+rs.getString("resetPasswordToken")); 
              //int id = rs.getInt("id");
              //String firstName = rs.getString("firstName");
              //String lastName = rs.getString("lastName");
              //Date dateCreated = rs.getDate("dateCreated");
              }*/
               
          }
          catch(Exception e){
        	  System.out.println(e);
          }  
    	   return columndata;
      }  
       
      public static void main(String args[])
      {
    	  DatabaseConnection dc=new DatabaseConnection();
    	  String data=dc.getColumnData("select * from users where username='snandigam@traveltripper.com'","username");
    	  dc.printColumns("select * from users where username='snandigam@traveltripper.com'");
    	  System.out.println(data);
    	  
      }
}  
