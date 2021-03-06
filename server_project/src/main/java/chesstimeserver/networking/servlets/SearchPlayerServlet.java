package chesstimeserver.networking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chesstimeserver.networking.FirebaseCommunicator;
import chesstimeserver.networking.services.SearchPlayerService;

/**
 * Servlet implementation class SearchPlayerServlet
 */
public class SearchPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchPlayerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SearchGame Request received");
		String passwordToken = request.getHeader("password_token");
		
		SearchPlayerService service = new SearchPlayerService();
		service.searchPlayer(passwordToken);
	}

}
