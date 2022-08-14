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
	
	public void removeHorasTarefa(String idTarefa, int horas) {
		this.tarefaService.removeHorasTarefa(idTarefa, horas);
	}

	public void concluirTarefa(String idTarefa) {
		this.tarefaService.concluirTarefa(idTarefa);
	}
	
	public void removerTarefa(String idTarefa) {
		this.tarefaService.removerTarefa(idTarefa);
	}
	
	public String exibirTarefa(String idTarefa) {
		return this.tarefaService.exibirTarefa(idTarefa);
	}
	
	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaService.associarPessoaTarefa(cpf, idTarefa);
	}
	
	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaService.removerPessoaTarefa(cpf, idTarefa);
	}
}
