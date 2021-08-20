package hepta.agenda.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "fullname")
	private String nomeCompleto;
	
	@Column(name = "email")
	private String email;
		
	@ManyToOne
	@JoinColumn (name = "grupo_id")
	private Integer idGrupo;
	
	private LocalDate dataNascimento;	
	
	private byte[] foto;
    private String fotoNome;
    private String fotoTamanho;
    private String fotoTipo;
	
    
	@Override
	public int hashCode() {
		return Objects.hash(email, id, idGrupo, nome, nomeCompleto, telefone, 
				dataNascimento, foto, fotoNome, fotoTamanho, fotoTipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contato))
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(id, other.id)
				&& Objects.equals(idGrupo, other.idGrupo)
				&& Objects.equals(nome, other.nome)
				&& Objects.equals(nomeCompleto, other.nomeCompleto)
				&& Objects.equals(telefone, other.telefone)
				&& Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(foto, other.foto)
				&& Objects.equals(fotoNome, other.fotoNome)
				&& Objects.equals(fotoTamanho, other.fotoTamanho)
				&& Objects.equals(fotoTipo, other.fotoTipo);
	}
    

	public String getFotoNome() {
		return fotoNome;
	}

	public void setFotoNome(String fotoNome) {
		this.fotoNome = fotoNome;
	}

	public String getFotoTamanho() {
		return fotoTamanho;
	}

	public void setFotoTamanho(String fotoTamanho) {
		this.fotoTamanho = fotoTamanho;
	}

	public String getFotoTipo() {
		return fotoTipo;
	}

	public void setFotoTipo(String fotoTipo) {
		this.fotoTipo = fotoTipo;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}
