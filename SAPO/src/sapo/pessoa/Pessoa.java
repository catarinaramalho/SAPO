package sapo.pessoa;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private String cpf;
	private String nome;
	private String[] habilidades;
	private List comentarios;
	
	public Pessoa(String cpf, String nome, String[] habilidades) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.comentarios = new ArrayList<Comentario>();
		
	}

	// getters e setters de todos e depois ver o que tirar; 
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String[] getHabilidades() {
		return habilidades;
	}


	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}


	public List getComentarios() {
		return comentarios;
	}


	public void setComentarios(List comentarios) {
		this.comentarios = comentarios;
	}


	public void alterarNomePessoa(String novoNome) {
		this.nome = novoNome;
	}

	public void alterarHabilidadesPessoa(String[] novasHabilidades){
		this.habilidades = novasHabilidades;
	}
	
}
