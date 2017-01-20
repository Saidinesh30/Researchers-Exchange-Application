package Data;

import Util.ConnectionPool;
import Util.DbUtil;
import Model.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.User;
import Util.PasswordUtil;
import java.sql.Date;
import Constants.StudyConstants;
/**
 *
 * @author RAVITEJA
 */
public class AnswerDB {
    
    public static int addAnswer(String SCode,Answer ans) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(StudyConstants.ADD_ANSWER);
	            ps.setString(1, SCode);
	            ps.setString(2, ans.getQuesId());
	            ps.setString(3, ans.getEmail());
	            ps.setString(4, ans.getChoice());
	            ps.setDate(7, (Date) ans.getSubmissionDate());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    
    public static ArrayList<String> getAnswersFor(String SCode) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	       ArrayList<User> users = new ArrayList<User>();
               ArrayList<String> ans=new ArrayList<String>();
               String temp;
               if(SCode.contains("@"))
               {
	        temp = "SELECT * from answer where username=?";
               }
               else
               {
                   temp = "SELECT * from answer where studyid=?";
               }
	        try {
	            ps = connection.prepareStatement(temp);
                    ps.setString(1, SCode);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	
                        ans.add(rs.getString("choice"));
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return ans;
	    }
    
}
