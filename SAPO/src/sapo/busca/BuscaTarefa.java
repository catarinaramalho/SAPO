package sapo.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.tarefa.TarefaService;

public class BuscaTarefa extends BuscaAbstract {
	Set<String> resultado;
	
	public BuscaTarefa(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<String> busca(TarefaService tarefaService) {
		this.resultado = tarefaService.busca(super.getCriterio());
		return this.resultado;
	}
	
	public Set<String> busca(TarefaService tarefaService, String idAtividade) {
		this.resultado = tarefaService.busca(super.getCriterio(), idAtividade);
		return this.resultado;
	}

	@Override
	public String[] representaBusca() {
		List<String> representacao = new ArrayList<>();
		representacao.add("TAREFA");
		for (String tarefas : this.resultado) {
			representacao.add(tarefas);
		}

		String[] retorno = new String[representacao.size()];
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = representacao.get(i);
		}

		return retorno;
	}

}
