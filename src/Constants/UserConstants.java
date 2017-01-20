package Constants;

import java.util.logging.Logger;
/**
 *
 * @author RAVITEJA
 */
public final class UserConstants {
	public static final Logger LOG = Logger.getLogger("Constants");

	private UserConstants() {
		new UserConstants();
	}
	public static final String ADD_USER = "INSERT INTO user "
	                + "(username, password, type, studies, participation, coins, name) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        public static final String ADD_TEMP_USER = "INSERT INTO TempUser "
	                + "(usertoken,username, password, type, studies, participation, coins, name, issuedate) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_USER = "SELECT * from user WHERE username = ?";
	public static final String GET_USERS = "SELECT * from user";
	public static final String VALIDATE_USER = "select * FROM user WHERE username = ? and password = ?";
	public static final String UPDATE_PARTICIPATION = "UPDATE user SET "
	                + "Participation = ? "
	                + "WHERE username = ? ";
	public static final String UPDATE_COINS = "UPDATE user SET "
	                + "coins = ? "
	                + "WHERE username = ? ";
        public static final String INVALID_USER = "Invalid User";
        public static final String EMAIL_EXISTS = "EMAIL ALREADY EXISTS...!!";
        public static final String PASSWORDS_DONOT_MATCH = "PASSWORDS DO NOT MATCH....!!";
	
}
