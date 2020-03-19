package ppi.pais;

public class PaisService {
	PaisDAO dao = new PaisDAO();

	public void criar(Pais pais) {
		dao.criar(pais);
	}

	public void atualizar(Pais pais){
		dao.atualizar(pais);
	}

	public void excluir(Pais pais){
		dao.excluir(pais);
	}

	public Pais carregar(int id){
		Pais pais = dao.carregar(id);
		return pais;
	}
	
	public Pais MaiorPopulacao() { 
		return dao.Maiorpopulacao();
	}
	
	public Pais MarioArea() {
		return dao.MenorArea();
	}
	
}