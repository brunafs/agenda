package hepta.agenda.service;

import java.io.InputStream;
import java.nio.file.Files;

import hepta.agenda.entity.ArquivoDTO;
import hepta.agenda.persistence.ArquivoDAO;

public class ArquivoService {
	  
    private ArquivoDAO arquivoDAO = null;

    public ArquivoService() {
    	arquivoDAO = new ArquivoDAO();
    }
    
 // SALVAR ARQUIVO
//    public Boolean salvarContato(ArquivoDTO arquivoDTO, File file) throws Exception{
//    	byte[] foto = convertArquivo(file);
//    	
//    	ArquivoDTO arquivoFormatado = arquivoDAO.mountArquivo(arquivoDTO, new ArquivoDTO(arquivoDTO.getArquivo()));
//    	
//    	
//        boolean retorno = arquivoDAO.create(arquivoDTO);
//		return retorno;
//    }
//    
//    public ArquivoDTO mountArquivo(InputStream file, ArquivoDTO arquivoDTO) {
//    	
//    	String[] arquivo = FotoUtil.convertStringToArray(ArquivoDTO.getNome());
//    	
//    	String nomeArquivo = arquivo[0];
//    	String extArquivo =  arquivo[1];
//    	
//    	arquivoDTO.setNome(nomeArquivo);
//    	arquivoDTO.setTipo(extArquivo);
//    	
//    	byte[] byteArquivo = FotoUtil.convertIsToByte(file);
//    	arquivoDTO.setArquivo(byteArquivo);
//    	
//    	return arquivoDTO;  	
//    	
//    }
//    
//    private byte[] convertArquivo(InputStream file) {
//    	return Files.readAllBytes(file.toPath());
//    }
	
	
}
