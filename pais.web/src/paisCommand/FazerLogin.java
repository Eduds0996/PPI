package paisCommand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paisModel.Usuario;
import paisService.UsuarioService;

public class FazerLogin implements Command {

	@SuppressWarnings("resource")
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("username");
		String senha = request.getParameter("passwd");

		Usuario usuario = new Usuario();
		usuario.setUsername(nome);
		usuario.setPassword(senha);
		UsuarioService service = new UsuarioService();
		FileWriter logRequest;
		logRequest = new FileWriter(new File("C:\\Users\\Eduardo/logreq.log"),true);
		
		
		if(service.validar(usuario)){
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Login realizado pelo " + usuario + "\n");
			System.out.println("Logou "+usuario);
		} else {
			System.out.println("Não Logou "+usuario);
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Senha ou Usuario invalidos " + "\n");
			throw new ServletException("Usuário/Senha inválidos");
		}
		logRequest.flush();
		response.sendRedirect("index.jsp");
	}

}
