package sapo.tarefa;

import java.util.ArrayList;
import java.util.Arrays;

public class TarefaGerencial extends Tarefa {
	private ArrayList<Tarefa> tarefas;

	public TarefaGerencial(String nome, String codigo, String[] habilidades, String nomeAtividade,
			ArrayList<Tarefa> tarefas) {
		super(nome, codigo, habilidades, nomeAtividade);
		this.tarefas = tarefas;
	}

	public void adicionarNaTarefaGerencial(Tarefa tarefa) {
		this.tarefas.add(tarefa);
	}

	public void removerDaTarefaGerencial(Tarefa tarefa) {
		this.tarefas.remove(tarefa);
	}

	public int contarTodasTarefasNaTarefaGerencial() {
		return this.tarefas.size();
	}
	
	private String listarTarefas() {
		String listagem =  "";
		for (int i = this.tarefas.size(); i > -1; i--) {
			listagem += this.tarefas.get(i).getNome() + " - " + this.tarefas.get(i).getCodigo() + "\n";;
		}
		return listagem.trim();
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
		retorno += "\n===\nTarefas:\n" + this.listarTarefas();

		return retorno;

	}
}
