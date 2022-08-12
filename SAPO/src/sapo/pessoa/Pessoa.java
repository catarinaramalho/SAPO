package sapo.pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pessoa {
	private String cpf;
	private String nome;
	private String[] habilidades;
	private List<Comentario> comentarios;
	private ValidadorPessoa validador;

	public Pessoa(String cpf, String nome, String[] habilidades) {
		this.validador = new ValidadorPessoa();
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.comentarios = new ArrayList<>();

	}

	// getters e setters de todos e depois ver o que tirar;
	public String getCpf() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.validador.validacaoNome(nome);
		this.nome = nome;
	}

	public void setHabilidades(String[] habilidades) {
		this.validador.validacaoHabilidades(habilidades);
		this.habilidades = habilidades;
	}

	public String[] getHabilidades() {
		return this.habilidades;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	// validar comentarios
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	// validar comentarios
	public void adicionarComentario(Pessoa autor, String comentario) {
		this.validador.validacao(cpf, comentario);
		this.comentarios.add(new Comentario(autor, comentario));
	}

	private String listarHabilidades() {
		Arrays.sort(this.habilidades);
		String listagemHabilidades = "";
		for (String habilidade : habilidades) {
			listagemHabilidades += "- " + habilidade + "\n";
		}
		return listagemHabilidades.trim();

	}

	public String listarComentarios() {
		String listagemComentarios = this.toString() + "\nComentários:\n";
		for (Comentario comentario : this.comentarios) {
			listagemComentarios += "-- " + comentario.toString() + "\n";
		}
		return listagemComentarios.trim();
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.cpf + "\n" + this.listarHabilidades();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}

}
