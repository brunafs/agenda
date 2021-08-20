package hepta.agenda.entity;

public class GrupoDTO {

	private Integer id;
	private String nome;
	
	public GrupoDTO(Grupo grupo) {
        this.setId(grupo.getIdGrupo());
        this.setNome(grupo.getGrupo());
	}
	
	public GrupoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
