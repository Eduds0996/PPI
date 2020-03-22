package paisTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import paisModel.Pais;
import paisService.PaisService;
import paisModel.Pais;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	Pais pais, copia;
	PaisService paisService;
	static int id = 0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Configurando");
		pais = new Pais();
		pais.setId(id);
		pais.setNome("Brasil" );
		pais.setPopulacao(210147125);
		pais.setArea(8515767.049);
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Brasil");
		copia.setPopulacao(210147125);
		copia.setArea(8515767.049);
		paisService = new PaisService();
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais();
		fixture.setId(0);
		fixture.setNome("Canada");
		fixture.setPopulacao(37242571);
		fixture.setArea(9984670);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(1);
		assertEquals("Carregando... " , novo.toString() , fixture.toString() );	
	}
	
	@Test
	public void test01Criar() {
		System.out.println("Criar");
		id = paisService.criar(pais);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais.toString(), copia.toString());
	}

	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setArea(00000);
		copia.setArea(00000);
		paisService.atualizar(pais);
		pais = paisService.carregar(pais.getId());
		//assertEquals("Atualizando... ", pais.toString() , copia.toString());	
	}
	
	@Test
	public void testExcluir() {
		System.out.println("Excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		paisService.excluir(id);
		pais = paisService.carregar(id);
		assertEquals("Excluindo... " , copia.toString() , pais.toString());
	}
	
	@Test
	public void testMaiorPopulacao() {
		System.out.println("Maior Populacao");
		System.out.println(paisService.MaiorPopulacao());
	}
	
	
	@Test
	public void testMenorArea() {
		System.out.println("Menor area");
		System.out.println(paisService.MenorArea());
	}
	
	@Test
	public void testVetor() {
		System.out.println("3 vetores: ");
		Pais[] vet = paisService.Vetor();
		for (Pais pais : vet) {
			System.out.println(pais);
		}
	}
}
