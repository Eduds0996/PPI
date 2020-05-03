package paisService;

import java.util.ArrayList;

import paisDao.PaisDAO;
import paisModel.Pais;

public class ListaService {
	PaisDAO dao;
	public ListaService() {
		dao = new PaisDAO();
	}
	
	public ArrayList<Pais> listarPaises() {
		return dao.listarPaises();
	}
	
	public ArrayList<Pais> listarPaises(String chave) {
		return dao.listarPaises(chave);
	}
}
