package paisService;

import paisDao.PaisDAO;

import paisModel.Pais;


public class PaisService {
	PaisDAO dao = new PaisDAO();

	public int criar(Pais pais) {
		return dao.criar(pais);
	}

	public void atualizar(Pais pais){
		dao.atualizar(pais);
	}

	public void excluir(int id){
		dao.excluir(id);
	}

	public Pais carregar(int id){
		Pais pais = dao.carregar(id);
		return pais;
	}
	
	public Pais MaiorPopulacao() { 
		return dao.Maiorpopulacao();
	}
	
	public Pais MenorArea() {
		return dao.MenorArea();
	}
	
	public Pais[] Vetor() {
		return PaisDAO.Vetor();
	}
}