package sapo.atividade;

public class TarefaController {
	TarefaService tarefaService;
	private Validador validador;
	
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
		this.validador = new Validador();
	}
	
	
	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		validador.validacao(nome, habilidades);
		return tarefaService.cadastraTarefa(id, nome, habilidades);	
	}
	
}
