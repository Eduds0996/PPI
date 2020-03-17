package ppi.pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {
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
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/loja-virtual\",\"root\",\"senha123\"");
	}
	
	
	public void criar() {
		String sqlInsert = "INSERT INTO cliente(nome, populacao, area) VALUES (?, ?, ?)";
		 
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void atualizar() {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregar() {
		String sqlSelect = "SELECT nome, fone, email FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
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


	public void Maiorpopulacao() {
		String maior = "select * from pais order by populacao desc limit 1";
		
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(maior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setMaiorpop(rs.getString("nome"));
				} else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
	}
	
	public void MenorArea() {
		String menor = "select * from pais order by area asc limit 1";
		
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(menor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setMenorarea(rs.getString("nome"));
				} else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public ArrayList<Pais> Vetor() {
		ArrayList<Pais> pais = new ArrayList<>();
		String select = "select * from pais";
		
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(select);) {
			try (ResultSet rs = stm.executeQuery();) {
				for(int  i = 1 ; i<=3 ; i++) {
					if (rs.next()) {
						Pais lista = new Pais();
						lista.setId(rs.getInt("id"));
						lista.setNome(rs.getString("nome"));
						lista.setArea(rs.getDouble("area"));
						lista.setPopulacao(rs.getLong("populacao"));
						pais.add(lista);
					} 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	@Override
	public String toString() {
		return "id: " + getId() + "| Nome: " + getNome() + " | Populacao: " + getPopulacao() + " | Area: " + getArea();
	}
	
}
