package sapo.busca;

import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.tarefa.TarefaService;

public class BuscaTarefa extends BuscaAbstract {
	
	public BuscaTarefa(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<String> busca(TarefaService tarefaService) {
		return tarefaService.busca(super.getCriterio());
	}
	
	public Set<String> busca(TarefaService tarefaService, String idAtividade) {
		return tarefaService.busca(super.getCriterio(), idAtividade);
	}

}
