package sapo.atividade;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import sapo.pessoa.Pessoa;

/**
 * Representação de uma atividade. Uma atividade representa um conjunto de ações
 * (tarefas) que devem ser tomadas para atingir um determinado objetivo.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
class Atividade {

	private String id;
	private String nome;
	private String descricao;
	private Pessoa responsavel;
	/**
	 * Inteiro que representa o estado de uma atividade. 0 caso a atividade esteja
	 * aberta, 1 caso a atividade esteja encerrada (concluída), ou 2 caso a
	 * atividade esteja desativada (abandonada ou invalidada).
	 */
	private int estado;
	private int contadorTarefas;
	private Map<String, Tarefa> tarefas;

	public Atividade(String id, String nome, String descricao, Pessoa responsavel) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.estado = 0;
		this.contadorTarefas = 0;
		this.tarefas = new HashMap<>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int novoEstado) {
		this.estado = novoEstado;
	}
	
	public void alteraNome(String id, String nome) {
		tarefas.get(id).setNome(nome);
	}
	
	public void alteraHabilidades(String id, String[] habilidades) {
		tarefas.get(id).setHabilidades(habilidades);
	}
	
	
	private Set<Tarefa> tarefasPendentes() {
		Set<Tarefa> tarefasPendentes = new HashSet<>();
		for (String key : this.tarefas.keySet()) {
			if (this.tarefas.get(key).getEstado() == false) {
				tarefasPendentes.add(this.tarefas.get(key));
			}
		}
		return tarefasPendentes;
	}
	
	/**
	 * Método que 
	 * @param nome
	 * @param habilidades
	 * @return
	 */
	public String cadastrarTarefa(String nome, String[] habilidades) {
		String idPronto = this.id + "-" + this.contadorTarefas;
		this.contadorTarefas++;
		tarefas.put(idPronto, new Tarefa(nome, idPronto, habilidades, this.getNome()));
		
		return idPronto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		String responsavel = "";
		if (this.responsavel != null) {
			responsavel = "\nResponsável: " + this.responsavel.getNome() + " – " + this.responsavel.getCpf();
		}
		return this.id + ": " + this.nome + responsavel + "\n===\n" + this.descricao + "\n===\nTarefas: ";
	}
}
