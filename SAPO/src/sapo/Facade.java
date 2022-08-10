package sapo;

import sapo.atividade.AtividadeController;
import sapo.atividade.AtividadeService;

/**
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa dos Santos - 121110281
 */
public class Facade {

	// private PessoaController pessoaController;
	private AtividadeController atividadeController;
	// … demais controllers

	public Facade() {
		var atividadeService = new AtividadeService();

		// this.pessoaController = new PessoaController();
		this.atividadeController = new AtividadeController(atividadeService);
		// … demais controllers
	}

	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		// this.pessoaController.cadastrarPessoa(cpf, nome, habilidades);
	}

	// métodos de atividade
	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		return this.atividadeController.cadastrarAtividade(nome, descricao, cpf);
	}

	public void encerrarAtividade(String atividadeId) {
		this.atividadeController.encerrarAtividade(atividadeId);
	}

	public void desativarAtividade(String atividadeId) {
		this.atividadeController.desativarAtividade(atividadeId);
	}

	public void reabrirAtividade(String atividadeId) {
		this.atividadeController.reabrirAtividade(atividadeId);
	}

	public String exibirAtividade(String atividadeId) {
		return this.atividadeController.exibirAtividade(atividadeId);
	}

	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.atividadeController.alterarDescricaoAtividade(atividadeId, descricao);
	}

	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		this.atividadeController.alterarResponsavelAtividade(atividadeId, cpf);
	}
}