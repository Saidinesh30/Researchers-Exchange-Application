package Data;

import Util.ConnectionPool;
import Util.DbUtil;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Report;
import Model.User;
import Constants.StudyConstants;

public class ReportDB {
	
	
	static ArrayList<Report> reportList = new ArrayList<Report>();
	
	 public static  ArrayList<Report> getReports() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Report> reports = new ArrayList<Report>();
	        try {
	            ps = connection.prepareStatement(StudyConstants.GET_REPORT);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	                reports.add(report);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return reports;
	    }
	
	 public static  ArrayList<Report> getReports(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Report> reports = new ArrayList<Report>();
	        try {
	            ps = connection.prepareStatement(StudyConstants.GET_REPORT2);
	            ps.setString(1,email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	                reports.add(report);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DbUtil.closeResultSet(rs);
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return reports;
	    }

	 public static  Report getReport(String studyCode) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(StudyConstants.GET_REPORT3);
	            ps.setString(1,studyCode);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	            	 return report;
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

	
	 public static void addReport(Report rep) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(StudyConstants.ADD_REPORT);
	            ps.setString(1, rep.getQuestionID());
	            ps.setString(2, rep.getStudyCode());
	            ps.setString(4, rep.getQues());
	            ps.setTimestamp(3, rep.getRepDate());
	            ps.setString(5, rep.getReporterEmail());
	            ps.setInt(6, 0);
	            ps.setString(7, rep.getRepStatus());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }


	
	 public static Report getReport(String studyCode, String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(StudyConstants.GET_REPORT4);
	            ps.setString(1,studyCode);
	            ps.setString(2,email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	            	 return report;
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
	 
	 
	 
	 public static void updateReportStatus(String sCode, String email,String status) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(StudyConstants.UPDATE_REPORT);
	            ps.setString(1, status);
	            ps.setString(2, sCode);
	            ps.setString(3, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DbUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
}
