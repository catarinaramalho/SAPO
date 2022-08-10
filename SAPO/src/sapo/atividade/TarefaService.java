package sapo.atividade;

public class TarefaService {
	private AtividadeService atividadeService;
	
	public TarefaService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
	}
	
	public String cadastraTarefa(String id, String nome, String[] habilidades) {
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(id);
		return atividade.cadastrarTarefa(nome, habilidades);
	}

}
