package hepta.agenda.persistence;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import hepta.agenda.entity.Contato;
import hepta.agenda.entity.ContatoDTO;

public class ContatoDAO {
	
	public boolean create(Contato contato) throws Exception {
		Connection connection = ConexaoUtil.conectar();			
		String sql = "INSERT INTO contato (nome, telefone, fullname, email, "
				+ "grupo_id, dataNascimento, foto, fotoNome, fotoTamanho, fotoTipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			LocalDate data = contato.getDataNascimento();
			Date dataNascimento = Date.valueOf(data);
			
			//ARQUIVO FOTO
			InputStream targetStream = new ByteArrayInputStream(contato.getFoto());
			
			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getTelefone());
			statement.setString(3, contato.getNomeCompleto());
			statement.setString(4, contato.getEmail());
			statement.setInt(5, contato.getIdGrupo());
			statement.setDate(6, dataNascimento);
			statement.setBlob(7, targetStream);
			statement.setString(8, contato.getFotoNome());
			statement.setString(9, contato.getFotoTamanho());
			statement.setString(10, contato.getFotoTipo());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Um novo contato foi adicionado");
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
	
	public boolean update(Contato contato) throws Exception {
		Connection connection = ConexaoUtil.conectar();				
		String sql = "UPDATE contato SET telefone=?, fullname=?, email=?, grupo_id=?, nome=?, dataNascimento=?,  foto=?, fotoNome=?, fotoTamanho=?, fotoTipo=? WHERE user_id=?";
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			LocalDate data = contato.getDataNascimento();
			Date dataNascimento = Date.valueOf(data);

			//ARQUIVO FOTO
			InputStream targetStream = new ByteArrayInputStream(contato.getFoto());
			
			statement.setInt(11, contato.getId());
			statement.setString(1, contato.getTelefone());
			statement.setString(2, contato.getNomeCompleto());
			statement.setString(3, contato.getEmail());
			statement.setInt(4, contato.getIdGrupo());
			statement.setString(5, contato.getNome());
			statement.setDate(6, dataNascimento);

			statement.setBlob(7, targetStream);
			

			statement.setString(8, contato.getFotoNome());
			statement.setString(9, contato.getFotoTamanho());
			statement.setString(10, contato.getFotoTipo());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("O contato foi atualizado");
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
		String sql = "DELETE FROM contato WHERE user_id=?";	
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);			
			int rows = statement.executeUpdate();			
			if (rows > 0) {
				System.out.println("O contato foi deletado");
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
	
	public List<Contato> listAll() throws Exception {	
		List<Contato> contatos = new ArrayList<Contato>();				
		Connection connection = ConexaoUtil.conectar();
		String sql = "SELECT * FROM contato";		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				
				Contato contatoBuscado = new Contato();
				
				Date data = result.getDate("dataNascimento");
				if(data != null) {
					contatoBuscado.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
				}
				
				contatoBuscado.setId(result.getInt(1)); 
				contatoBuscado.setNome(result.getString(2));
				contatoBuscado.setTelefone(result.getString(3));
				contatoBuscado.setNomeCompleto(result.getString(4));
				contatoBuscado.setEmail(result.getString(5));
				contatoBuscado.setIdGrupo(result.getInt(8));
				contatoBuscado.setFoto(result.getBytes(7));
				contatoBuscado.setFotoNome(result.getString(9));
				contatoBuscado.setFotoTamanho(result.getString(10));
				contatoBuscado.setFotoTipo(result.getString(11));
				
				contatos.add(contatoBuscado);
				
				System.out.println(contatoBuscado.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return contatos;
	}
	
	public ContatoDTO listPorId(Integer id) throws Exception {	
		ContatoDTO contatoSelecionar = new ContatoDTO();
		Connection connection = ConexaoUtil.conectar();
		try {			
			String sql = "SELECT * FROM contato WHERE user_id=?";		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);			
			ResultSet result = statement.executeQuery();			
			while(result.next()) {				
				contatoSelecionar.setId(result.getInt(1));
				contatoSelecionar.setNome(result.getString(2));
				contatoSelecionar.setTelefone(result.getString(3));
				contatoSelecionar.setNomeCompleto(result.getString(4));
				contatoSelecionar.setEmail(result.getString(5));
				contatoSelecionar.setIdGrupo(result.getInt(8));	
				contatoSelecionar.setFoto(result.getBytes(7));
				contatoSelecionar.setFotoNome(result.getString(9));
				contatoSelecionar.setFotoTamanho(result.getString(10));
				contatoSelecionar.setFotoTipo(result.getString(11));
				
				Date data = result.getDate("dataNascimento");
                if(data != null) {
                  contatoSelecionar.setDataNascimento(result.getDate("dataNascimento").toLocalDate());  
                } 
				
				System.out.println(contatoSelecionar.getNome());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}		
		return contatoSelecionar;
	}	
}
