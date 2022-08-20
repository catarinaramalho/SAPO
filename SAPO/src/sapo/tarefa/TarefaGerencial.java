package sapo.tarefa;

import java.util.ArrayList;
import java.util.Arrays;


import sapo.pessoa.Pessoa;

public class TarefaGerencial extends Tarefa {
	private ArrayList<Tarefa> tarefas;

	public TarefaGerencial(String nome, String codigo, String[] habilidades, String nomeAtividade,
			ArrayList<Tarefa> tarefas) {
		super(nome, codigo, SomaHabilidadesRecomendadas(habilidades,tarefas), nomeAtividade);
		this.tarefas = tarefas;
		this.duracao = calculaDuracao(tarefas);
	}
	
	public static int calculaDuracao(ArrayList<Tarefa> tarefas) {
		int duracao = 0;
		for (Tarefa tarefa : tarefas) {
			duracao += tarefa.getDuracao();
		}
		return duracao;
	}
	
	public static String[] SomaHabilidadesRecomendadas(String[] habilidades, ArrayList<Tarefa>tarefas) {
		ArrayList<String> habilidadesUnidas = new ArrayList<String>();
		habilidadesUnidas.add("gestão");
		for (String habilidade : habilidades) {
			habilidadesUnidas.add(habilidade);
		}
		for (Tarefa tarefa : tarefas) {
			for (String habilidade : tarefa.getHabilidadesRecomendadas()) {
				habilidadesUnidas.add(habilidade);
			}
		}
		return habilidadesUnidas.toArray(new String[habilidadesUnidas.size()]);
	}
	
	public void concluirTarefa() {
		for (Tarefa tarefa : this.tarefas) {
			if(!tarefa.getEstado() == true) {
				throw new IllegalArgumentException("Esta tarefa gerencial não pode ser concluída!");
			}
		} this.concluida = true;
		
	}
	
	
	@Override
	public int tamanho() {
		return this.tarefas.size() + 1;
	}

	@Override
	public void associarPessoa(Pessoa pessoa) {
		if (!this.concluida == true) {
			super.associarPessoa(pessoa);
		} else {
			throw new IllegalArgumentException("Não pode associar pessoa a uma tarefa concluída");
		}
		
	}

	@Override
	public void removerPessoa(String cpf) {
		if (!this.concluida == true) {
			super.removerPessoa(cpf);
		} else {
			throw new IllegalArgumentException("Não pode remover pessoa de uma tarefa concluída");
		}
	}
	
	

	public ArrayList<Tarefa> getTarefas() {
		return this.tarefas;
	}

	public void adicionarNaTarefaGerencial(TarefaGerencial tarefa) {
		if (tarefa.getEstado()) {
			throw new IllegalArgumentException("Não pode adicionar uma tarefa concluída!");
			
		} else {
			if(!tarefa.getTarefas().contains(this)){
				throw new IllegalArgumentException("Não pode adicionar uma tarefa que gerem ciclos!");
			} else {
				this.tarefas.add(tarefa);
			} 
		}
			
	}
	
	public void adicionarNaTarefaGerencial(Tarefa tarefa) {
		if (tarefa.getEstado()) {
			throw new IllegalArgumentException("Não pode adicionar uma tarefa concluída!");
		} else {
			this.tarefas.add(tarefa);
		}
			
	}

	public void removerDaTarefaGerencial(Tarefa tarefa) {
		this.tarefas.remove(tarefa);
	}

	public int contarTodasTarefasNaTarefaGerencial() {
		int contadorTarefas = 0;
		for (Tarefa tarefa : this.tarefas) {
			contadorTarefas += tarefa.tamanho();
		}
		return contadorTarefas;
	}

	private String listarTarefas() {
		String listagem = "";
		for (int i = this.tarefas.size(); i > -1; i--) {
			listagem += this.tarefas.get(i).getNome() + " - " + this.tarefas.get(i).getCodigo() + "\n";
			;
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