package sapo.atividade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import sapo.pessoa.Pessoa;
import sapo.tarefa.Tarefa;

/**
 * Repositório de Atividades. Com operações simples de cadastrar e recuperar
 * atividade.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
class AtividadeRepository {

	/**
	 * Mapa de Atividades. Armazena as atividades em um mapa de atividades que tem a
	 * String id da atividade coo chave.
	 */
	private Map<String, Atividade> atividades;
	/**
	 * Validador utilizado para validar os parâmetros.
	 */
	private ValidadorAtividade validador;

	/**
	 * Construtor padrão do repositório, incializando o mapa de atividades e o
	 * validador.
	 */
	AtividadeRepository() {
		this.atividades = new TreeMap<>();
		this.validador = new ValidadorAtividade();
	}

	/**
	 * Cadastra uma atividade no mapa de atividades. Cadastra a partir do nome e
	 * desrição da atividade e, também, pela Pessoa a ser responsável pela
	 * atividade. Retorna uma String que representa o id da atividade.
	 * 
	 * @param nome      String que representa o nome da atividade.
	 * @param descricao String que representa a descrição da atividade.
	 * @param Pessoa    Pessoa que representa a responsável pela atividade.
	 * @return String com o id dessa atividade no sistema.
	 */
	String cadastrarAtividade(String nome, String descricao, Pessoa responsavel) {
		String id = gerarId(nome);
		this.atividades.put(id, new Atividade(id, nome, descricao, responsavel));
		return id;
	}

	/**
	 * Gera um id para uma atividade a partir do nome da atividade. O id é formado
	 * pelas 3 primeiras consoantes do nome da atividade seguido por um hífen e um
	 * número sequencial iniciado em 0. Primeiramente, transforma-se todos os
	 * caracteres de nome em caracteres maiúsculos. Segundamente, substitui-se todos
	 * os caracteres que não estão no intervalo de A à Z e as vogais por vazio,
	 * restando apenas as consoantes. Terceiramente, caso a palavra tenha menos de 3
	 * consoantes, acrescenta-se o caractere "X" para cada consoante a menos que 3.
	 * 
	 * @param nome Nome da atividade a ter o id gerado
	 * @return As 3 primeiras consoantes do id seguidas por um hífen e um número
	 *         sequencial.
	 */
	private String gerarId(String nome) {
		String id = nome.toUpperCase().replaceAll("[^A-Z]|[AEIOU]", "");
		while (id.length() < 3) {
			id += "X";
		}
		return id.substring(0, 3) + "-" + (this.atividades.size());
	}

	/**
	 * Recupera atividade a partir do id, validando o parâmetro recebido.
	 * 
	 * @param atividadeId String que representa o id da atividade.
	 * @return Um optional de Atividade ou optional vazio, caso a atividade não
	 *         exista no repositório.
	 */
	Optional<Atividade> recuperarAtividade(String atividadeId) {
		Atividade atividade = null;
		this.validador.validacaoId(atividadeId);
		atividade = this.atividades.get(atividadeId);
		return Optional.ofNullable(atividade);
	}

	/**
	 * Busca um conjunto de atividades a partir dos critérios de busca.
	 * 
	 * @param criterioBusca Um array contendo os critérios de busca.
	 * @return Um conjunto das representações textuais das atividades que atendem a
	 *         busca.
	 */
	public Set<String> busca(String[] criterioBusca) {
		Set<String> resultadosBusca = new HashSet<>();

		for (String criterio : criterioBusca) {
			for (String chave : this.atividades.keySet()) {
				if (this.atividades.get(chave).getNome().toUpperCase().contains(criterio.toUpperCase())) {
					resultadosBusca.add(this.atividades.get(chave).toString());
					continue;
				}

				if (this.atividades.get(chave).getId().toUpperCase().equals(criterio.toUpperCase())) {
					resultadosBusca.add(this.atividades.get(chave).toString());
					continue;
				}

				if (this.atividades.get(chave).getId().toUpperCase().contains(criterio.toUpperCase())) {
					resultadosBusca.add(this.atividades.get(chave).toString());
					continue;
				}

				if (this.atividades.get(chave).getDescricao().toUpperCase().contains(criterio.toUpperCase())) {
					resultadosBusca.add(this.atividades.get(chave).toString());
					continue;
				}
			}
		}

		return resultadosBusca;
	}

	/**
	 * Recupera uma lista de Atividades.
	 * 
	 * @return Lista de Atividades.
	 */
	public List<Atividade> recuperaAtividades() {
		Set<Atividade> atividades = new HashSet<>();
		for (String id : this.atividades.keySet()) {
			atividades.add(this.atividades.get(id));
		}

		List<Atividade> listaAtividades = new ArrayList<>();
		for (Atividade atividade : atividades) {
			listaAtividades.add(atividade);
		}

		return listaAtividades;
	}

	/**
	 * Recupera uma um conjunto de tarefas associadas a uma pessoa, mediante um cpf.
	 * 
	 * @param cpf String que contém o cpf da pessoa associada as tarefas.
	 * @return Conjunto de tarefas associadas a pessoa.
	 */
	public Set<Tarefa> tarefasAssociadasPessoa(String cpf) {
		Set<Tarefa> tarefasAssociadas = new HashSet<>();
		for (Map.Entry<String, Atividade> atividade : this.atividades.entrySet()) {
			tarefasAssociadas.addAll(atividade.getValue().tarefasAssociadasPessoa(cpf));
		}
		return tarefasAssociadas;
	}
}
