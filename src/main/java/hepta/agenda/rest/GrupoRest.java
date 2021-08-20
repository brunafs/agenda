package hepta.agenda.rest;

import java.util.ArrayList;
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

import hepta.agenda.entity.GrupoDTO;
import hepta.agenda.service.GrupoService;

@Path("/grupos")
public class GrupoRest {
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
//	private GrupoDAO dao;
	private GrupoService grupoService;
	
	public GrupoRest() {
		grupoService = new GrupoService();
	}
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	// LISTAR GRUPOS
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response GrupoListar() {
		List<GrupoDTO> grupos = new ArrayList<>();
		try {
			grupos = grupoService.listar();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar grupos de contatos").build();
		}
		return Response.status(Status.OK).entity(grupos).build();
	}
	
	// LISTAR UM GRUPO POR ID
	@Path("/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response GrupoListar(@PathParam("id") Integer id) {
		GrupoDTO grupoResponse = new GrupoDTO();
		try {
			grupoResponse = grupoService.buscar(id);
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar grupo de contato").build();
		}
		return Response.status(Status.OK).entity(grupoResponse).build();
	}
	
	// ADICIONAR NOVO GRUPO
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@POST
		public Response GrupoCreate(GrupoDTO grupo) {
			Response entity = null;
			try {
				grupoService.salvar(grupo);
				entity = Response.status(Status.CREATED).build();	
				return entity;
			} catch (Exception e){
				e.printStackTrace();
				entity = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar novo grupo de contato").build();
				return entity;
			} 
		}
		
		// ATUALIZAR UM GRUPO
		@Consumes(MediaType.APPLICATION_JSON)
		@PUT
		public Response GrupooUpdate(GrupoDTO grupo) {
//			Response entity = null;
//			boolean reponse = false;
			try {
				grupoService.update(grupo);
				return Response.ok().build();
			} catch (Exception e){
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar grupo de contato").build();
			} 
		}
		
		// DELETAR UM GRUPO
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@DELETE
		public Response GrupoDelete(@PathParam("id") Integer id) {
			Response entity = null;
			boolean reponse = false;
			try {
				grupoService.delete(id);
				entity = reponse ? Response.ok().build() : Response.notModified().build();
				return entity;
			} catch (Exception e){
				e.printStackTrace();
				entity = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar grupo de contato").build();
				return entity;
			} 
		}
}
