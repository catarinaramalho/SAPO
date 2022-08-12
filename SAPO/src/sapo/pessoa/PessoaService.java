package sapo.pessoa;

public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	public PessoaService() {
		this.pessoaRepository = new PessoaRepository();
	}
	
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.pessoaRepository.cadastrarPessoa(cpf, nome, habilidades);
	}
	
	public String exibirPessoa(String cpf) {
		return this.pessoaRepository.exibirPessoa(cpf);
	}
	
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.pessoaRepository.alterarNomePessoa(cpf, novoNome);
	}
	
	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.pessoaRepository.alterarHabilidades(cpf, novasHabilidades);
	}
	
	public void removerPessoa(String cpf) {
		this.pessoaRepository.removerPessoa(cpf);
	}
	
	public Pessoa recuperarPessoa(String cpf) {
		return this.pessoaRepository.recuperarPessoa(cpf);
	}
	
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.pessoaRepository.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}
	
	public String listarComentariosPessoa(String cpf) {
		return this.pessoaRepository.listarComentariosPessoa(cpf);
		
	}
	

}
