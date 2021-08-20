package hepta.agenda.entity;

public class ArquivoDTO {

	private Integer id;	
	private byte[] arquivo;
	private String nome;
    private String tamanho;
    private String tipo;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] foto) {
		this.arquivo = foto;
	}
	
	
	public ArquivoDTO(Arquivo file) {
        this.setId(file.getId());
        this.setArquivo(file.getArquivo());
	}
	
	public ArquivoDTO() {
	}
	
}
