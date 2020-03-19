package ppi.pais;

import java.io.Serializable;

public class Pais implements Serializable {
	private int id;
	private String nome;
	private long populacao;
	private double area;
	private String maiorpop;
	private String menorarea;
	
	
	public Pais(int id, String nome, long populacao, double area ) {
		setId(id);
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
	}
	
	public Pais() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	public String getMaiorpop() {
		return maiorpop;
	}


	public void setMaiorpop(String pais) {
		this.maiorpop = pais;
	}


	public String getMenorarea() {
		return menorarea;
	}


	public void setMenorarea(String pais) {
		this.menorarea = pais;
	}
}
