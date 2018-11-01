package chesstimeserver.networking.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chesstimeserver.networking.FirebaseCommunicator;
import chesstimeserver.networking.services.UpdateFirebaseTokenService;


public class UpdateFirebaseTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateFirebaseTokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newFirebaseToken = null;
		String passwordToken = null;
		
		try
		{		
			newFirebaseToken = request.getHeader("firebase_token");
			System.out.println("Received Firebase Token:"+newFirebaseToken);
			passwordToken = request.getHeader("password_token");
			
			UpdateFirebaseTokenService service = new UpdateFirebaseTokenService();
			service.updateFirebaseToken(passwordToken, newFirebaseToken);
		}
		catch(RuntimeException ex) {
			System.out.println("Bad Request");
			response.setStatus(400); //400: Bad Request
		}	
	}
}
