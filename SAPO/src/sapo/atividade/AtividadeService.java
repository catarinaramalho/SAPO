package sapo.atividade;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;
import sapo.tarefa.Tarefa;

/**
 * Service para a lógica de negócio de Atividade. Valida entradas e faz a
 * comunicação com o repositório de atividade.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class AtividadeService {
	/**
	 * Repository de Atividade para obtenção das operações e dos dados das
	 * atividades armazenadas no sistema.
	 */
	private AtividadeRepository atividadeRepository;
	/**
	 * Service de pessoa para obtenção das operações de lógica de negócio de pessoa.
	 */
	private PessoaService pessoaService;
	/**
	 * Validador a ser utilizado pelo service para validar os parâmetros recebidos.
	 */
	private ValidadorAtividade validador;

	/**
	 * Construtor padrão do service, que inicializa o repositório de atividades,
	 * recebe o service de pessoas e inicializa o validador.
	 * 
	 * @param pessoaService Service de pessoa a ser usado.
	 */
	public AtividadeService(PessoaService pessoaService) {
		this.atividadeRepository = new AtividadeRepository();
		this.pessoaService = pessoaService;
		this.validador = new ValidadorAtividade();
	}

	/**
	 * Cadastra uma atividade no sistema. Cadastra a partir do nome e desrição da
	 * atividade e, também, pelo CPF da pessoa a ser responsável pela atividade.
	 * Esse cpf é usado para recuperar a pessoa responsável. Retorna uma String que
	 * representa o id da atividade.
	 * 
	 * @param nome      String que representa o nome da atividade.
	 * @param descricao String que representa a descrição da atividade.
	 * @param cpf       String que representa o CPF da pessoa a ser responsável pela
	 *                  atividade.
	 * @return String com o id dessa atividade no sistema.
	 */
	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		Pessoa responsavel = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		return this.atividadeRepository.cadastrarAtividade(nome, descricao, responsavel);
	}

	/**
	 * Encerra uma atividade no sistema, a partir do id dessa atividade, validando
	 * esse id e recuperando a atividade cadastrada a partir dele.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 */
	public void encerrarAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.getEstado() == 1) {
			throw new IllegalStateException("Não é possível encerrar uma atividade já encerrada");
		}
		if (atividade.tarefasPendentes().size() != 0) {
			throw new IllegalStateException("Não é possível encerrar atividades com tarefas pendentes");
		}
		atividade.setEstado(1);
	}

	/**
	 * Desativa uma atividade no sistema, a partir do id dessa atividade, validando
	 * esse id e recuperando a atividade cadastrada a partir dele.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 */
	public void desativarAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.getEstado() == 2) {
			throw new IllegalStateException("Não é possível desativar uma atividade já desativada");
		}
		if (atividade.tarefasPendentes().size() != 0) {
			throw new IllegalStateException("Não é possível desativar atividades com tarefas pendentes");
		}
		atividade.setEstado(2);
	}

	/**
	 * Reabre uma atividade no sistema, a partir do id dessa atividade, validando
	 * esse id e recuperando a atividade cadastrada a partir dele.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 */
	public void reabrirAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.getEstado() == 0) {
			throw new IllegalStateException("Não é possível reabrir uma atividade já aberta");
		}
		atividade.setEstado(0);
	}

	/**
	 * Exibe uma atividade no sistema, a partir do id dessa atividade, validando
	 * esse id e recuperando a atividade cadastrada a partir dele.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 * @return Uma representação textual dessa atividade.
	 */
	public String exibirAtividade(String atividadeId) {
		return this.recuperaAtividadeOuFalhe(atividadeId).toString();
	}

	/**
	 * Altera a descrição de uma atividade no sistema, a partir do id dessa
	 * atividade, validando esse id, recuperando a atividade cadastrada a partir
	 * dele, passando a nova descrição.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 * @param descricao   String com a nova descricao.
	 */
	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.recuperaAtividadeOuFalhe(atividadeId).setDescricao(descricao);
	}

	/**
	 * Altera o responsável de uma atividade no sistema, a partir do id dessa
	 * atividade, validando esse id, recuperando a atividade cadastrada a partir
	 * dele, passando o novo responsável recuperado pelo service de pessoa, caso ele
	 * exista.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 * @param cpf         String com o cpf do novo responsável.
	 */
	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		Pessoa responsavel = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		this.recuperaAtividadeOuFalhe(atividadeId).setResponsavel(responsavel);
	}

	/**
	 * Recupera atividade a partir do id, pela operação do repository, validando o
	 * parâmetro recebido.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 * @return Uma Atividade, caso a atividade exista no repositório.
	 */
	public Atividade recuperaAtividadeOuFalhe(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		Optional<Atividade> optional = this.atividadeRepository.recuperarAtividade(atividadeId);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("A atividade com id " + atividadeId + " não existe");
		}
		return optional.get();
	}

	/**
	 * Busca um conjunto de atividades a partir dos critérios de busca.
	 * 
	 * @param criterioBusca Um array contendo os critérios de busca.
	 * @return Um conjunto das representações textuais das atividades que atendem a
	 *         busca.
	 */
	public Set<String> busca(String[] criterioBusca) {
		return this.atividadeRepository.busca(criterioBusca);
	}

	/**
	 * Recupera uma lista de Atividades.
	 * 
	 * @return Lista de Atividades.
	 */
	public List<Atividade> recuperaAtividades() {
		return this.atividadeRepository.recuperaAtividades();
	}

	/**
	 * Recupera uma um conjunto de tarefas associadas a uma pessoa, mediante um cpf.
	 * 
	 * @param cpf String que contém o cpf da pessoa associada as tarefas.
	 * @return Conjunto de tarefas associadas a pessoa.
	 */
	public Set<Tarefa> tarefasAssociadasPessoa(String cpf) {
		return this.atividadeRepository.tarefasAssociadasPessoa(cpf);
	}
}
