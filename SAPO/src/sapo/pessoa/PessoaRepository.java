package sapo.pessoa;

import java.util.HashMap;
import java.util.Map;

public class PessoaRepository {
	Map<String, Pessoa> pessoas;
	ValidadorPessoa validador;

	public PessoaRepository() {
		this.pessoas = new HashMap<String, Pessoa>();
		this.validador = new ValidadorPessoa();
	}

	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		Pessoa pessoa = new Pessoa(cpf, nome, habilidades);
		this.pessoas.put(cpf, pessoa);
	}

	public void removerPessoa(String cpf) {
		this.pessoas.remove(cpf);
	}

	public Pessoa recuperarPessoa(String cpf) {
		return this.pessoas.get(cpf);
	}
	
	public String exibirPessoa(String cpf) {
		return this.pessoas.get(cpf).toString();
	}
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.pessoas.get(cpf).setNome(novoNome);
	}
	
	public void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.pessoas.get(cpf).setHabilidades(novasHabilidades);
	}
	
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.pessoas.get(cpf).adicionarComentario(this.pessoas.get(autorCpf), comentario);
	}
	
	public String listarComentariosPessoa(String cpf) {
		return this.pessoas.get(cpf).listarComentarios();
		
	}
	
//	private Boolean existeCpf(String cpf) {
//		for (String cpfCadastrado : this.pessoas.keySet()) {
//			if(cpfCadastrado.equals(cpf)) {
//				return true;
//			}
//		} return false;
//		
//	}
	
	

}
