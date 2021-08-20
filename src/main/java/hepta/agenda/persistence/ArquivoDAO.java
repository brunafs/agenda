package hepta.agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

import hepta.agenda.entity.ArquivoDTO;

public class ArquivoDAO {

	public boolean create(ArquivoDTO file) throws Exception {
		Connection connection = ConexaoUtil.conectar();			
		String sql = "INSERT INTO arquivo (arquivo) VALUES (?)";
		boolean retorno = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setBytes(1, file.getArquivo());
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Um novo arquivo foi adicionado");
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
	
}