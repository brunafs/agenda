package hepta.agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hepta.agenda.entity.Users;

public class UsersDAO {
	
	public void create(Users user) throws Exception {
		Connection connection = ConexaoUtil.conectar();			
		String sql = "INSERT INTO users (username, password, fullname, email) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNome());
			statement.setString(3, user.getNomeCompleto());
			statement.setString(4, user.getEmail());
			statement.setString(2, user.getPassword());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Um novo usuário foi adicionado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void update(Users user) throws Exception {
		Connection connection = ConexaoUtil.conectar();				
		String sql = "UPDATE users SET password=?, fullname=?, email=? WHERE username=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, user.getNome());
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getNomeCompleto());
			statement.setString(3, user.getEmail());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Um novo usuário foi atualizado");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	
	public void delete(Users user) throws Exception {
		Connection connection = ConexaoUtil.conectar();
		String sql = "DELETE FROM users WHERE username=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNome());
			
			int rows = statement.executeUpdate();			
			if (rows > 0) {
				System.out.println("O usuário foi deletado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public List<Users> listAll() throws Exception {
		
		List<Users> users = new ArrayList<Users>();
		
		
		Connection connection = ConexaoUtil.conectar();
		String sql = "SELECT * FROM users";		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				
				Users userBuscado = new Users();
					
				userBuscado.setId(result.getInt(1));
				userBuscado.setNome(result.getString(2));
				userBuscado.setPassword(result.getString(3));
				userBuscado.setNomeCompleto(result.getString(4));
				userBuscado.setEmail(result.getString(5));
				
				users.add(userBuscado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return users;
	}
	
	public List<Users> listPorNome(Users user) throws Exception {
		
		List<Users> users = new ArrayList<Users>();
		Connection connection = ConexaoUtil.conectar();
		try {			
			String name = user.getNome();
			String sql = "SELECT * FROM users WHERE username=" + name;	
		
			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, user.getNome());
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Users userBuscado = new Users();
				
				userBuscado.setId(result.getInt(1));
				userBuscado.setNome(result.getString(2));
				userBuscado.setPassword(result.getString(3));
				userBuscado.setNomeCompleto(result.getString(4));
				userBuscado.setEmail(result.getString(5));
				
				users.add(userBuscado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	
	
}