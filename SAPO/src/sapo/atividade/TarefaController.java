package sapo.atividade;

public class TarefaController {
	TarefaService tarefaService;
	private Validador validador;

	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
		this.validador = new Validador();
	}

	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		this.validador.validacao(nome, habilidades);
		return this.tarefaService.cadastraTarefa(id, nome, habilidades);
	}

	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		this.tarefaService.alterarNomeTarefa(idTarefa, novoNome);
	}

	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.tarefaService.alterarHabilidadesTarefa(idTarefa, habilidades);
	}

	public void adicionarHorasTarefa(String idTarefa, int horas) {
		this.tarefaService.adicionarHorasTarefa(idTarefa, horas);
	}

}
