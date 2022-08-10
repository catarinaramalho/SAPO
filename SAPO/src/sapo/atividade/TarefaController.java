package sapo.atividade;

public class TarefaController {
	TarefaService tarefaService;
	private ValidadorTarefa validadorTarefa;
	
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
		this.validadorTarefa = new ValidadorTarefa();
	}
	
	
	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		validadorTarefa.validacao(nome, habilidades);
		return tarefaService.cadastraTarefa(id, nome, habilidades);
		
	}
}
