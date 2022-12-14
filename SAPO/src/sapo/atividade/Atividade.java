package sapo.atividade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import sapo.pessoa.Pessoa;
import sapo.tarefa.Tarefa;
import sapo.tarefa.TarefaGerencial;

/**
 * Representação de uma atividade. Uma atividade representa um conjunto de ações
 * (tarefas) que devem ser tomadas para atingir um determinado objetivo.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa dos Santos - 121110281
 */
public class Atividade {

	/**
	 * Identificador da atividade. String composta pelas 3 primeiras consoantes do
	 * nome da atividade e um número sequencial que identifica unicamente atividade.
	 */
	private String id;
	/**
	 * String que representa o nome da atividade.
	 */
	private String nome;
	/**
	 * String que representa a descrição da atividade.
	 */
	private String descricao;
	/**
	 * Pessoa responsável pela atividade. Pode assumir valor null.
	 */
	private Pessoa responsavel;
	/**
	 * Inteiro que representa o estado de uma atividade. 0 caso a atividade esteja
	 * aberta, 1 caso a atividade esteja encerrada (concluída), ou 2 caso a
	 * atividade esteja desativada (abandonada ou invalidada).
	 */
	private int estado;
	/**
	 * Contador que contabiliza quantas tarefas foram armazenadas para poder gerar o
	 * id de tarefas.
	 */
	private int contadorTarefas;
	/**
	 * Mapa que armazena as tarefas da atividade.
	 */
	private Map<String, Tarefa> tarefas;
	/**
	 * Validador utilizado para validar os parâmetros.
	 */
	private ValidadorAtividade validador;

	/**
	 * Construtor de Atividade. Constrói uma atividade a partir de um id, nome,
	 * descrição e pessoa responsável.
	 * 
	 * @param id          String com o identificador único da atividade.
	 * @param nome        String com o nome da atividade.
	 * @param descricao   String com a descrição da atividade.
	 * @param responsavel Pessoa responsável pela atividade.
	 */
	public Atividade(String id, String nome, String descricao, Pessoa responsavel) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.estado = 0;
		this.contadorTarefas = 0;
		this.tarefas = new TreeMap<>();
		this.validador = new ValidadorAtividade();
	}

	/**
	 * Retorna o valor do atributo de id único da atividade.
	 * 
	 * @return String com o id da atividade.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Retorna o valor do atributo de nome da atividade.
	 * 
	 * @return String com o nome da atividade.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o valor do atributo de estado da atividade. 0 caso a atividade esteja
	 * aberta, 1 caso a atividade esteja encerrada (concluída), ou 2 caso a
	 * atividade esteja desativada (abandonada ou invalidada).
	 * 
	 * @return int que representa o estado da atividade.
	 */
	public int getEstado() {
		return this.estado;
	}

	/**
	 * Retorna o valor do atributo de estado da atividade.
	 * 
	 * @return String que representa a descrição.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Altera a descrição com uma nova descrição.
	 * 
	 * @param novaDescricao Uma string com uma nova descrição.
	 */
	public void setDescricao(String novaDescricao) {
		this.descricao = novaDescricao;
	}

	/**
	 * Altera o responsável com um novo responsável.
	 * 
	 * @param novoResponsavel Uma nova pessoa responsável
	 */
	public void setResponsavel(Pessoa novoResponsavel) {
		this.responsavel = novoResponsavel;
	}

	/**
	 * Altera o estado com um novo estado.
	 * 
	 * @param novoEstado O novo estado da atividade
	 */
	public void setEstado(int novoEstado) {
		this.estado = novoEstado;
	}

	/**
	 * Altera o nome da atividade mediante o id, com o novo nome.
	 * 
	 * @param id   Id da atividade para o nome ser alterado.
	 * @param nome O novo nome da atividade.
	 */
	public void alteraNome(String id, String nome) {
		tarefas.get(id).setNome(nome);
	}

	/**
	 * Altera as habilidades mediante o id, com o novo nome.
	 * 
	 * @param id          Id da atividade para as habilidades serem alteradas.
	 * @param habilidades Novas habilidades.
	 */
	public void alteraHabilidades(String id, String[] habilidades) {
		tarefas.get(id).setHabilidades(habilidades);
	}

	/**
	 * Verifica as tarefas que não estão concluídas e retorna um conjunto dessas
	 * tarefas pendentes.
	 * 
	 * @return um conjunto de tarefas pendentes.
	 */
	public Set<Tarefa> tarefasPendentes() {
		Set<Tarefa> tarefasPendentes = new HashSet<>();
		for (String key : this.tarefas.keySet()) {
			if (this.tarefas.get(key).getEstado() == false) {
				tarefasPendentes.add(this.tarefas.get(key));
			}
		}
		return tarefasPendentes;
	}

	/**
	 * Verifica as 3 tarefas que não estão concluídas e retorna uma listagem em
	 * formato de texto dessas tarefas pendentes.
	 * 
	 * @return uma listagem de 3 tarefas pendentes.
	 */
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

	/**
	 * Retorna a quantidade de tarefas de atividade.
	 * 
	 * @return a quantidade de tarefas de atividade.
	 */
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
		this.tarefas.put(idPronto, new Tarefa(nome, idPronto, habilidades, this.getNome()));

		return idPronto;
	}

	/**
	 * Método que cadastra uma tarefa gerencial no mapa de tarefas que essa
	 * atividade possui. Associando essa tarefa cadastrada à um código que
	 * representa a tarefa no sistema.
	 * 
	 * @param nome        O nome que será atribuido à tarefa na hora de seu
	 *                    cadastro.
	 * @param habilidades As habilidades necessárias para a realização da tarefa.
	 * @param idTarefas   Um array de id de tarefas que a tarefa gerencial irá
	 *                    gerenciar.
	 * @return Retorna o id em que a tarefa foi cadastrada no sistema.
	 */
	public String cadastrarTarefaGerencial(String nome, String[] habilidades, String[] idTarefas) {
		String idPronto = this.id + "-" + this.contadorTarefas;
		this.contadorTarefas++;
		ArrayList<Tarefa> tarefasDeTarefaGerencial = new ArrayList<Tarefa>();
		for (String idTarefa : idTarefas) {
			if (this.recuperarTarefa(idTarefa).isEmpty()) {
				throw new NoSuchElementException("A tarefa com id " + idTarefa + " não existe");
			} else {
				tarefasDeTarefaGerencial.add(this.recuperarTarefa(idTarefa).get());
			}
		}
		this.tarefas.put(idPronto,
				new TarefaGerencial(nome, idPronto, habilidades, this.getNome(), tarefasDeTarefaGerencial));

		return idPronto;
	}

	/**
	 * Metodo que acessa o mapa de tarefas da atividade e remove dele a tarefa que
	 * corresponde ao ID passado como parametro.
	 * 
	 * @param id Id de identificação da tarefa, que será usado para localiza-la no
	 *           mapa de tarefas que a atividade possui.
	 */
	public void removeTarefa(String id) {
		this.tarefas.remove(id);
	}

	/**
	 * Recupera tarefa a partir do id passado pelo parâmetro caso ela exista no
	 * sistema.
	 * 
	 * @param tarefaId Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @return Retorna um Optional, que caso possua uma tarefa cadastrada com o id
	 *         passado irá conter uma tarefa, caso contrario, o optional retornara
	 *         sem atividade e uma excessão será lançada.
	 */
	public Optional<Tarefa> recuperarTarefa(String tarefaId) {
		Tarefa tarefa = null;
		this.validador.validacaoId(tarefaId);
		tarefa = this.tarefas.get(tarefaId);
		return Optional.ofNullable(tarefa);
	}

	/**
	 * Recupera tarefa a partir do id passado pelo parâmetro caso ela exista no
	 * sistema.
	 * 
	 * @param tarefaId Id de identificação da tarefa, que será usado para
	 *                 localiza-la no mapa de tarefas que a atividade possui.
	 * @return Retorna um Optional, que caso possua uma tarefa cadastrada com o id
	 *         passado irá conter uma tarefa, caso contrario, o optional retornara
	 *         sem atividade e uma excessão será lançada.
	 */
	public Set<Tarefa> tarefasAssociadasPessoa(String cpf) {
		Set<Tarefa> tarefasAssociadas = new HashSet<>();
		for (Map.Entry<String, Tarefa> tarefa : this.tarefas.entrySet()) {
			if (tarefa.getValue().pessoaAssociada(cpf)) {
				tarefasAssociadas.add(tarefa.getValue());
			}
		}
		return tarefasAssociadas;
	}

	/**
	 * HashCode de um objeto Atividade. O hashCode é gerado a partir do id da
	 * atividade.
	 * 
	 * @return int com o hashCode de um objeto Atividade.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se o objeto Atividade é igual a um outro objeto passado como
	 * parâmetro. Uma atividade é identificada unicamente pelo sei id.
	 * 
	 * @return Retorna true caso o object seja igual, e false caso seja diferente.
	 */
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

	/**
	 * Representa textualmente a atividade.
	 * 
	 * @return Representação textual da atividade.
	 */
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

	/**
	 * Busca uma tarefa a partir dos critérios de busca passado no parâmetro e
	 * retrona um conjunto da representação textual de tarefas que atendem a essa
	 * busca.
	 * 
	 * @param criterioBusca Um array contendo os critérios de busca da tarefa.
	 * @return Um conjunto das representações textuais das tarefas que atendem a
	 *         busca.
	 */
	public Set<String> buscaTarefa(String[] criterioBusca) {
		Set<String> resultadosBusca = new HashSet<>();
		for (String criterio : criterioBusca) {
			for (String chave : this.tarefas.keySet()) {
				if (this.tarefas.get(chave).getNome().toUpperCase().contains(criterio.toUpperCase())) {
					resultadosBusca.add(this.tarefas.get(chave).toString());
					continue;
				}
			}
		}
		return resultadosBusca;
	}

	/**
	 * Conta as habilidades em comum entre as listas de habilidades passada no
	 * parâmetro e as habilidades do id da tarefa passada pelo parâmetro.
	 * 
	 * @param habilidades Um array contendo as habilidades a serem comparadas em
	 *                    comum.
	 * @param id          Id da tarefa para comparação das suas habilidades
	 * @return Um número inteiro que representa a quantidade de habilidades em
	 *         comum.
	 */
	private int habilidadesEmComum(String[] habilidades, String id) {
		int contador = 0;
		for (String habilidadePessoa : habilidades) {
			for (String habilidadeTarefa : this.tarefas.get(id).getHabilidades()) {
				if (habilidadePessoa.equals(habilidadeTarefa)) {
					contador += 1;
					break;
				}
			}
		}
		return contador;
	}

	/**
	 * Sugere tarefas a partir de uma lista de habilidades.
	 * 
	 * @param habilidades Um array contendo as habilidades para requisição de
	 *                    tarefas a serem sugeridas.
	 * @return Um conjunto de tarefas a serem sugeridas.
	 */
	public Set<String> sugere(String[] habilidades) {
		Set<String> resultadosBusca = new HashSet<>();
		int contador = habilidades.length;
		boolean temPessoa = false;
		while (true) {
			if (contador <= 0) {
				break;
			}
			for (String tarefa : this.tarefas.keySet()) {
				if (habilidadesEmComum(habilidades, tarefa) == contador
						&& this.tarefas.get(tarefa).temPessoa() == temPessoa) {
					resultadosBusca.add(this.tarefas.get(tarefa).toString());
				}
			}

			if (!temPessoa) {
				temPessoa = true;
				continue;
			}

			if (temPessoa) {
				contador--;
				temPessoa = false;
			}

		}
		return resultadosBusca;
	}
}
