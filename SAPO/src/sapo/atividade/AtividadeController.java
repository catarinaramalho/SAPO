package sapo.atividade;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
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

	public void encerrarAtividade(String atividadeId) {
		// TODO
	}

	public void desativarAtividade(String atividadeId) {
		// TODO
	}

	public void reabrirAtividade(String atividadeId) {
		// TODO
	}

	public String exibirAtividade(String atividadeId) {
		// TODO
		return null;
	}

	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		// TODO
	}

	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		// TODO
	}
}
