import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hepta.agenda.entity.Users;
import hepta.agenda.persistence.UsersDAO;

class UsersDAOTest {

	@Test
	void testCreate() throws Exception {

		Users userTest = new Users();
		userTest.setNome("Teste");
		userTest.setPassword("teste");
		userTest.setEmail("teste@gmail.com");
		userTest.setNomeCompleto("TESTE TESTE");
		
	    UsersDAO users = new UsersDAO();
	    users.create(userTest);
	}
	
	@Test
	void testUpdate() throws Exception {
		Users userTest = new Users();
		userTest.setNome("Teste Atualizar");
		userTest.setPassword("testeAtualizar");
		userTest.setEmail("testeAtualizar@gmail.com");
		userTest.setNomeCompleto("TESTE Atualizar");
		
	    UsersDAO users = new UsersDAO();
	    users.update(userTest);
	}
	
	@Test
	void testDelete() throws Exception {
		Users userTest = new Users();
		userTest.setNome("Teste Atualizar");
		
	    UsersDAO users = new UsersDAO();
	    users.delete(userTest);
	}
	
	@Test
	void testListarAll() throws Exception {
	    UsersDAO users = new UsersDAO();
	    users.listAll();
	}
	
	@Test
	void testListar() throws Exception {
	    Users userTest = new Users();
	    userTest.setNome("Teste");
		
		UsersDAO users = new UsersDAO();
	    users.listPorNome(userTest);
	}
	
}
