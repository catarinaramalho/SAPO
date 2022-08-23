package sapo.pessoa;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import sapo.funcao.Funcao;

/**
 * Service para a lógica de negócio de pessoa. Valida entradas e faz a
 * comunicação com o repositório de pessoas.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class PessoaService {
	/**
	 * Repository de Pessoa para obtenção das operações e dos dados das pessoas
	 * armazenadas no sistema.
	 */
	private PessoaRepository pessoaRepository;
	/**
	 * Validador a ser utilizado pelo service para validar os parâmetros recebidos.
	 */
	private ValidadorPessoa validador;

	/**
	 * Construtor padrão do service, que inicializa o repositório de pessoas e o
	 * validador.
	 */
	public PessoaService() {
		this.pessoaRepository = new PessoaRepository();
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
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		this.pessoaRepository.cadastrarPessoa(cpf, nome, habilidades);
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
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		this.validador.validacao(cpf, nome, habilidades);
		this.pessoaRepository.cadastrarPessoa(cpf, nome, habilidades, funcao);
	}

	/**
	 * Exibe pessoa a partir de cpf, validando os parâmetros e exibindo a
	 * representação textual da pessoa, caso ela exista.
	 * 
	 * @param cpf String com cpf da pessoa a ser cadastrada.
	 * @return A representação textual da pessoa ou uma exceção caso ela não exista.
	 */
	public String exibirPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.recuperarPessoaOuFalhe(cpf).toString();
	}

	/**
	 * Altera o nome de uma pessoa a partir de cpf, buscada por meio do repositório,
	 * para receber o novo nome, validando os parâmetros.
	 * 
	 * @param cpf      String com cpf da pessoa.
	 * @param novoNome String com o novo nome.
	 */
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.validador.validacao(cpf, novoNome);
		this.recuperarPessoaOuFalhe(cpf).setNome(novoNome);
	}

	/**
	 * Altera as habilidades de uma pessoa a partir de cpf, a qual é buscada pelo
	 * repositório de pessoas, para receber as novas habilidades, validando os
	 * parâmetros.
	 * 
	 * @param cpf              String com cpf da pessoa a ser cadastrada.
	 * @param novasHabilidades Array com as novasHabilidades.
	 */
	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.validador.validacao(cpf, novasHabilidades);
		this.recuperarPessoaOuFalhe(cpf).setHabilidades(novasHabilidades);
	}

	/**
	 * Remove pessoa a partir de cpf, caso seu cadastro exista no repositório (mapa
	 * de pessoas), validando o parâmetro recebido.
	 * 
	 * @param cpf String com cpf da pessoa a ser removida.
	 */
	public void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		this.pessoaRepository.removerPessoa(cpf);
	}

	/**
	 * Recupera pessoa a partir de cpf, caso seu cadastro exista no repositório
	 * (mapa de pessoas). Caso contrário, retorna uma exceção. Também, valida o
	 * parâmetro recebido.
	 * 
	 * @param cpf String com cpf da pessoa a ser recuperada.
	 * @return Um optional de Pessoa ou optional vazio, caso a pessoa não exista no
	 *         repositório.
	 */
	public Pessoa recuperarPessoaOuFalhe(String cpf) {
		this.validador.validacaoCpf(cpf);
		Optional<Pessoa> optional = this.pessoaRepository.recuperarPessoa(cpf);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("Esta pessoa não existe!");
		}
		return optional.get();
	}

	/**
	 * Adiciona comentário para pessoa, a partir do cpf, do comentário e do cpf do
	 * autor do comentário, validando os parâmetros e acessando as pessoas a partir
	 * do repositório de pessoas.
	 * 
	 * @param cpf        String com cpf da pessoa.
	 * @param comentario String com o comentario a ser adicionado.
	 * @param autorCpf   String com cpf do autor do comentário.
	 */
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.validador.validacao(cpf, comentario, autorCpf);
		this.recuperarPessoaOuFalhe(cpf).adicionarComentario(this.recuperarPessoaOuFalhe(autorCpf), comentario);
	}

	/**
	 * Lista os comentários de uma pessoa a partir do cpf,validando os parâmetros e
	 * acessando as pessoas a partir do repositório de pessoas.
	 * 
	 * @param cpf String com cpf da pessoa.
	 * @return String contendo a listagem de comentários de uma pessoa.
	 */
	public String listarComentariosPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.recuperarPessoaOuFalhe(cpf).listarComentarios();

	}

	/**
	 * Busca um conjunto de pessoas que atende a lista de critérios de busca, as
	 * quais são acessadas pelo repositório de pessoas.
	 * 
	 * @param criterioBusca Um array de critérios de busca, pelo qual o conjunto de
	 *                      pessoas deve atender.
	 * @return Um conjunto de pessoas que atende ao critério passado.
	 */
	public Set<Pessoa> busca(String[] criterioBusca) {
		return this.pessoaRepository.busca(criterioBusca);
	}

	/**
	 * Lista pessoas que estão cadastradas no sistema, ordenadas por nome e por cpf
	 * em caso de empate, as quais são acessadas pelo repositório de pessoas.
	 * 
	 * @return Um array de strings com uma representação textual de identificação de
	 *         cada pessoa para a listagem.
	 */
	public String[] listarPessoas() {
		return this.pessoaRepository.listarPessoas();
	}
}
