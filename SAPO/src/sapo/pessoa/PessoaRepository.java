package sapo.pessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import sapo.funcao.Funcao;

/**
 * Repositório de Pessoas. Com operações simples de cadastrar, remover,
 * recuperar, listar e buscar pessoas.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
class PessoaRepository {
	/**
	 * Mapa de pessoas, com cpf como chave de cada pessoa.
	 */
	private Map<String, Pessoa> pessoas;
	/**
	 * Validador a ser utilizado pelo repositório para validar os parâmetros
	 * recebidos.
	 */
	private ValidadorPessoa validador;

	/**
	 * Construtor padrão do repositório, que inicializa o mapa de pessoas e o
	 * validador.
	 */
	PessoaRepository() {
		this.pessoas = new HashMap<String, Pessoa>();
		this.validador = new ValidadorPessoa();
	}

	/**
	 * Cadastra pessoa a partir de cpf, nome e habilidades, validando os parâmetros,
	 * criando a Pessoa. A operação de cadastro só é realizada com sucesso caso o
	 * cpf informado ainda não exista no repositório.
	 * 
	 * @param cpf         String com cpf da pessoa a ser cadastrada.
	 * @param nome        String com nome da pessoa a ser cadastrada.
	 * @param habilidades Array com a lista de habilidades.
	 */
	void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		if (!this.existeCpf(cpf)) {
			Pessoa pessoa = new Pessoa(cpf, nome, habilidades);
			this.pessoas.put(cpf, pessoa);
		} else {
			throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema!");
		}
	}

	/**
	 * Cadastra pessoa a partir de cpf, nome, habilidades e função, validando os
	 * parâmetros, criando a Pessoa. A operação de cadastro só é realizada com
	 * sucesso caso o cpf informado ainda não exista no repositório.
	 * 
	 * @param cpf         String com cpf da pessoa a ser cadastrada.
	 * @param nome        String com nome da pessoa a ser cadastrada.
	 * @param habilidades Array com a lista de habilidades.
	 * @param funcao      Função da pessoa.
	 */
	void cadastrarPessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		this.validador.validacao(cpf, nome, habilidades);
		if (!this.existeCpf(cpf)) {
			Pessoa pessoa = new Pessoa(cpf, nome, habilidades, funcao);
			this.pessoas.put(cpf, pessoa);
		} else {
			throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema!");
		}
	}

	/**
	 * Remove pessoa a partir de cpf, caso seu cadastro exista no repositório (mapa
	 * de pessoas), validando o parâmetro recebido.
	 * 
	 * @param cpf String com cpf da pessoa a ser removida.
	 */
	void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (this.existeCpf(cpf)) {
			this.pessoas.remove(cpf);
		} else {
			throw new NoSuchElementException("O CPF informado não está cadastrado no sistema!");
		}
	}

	/**
	 * Recupera pessoa a partir de cpf, caso seu cadastro exista no repositório
	 * (mapa de pessoas), validando o parâmetro recebido.
	 * 
	 * @param cpf String com cpf da pessoa a ser recuperada.
	 * @return Um optional de Pessoa ou optional vazio, caso a pessoa não exista no
	 *         repositório.
	 */
	Optional<Pessoa> recuperarPessoa(String cpf) {
		Pessoa pessoa = null;
		this.validador.validacaoCpf(cpf);
		pessoa = this.pessoas.get(cpf);
		return Optional.ofNullable(pessoa);
	}

	/**
	 * Verifica se a pessoa está cadastrada a partir de cpf, validando o parâmetro
	 * recebido.
	 * 
	 * @param cpf String com cpf da pessoa a ser verificada.
	 * @return Verdadeiro ou Falso para a existência da pessoa.
	 */
	Boolean existeCpf(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * Busca um conjunto de pessoas que atende ao critério de busca.
	 * 
	 * @param criterioBusca Um array de critérios de busca, pelo qual o conjunto de
	 *                      pessoas deve atender.
	 * @return Um conjunto de pessoas que atende ao critério passado.
	 */
	public Set<Pessoa> busca(String[] criterioBusca) {
		Set<Pessoa> resultadosBusca = new HashSet<>();
		boolean possui;
		boolean naoCorresponde;

		for (String chave : this.pessoas.keySet()) {
			naoCorresponde = false;
			for (String criterio : criterioBusca) {
				possui = false;
				if (chave.toUpperCase().equals(criterio.toUpperCase())) {
					possui = true;
				}
				if (this.pessoas.get(chave).getNome().toUpperCase().contains(criterio.toUpperCase())) {
					possui = true;
				}
				if (this.pessoas.get(chave).possuiHabilidade(criterio.toUpperCase())) {
					possui = true;
				}
				if (!possui) {
					naoCorresponde = true;
					break;
				}
			}

			if (naoCorresponde) {
				continue;
			}

			resultadosBusca.add(this.pessoas.get(chave));

		}
		return resultadosBusca;
	}

	/**
	 * Lista pessoas que estão cadastradas no sistema, ordenadas por nome e por cpf
	 * em caso de empate.
	 * 
	 * @return Um array de strings com uma representação textual de identificação de
	 *         cada pessoa para a listagem.
	 */
	String[] listarPessoas() {
		String[] pessoas = new String[this.pessoas.size()];
		List<Entry<String, Pessoa>> list = new ArrayList<>(this.pessoas.entrySet());
		list.sort(Entry.comparingByValue());
		for (int i = 0; i < pessoas.length; i++) {
			pessoas[i] = list.get(i).getValue().getNome() + " - " + list.get(i).getValue().getCpf();
		}
		return pessoas;
	}
}
