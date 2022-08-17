package sapo.tarefa;

/**
 * Classe responsável por fazer validações na hora de criar uma nova tarefa e
 * pela delegação de operações envolvendo cada objeto Tarefa para a classe
 * responsável, chamando os metodos de tarefaService que possuem a logica para
 * executar as operações.
 * 
 * @author Lucas Leones Costa dos Santos
 *
 */
public class TarefaController {
	TarefaService tarefaService;
	private ValidadorTarefa validadorTarefa;

	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
		this.validadorTarefa = new ValidadorTarefa();
	}

	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		this.validadorTarefa.validacao(nome, habilidades);
		return this.tarefaService.cadastraTarefa(id, nome, habilidades);
	}
	
	public String cadastrarTarefaGerencial(String id, String nome, String[] habilidades) {
		this.validadorTarefa.validacao(nome, habilidades);
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
