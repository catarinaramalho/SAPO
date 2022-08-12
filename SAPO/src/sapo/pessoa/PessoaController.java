package sapo.pessoa;

public class PessoaController {
	private PessoaService pessoaService;

	public PessoaController() {
		this.pessoaService = new PessoaService();
	}

	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.pessoaService.cadastrarPessoa(cpf, nome, habilidades);
	}

	public String exibirPessoa(String cpf) {
		return this.pessoaService.exibirPessoa(cpf);
	}

	public void alterarNomePessoa(String cpf, String novoNome) {
		this.pessoaService.alterarNomePessoa(cpf, novoNome);
	}

	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.pessoaService.alterarHabilidades(cpf, novasHabilidades);
	}

	public void removerPessoa(String cpf) {
		this.pessoaService.removerPessoa(cpf);
	}

	public Pessoa recuperarPessoa(String cpf) {
		return this.pessoaService.recuperarPessoa(cpf);
	}

	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.pessoaService.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}

	public String listarComentariosPessoa(String cpf) {
		return this.pessoaService.listarComentariosPessoa(cpf);

	}

}
