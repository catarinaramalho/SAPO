package sapo.atividade;

import java.util.NoSuchElementException;
import java.util.Optional;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class TarefaService {
	private AtividadeService atividadeService;
	private Validador validador;
	private PessoaService pessoaService;

	public TarefaService(AtividadeService atividadeService, PessoaService pessoaService) {
		this.atividadeService = atividadeService;
		this.pessoaService = pessoaService;
		this.validador = new Validador();
	}

	public String cadastraTarefa(String id, String nome, String[] habilidades) {
		validador.validacao(nome, habilidades);
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(id);
		return atividade.cadastrarTarefa(nome, habilidades);
	}

	private Tarefa recuperaTarefaOuFalhe(String idTarefa) {
		this.validador.validacaoId(idTarefa);

		String idAtividade;
		String[] idSeparado = idTarefa.split("-");
		idAtividade = idSeparado[0] + "-" + idSeparado[1];

		Optional<Tarefa> optional = atividadeService.recuperaAtividadeOuFalhe(idAtividade).recuperarTarefa(idTarefa);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("A tarefa com id " + idTarefa + " não existe");
		}
		return optional.get();
	}

	/**
	 * Metodo que resgata uma tarefa já cadastrada do sistema e altera o seu
	 * atributo "nome".
	 * 
	 * @param id   Id de identificação da tarefa, que será usado para localiza-la no
	 *             mapa de tarefas que a atividade possui.
	 * @param nome Novo nome que a tarefa vai assumir.
	 */
	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefa);
		tarefa.setNome(novoNome);
	}

	/**
	 * Metodo que resgata uma tarefa ja cadastrada no sistema e altera o seu array
	 * de habilidades necessárias.
	 * 
	 * @param id          Id de identificação da tarefa, que será usado para
	 *                    localiza-la no mapa de tarefas que a atividade possui.
	 * @param habilidades Novo array contendo as novas habilidades necessarias para
	 *                    realizar a tarefa.
	 */
	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefa);
		tarefa.setHabilidades(habilidades);
	}

	/**
	 * Metodo que acessa uma tarefa cadastrada no sistema e adiciona uma quatidade
	 * de horas que foi dedicada à realização da tarefa.
	 * 
	 * @param id    Id de identificação da tarefa, que será usado para localiza-la
	 *              no mapa de tarefas que a atividade possui.
	 * @param horas Quantidade de horas em que parte da tarefa foi realizada que
	 *              deve ser adicionada.
	 */
	public void adicionarHorasTarefa(String idTarefa, int horas) {
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefa);
		tarefa.acrescentarHoras(horas);
	}

	/**
	 * Metodo que acessa uma tarefa cadastrada no sistema e remove uma quatidade de
	 * horas que foi dedicada à realização da tarefa.
	 * 
	 * @param id    Id de identificação da tarefa, que será usado para localiza-la
	 *              no mapa de tarefas que a atividade possui.
	 * @param horas Quantidade de horas em que parte da tarefa foi realizada que
	 *              deve ser removida.
	 */
	public void removeHorasTarefa(String idTarefa, int horas) {
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefa);
		tarefa.removerHoras(horas);
	}

	/**
	 * Metodo que altera o atributo "concluida" de uma tarefa para TRUE,
	 * simbolizando que a tarefa ja foi realizada.
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 */
	public void concluirTarefa(String idTarefa) {
		this.recuperaTarefaOuFalhe(idTarefa).concluirTarefa();
	}

	public void removerTarefa(String idTarefa) {
		String idAtividade;
		String[] idSeparado = idTarefa.split("-");
		idAtividade = idSeparado[0] + "-" + idSeparado[1];
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(idAtividade);
		atividade.removeTarefa(idTarefa);
	}

	/**
	 * Metodo que acessa uma atividade, pega uma tarefa armazenada na tarefa e pega
	 * a representação textual dessa tarefa(toString).
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 * @return Retorna a representação na forma de String daquela tarefa.
	 */
	public String exibirTarefa(String idTarefa) {
		return this.recuperaTarefaOuFalhe(idTarefa).toString();
	}
	
	/**
	 * Método que associa uma pessoa (responsável) à uma tarefa cadastrada na
	 * atividade.
	 * 
	 * @param id     Id de identificação da tarefa, que será usado para localiza-la
	 *               no mapa de tarefas que a atividade possui.
	 * @param pessoa O objeto pessoa que será atribuido como responsavel pela
	 *               realização daquela tarefa.
	 */
	public void associarPessoaTarefa(String cpf, String idTarefa) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		this.recuperaTarefaOuFalhe(idTarefa).associarPessoa(pessoa);
	}
	
	/**
	 * Método que remove uma das pessoas que estão associadas a uma tarefa.
	 * 
	 * @param id     Id de identificação da tarefa, que será usado para localiza-la
	 *               no mapa de tarefas que a atividade possui.
	 * @param pessoa A pessoa cujo cpf sera removido do mapa de responsáveis da
	 *               tarefa.
	 */
	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.recuperaTarefaOuFalhe(idTarefa).removerPessoa(cpf);
	}
}
