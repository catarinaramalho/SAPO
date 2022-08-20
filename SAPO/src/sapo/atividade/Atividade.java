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
 * (tarefas) que devem ser tomadas para atingir um determinado objetivo. F
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 * @author Lucas Leones Costa dos Santos - 121110281
 */
public class Atividade {

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
	private ValidadorAtividade validador;

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

	public String getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getEstado() {
		return this.estado;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String novaDescricao) {
		this.descricao = novaDescricao;
	}

	public void setResponsavel(Pessoa novoResponsavel) {
		this.responsavel = novoResponsavel;
	}

	public void setEstado(int novoEstado) {
		this.estado = novoEstado;
	}

	public void alteraNome(String id, String nome) {
		tarefas.get(id).setNome(nome);
	}

	public void alteraHabilidades(String id, String[] habilidades) {
		tarefas.get(id).setHabilidades(habilidades);
	}

	public Set<Tarefa> tarefasPendentes() {
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
		this.tarefas.put(idPronto, new Tarefa(nome, idPronto, habilidades, this.getNome()));

		return idPronto;
	}

	public String cadastrarTarefaGerencial(String nome2, String[] habilidades, String[] idTarefas) {
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
	 * FALTA DOCUMENTAR MAS EU NAO SEI OQ DIZER
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

	public Set<Tarefa> tarefasAssociadasPessoa(String cpf) {
		Set<Tarefa> tarefasAssociadas = new HashSet<>();
		for (Map.Entry<String, Tarefa> tarefa : this.tarefas.entrySet()) {
			if (tarefa.getValue().pessoaAssociada(cpf)) {
				tarefasAssociadas.add(tarefa.getValue());
			}
		}
		return tarefasAssociadas;
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

	public Set<String> buscaTarefa(String[] criterioBusca) {
		Set<String> resultadosBusca = new HashSet<>();
		for (String criterio : criterioBusca) {
			for (String chave : this.tarefas.keySet()) {
				if (this.tarefas.get(chave).getNome().toUpperCase().equals(criterio.toUpperCase())) {
					resultadosBusca.add(this.tarefas.get(chave).toString());
					continue;
				}
			}
		}
		return resultadosBusca;
	}

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

	public Set<String> sugere(String[] habilidades) {
		Set<String> resultadosBusca = new HashSet<>();
		int contador = habilidades.length;
		while (true) {
			if (contador <= 0) {
				break;
			}
			for (String tarefa : this.tarefas.keySet()) {
				if (habilidadesEmComum(habilidades, tarefa) == contador) {
					resultadosBusca.add(this.tarefas.get(tarefa).toString());
				}
				contador--;
			}
		}
		return resultadosBusca;
	}
}
