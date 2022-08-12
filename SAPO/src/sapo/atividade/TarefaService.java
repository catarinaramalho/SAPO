package sapo.atividade;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TarefaService {
	private AtividadeService atividadeService;
	private Validador validador;
	
	public TarefaService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
		this.validador = new Validador(); 
	}
	
	public String cadastraTarefa(String id, String nome, String[] habilidades) {
		validador.validacao(nome, habilidades);
		Atividade atividade = atividadeService.recuperaAtividadeOuFalhe(id);
		return atividade.cadastrarTarefa(nome, habilidades);
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
	
	public void adicionarHorasTarefa(String idTarefa, int horas) {
		Tarefa tarefa = this.recuperaTarefaOuFalhe(idTarefa);
		tarefa.acrescentarHoras(horas);
	}

	private Tarefa recuperaTarefaOuFalhe(String idTarefa) {
		this.validador.validacaoId(idTarefa);
		
		String idAtividade;
		String [] idSeparado = idTarefa.split("-");
		idAtividade = idSeparado[0] + "-" + idSeparado[1];
		
		Optional<Tarefa> optional = atividadeService.recuperaAtividadeOuFalhe(idAtividade).recuperarTarefa(idTarefa);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("A tarefa com id " + idTarefa + " não existe");
		}
		return optional.get();
	}
}
