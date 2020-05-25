package paisFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import paisModel.Usuario;



@WebFilter("/*")
public class LogFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("resource")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		Usuario usuario = (Usuario)session.getAttribute("logado");
		FileWriter logRequest;
		logRequest = new FileWriter(new File("C:\\Users\\Eduardo/logreq.log"),true);
		request.setCharacterEncoding("UTF-8");
		if(usuario == null){
			
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Situação do Login: " +req.getParameter("paisCommand") + "\n");
			System.out.println(req.getParameter("paisCommand"));
		} else {
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Situação do Login: " +req.getParameter("paisCommand") + "\n");
			System.out.println(usuario.getUsername()+ " -> " + req.getParameter("paisCommand"));
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
		if(usuario == null){
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Situação do Login: " +req.getParameter("paisCommand") + "\n");
			System.out.println(req.getParameter("paisCommand"));
		} else {
			logRequest.append("\n" +Calendar.getInstance().getTime() +" Situação do Login: " + usuario.getUsername() + "\n");
			System.out.println(req.getParameter("paisCommand")+" -> " + usuario.getUsername());
		}
		logRequest.flush();
		System.out.println("---------------------------------------------------------------------");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
