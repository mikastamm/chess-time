package chesstimeserver.networking.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chesstimeserver.networking.FirebaseCommunicator;


public class UpdateFirebaseTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateFirebaseTokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static String userFirebaseToken;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newFirebaseToken = null;
		String passwordToken = null;
		
		try
		{		
			newFirebaseToken = request.getHeader("firebase_token");
			System.out.println("Received Firebase Token:"+newFirebaseToken);
			passwordToken = request.getHeader("password_token");
		}
		catch(RuntimeException ex) {
			System.out.println("Bad Request");
			response.setStatus(400); //400: Bad Request
		}
		
		
		userFirebaseToken = newFirebaseToken;
		
	//	FirebaseCommunicator.sendTestFCM();
	}

}
