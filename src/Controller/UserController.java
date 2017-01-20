package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Data.StudyDB;
import Data.UserDB;
import Model.User;
import Constants.UserConstants;
import Data.EmailDB;
import java.util.UUID;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                String action = request.getParameter("action");
                String token = request.getParameter("token");
                String reset = request.getParameter("reset");
                String recommend = request.getParameter("recommend");
		HttpSession session = request.getSession();
		String url = "/home.jsp";
                 if(token!=null)
                    {
                      action="activate";
                    }
                 if(recommend!=null)
                 {
                     action="recommend";
                 }
                if(reset!=null)
                {
                    action="changepassword";
                }
		if (action == null || action.equals("") || action.isEmpty()) {
			url = "/home.jsp";
			Cookie c = new Cookie("host", request.getRemoteHost());
			Cookie c1 = new Cookie("port", request.getRemotePort() + "");
			c.setMaxAge(60 * 60 * 24 * 365 * 1);
			c1.setMaxAge(60 * 60 * 24 * 365 * 1);
			response.addCookie(c);
			response.addCookie(c1);
			Cookie[] cookies = request.getCookies();
			if(cookies==null){
			   response.setIntHeader("Refresh", 1);
                        }
		
		}
		else {
			if (action.equals("login")) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				if (UserDB.validateUser(email, password)) {
					User user = UserDB.getUser(email);
					String userType = user.getUserType();
					if (userType.equalsIgnoreCase("Participant")) {
						session.setAttribute("theUser", user);
						int participants = StudyDB.getParticipants(user.getEmail());
						session.setAttribute("par", participants);
						url = "/main.jsp";
					} else if (userType.equalsIgnoreCase("Admin")) {
						session.setAttribute("theAdmin", user);
						url = "/admin.jsp";
					}
				} else {
					request.setAttribute("msg", UserConstants.INVALID_USER);
					url = "/login.jsp";
				}
			}
                        if(action.equals("activate"))
                        {
                            User user = UserDB.getTempUser(token);
                            UserDB.addUser(user);
                            UserDB.deleteTempUser(token);
                            session.setAttribute("theUser", user);
                            url="/main.jsp";
                        }
                         if(action.equals("forgot"))
                        {
                            String email=request.getParameter("email");
                            
                            User user=UserDB.getUser(email);
                            if(user==null)
                            {
                                request.setAttribute("msg", "Invalid Email");
                                url="/forgotpass.jsp";
                            }
                            else{
                            UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");  
                            String newtoken=uid.randomUUID().toString();
                                UserDB.addTempUser(user, newtoken);
                            EmailDB.passwordResetTrigger(request, email, newtoken);
                            url="/login.jsp";
                            }
                        }
                         
                         if(action.equals("changepassword"))
                        {
                            String pass=request.getParameter("pass");
				String conf_Password = request.getParameter("confirm_pass");
                                String email=request.getParameter("email");
                               
                            if(pass==null)
                            {
                                 User user;
                           
                                user = UserDB.getTempUser(reset);
                                 //UserDB.addUser(user);
                           // UserDB.deleteTempUser(token);
                            session.setAttribute("resetemail", user.getEmail());
                           session.setAttribute("reset", reset);
                                url="/reset.jsp";
                            }
                            else
                            {
                                if (!pass.equals(conf_Password)) {
					request.setAttribute("msg", UserConstants.PASSWORDS_DONOT_MATCH);
					request.setAttribute("email", email);
					//request.setAttribute("name", name);
					url = "/reset.jsp";
				}
                                else
                                {
                                    
                                User user = UserDB.getTempUser(request.getParameter("reset"));
                            
                                     UserDB.updateUser(user,pass);
                                     UserDB.deleteTempUser(reset);
                            session.setAttribute("theUser", user);
                            url="/main.jsp";
                               
                                }
                                
                            }
                           
                        }
			if (action.equals("create")) {
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String conf_Password = request.getParameter("confirm_password");
				if (UserDB.getUser(email) != null) {
					request.setAttribute("msg", UserConstants.EMAIL_EXISTS);
					request.setAttribute("email", email);
					request.setAttribute("name", name);
					url = "/signup.jsp";
				} else if (!password.equals(conf_Password)) {
					request.setAttribute("msg", UserConstants.PASSWORDS_DONOT_MATCH);
					request.setAttribute("email", email);
					request.setAttribute("name", name);
					url = "/signup.jsp";
				} else {
                                     UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");  
                                        String newtoken=uid.randomUUID().toString();
					User user = new User(name, email, "Participant", password, conf_Password, 0, 0, 0);
					int participants = StudyDB.getParticipants(user.getEmail());
					session.setAttribute("par", participants);
					UserDB.addTempUser(user, newtoken);
					EmailDB.emailActivationTrigger(request, email,newtoken);
					url = "/login.jsp";
				}

			}
                        
                        if(action.equals("recommend"))
                        {
                            User user=UserDB.getUser(recommend);
                            UserDB.updateCoins(recommend, user.getNumCoins()+2);
                        }
                        
                        if (action.equals("logout")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					session.invalidate();
				}
				url = "/home.jsp";
			}

			if (action.equals("how")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/how.jsp";
				}
			}
                        
			if (action.equals("main")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/login.jsp";
				}
                        }

			if (action.equals("about")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/aboutl.jsp";
				} else {
					url = "/about.jsp";
				}
			}

			if (action.equals("home")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/home.jsp";
				}
			}

		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
        
	public UserController() {
		super();
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
