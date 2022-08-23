package sapo.pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import sapo.funcao.Funcao;
import sapo.tarefa.Tarefa;

/**
 * Representação de uma pessoa. Uma pessoa possui nome, cpf (sua identificação)
 * e pode conter habilidades e uma função.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa dos Santos - 121110281
 */
public class Pessoa implements Comparable<Pessoa> {

	/**
	 * Identificador da pessoa.
	 */
	private String cpf;
	/**
	 * Nome da pessoa.
	 */
	private String nome;
	/**
	 * Habilidades de uma pessoa, que pode ser uma lista vazia de habilidades.
	 */
	private String[] habilidades;
	/**
	 * Lista de Comentários que uma pessoa pode receber.
	 */
	private List<Comentario> comentarios;
	/**
	 * Função de uma pesssoa (professor e aluno) ou pode ser nulo.
	 */
	private Funcao funcao;
	/**
	 * Nível de uma pessoa.
	 */
	private int nivel;
	/**
	 * Conjunto de tarefas avaliadas de pessoa.
	 */
	private Set<Tarefa> tarefasAvaliadas;
	/**
	 * O validador dos atributos de pessoa.
	 */
	private ValidadorPessoa validador;

	/**
	 * Construtor de Pessoa. Constrói uma pessoa a partir de um cpf, nome e
	 * habilidades.
	 * 
	 * @param cpf         String com o identificador único da pessoa.
	 * @param nome        String com o nome da pessoa.
	 * @param habilidades array contendo a lista de habilidades.
	 */
	public Pessoa(String cpf, String nome, String[] habilidades) {
		this(cpf, nome, habilidades, null);
	}

	/**
	 * Construtor de Pessoa. Constrói uma pessoa a partir de um cpf, nome e
	 * habilidades e uma função, inicializa o nível com 0, o validador e o conjunto
	 * de tarefas avaliadas.
	 * 
	 * @param cpf         String com o identificador único da pessoa.
	 * @param nome        String com o nome da pessoa.
	 * @param habilidades array contendo a lista de habilidades.
	 * @param funcao      Função da pessoa.
	 */
	public Pessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.comentarios = new ArrayList<>();
		this.funcao = funcao;
		this.nivel = 0;
		this.tarefasAvaliadas = new HashSet<>();
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
			listagemHabilidades += "\n- " + habilidade;
		}
		return listagemHabilidades;
	}

	public String listarComentarios() {
		String listagemComentarios = this.toString() + "\nComentários:";
		for (Comentario comentario : this.comentarios) {
			listagemComentarios += "\n-- " + comentario.toString();
		}
		return listagemComentarios;
	}

	public boolean possuiHabilidade(String criterio) {
		for (String habilidade : this.habilidades) {
			if (habilidade.toUpperCase().equals(criterio)) {
				return true;
			}
		}
		return false;
	}

	private void armazenaNivel(int nivel) {
		this.nivel = nivel;
		// TODO
	}

	@Override
	public String toString() {
		String toString = this.nome + " - " + this.cpf;
		if (funcao != null) {
			toString += "\n" + this.funcao.toString();
		}
		if (!this.listarHabilidades().isBlank()) {
			toString += this.listarHabilidades();
		}
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
