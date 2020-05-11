package paisCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paisModel.Pais;
import paisService.ListaService;

public class ListarPaisesBusca implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String chave = request.getParameter("data[search]");
		ListaService paisservice = new ListaService();
		ArrayList<Pais> lista = null;
		HttpSession session =request.getSession();
		
		if (chave != null && chave.length() > 0) {
			lista = paisservice.listarPaises(chave);
		}else {
				
			lista = paisservice.listarPaises();
		}
		session.setAttribute("lista", lista);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarPaises.jsp");
		dispatcher.forward(request, response);

	}

}
