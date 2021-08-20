package hepta.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idGrupo;
	
	@Column(name = "grupo")
	private String grupo;
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	
	public void setIdGrupo(Integer id) {
		this.idGrupo = id;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
