package Constants;

import java.util.logging.Logger;
/**
 *
 * @author RAVITEJA
 */
public final class StudyConstants {
	public static final Logger LOG = Logger.getLogger("Constants");

	private StudyConstants() {
		new StudyConstants();
	}
	public static final String ADD_STUDY = "INSERT INTO study "
				+ "(StudyID, StudyName, Description, Username, DateCreated, ImageURL, ReqParticipants, ActParticipants, SStatus) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String ADD_QUESTION = "INSERT INTO Question "
				+ "(StudyID, QuestionID, Question, AnswerType, Option1, Option2, Option3, Option4, Option5) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
         public static final String ADD_ANSWER = "INSERT INTO answer " + "(StudyID, questionId, Username, choice, Datesubmitted)"
				+ "VALUES (?, ?, ?, ?, ?)";
	public static final String GET_STUDIES = "SELECT * from study";
	public static final String GET_STUDY = "SELECT * from study WHERE studyid = ?";
	public static final String GET_STUDY2 = "SELECT * from study WHERE studyid = ? and username =?";
        public static final String GET_STUDY3 = "SELECT * from study where username=?";
        public static final String GET_STUDY4 = "SELECT * from study where sstatus=?";
	public static final String GET_QUESTION = "SELECT * from question where studyid=?";
	public static final String DELETE_STUDY = "DELETE FROM study " + "WHERE StudyID = ?";
        public static final String DELETE_QUESTION = "DELETE FROM question " + "WHERE StudyID = ?";
        public static final String UPDATE_STUDY = "UPDATE study SET " + "StudyID = ?, " + "StudyName = ?, " + "Description = ?, "
				+ "Username = ?, " + "DateCreated = ?, " + "ImageURL = ?, " + "ReqParticipants = ?, "
				+ "ActParticipants = ?, " + "SStatus = ? " + "WHERE StudyID = ?";
        public static final String UPDATE_QUESTION = "UPDATE Question SET " + "StudyID = ?, " + "QuestionID = ?, " + "Question = ?, "
				+ "AnswerType = ?, " + "Option1 = ?, " + "Option2 = ?, " + "Option3 = ?, " + "Option4 = ?, "
				+ "Option5 = ? " + "WHERE StudyID = ?";
        public static final String UPDATE_STUDY2 = "UPDATE study SET " + "SStatus = ? " + "WHERE StudyID = ? and username=?";
        public static final String UPDATE_STUDY3 = "UPDATE study SET " + "ActParticipants = ? " + "WHERE StudyID = ? ";
        public static final String COUNT_STUDY = "SELECT count(*) from study WHERE studyid = ?";
        public static final String GET_REPORT =  "SELECT * from reported";
        public static final String GET_REPORT2 =  "SELECT * from reported where username=?";
        public static final String GET_REPORT3 =  "SELECT * from reported where studyid=?";
        public static final String GET_REPORT4 =  "SELECT * from reported where studyid=? and username=?";
        public static final String ADD_REPORT = "INSERT INTO reported "
	                + "(QuestionID, StudyID, Date, Question, Username, NumParticipants, Status) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        public static final String UPDATE_REPORT = "UPDATE reported SET "
	                + "Status = ? "
	                + "WHERE StudyID = ? and username=?";
        public static final String GET_ANSWER = "SELECT * from answer where username=?";
        public static final String GET_ANSWER2 = "SELECT * from answer where studyid=?";
        public static final String INVALID_USER = "Invalid User";
        public static final String EMAIL_EXISTS = "EMAIL ALREADY EXISTS...!!";
        public static final String PASSWORDS_DONOT_MATCH = "PASSWORDS DO NOT MATCH....!!";
	
}
