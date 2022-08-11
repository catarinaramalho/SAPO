package sapo.atividade;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import sapo.pessoa.Pessoa;

/**
 * Representação de uma atividade. Uma atividade representa um conjunto de ações
 * (tarefas) que devem ser tomadas para atingir um determinado objetivo.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa Dos Santos - 121110281
 */
class Atividade {

	private String id;
	private String nome;
	private String descricao;
	private Pessoa responsavel;
	/**
	 * Inteiro que representa o estado de uma atividade. 0 caso a atividade esteja
	 * aberta, 1 caso a atividade esteja encerrada (concluída), ou 2 caso a
	 * atividade esteja desativada (abandonada ou invalidada).
	 */
	private int estado;
	private int contadorTarefas;
	private Map<String, Tarefa> tarefas;

	public Atividade(String id, String nome, String descricao, Pessoa responsavel) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.estado = 0;
		this.contadorTarefas = 0;
		this.tarefas = new HashMap<>();
	}

	public String getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int novoEstado) {
		this.estado = novoEstado;
	}<<<<<<<HEAD

	private Set<Tarefa> tarefasPendentes() {
=======

	public Set<Tarefa> tarefasPendentes() {
>>>>>>> c0cb130daa806edd2a5c489b50e8a93c102dadc3
		Set<Tarefa> tarefasPendentes = new HashSet<>();
		for (String key : this.tarefas.keySet()) {
			if (this.tarefas.get(key).getEstado() == false) {
				tarefasPendentes.add(this.tarefas.get(key));
			}
		}
		return tarefasPendentes;
	}

	private String listar3TarefasPendentes() {
		int contador = 0;
		String tarefasPendentes = "";
		for (Tarefa tarefa : this.tarefasPendentes()) {
			if (contador == 3) {
				return tarefasPendentes;
			}
			tarefasPendentes += "\n- " + tarefa.getNome() + " - " + tarefa.getId();
			contador++;
		}
		return tarefasPendentes;
	}

	private int totalTarefas() {
		return this.tarefas.size();
	}

	/**
	 * Método que cadastra uma tarefa no mapa de tarefas que essa atividade possui.
	 * Associando essa tarefa cadastrada à um código que representa a tarefa no
	 * sistema.
	 * 
	 * @param nome        O nome que será atribuido à tarefa na hora de seu
	 *                    cadastro.
	 * @param habilidades As habilidades necessárias para a realização da tarefa.
	 * @return Retorna o id em que a tarefa foi cadastrada no sistema.
	 */
	public String cadastrarTarefa(String nome, String[] habilidades) {
		String idPronto = this.id + "-" + this.contadorTarefas;
		this.contadorTarefas++;
		tarefas.put(idPronto, new Tarefa(nome, idPronto, habilidades, this.getNome()));

		return idPronto;
	}

	/**
	 * Metodo que resgata uma tarefa já cadastrada do sistema e altera o seu
	 * atributo "nome".
	 * 
	 * @param id   Id de identificação da tarefa, que será usado para localiza-la no
	 *             mapa de tarefas que a atividade possui.
	 * @param nome Novo nome que a tarefa vai assumir.
	 */
	public void alteraNomeTarefa(String id, String nome) {
		tarefas.get(id).setNome(nome);
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
	public void alteraHabilidadesTarefa(String id, String[] habilidades) {
		tarefas.get(id).setHabilidades(habilidades);
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
	public void adicionaHorasTarefa(String id, int horas) {
		tarefas.get(id).acrescentarHoras(horas);
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
	public void removeHorasTarefa(String id, int horas) {
		tarefas.get(id).removeHoras(horas);
		;
	}

	/**
	 * Metodo que altera o atributo "concluida" de uma tarefa para TRUE,
	 * simbolizando que a tarefa ja foi realizada.
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 */
	public void concluirTarefa(String id) {
		tarefas.get(id).concluiTarefa();
	}

	/**
	 * Metodo que acessa o mapa de tarefas da atividade e remove dele a tarefa que
	 * corresponde ao ID passado como parametro.
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 */
	public void removeTarefa(String id) {
		tarefas.remove(id);
	}

	/**
	 * Metodo que acessa o mapa de tarefas que a atividade possui e devolve a
	 * representação textual daquela tarefa.
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 * @return Retorna a representação na forma de String daquela tarefa.
	 */
	public String exibeTarefa(String id) {
		return tarefas.get(id).toString();
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
	public void associarPessoaTarefa(String id, Pessoa pessoa) {
		tarefas.get(id).associaPessoa(pessoa);
	}

	/**
	 * Método que remove uma das pessoas que estão associadas a uma tarefa.
	 * 
	 * @param id     Id de identificação da tarefa, que será usado para localiza-la
	 *               no mapa de tarefas que a atividade possui.
	 * @param pessoa A pessoa cujo cpf sera removido do mapa de responsáveis da
	 *               tarefa.
	 */
	public void removerPessoaTarefa(String id, Pessoa pessoa) {
		tarefas.get(id).removePessoa(pessoa.getCpf());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		String representacaoTextual = this.id + ": " + this.nome;
		if (this.responsavel != null) {
			representacaoTextual += "\nResponsável: " + this.responsavel.getNome() + " – " + this.responsavel.getCpf();
		}
		representacaoTextual += "\n===\n" + this.descricao + "\n===\nTarefas: "
				+ (this.totalTarefas() - this.tarefasPendentes().size()) + "/" + this.totalTarefas()
				+ this.listar3TarefasPendentes();
		return representacaoTextual;
	}
}
