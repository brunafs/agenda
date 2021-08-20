package hepta.agenda.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import hepta.agenda.entity.ContatoDTO;
import hepta.agenda.service.ContatoService;

@Path("/contatos")
public class ContatoRest {

	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
//	private ContatoDAO dao;
	private ContatoService contatoService;
	
	public ContatoRest() {
		contatoService = new ContatoService();
	}
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	// LISTAR CONTATOS
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response ContatoListar() {
		List<ContatoDTO> contatos = new ArrayList<>();
		try {
			contatos = contatoService.listarContato();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar contatos").build();
		}
		return Response.status(Status.OK).entity(contatos).build();
	}
	
	// LISTAR UM CONTATO POR ID
	@Path("/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response ContatoListar(@PathParam("id") Integer id) {
		ContatoDTO contatoResponse = new ContatoDTO();
		try {
			contatoResponse = contatoService.buscarContato(id);
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar contato").build();
		}
		return Response.status(Status.OK).entity(contatoResponse).build();
	}
	
//	//BUSCAR FOTO/ARQUIVO
//	public Response BuscarFoto() {
//		
//		String encodedString = Base64.getEncoder().encodeToString(fileContent);
//		
//		
//		
//		// encode to base64
//        byte[] data = Base64.getEncoder().encode( IOUtils.toByteArray( file.toURI() ) );
//
//        // prepare response
//        return Response.ok( data, "image/jpg" ).header( "Content-Disposition","inline; filename = "" + fileName + """ ).build();
//                
//	}
	
	// ADICIONAR NOVO CONTATO
//	@Consumes(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response ContatoCreate(@FormDataParam("contato") FormDataBodyPart contato, @FormDataParam("file") File foto) {
		Response entity = null;
		try {			
			ContatoDTO contatoDTO = new ContatoDTO();			
			contato.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			contatoDTO = contato.getValueAs(ContatoDTO.class);
			
			Boolean salvarContato = contatoService.salvarContato(contatoDTO, foto);			
			entity = salvarContato ? Response.status(Status.CREATED).build() : Response.notModified().build();			
			return entity;
		} catch (Exception e){
			e.printStackTrace();
			entity = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar novo contato").build();
			return entity;
		} 
	}
	
	// ATUALIZAR UM CONTATO
//	@Consumes(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response ContatoUpdate(@FormDataParam("contato") FormDataBodyPart contato, @FormDataParam("file") File foto) {
		Response entity = null;
		try {
			ContatoDTO contatoDTO = new ContatoDTO();			
			contato.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			contatoDTO = contato.getValueAs(ContatoDTO.class);
			Boolean updateContato = contatoService.updateContato(contatoDTO, foto);
			entity = updateContato ? Response.ok().build() : Response.notModified().build() ;					
			return entity;
		} catch (Exception e){
			e.printStackTrace();
			entity = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar contato").build();
			return entity;
		}
	}
	
	// DELETAR UM CONTATO
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response ContatoDelete(@PathParam("id") Integer id) {
		Response entity = null;
		try {
			Boolean deleteContato = contatoService.deleteContato(id);
			entity =  deleteContato ? Response.ok().build() : Response.notModified().build() ;	
			return entity;
		} catch (Exception e){
			e.printStackTrace();
			entity = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar contato").build();
			return entity;
		} 
	}
}
