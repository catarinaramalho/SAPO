package sapo.atividade;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class AtividadeController {

	private AtividadeService atividadeService;
	private Validador validador;

	public AtividadeController(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validador = new Validador();
	}

	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		this.validador.validacao(nome, descricao, cpf);
		return this.atividadeService.cadastrarAtividade(nome, descricao, cpf);
	}

	public void encerrarAtividade(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		this.atividadeService.encerrarAtividade(atividadeId);
	}

	public void desativarAtividade(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		this.atividadeService.desativarAtividade(atividadeId);
	}

	public void reabrirAtividade(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		this.atividadeService.reabrirAtividade(atividadeId);
	}

	public String exibirAtividade(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		return this.atividadeService.exibirAtividade(atividadeId);
	}

	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.validador.validacaoAlterarDescricaoAtividade(atividadeId, descricao);
		this.atividadeService.alterarDescricaoAtividade(atividadeId, descricao);
	}

	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		// TODO
	}
}
