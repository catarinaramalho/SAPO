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

	// demais métodos …
}