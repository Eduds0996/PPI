package ppi.pais;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
 public class PaisTest {
	Pais pais, copia;
	static int id = 0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Configurando");
		pais = new Pais(1 , "Brasil" , 211254285 , 8511000.00);
		copia = new Pais(1 , "Brasil" , 211254285 , 8511000.00);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
		System.out.println("Pais" + copia.getId() + "Pais" + pais.getId());
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		
		Pais fixture = new Pais(1 , "Brasil" , 211254285 , 8511000.00);
		Pais novo = new Pais(15 , "" , 0, 0.0);
		novo.carregar();
		assertEquals("Carregando... " , novo.toString() , fixture.toString() );	
	}
	
	@Test
	public void test01Criar() {
		System.out.println("Criar");
		pais.criar();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("Criando... ", copia.toString() , pais.toString());
	}

	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setArea(89.01);
		copia.setArea(78.25);
		copia.atualizar();
		copia.carregar();
		assertEquals("Atualizando... ", copia.toString() , pais.toString());	
	}
	
	@Test
	public void testExcluir() {
		System.out.println("Excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		pais.excluir();
		pais.carregar();
		assertEquals("Excluindo... " , copia.toString() , pais.toString());
	}
	
	@Test
	public void testMaiorPopulacao() {
		pais.getMaiorpop();
		assertEquals("Testa Maior população", "China", pais.getMaiorpop());
		
	}

	@Test
	public void testMenorArea() {
		pais.getMenorarea();
		assertEquals("Testa menor Area: ", "Italia", pais.getMenorarea());
	}
}
