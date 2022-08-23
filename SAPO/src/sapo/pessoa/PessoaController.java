package sapo.pessoa;

/**
 * Controller para operações em Pessoa. Valida entradas e direciona o fluxo da
 * aplicação mapeando e direcionado as ações recebidas pelo Facade para o Pessoa
 * Service.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class PessoaController {
	/**
	 * Service de Pessoa a operar o fluxo recebido pelo controller.
	 */
	private PessoaService pessoaService;
	/**
	 * Validador a ser utilizado pelo controller para validar os parâmetros
	 * recebidos.
	 */
	private ValidadorPessoa validador;

	/**
	 * Construtor padrão do controller. Recebe uma PessoaService como parâmetro e
	 * atribui ao respectivo atributo. E cria um validador a ser usado no
	 * controller.
	 * 
	 * @param pessoaService PessoaService a ser usado no controller.
	 */
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
		this.validador = new ValidadorPessoa();
	}

	/**
	 * Cadastra pessoa a partir de cpf, nome e habilidades, validando os parâmetros
	 * e direcionando para o Pessoa Service.
	 * 
	 * @param cpf         String com cpf da pessoa a ser cadastrada.
	 * @param nome        String com nome da pessoa a ser cadastrada.
	 * @param habilidades Array com a lista de habilidades.
	 */
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		this.pessoaService.cadastrarPessoa(cpf, nome, habilidades);
	}

	/**
	 * Exibe pessoa a partir de cpf, validando os parâmetros e direcionando para o
	 * Pessoa Service.
	 * 
	 * @param cpf String com cpf da pessoa a ser cadastrada.
	 * @return A representação textual da pessoa ou uma exceção caso ela não exista.
	 */
	public String exibirPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaService.exibirPessoa(cpf);
	}

	/**
	 * Altera o nome de uma pessoa a partir de cpf, com o novo nome, validando os
	 * parâmetros e direcionando para o Pessoa Service.
	 * 
	 * @param cpf      String com cpf da pessoa.
	 * @param novoNome String com o novo nome.
	 */
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.validador.validacao(novoNome, cpf);
		this.pessoaService.alterarNomePessoa(cpf, novoNome);
	}

	/**
	 * Altera as habilidades de uma pessoa a partir de cpf, com as novas
	 * habilidades, validando os parâmetros e direcionando para o Pessoa Service.
	 * 
	 * @param cpf              String com cpf da pessoa a ser cadastrada.
	 * @param novasHabilidades Array com as novasHabilidades.
	 */
	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.validador.validacao(cpf, novasHabilidades);
		this.pessoaService.alterarHabilidades(cpf, novasHabilidades);
	}

	/**
	 * Remove pessoa a partir de cpf, validando os
	 * parâmetros e direcionando para o Pessoa Service.
	 * 
	 * @param cpf String com cpf da pessoa a ser cadastrada.
	 */
	public void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		this.pessoaService.removerPessoa(cpf);
	}

	/**
	 * Adiciona comentário para pessoa, a partir do cpf, do comentário e do cpf do
	 * autor do comentário, validando os parâmetros e direcionando para o Pessoa
	 * Service.
	 * 
	 * @param cpf        String com cpf da pessoa.
	 * @param comentario String com o comentario a ser adicionado.
	 * @param autorCpf   String com cpf do autor do comentário.
	 */
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.validador.validacao(cpf, comentario, autorCpf);
		this.pessoaService.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}

	/**
	 * Lista os comentários de uma pessoa a partir do cpf,validando os parâmetros e
	 * direcionando para o Pessoa Service.
	 * 
	 * @param cpf String com cpf da pessoa.
	 * @return String contendo a listagem de comentários de uma pessoa.
	 */
	public String listarComentariosPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaService.listarComentariosPessoa(cpf);

	}

}
