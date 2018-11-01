package chesstimeserver.networking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.networking.FirebaseCommunicator;
import chesstimeserver.networking.services.RegisterData;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getHeader("name");
		String fbid = request.getHeader("firebase_instance_id");
		String pwtoken = DatabaseContainer.applicationDatabase.registerUser(name);
		if(pwtoken == null) //Name vergeben
		{
			response.setStatus(403);
		}
		else {
			RegisterData data = new RegisterData();
			data.password_token = pwtoken;
			Gson gson = new Gson();
			String json = gson.toJson(data, RegisterData.class);
			FirebaseCommunicator.sendStringFCM(json, fbid);
		}
	}

}
