package sapo.tarefa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import sapo.pessoa.Pessoa;

/**
 * Classe que representa uma tarefa no sistema, onde varias tarefas podem ser
 * armazenada em uma atividasde e cada tarefa só pode estar associada com uma
 * atividade.
 * 
 * @author Lucas Leones Costa Dos Santos - 121110281
 *
 */
public class Tarefa {
	protected String nome;
	protected String codigo;
	protected String[] habilidadesRecomendadas;
	protected String nomeAtividade;
	protected int duracao;
	protected boolean concluida;
	protected Map<String, Pessoa> pessoasAssociadas;
	

	public Tarefa(String nome, String codigo, String[] habilidades, String nomeAtividade) {
		this.nome = nome;
		this.codigo = codigo;
		this.habilidadesRecomendadas = habilidades;
		this.nomeAtividade = nomeAtividade;
		this.pessoasAssociadas = new HashMap<>();
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setHabilidades(String[] novasHabilidades) {
		this.habilidadesRecomendadas = novasHabilidades;
	}

	public boolean getEstado() {
		return this.concluida;
	}

	public String getId() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String[] getHabilidades() {
		return this.habilidadesRecomendadas;
	}
	
	public boolean temPessoa() {
		if (this.pessoasAssociadas.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo que adiciona uma quantidade de horas ao atributo "duracao" dessa
	 * tarefa.
	 */
	public void acrescentarHoras(int horas) {
		this.duracao += horas;
	}

	/**
	 * Metodo que remove uma quantidade de horas ao atributo "duracao" dessa tarefa.
	 */
	public void removerHoras(int horas) {
		this.duracao -= horas;
	}

	/**
	 * Metodo que associa uma pessoa ao mapa de pessoas resposaveis que a tarefa
	 * possui, usando do cpf como chave para localizar a pessoa no mapa.
	 * 
	 * @param pessoa O objeto pessoa que será armazenado no mapa de pessoas
	 *               responsáveis por essa tarefa.
	 */
	public void associarPessoa(Pessoa pessoa) {
		this.pessoasAssociadas.put(pessoa.getCpf(), pessoa);
	}

	/**
	 * Metodo que remove uma pessoa do mapa de pessoas resposaveis que a tarefa
	 * possui, usando do cpf para localizar qual pessoa deve ser removida.
	 * 
	 * @param cpf A chave de localização no mapa que vai indicar qual pessoa deve
	 *            ser removida.
	 */
	public void removerPessoa(String cpf) {
		this.pessoasAssociadas.remove(cpf);
	}

	/**
	 * Metodo que
	 */
	public void concluirTarefa() {
		this.concluida = true;
	}

	public boolean pessoaAssociada(String cpf) {
		return this.pessoasAssociadas.containsKey(cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		String retorno = "";
		retorno += this.nome + " - " + this.codigo + "\n";
		retorno += "- " + this.nomeAtividade + "\n";

		retorno += Arrays.toString(habilidadesRecomendadas).substring(1,
				(Arrays.toString(habilidadesRecomendadas).length() - 1)) + "\n";

		retorno += "(" + this.duracao + " hora(s) executada(s))\n===\nEquipe:\n";
		for (String cpf : this.pessoasAssociadas.keySet()) {
			retorno += this.pessoasAssociadas.get(cpf).getNome() + " – " + this.pessoasAssociadas.get(cpf).getCpf();
		}

		return retorno;
	}
}
