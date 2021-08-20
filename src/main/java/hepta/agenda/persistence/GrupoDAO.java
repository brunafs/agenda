package hepta.agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import hepta.agenda.entity.GrupoDTO;

@Entity
public class GrupoDAO {

	public boolean create(GrupoDTO grupo) throws Exception {
		Connection connection = ConexaoUtil.conectar();			
		String sql = "INSERT INTO grupo (grupo) VALUES (?)";
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, grupo.getNome());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Um novo grupo de contatos foi adicionado");
				retorno = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			connection.close();
		}
		return retorno;
	}
	
	public boolean update(GrupoDTO grupo) throws Exception {
		Connection connection = ConexaoUtil.conectar();				
		String sql = "UPDATE grupo SET grupo=? WHERE id=?";
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, grupo.getNome());
			statement.setInt(2, grupo.getId());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("O grupo de contatos foi atualizado");
				retorno = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			connection.close();
		}
		return retorno;
		
	}
	
	public boolean delete(Integer id) throws Exception {
		Connection connection = ConexaoUtil.conectar();
		String sql = "DELETE FROM grupo WHERE id=?";		
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			int rows = statement.executeUpdate();			
			if (rows > 0) {
				System.out.println("O grupo de contatos foi deletado");
				retorno = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			connection.close();
		}
		return retorno;
	}
	
	public List<GrupoDTO> listAll() throws Exception {	
		List<GrupoDTO> grupos = new ArrayList<GrupoDTO>();				
		Connection connection = ConexaoUtil.conectar();
		String sql = "SELECT * FROM grupo";		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);			
			while (result.next()) {				
				GrupoDTO grupoBuscado = new GrupoDTO();
					
				grupoBuscado.setId(result.getInt(1));
				grupoBuscado.setNome(result.getString(2));
				
				grupos.add(grupoBuscado);
				System.out.println(grupoBuscado.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return grupos;
	}
	
	public GrupoDTO listPorId(Integer id) throws Exception {
		
		Connection connection = ConexaoUtil.conectar();
		GrupoDTO grupoBuscado = new GrupoDTO();
		try {			
			String sql = "SELECT * FROM grupo WHERE id=?";		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);			
			ResultSet result = statement.executeQuery();			
			while (result.next()) {				
				grupoBuscado.setId(result.getInt(1));
				grupoBuscado.setNome(result.getString(2));

				System.out.println(grupoBuscado.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return grupoBuscado;
	}	
	
}
