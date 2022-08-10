package sapo.atividade;

public class TarefaController {
	TarefaService tarefaService;
	
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}
	

	//VALIDACAO DE ID NOME E HABILIDADE
	
	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		return tarefaService.cadastraTarefa(id, nome, habilidades);
		
	}
}
