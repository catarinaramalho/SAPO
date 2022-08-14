package sapo.atividade;

/**
 * Controller para operações em Atividade. Valida entradas e direciona o fluxo
 * da aplicação mapeando e direcionado as ações recebidas pelo Facade para o
 * AtividadeService.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class AtividadeController {

	/**
	 * Service de Atividade a operar o fluxo recebido pelo controller.
	 */
	private AtividadeService atividadeService;
	/**
	 * Validador a ser utilizado pelo controller para validar os parâmetros
	 * recebidos.
	 */
	private ValidadorAtividade validador;

	/**
	 * Contrutor padrão do controller. Recebe uma AtividadeService como parâmetro e
	 * atribui ao respectivo atributo. E cria um validador a ser usado no
	 * controller.
	 * 
	 * @param atividadeService AtividadeService a ser usado no controller.
	 */
	public AtividadeController(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validador = new ValidadorAtividade();
	}

	/**
	 * Cadastra uma atividade no sistema. Cadastra a partir do nome e desrição da
	 * atividade e, também, pelo CPF da pessoa a ser responsável pela atividade.
	 * Retorna uma String que representa o id da atividade.
	 * 
	 * @param nome      String que representa o nome da atividade.
	 * @param descricao String que representa a descrição da atividade.
	 * @param cpf       String que representa o CPF da pessoa a ser responsável pela
	 *                  atividade.
	 * @return String com o id dessa atividade no sistema.
	 */
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
