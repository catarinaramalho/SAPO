package sapo.pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import sapo.funcao.Funcao;
import sapo.funcao.Professor;
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
	 * Retorna um cpf da pessoa.
	 * 
	 * @return String com o cpf da pessoa.
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Retorna o nome da pessoa.
	 * 
	 * @return String com o nome da pessoa.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Altera o nome da pessoa.
	 * 
	 * @param nome String com novo nome da pessoa.
	 */
	public void setNome(String nome) {
		this.validador.validacaoNome(nome);
		this.nome = nome;
	}

	/**
	 * Método que retorna as habilidades da pessoa. * @return Array com a lista de
	 * habilidades da pessoa.
	 */
	public String[] getHabilidades() {
		return this.habilidades;
	}

	/**
	 * Altera as habilidades de pessoa.
	 * 
	 * @param habilidades Array com novas habilidades de pessoa.
	 */
	public void setHabilidades(String[] habilidades) {
		this.validador.validacaoHabilidades(habilidades);
		this.habilidades = habilidades;
	}

	/**
	 * Retorna lista de comentários.
	 * 
	 * @return Lista de comentários da pessoa.
	 */
	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	/**
	 * Retorna a função da pesoa.
	 * 
	 * @return Função da pessoa.
	 */
	public Funcao getFuncao() {
		return this.funcao;
	}

	/**
	 * Altera a função de uma pessoa.
	 * 
	 * @param novaFuncao String com a nova função de pessoa.
	 */
	public void setFuncao(Funcao novaFuncao) {
		this.funcao = novaFuncao;
	}

	/**
	 * Retorna o nível da pessoa.
	 * 
	 * @return Nível da pessoa.
	 */
	public int getNivel() {
		return this.nivel;
	}

	public Set<Tarefa> getTarefasAvaliadas() {
		return this.tarefasAvaliadas;
	}
	
	public String[] getDisciplinas() {
		if (this.funcao == null) {
			throw new NullPointerException("Pessoa não é do tipo professor");
		} else if (this.funcao.getClass() != Professor.class) {
			throw new ClassCastException("Pessoa não é do tipo professor");
		}
		Professor professor = (Professor) this.funcao;
		return professor.getDisciplinas();
	}

	/**
	 * Adiciona um comentário na lista de comntários da pessoa, criando um
	 * comentário do tipo Comentario.
	 * 
	 * @param autor      Pessoa que escreveu o comentário.
	 * @param comentario String com o comentário.
	 */
	public void adicionarComentario(Pessoa autor, String comentario) {
		this.validador.validacaoComentario(comentario);
		this.comentarios.add(new Comentario(autor, comentario));
	}

	/**
	 * Lista habilidades em ordem alfabética da pessoa.
	 * 
	 * @return String com a Listagem de habilidades.
	 */
	private String listarHabilidades() {
		Arrays.sort(this.habilidades);
		String listagemHabilidades = "";
		for (String habilidade : habilidades) {
			listagemHabilidades += "\n- " + habilidade;
		}
		return listagemHabilidades;
	}

	/**
	 * Lista comentários da pessoa.
	 * 
	 * @return String com a Listagem de comentários.
	 */
	public String listarComentarios() {
		String listagemComentarios = this.toString() + "\nComentários:";
		for (Comentario comentario : this.comentarios) {
			listagemComentarios += "\n-- " + comentario.toString();
		}
		return listagemComentarios;
	}

	/**
	 * Verificação se a pessoa possui a habilidade conforme o critério passado no
	 * parâmetro.
	 * 
	 * @param criterio String contendo o critério de verificação.
	 * @return Verdadeiro ou Falso conforme a verificação.
	 */
	public boolean possuiHabilidade(String criterio) {
		for (String habilidade : this.habilidades) {
			if (habilidade.toUpperCase().equals(criterio)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Armazena o nível da pessoa.
	 * 
	 * @param nivel Inteiro contendo o novo nível da pessoa.
	 */
	public void armazenaNivel(Set<Tarefa> tarefas, int nivel) {
		this.tarefasAvaliadas.addAll(tarefas);
		this.nivel = nivel;
	}

	/**
	 * Representação textual da pessoa, com seus atributos e funções.
	 * 
	 * @param Representação Textual.
	 */
	@Override
	public String toString() {
		String toString = this.nome + " – " + this.cpf;
		if (funcao != null) {
			toString += "\n" + this.funcao.toString();
		}
		if (!this.listarHabilidades().isBlank()) {
			toString += this.listarHabilidades();
		}
		return toString;
	}

	/**
	 * Retorna a referência da pessoa.
	 * 
	 * @param um inteiro baseado no identificador único, o próprio cpf da pessoa.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	/**
	 * Compara pessoas mediante o cpf.
	 * 
	 * @param Valor lógico para a verificação.
	 */
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

	/**
	 * Compara pessoas mediante o cpf.
	 * 
	 * @param outraPessoa Pessoa a ser comparada.
	 * @return Número inteiro que resulta da verificação.
	 */
	@Override
	public int compareTo(Pessoa outraPessoa) {
		if (outraPessoa == null) {
			throw new NullPointerException("Pessoa não pode ser nulo!");
		}
		if (this.getNome().compareTo(outraPessoa.getNome()) == 0) {
			return this.getCpf().compareTo(outraPessoa.getCpf());
		}
		return this.getNome().compareTo(outraPessoa.getNome());
	}
}
