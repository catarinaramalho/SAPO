package sapo.tarefa;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import sapo.atividade.Atividade;
import sapo.atividade.AtividadeService;
import sapo.pessoa.PessoaService;

/**
 * Classe responsável pela logica que envolve as operações em cada objeto
 * Tarefa, resgatando uma tarefa de uma atividade para usar seus metodos
 * internos ou chamando algum metodo da Atividade que vai operar emc ima da
 * tarefa.
 * 
 * @author Lucas Leones Costa dos Santos
 *
 */
public class TarefaService {
	private AtividadeService atividadeService;
	private ValidadorTarefa validadorTarefa;
	private PessoaService pessoaService;

	public TarefaService(AtividadeService atividadeService, PessoaService pessoaService) {
		this.atividadeService = atividadeService;
		this.pessoaService = pessoaService;
		this.validadorTarefa = new ValidadorTarefa();
	}

	/**
	 * Metodo que realiza a validação para ter certeza que o nome e o conjunto de
	 * habilidades é valido. Em seguida, uma atividade com o id passado é recuperada
	 * e uma tarefa é cadastrada no seu mapa de tarefas.
	 * 
	 * @param id          Id de identificação da Atividade, atividade essa que será
	 *                    o local que armazenará a tarefa.
	 * @param nome        O nome que a tarefa vai assumir.
	 * @param habilidades O conjunto de habilidades necessárias para realizar essa
	 *                    tarefa.
	 * @return Retorna o id em que a tarefa foi cadastrada no mapa de tarefas (sua
	 *         chave). O id é gerado pela atividade de acordo com o id da propria
	 *         tarefa e a ordem em que essa tarefa esta sendo cadastrada no mapa.
	 */
	public String cadastraTarefa(String atividadeId, String nome, String[] habilidades) {
		validadorTarefa.validacao(nome, habilidades);
		return atividadeService.recuperaAtividadeOuFalhe(atividadeId).cadastrarTarefa(nome, habilidades);
	}

	private Tarefa recuperaTarefaOuFalhe(String idTarefa) {
		this.validadorTarefa.validacaoId(idTarefa);

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
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @param nome     Novo nome que a tarefa vai assumir.
	 */
	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		this.recuperaTarefaOuFalhe(idTarefa).setNome(novoNome);
	}

	/**
	 * Metodo que resgata uma tarefa ja cadastrada no sistema e altera o seu array
	 * de habilidades necessárias.
	 * 
	 * @param idTarefa    Id de identificação da tarefa, que será usado para
	 *                    localiza-la no mapa de tarefas que a atividade possui.
	 * @param habilidades Novo array contendo as novas habilidades necessarias para
	 *                    realizar a tarefa.
	 */
	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.recuperaTarefaOuFalhe(idTarefa).setHabilidades(habilidades);
	}

	/**
	 * Metodo que acessa uma tarefa cadastrada no sistema e adiciona uma quatidade
	 * de horas que foi dedicada à realização da tarefa.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @param horas    Quantidade de horas em que parte da tarefa foi realizada que
	 *                 deve ser adicionada.
	 */
	public void adicionarHorasTarefa(String idTarefa, int horas) {
		this.recuperaTarefaOuFalhe(idTarefa).acrescentarHoras(horas);
	}

	/**
	 * Metodo que acessa uma tarefa cadastrada no sistema e remove uma quatidade de
	 * horas que foi dedicada à realização da tarefa.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @param horas    Quantidade de horas em que parte da tarefa foi realizada que
	 *                 deve ser removida.
	 */
	public void removeHorasTarefa(String idTarefa, int horas) {
		this.recuperaTarefaOuFalhe(idTarefa).removerHoras(horas);
	}

	/**
	 * Metodo que altera o atributo "concluida" de uma tarefa para TRUE,
	 * simbolizando que a tarefa ja foi realizada.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 */
	public void concluirTarefa(String idTarefa) {
		this.recuperaTarefaOuFalhe(idTarefa).concluirTarefa();
	}

	/**
	 * Metodo que acessa uma atividade através do Id da tarefa e remove dela a
	 * tarefa correspondente ao id passado como parametro.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 */
	public void removerTarefa(String idTarefa) {
		String idAtividade;
		String[] idSeparado = idTarefa.split("-");
		idAtividade = idSeparado[0] + "-" + idSeparado[1];
		atividadeService.recuperaAtividadeOuFalhe(idAtividade).removeTarefa(idTarefa);

	}

	/**
	 * Metodo que acessa uma atividade, pega uma tarefa armazenada na tarefa e pega
	 * a representação textual dessa tarefa(toString).
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @return Retorna a representação na forma de String daquela tarefa.
	 */
	public String exibirTarefa(String idTarefa) {
		return this.recuperaTarefaOuFalhe(idTarefa).toString();
	}

	/**
	 * Método que associa uma pessoa (responsável) à uma tarefa cadastrada na
	 * atividade.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @param cpf      O cpf da pessoa que sera removida do mapa de responsáveis da
	 *                 tarefa.
	 */
	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.recuperaTarefaOuFalhe(idTarefa).associarPessoa(this.pessoaService.recuperarPessoaOuFalhe(cpf));
	}

	/**
	 * Método que remove uma das pessoas que estão associadas a uma tarefa.
	 * 
	 * @param idTarefa Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @param cpf      O cpf da pessoa que sera removida do mapa de responsáveis da
	 *                 tarefa.
	 */
	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.recuperaTarefaOuFalhe(idTarefa).removerPessoa(cpf);
	}

	public Set<String> busca(String[] criterio) {
		Set<String> retorno = new HashSet<>();
		List<Atividade> atividades = atividadeService.recuperaAtividades();
		for (Atividade atividade : atividades) {
			for (String representacao : atividade.buscaTarefa(criterio))
				retorno.add(representacao);
		}
		return retorno;
	}

	public Set<String> busca(String[] criterio, String idAtividade) {
		return this.atividadeService.recuperaAtividadeOuFalhe(idAtividade).buscaTarefa(criterio);
	}

	public String[] sugereTarefa(String[] habilidades) {
		Set<String> retorno = new HashSet<>();
		List<Atividade> atividades = atividadeService.recuperaAtividades();
		for (Atividade atividade : atividades) {
			for (String representacao : atividade.sugere(habilidades))
				retorno.add(representacao);
		}

		String[] arrayResultados = new String[retorno.size()];

		int i = 0;
		for (String resultado : retorno)
			arrayResultados[i++] = resultado;

		return arrayResultados;
	}

	public Set<Tarefa> tarefasAssociadasPessoa(String cpf) {
		return this.atividadeService.tarefasAssociadasPessoa(cpf);
	}
	
	private boolean isTarefaGerencial(Tarefa tarefa) {
		if (tarefa instanceof TarefaGerencial) {
			return true;
		} return false;
	}
	
	public String cadastraTarefaGerencial(String atividadeId, String nome, String[] habilidades, String[] idTarefas) {
		this.validadorTarefa.validacao(atividadeId, nome, habilidades, idTarefas);
		return atividadeService.recuperaAtividadeOuFalhe(atividadeId).cadastrarTarefaGerencial(nome, habilidades, idTarefas);
	}
	
	public void adicionarNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
		this.validadorTarefa.validacao(idTarefaGerencial, idTarefa);
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefaGerencial);
		if (this.isTarefaGerencial(tarefa)) {
			TarefaGerencial tarefaGerencial = (TarefaGerencial)(tarefa);
			Tarefa tarefaArmazenada = this.recuperaTarefaOuFalhe(idTarefa);
			if (this.isTarefaGerencial(tarefaArmazenada)) {
				TarefaGerencial tarefaGerencialArmazenada = (TarefaGerencial)(tarefaArmazenada);
				tarefaGerencial.adicionarNaTarefaGerencial(tarefaGerencialArmazenada);
			} else {
				tarefaGerencial.adicionarNaTarefaGerencial(tarefaArmazenada);
			}
			
		} else {
			throw new IllegalArgumentException("Não existe Tarefa Gerencial para esse id! Portanto, não pode adicionar uma tarefa dela!");
		}
		
	}
	
	public void removerDaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
		this.validadorTarefa.validacao(idTarefaGerencial, idTarefa);
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefaGerencial);
		if (this.isTarefaGerencial(tarefa)) {
			TarefaGerencial tarefaGerencial = (TarefaGerencial)(tarefa);
			tarefaGerencial.removerDaTarefaGerencial(this.recuperaTarefaOuFalhe(idTarefa));
			
		} else {
			throw new IllegalArgumentException("Não existe Tarefa Gerencial para esse id! Portanto, não pode remover uma tarefa dela!");
		}
		
	}
	
	public int contarTodasTarefasNaTarefaGerencial(String idTarefaGerencial) {
		this.validadorTarefa.validacaoIdTarefaGerencial(idTarefaGerencial);
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefaGerencial);
		if (this.isTarefaGerencial(tarefa)) {
			TarefaGerencial tarefaGerencial = (TarefaGerencial)(tarefa);
			return tarefaGerencial.contarTodasTarefasNaTarefaGerencial();
			
		} else {
			throw new IllegalArgumentException("Não existe Tarefa Gerencial para esse id! Portanto, não pode contar tarefas dela!");
		}
	}

}
