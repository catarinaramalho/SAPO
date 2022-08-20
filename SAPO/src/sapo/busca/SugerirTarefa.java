package sapo.busca;

import java.util.ArrayList;
import java.util.List;

import sapo.pessoa.Pessoa;
import sapo.tarefa.TarefaService;

public class SugerirTarefa {
	private String[] resultado;

	public SugerirTarefa() {
	}

	public String[] sugere(TarefaService tarefaService, Pessoa pessoa) {
		String[] habilidades = pessoa.getHabilidades();
		this.resultado = tarefaService.sugereTarefa(habilidades);
		return this.resultado;
	}

	public String[] representaBusca() {
		List<String> representacao = new ArrayList<>();
		representacao.add("SUGESTÃO");
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
