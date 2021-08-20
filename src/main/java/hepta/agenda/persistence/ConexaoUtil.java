package hepta.agenda.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {  
		  	
		  public static Connection conectar() {			  
			  String jdbcURL = "jdbc:mysql://localhost:3306/sampledb";
			  String dbUsername = "root";
			  String dbPassword = "1234";
			  Connection conn = null;
			  try {
				  Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
			      System.out.println("Conectou no banco de dados.");
			  } catch (SQLException ex) {
				  System.out.println("Erro: Não conseguiu conectar no BD.");
			  } catch (ClassNotFoundException ex) {
			      System.out.println("Erro: Não encontrou o driver do BD.");
			}

			return conn;
		  }
		  
		  public void desconectar(Connection conn) {
			  try {
				  if(conn != null && !conn.isClosed()) {
					  conn.close();
					  System.out.println("Desconectou do banco de dados.");
				  }
			  } catch (SQLException ex) {
				  System.out.println("Não conseguiu desconectar do BD.");
			  }
		  }		  
		  
		  public static void main(String[] args) {
			  ConexaoUtil conexao = new ConexaoUtil();
			  Connection conn = conexao.conectar();
			  conexao.desconectar(conn);
		  }
	
}
