package sapo.pessoa;

import java.util.NoSuchElementException;
import java.util.Optional;

public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	private ValidadorPessoa validador;
	
	public PessoaService() {
		this.pessoaRepository = new PessoaRepository();
		this.validador = new ValidadorPessoa();
	}
	
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		this.pessoaRepository.cadastrarPessoa(cpf, nome, habilidades);
	}
	
	public String exibirPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaRepository.recuperarPessoa(cpf).toString();
	}
	
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.validador.validacao(cpf, novoNome);
		this.pessoaRepository.alterarNomePessoa(cpf, novoNome);
	}
	
	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.validador.validacao(cpf, novasHabilidades);
		this.pessoaRepository.alterarHabilidades(cpf, novasHabilidades);
	}
	
	public void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		this.pessoaRepository.removerPessoa(cpf);
	}
	
	public Pessoa recuperarPessoaOuFalhe(String cpf) {
		this.validador.validacaoCpf(cpf);
		Optional<Pessoa> optional = this.pessoaRepository.recuperarPessoa(cpf);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("Esta pessoa não existe!");
		}
		return optional.get();
	}
	
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.validador.validacao(cpf, comentario, autorCpf);
		this.pessoaRepository.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}
	
	public String listarComentariosPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.pessoaRepository.listarComentariosPessoa(cpf);
		
	}
	

}
