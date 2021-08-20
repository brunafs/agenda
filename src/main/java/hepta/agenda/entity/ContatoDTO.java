package hepta.agenda.entity;

import java.time.LocalDate;
import java.util.Base64;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class ContatoDTO {
	
	private Integer id;
	private String nome;
	private String telefone;	
	private String nomeCompleto;
	private String email;
	private Integer idGrupo;
	
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataNascimento;
	
	private byte[] foto;
    private String fotoNome;
    private String fotoTamanho;
    private String fotoTipo;
	
	public ContatoDTO(Contato contato) {
        this.setId(contato.getId());
        this.setNome(contato.getNome());
        this.setTelefone(contato.getTelefone());
        this.setNomeCompleto(contato.getNomeCompleto());
        this.setEmail(contato.getEmail());
        this.setIdGrupo(contato.getIdGrupo());
        this.setDataNascimento(contato.getDataNascimento());
        this.setFoto(contato.getFoto());
        this.setFotoNome(contato.getFotoNome());
        this.setFotoTamanho(contato.getFotoTamanho());
        this.setFotoTipo(contato.getFotoTipo());
	}
	
	public ContatoDTO() {
	}
	
	public Contato toContato() {
		Contato contato = new Contato();
		
		contato.setId(this.id);
		contato.setNome(this.nome);
		contato.setNomeCompleto(this.nomeCompleto);
		contato.setTelefone(this.telefone);
		contato.setEmail(this.email);
		contato.setIdGrupo(this.idGrupo);
		contato.setDataNascimento(this.dataNascimento);
		contato.setFoto(this.foto);
		contato.setFotoNome(this.fotoNome);
		contato.setFotoTamanho(this.fotoTamanho);
		contato.setFotoTipo(this.fotoTipo);
		
		return contato;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdGrupo() {
		return idGrupo;
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
}
