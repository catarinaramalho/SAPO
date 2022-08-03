package sapo.atividade;

public class AtividadeController {

	private AtividadeService atividadeService;
	private ValidadorAtividades validador;

	public AtividadeController(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validador = new ValidadorAtividades();
	}

	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		this.validador.validacao(nome, descricao, cpf);
		return this.atividadeService.cadastrarAtividade(nome, descricao, cpf);
	}
}
