package sapo;

import sapo.atividade.AtividadeController;
import sapo.atividade.AtividadeService;
import sapo.busca.BuscaController;
import sapo.busca.BuscaService;
import sapo.funcao.FuncaoController;
import sapo.funcao.FuncaoService;
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
	private FuncaoController funcaoController;
	// … demais controllers

	public Facade() {
		var pessoaService = new PessoaService();
		var atividadeService = new AtividadeService(pessoaService);
		var tarefaService = new TarefaService(atividadeService, pessoaService);
		var buscaService = new BuscaService(pessoaService, atividadeService);
		var funcaoService = new FuncaoService(pessoaService, tarefaService);

		this.pessoaController = new PessoaController(pessoaService);
		this.atividadeController = new AtividadeController(atividadeService);
		this.tarefaController = new TarefaController(tarefaService);
		this.buscaController = new BuscaController(buscaService);
		this.funcaoController = new FuncaoController(funcaoService);
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

	public void alterarHabilidadesPessoa(String cpf, String[] novasHabilidades) {
		this.pessoaController.alterarHabilidades(cpf, novasHabilidades);
	}

	public void removerPessoa(String cpf) {
		this.pessoaController.removerPessoa(cpf);
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

	// métodos de tarefa
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

	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.removerPessoaTarefa(cpf, idTarefa);
	}

	// métodos de busca
	
	public String[] exibirPessoas(String consulta) {
		return this.buscaController.buscarPessoas(consulta);
	}
	
	public String[] buscarAtividade(String consulta) {
		return this.buscaController.buscarAtividades(consulta);
	}

	public String[] buscarTarefas(String nome) {
		return this.buscaController.buscarTarefas(nome);
	}

	public String[] buscarTarefas(String idAtividade, String nome) {
		return this.buscaController.buscarTarefas(nome, idAtividade);
	}

	public String[] sugerirTarefas(String cpf) {
		return this.buscaController.sugerirTarefas(cpf);
	}

	public String[] buscasMaisRecentes(int nBuscas) {
		return this.buscaController.buscasMaisRecentes(nBuscas);
	}

	public String[] exibirHistóricoBusca(int indexBusca) {
		return this.buscaController.exibirHistóricoBusca(indexBusca);
	}

	// métodos de função
	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.funcaoController.cadastrarAluno(cpf, nome, matr, periodo, habilidades);
	}

	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.funcaoController.cadastrarProfessor(cpf, nome, siape, disciplinas, habilidades);
	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.funcaoController.definirFuncaoProfessor(cpf, siape, disciplinas);
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.funcaoController.definirFuncaoAluno(cpf, matr, periodo);
	}

	public void removerFuncao(String cpf) {
		this.funcaoController.removerFuncao(cpf);
	}

	public int pegarNivel(String cpf) {
		return this.funcaoController.pegarNivel(cpf);
	}

	public String[] listarPessoas() {
		return this.funcaoController.listarPessoas();
	}

	// métodos de tarefas gerenciais
}
