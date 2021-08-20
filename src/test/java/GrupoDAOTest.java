import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import hepta.agenda.entity.GrupoDTO;
import hepta.agenda.persistence.GrupoDAO;

@TestMethodOrder(OrderAnnotation.class)

class GrupoDAOTest {
	
	@Test
	@Order(1)
	void testCreate() throws Exception {
		GrupoDTO grupoTest = new GrupoDTO();
		grupoTest.setNome("Trabalho");
		
	    GrupoDAO grupo = new GrupoDAO();
	    grupo.create(grupoTest);
	}
	
	@Test
	@Order(2)
	void testUpdate() throws Exception {
		GrupoDTO grupoTest = new GrupoDTO();
		grupoTest.setId(3);
		grupoTest.setNome("Trabalho Atualizado");
		
	    GrupoDAO grupo = new GrupoDAO();
	    grupo.update(grupoTest);
	}
	    	
	@Test
	@Order(6)
	void testDelete() throws Exception {
		GrupoDTO grupoTest = new GrupoDTO();
		grupoTest.setId(3);
		
	    GrupoDAO grupo = new GrupoDAO();
	    grupo.delete(grupoTest.getId());
	}
	
	@Test
	@Order(3)
	void testListarAll() throws Exception {
	    GrupoDAO grupo = new GrupoDAO();
	    grupo.listAll();
	}
	
	@Test
	@Order(4)
	void listPorId() throws Exception {
		GrupoDTO grupoTest = new GrupoDTO();
		grupoTest.setId(3);
		
	    GrupoDAO grupo = new GrupoDAO();
	    grupo.listPorId(grupoTest.getId());
	}

}
