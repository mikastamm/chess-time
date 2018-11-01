package chesstimeserver.networking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chesstimeserver.networking.services.MoveService;

/**
 * Servlet implementation class MoveServlet
 */
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passwordToken, gameId, from, to;
		passwordToken = request.getHeader("password_token");
		gameId = request.getHeader("game_id");
		from = request.getHeader("from");
		to = request.getHeader("to");
		
		MoveService service = new MoveService();
		service.move(passwordToken, gameId, from, to);
	}

}
