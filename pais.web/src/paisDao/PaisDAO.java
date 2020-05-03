package paisDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import paisModel.Pais;

public class PaisDAO {	
	public int criar(Pais pais) {
		String sqlInsert = "INSERT  INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		 
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int id) {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	
	public Pais Maiorpopulacao() {
		String maior = "select * from pais order by populacao desc limit 1";
		Pais pais = new Pais();

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(maior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	

	public Pais MenorArea() {
		String menor = "select * from pais order by area asc limit 1";
		Pais pais = new Pais();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(menor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public static Pais[] Vetor() {
		Pais pais = null;
		Pais[] vetor = new Pais[4];
		int count = 0;
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais limit 4";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					pais = new Pais(id, nome, populacao, area);
					vetor[count++] = pais;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return vetor;
	}
	public ArrayList<Pais> listarPaises() {
		Pais pais ;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM pais.pais" ;
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn .prepareStatement( sqlSelect );) {
			try (ResultSet rs = stm .executeQuery();) {
				while ( rs .next()) {
					pais = new Pais();
					pais .setId( rs .getInt( "id" ));
					pais .setNome( rs .getString( "nome" ));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getLong("area"));
					lista .add( pais );
				}
			} catch (SQLException e ) {
				e .printStackTrace();
			}
		} catch (SQLException e1 ) {
			System. out .print( e1 .getStackTrace());
		}
		return lista ;
	}
	
	public ArrayList<Pais> listarPaises(String chave ) {
		Pais pais ;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM pais.pais where upper(nome)like ?" ;
						// usando o try with resources do Java 7, quefecha o queabriu
						try (Connection conn = ConnectionFactory.obtemConexao();
								PreparedStatement stm = conn .prepareStatement( sqlSelect );) {
							stm .setString(1, "%" + chave .toUpperCase() + "%" );
							try (ResultSet rs = stm .executeQuery();) {
								while ( rs .next()) {
									pais = new Pais();
									pais .setId( rs .getInt( "id" ));
									pais .setNome( rs .getString( "nome" ));
									pais.setPopulacao(rs.getLong("populacao"));
									pais.setArea(rs.getDouble("area"));
									lista .add( pais );
								}
							} catch (SQLException e ) {
								e .printStackTrace();
							}
						} catch (SQLException e1 ) {
							System. out .print( e1 .getStackTrace());
						}
		return lista ;
	}
}
