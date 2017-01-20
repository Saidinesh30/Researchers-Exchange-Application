package Data;

import Util.ConnectionPool;
import Util.DbUtil;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Constants.StudyConstants;
import Model.Answer;
import Model.Study;
import Model.User;

public class StudyDB {

	static ArrayList<Study> studyList = new ArrayList<Study>();
	static List answerList = new ArrayList();
	static ArrayList<Answer> answersList = new ArrayList<Answer>();

	

	public static ArrayList<Study> getStudies() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				try {
					ps1 = connection.prepareStatement(StudyConstants.GET_QUESTION);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs1);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}

	public static Study getStudy(String StudyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		List<String> answerList = new ArrayList<String>();
		ResultSet rs1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDY);
			ps.setString(1, StudyCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				try {
					ps1 = connection.prepareStatement(StudyConstants.GET_QUESTION);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				return study;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs1);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}

	public static Study getStudy(String StudyCode, String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		List<String> answerList = new ArrayList<String>();
		ResultSet rs1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDY2);
			ps.setString(1, StudyCode);
			ps.setString(2, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				try {
					ps1 = connection.prepareStatement(StudyConstants.GET_QUESTION);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				return study;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs1);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}


	public static List<Study> getStudies(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDY3);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				try {
					ps1 = connection.prepareStatement(StudyConstants.GET_QUESTION);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs1);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}

	

	public static List<Study> getStudiesByStatus(String status) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDY4);
			ps.setString(1, status);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				try {
					ps1 = connection.prepareStatement(StudyConstants.GET_QUESTION);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs1);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}

	public static void addStudy(Study study) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.ADD_STUDY);
			ps.setString(1, study.getStudyCode());
			ps.setString(2, study.getStudyName());
			ps.setString(3, study.getDescription());
			ps.setString(4, study.getEmail());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.setString(6, study.getImageURL());
			ps.setInt(7, study.getRequestedParticipants());
			ps.setInt(8, study.getNumOfParticipants());
			ps.setString(9, study.getStatus());
			ps.executeUpdate();
			try {
				ps1 = connection.prepareStatement(StudyConstants.ADD_QUESTION);
				ps1.setString(1, study.getStudyCode());
				ps1.setString(2, study.getStudyCode() + "" + study.getStudyName());
				ps1.setString(3, study.getQuestion());
				ps1.setString(4, "MCQ");
				if (study.getAnswers().size() == 3) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, null);
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 4) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 5) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, (String) study.getAnswers().get(4));
				}
				ps1.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DbUtil.closePreparedStatement(ps);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}

	
	public static void removeStudy(String studyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.DELETE_STUDY);
			ps.setString(1, studyCode);
			ps.executeUpdate();
			ps1 = connection.prepareStatement(StudyConstants.DELETE_QUESTION);
			ps1.setString(1, studyCode);
			ps1.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DbUtil.closePreparedStatement(ps);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}


	public static void updateStudy(String sCode, Study study) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.UPDATE_STUDY);
			ps.setString(1, study.getStudyCode());
			ps.setString(2, study.getStudyName());
			ps.setString(3, study.getDescription());
			ps.setString(4, study.getEmail());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.setString(6, study.getImageURL());
			ps.setInt(7, study.getRequestedParticipants());
			ps.setInt(8, study.getNumOfParticipants());
			ps.setString(9, study.getStatus());
			ps.setString(10, sCode);
			ps.executeUpdate();
			try {
				ps1 = connection.prepareStatement(StudyConstants.UPDATE_QUESTION);
				ps1.setString(1, study.getStudyCode());
				ps1.setString(2, study.getStudyCode() + "" + study.getStudyName());
				ps1.setString(3, study.getQuestion());
				ps1.setString(4, "MCQ");
				if (study.getAnswers().size() == 3) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, null);
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 4) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 5) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, (String) study.getAnswers().get(4));
				}
				ps1.setString(10, sCode);
				ps1.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DbUtil.closePreparedStatement(ps);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}

	public static void updateStudyStatus(String sCode, String email, String status) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(StudyConstants.UPDATE_STUDY2);
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

	public static void updateStudyPar(String sCode, int par) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(StudyConstants.UPDATE_STUDY3);
			ps.setInt(1, par);
			ps.setString(2, sCode);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DbUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	
	public static int getParticipants(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int participant_Count = 0;
		try {
			ps = connection.prepareStatement(StudyConstants.GET_STUDY3);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				participant_Count = participant_Count + rs.getInt("ActParticipants");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return participant_Count;

	}

	public static void addAnswers(Answer answer) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ps = connection.prepareStatement(StudyConstants.ADD_ANSWER);
			ps.setString(1, answer.getStudyCode());
			ps.setString(2, answer.getQuesId());
			ps.setString(3, answer.getEmail());
			ps.setString(4, answer.getChoice());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DbUtil.closePreparedStatement(ps);
			DbUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}
	
	
	
	
	public static int getStudyCount(String StudyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
			ps = connection.prepareStatement(StudyConstants.COUNT_STUDY);
			ps.setString(1, StudyCode);
			rs = ps.executeQuery();
			if(rs.next())
			{
				 int numberOfRows = rs.getInt(1);
			    System.out.println("numberOfRows= " + numberOfRows);
			    return numberOfRows;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return 0;
	}
	
	
    public static String getSCode(String a)
	{
		if(StudyDB.getStudyCount(a)!=1)
		{
			return a;
		}else
		{
			return getSCode("XY" + (int) (Math.random() * 100));
		}
	}
}
