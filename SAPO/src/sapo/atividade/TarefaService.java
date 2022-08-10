package sapo.atividade;

public class TarefaService {
	private AtividadeService atividadeService;
	private ValidadorTarefa validadorTarefa;
	
	public TarefaService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validadorTarefa = new ValidadorTarefa(); 
	}
	
	public String cadastraTarefa(String id, String nome, String[] habilidades) {
		validadorTarefa.validacao(nome, habilidades);
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(id);
		return atividade.cadastrarTarefa(nome, habilidades);
	}

}
