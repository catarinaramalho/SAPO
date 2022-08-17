package sapo.tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaGerencial extends Tarefa {
	private List<String> idTarefas;

	public TarefaGerencial(String nome, String codigo, String[] habilidades, String nomeAtividade, String[] idTarefas) {
		super(nome, codigo, habilidades, nomeAtividade);
		this.idTarefas = this.converterArrayParaLista(idTarefas);
	}
	
	private List<String> converterArrayParaLista(String[] idTarefas) {
		List<String> lista = new ArrayList<String>();
		for (String id : idTarefas) {
			lista.add(id);
		}
		return lista;
	}
	
	 public void adicionarNaTarefaGerencial(String idTarefa) {
		 this.idTarefas.add(idTarefa);
	 }

	 public void removerDaTarefaGerencial(String idTarefa){
		 this.idTarefas.remove(idTarefa);
	 }

	 public int contarTodasTarefasNaTarefaGerencial() {
		 return this.idTarefas.size();
	 }
}
