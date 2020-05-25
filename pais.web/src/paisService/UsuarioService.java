package paisService;

import java.io.IOException;

import paisDao.UsuarioDAO;
import paisModel.Usuario;

public class UsuarioService {
	
	public boolean validar(Usuario usuario) throws IOException{
		UsuarioDAO dao = new UsuarioDAO();
		CryptoService cryptoService = new CryptoService();
		String senhaCriptografada = cryptoService.criptografar(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);
		return dao.validar(usuario);
	}
}
