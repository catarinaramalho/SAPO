package sapo;

import sapo.atividade.AtividadeController;
import sapo.atividade.AtividadeService;
import sapo.busca.BuscaController;
import sapo.busca.BuscaService;
import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaController;
import sapo.pessoa.PessoaService;
import sapo.tarefa.TarefaController;
import sapo.tarefa.TarefaService;

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
	private BuscaController buscaController;
	// … demais controllers

	public Facade() {
		var pessoaService = new PessoaService();
		var atividadeService = new AtividadeService(pessoaService);
		var tarefaService = new TarefaService(atividadeService, pessoaService);
		var buscaService = new BuscaService(pessoaService);

		this.pessoaController = new PessoaController(pessoaService);
		this.atividadeController = new AtividadeController(atividadeService);
		this.tarefaController = new TarefaController(tarefaService);
		this.buscaController = new BuscaController(buscaService);
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
	
	public void removerHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.removeHorasTarefa(idTarefa, horas);
	}
	
	public void concluirTarefa(String idTarefa) {
		this.tarefaController.concluirTarefa(idTarefa);
	}
	
	
	public void removerTarefa(String idTarefa) {
		this.tarefaController.removerTarefa(idTarefa);
	}
	
	public String exibirTarefa(String idTarefa) {
		return this.tarefaController.exibirTarefa(idTarefa);
	}
	
	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.associarPessoaTarefa(cpf, idTarefa);
	}
	
	public void removerPessoaTarefa(String cpf, String idTarefa){
		this.tarefaController.removerPessoaTarefa(cpf, idTarefa);
	}
	
	public String[] buscaPessoa(String criterioBusca) {
		return this.buscaController.busca(criterioBusca);
	}
}