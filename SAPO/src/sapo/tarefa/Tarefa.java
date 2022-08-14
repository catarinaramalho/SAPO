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
	private String nome;
	private String codigo;
	private String[] habilidadesRecomendadas;
	private String nomeAtividade;
	private int duracao;
	private boolean concluida;
	private Map<String, Pessoa> pessoasAssociadas;

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

	/**
	 * 
	 */
	public void acrescentarHoras(int horas) {
		this.duracao += horas;
	}

	public void removerHoras(int horas) {
		this.duracao -= horas;
	}

	public void associarPessoa(Pessoa pessoa) {
		this.pessoasAssociadas.put(pessoa.getCpf(), pessoa);
	}

	public void removerPessoa(String cpf) {
		this.pessoasAssociadas.remove(cpf);
	}

	public void concluirTarefa() {
		this.concluida = true;
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
