package hepta.agenda.service;

import java.util.List;

import hepta.agenda.entity.GrupoDTO;
import hepta.agenda.persistence.GrupoDAO;

public class GrupoService {
    private GrupoDAO grupoDAO = null;

    public GrupoService() {
    	grupoDAO = new GrupoDAO();
    }
    
    //SALVAR 
    public Boolean salvar(GrupoDTO grupoDTO) throws Exception{
        boolean retorno = grupoDAO.create(grupoDTO);
		return retorno;
    } 
    // LISTAR
    public List<GrupoDTO> listar() throws Exception {
        return grupoDAO.listAll();
    }
    // LISTAR POR ID
    public GrupoDTO buscar(int id) throws Exception {
        return grupoDAO.listPorId(id);
    }
    // ATUALIZAR
    public Boolean update(GrupoDTO grupoDTO) throws Exception {
    	boolean update = grupoDAO.update(grupoDTO);
    	return update;
    }
    // DELETAR
    public Boolean delete(int id) throws Exception {
    	boolean delete = grupoDAO.delete(id);
    	return delete;
    }
}
