package sapo.busca;

import sapo.pessoa.Pessoa;
import sapo.tarefa.TarefaService;

public class SugerirTarefa {

	public SugerirTarefa() {
	}

	public String[] sugere(TarefaService tarefaService, Pessoa pessoa) {
		String[] habilidades = pessoa.getHabilidades();
		return tarefaService.sugereTarefa(habilidades);
	}
}
