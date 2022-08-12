package sapo;

import sapo.atividade.AtividadeController;
import sapo.atividade.AtividadeService;
import sapo.atividade.TarefaController;
import sapo.atividade.TarefaService;
import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaController;

/**
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa dos Santos - 121110281
 */
public class Facade {

	private PessoaController pessoaController;
	private AtividadeController atividadeController;
	private TarefaController tarefaController;
	// … demais controllers

	public Facade() {
		var atividadeService = new AtividadeService();
		var tarefaService = new TarefaService(atividadeService);

		this.pessoaController = new PessoaController();
		this.atividadeController = new AtividadeController(atividadeService);
		this.tarefaController = new TarefaController(tarefaService);
		// … demais controllers
	}

	// métodos de pessoa
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.pessoaController.cadastrarPessoa(cpf, nome, habilidades);
	}

	public String exibirPessoa(String cpf) {
		return this.pessoaController.exibirPessoa(cpf);
	}

	public void alterarNomePessoa(String cpf, String novoNome) {
		this.pessoaController.alterarNomePessoa(cpf, novoNome);
	}

	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.pessoaController.alterarHabilidades(cpf, novasHabilidades);
	}

	public void removerPessoa(String cpf) {
		this.pessoaController.removerPessoa(cpf);
	}

	public Pessoa recuperarPessoa(String cpf) {
		return this.pessoaController.recuperarPessoa(cpf);
	}

	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.pessoaController.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}

	public String listarComentariosPessoa(String cpf) {
		return this.pessoaController.listarComentariosPessoa(cpf);

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

	public String cadastrarTarefa(String id, String nome, String[] habilidades) {
		return this.tarefaController.cadastrarTarefa(id, nome, habilidades);
	}

	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		this.tarefaController.alterarNomeTarefa(idTarefa, novoNome);
	}
	
	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.tarefaController.alterarHabilidadesTarefa(idTarefa, habilidades);
	}
	
	public void adicionarHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.adicionarHorasTarefa(idTarefa, horas);
	}
	+ removerHorasTarefa(idTarefa: str, horas: int): void
	+ concluirTarefa(idTarefa: str): void
	+ removerTarefa(idTarefa: str): void
	+ exibirTarefa(idTarefa: str): str
	+ associarPessoaTarefa(cpf: str, idTarefa: str): void
	+ removerPessoaTarefa(cpf: str, idTarefa: str): void
}