package sapo.atividade;

import java.util.Objects;

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

	public Atividade(String id, String nome, String descricao, Pessoa responsavel) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.responsavel = responsavel;
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
		return this.id + ": " + this.nome + "\nResponsável: " + this.responsavel.getNome() + " – "
				+ this.responsavel.getCpf() + "\n===\n" + this.descricao + "\n===\n";
	}
}
