package sapo.atividade;

public class TarefaService {
	private AtividadeService atividadeService;
	private Validador validador;
	
	public TarefaService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validador = new Validador(); 
	}
	
	public String cadastraTarefa(String id, String nome, String[] habilidades) {
		validador.validacao(nome, habilidades);
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(id);
		return atividade.cadastrarTarefa(nome, habilidades);
	}

}
