package sapo.pessoa;

public class PessoaController {
	private PessoaService pessoaService;
	private ValidadorPessoa validador;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
		this.validador = new ValidadorPessoa();
	}

	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		this.pessoaService.cadastrarPessoa(cpf, nome, habilidades);
	}
	
	public Pessoa recuperarPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaService.recuperarPessoaOuFalhe(cpf);
	}

	public String exibirPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaService.exibirPessoa(cpf);
	}

	public void alterarNomePessoa(String cpf, String novoNome) {
		this.validador.validacao(novoNome, cpf);
		this.pessoaService.alterarNomePessoa(cpf, novoNome);
	}

	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.validador.validacao(cpf, novasHabilidades);
		this.pessoaService.alterarHabilidades(cpf, novasHabilidades);
	}

	public void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		this.pessoaService.removerPessoa(cpf);
	}

	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.validador.validacao(cpf, comentario, autorCpf);
		this.pessoaService.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}

	public String listarComentariosPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaService.listarComentariosPessoa(cpf);

	}

}
