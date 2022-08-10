package sapo.pessoa;

import java.util.HashMap;
import java.util.Map;

public class PessoaRepository {
	Map<String,Pessoa> pessoas;
	
	public PessoaRepository() {
		this.pessoas = new HashMap<String, Pessoa>();
	}
	
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		Pessoa pessoa = new Pessoa(cpf,nome,habilidades);
		this.pessoas.put(cpf, pessoa);
	}
	
	public void removerPessoa(String cpf) {
		this.pessoas.remove(cpf);
	}
	
	public Pessoa recuperarPessoa(String cpf) {
		return this.pessoas.get(cpf);
	}
}
