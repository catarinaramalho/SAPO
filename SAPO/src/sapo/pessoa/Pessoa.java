package sapo.pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import sapo.funcao.Funcao;

public class Pessoa implements Comparable<Pessoa> {
	private String cpf;
	private String nome;
	private String[] habilidades;
	private List<Comentario> comentarios;
	private Funcao funcao;
	private int nivel;
	private ValidadorPessoa validador;

	public Pessoa(String cpf, String nome, String[] habilidades) {
		this(cpf, nome, habilidades, null);
	}

	public Pessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.comentarios = new ArrayList<>();
		this.funcao = funcao;
		this.nivel = 0;
		this.validador = new ValidadorPessoa();
	}

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

	public String[] getHabilidades() {
		return this.habilidades;
	}

	public void setHabilidades(String[] habilidades) {
		this.validador.validacaoHabilidades(habilidades);
		this.habilidades = habilidades;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Funcao getFuncao() {
		return this.funcao;
	}

	public void setFuncao(Funcao novaFuncao) {
		this.funcao = novaFuncao;
	}

	public int getNivel() {
		return this.nivel;
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

	public boolean possuiHabilidade(String criterio) {
		for (String habilidade : this.habilidades) {
			if (habilidade.toUpperCase().equals(criterio)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String toString = this.nome + " - " + this.cpf + "\n";
		if (funcao != null) {
			toString += this.funcao.toString() + "\n";
		}
		toString += this.listarHabilidades();
		return toString;
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

	@Override
	public int compareTo(Pessoa outraPessoa) {
		if (outraPessoa == null) {
			throw new NullPointerException("Pessoa não pode ser nulo");
		}
		if (this.getNome().compareTo(outraPessoa.getNome()) == 0) {
			return this.getCpf().compareTo(outraPessoa.getCpf());
		}
		return this.getNome().compareTo(outraPessoa.getNome());
	}
}
