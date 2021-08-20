import org.junit.jupiter.api.Test;

import hepta.agenda.entity.ContatoDTO;
import hepta.agenda.persistence.ContatoDAO;

class ContatoDAOTest {

	@Test
	void testCreate() throws Exception {
		ContatoDTO contatoTest = new ContatoDTO();
		contatoTest.setNome("Teste");
		contatoTest.setTelefone("teste");
		contatoTest.setEmail("teste@gmail.com");
		contatoTest.setNomeCompleto("TESTE TESTE");
		contatoTest.setIdGrupo(1);
		contatoTest.setDataNascimento(null);
		contatoTest.setFoto(null);
		
		
	    ContatoDAO contato = new ContatoDAO();
	    contato.create(contatoTest);
	}
	
	@Test
	void testUpdate() throws Exception {
		ContatoDTO contatoTest = new ContatoDTO();
		contatoTest.setId(10);
		contatoTest.setNome("Teste Atualizar");
		contatoTest.setTelefone("testeAtualizar");
		contatoTest.setEmail("testeAtualizar@gmail.com");
		contatoTest.setNomeCompleto("TESTE Atualizar");
		contatoTest.setIdGrupo(2);
		
		ContatoDAO contato = new ContatoDAO();
		contato.update(contatoTest);
	}
	    	
	@Test
	void testDelete() throws Exception {
		ContatoDTO contatoTest = new ContatoDTO();
		contatoTest.setId(10);
		
		ContatoDAO contato = new ContatoDAO();
		contato.delete(contatoTest.getId());
	}
	
	@Test
	void testListarAll() throws Exception {
		ContatoDAO contatoTest = new ContatoDAO();
		contatoTest.listAll();
	}
	
	@Test
	void listPorId() throws Exception {
		ContatoDTO contatoTest = new ContatoDTO();
		contatoTest.setId(9);
		
	    ContatoDAO contato = new ContatoDAO();
	    contato.listPorId(contatoTest.getId());
	}

}
