package Data;

import Util.ConnectionPool;
import Util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import Constants.UserConstants;
import Util.PasswordUtil;

public class UserDB {

	
     public static User getUser(String email) {
	         ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(UserConstants.GET_USER);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
	            	user.setNumPostedStudies(rs.getInt("studies"));
	            	user.setNumParticipation(rs.getInt("participation"));
	            	user.setNumCoins(rs.getInt("coins"));
	               return user;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
     
     
	  public static List<User> getUsers() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	       ArrayList<User> users = new ArrayList<User>();
	        try {
	            ps = connection.prepareStatement(UserConstants.GET_USERS);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	               	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
	            	user.setNumPostedStudies(rs.getInt("studies"));
	            	user.setNumParticipation(rs.getInt("participation"));
	            	user.setNumCoins(rs.getInt("coins"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return users;
	    }
     
	 public static boolean validateUser(String email, String pwd) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
                ResultSet rs2=null;
                try {
                    ps=connection.prepareStatement("select * from Saltvalue where username=? ");
                    ps.setString(1, email);
                    rs=ps.executeQuery();
                    String salt="";
                    while(rs.next())
                    {
                       salt=rs.getString("salt");
                    }
	            ps = connection.prepareStatement(UserConstants.VALIDATE_USER);
	            ps.setString(1, email);
	            ps.setString(2, PasswordUtil.hashAndSaltPassword(pwd, salt));
	            rs2= ps.executeQuery();
	            while (rs2.next())
	            {
	             return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return false;
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return false;
	    }

                     	
     public static User getTempUser(String token) {
	         ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement("SELECT * from Tempuser WHERE userToken = ?");
	            ps.setString(1, token);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
	            	user.setUserType(rs.getString("type"));
	            	user.setNumPostedStudies(rs.getInt("studies"));
	            	user.setNumParticipation(rs.getInt("participation"));
	            	user.setNumCoins(rs.getInt("coins"));
	               return user;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
     
            public static void deleteTempUser(String token) {
	         ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement("DELETE from TempUser WHERE userToken = ?");
	           ps.setString(1, token);
	            ps.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e);
	            
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        
	    }

              public static int addTempUser(User user,String token){
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        try {
                   java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	            ps = connection.prepareStatement(UserConstants.ADD_TEMP_USER);
                     //String pass=PasswordUtil.hashPassword(user.getPassword());
                     ps.setString(1, token);
	            ps.setString(2, user.getEmail());
	            ps.setString(3, user.getPassword());
	            ps.setString(4, user.getUserType());
	            ps.setInt(5, user.getNumPostedStudies());
	            ps.setInt(6, user.getNumParticipation());
	            ps.setInt(7, user.getNumCoins());
	            ps.setString(8, user.getName());
                    ps.setTimestamp(9, date);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
            
    public static int addUser(User user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        try {
                    String[] arr=PasswordUtil.hashAndSaltPassword(user.getPassword());
                    String pass=arr[0];
                    String salt=arr[1];
                    ps = connection.prepareStatement(UserConstants.ADD_USER);
	            ps.setString(1, user.getEmail());
	            ps.setString(2, pass);
	            ps.setString(3, user.getUserType());
	            ps.setInt(4, user.getNumPostedStudies());
	            ps.setInt(5, user.getNumParticipation());
	            ps.setInt(6, user.getNumCoins());
	            ps.setString(7, user.getName());
                    ps.executeUpdate();
                    ps= connection.prepareStatement("INSERT into Saltvalue (username,password,salt) Values (?, ? ,?)");
                    ps.setString(1, user.getEmail());
                    ps.setString(2, pass);
                    ps.setString(3, salt);
                    return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static void updateParticipation(String email, int parNum) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(UserConstants.UPDATE_PARTICIPATION);
	            ps.setInt(1, parNum);
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
 public static void updateUser(User user, String password) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        try {
                     String[] arr=PasswordUtil.hashAndSaltPassword(password);
                    String pass=arr[0];
                    String salt=arr[1];
	            ps = connection.prepareStatement("UPDATE User SET password = ? WHERE username = ? ");
	            ps.setString(1, pass);
	            ps.setString(2, user.getEmail());
	            ps.executeUpdate();
                    ps= connection.prepareStatement("UPDATE Saltvalue SET password=? WHERE username = ?");
                    ps.setString(1, pass);
                    ps.setString(2, user.getEmail());
                    ps.executeUpdate();
                    ps= connection.prepareStatement("UPDATE Saltvalue SET salt=? WHERE username = ?");
                    ps.setString(1, salt);
                    ps.setString(2, user.getEmail());
                    ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 public static void updateCoins(String email, int coins) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(UserConstants.UPDATE_COINS);
	            ps.setInt(1, coins);
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
}
