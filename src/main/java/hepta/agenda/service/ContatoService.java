package hepta.agenda.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hepta.agenda.entity.Contato;
import hepta.agenda.entity.ContatoDTO;
import hepta.agenda.persistence.ContatoDAO;

public class ContatoService {
    
    private ContatoDAO contatoDAO = null;

    public ContatoService() {
    	contatoDAO = new ContatoDAO();
    }
    
    //CONVERTER FOTO/ARQUIVO
    private byte[] converterFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
    
    // SALVAR CONTATO
    public Boolean salvarContato(ContatoDTO contatoDTO, File file) throws Exception {
    	byte[] foto = converterFile(file);
    	contatoDTO.setFoto(foto);
    	Contato contato = contatoDTO.toContato();
        boolean retorno = contatoDAO.create(contato);
		return retorno;
    }
    // LISTAR CONTATOS
    public List<ContatoDTO> listarContato() throws Exception {
        List<Contato> contatos = contatoDAO.listAll();
//        List<ContatoDTO> contatosDTO = new ArrayList(contatos);
        
        List<ContatoDTO> contatosDTO = contatos.stream().map(ContatoDTO::new).collect(Collectors.toList());
        System.out.println(contatosDTO);
        
    	return contatosDTO;
    	//return contatoDAO.listAll();
    }
    // LISTAR UM CONTATO POR ID
    public ContatoDTO buscarContato(int id) throws Exception {
        return contatoDAO.listPorId(id);
    }
    // ATUALIZAR CONTATO
    public Boolean updateContato(ContatoDTO contatoDTO, File file) throws Exception {
    	try {
    		byte[] foto = converterFile(file);
        	contatoDTO.setFoto(foto);
        	Contato contato = contatoDTO.toContato();
        	boolean update = contatoDAO.update(contato);
        	return update;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	
    }
    // DELETAR CONTATO
    public Boolean deleteContato(int id) throws Exception {
    	boolean delete = contatoDAO.delete(id);
    	return delete;
    }
}