package sapo.pessoa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


class PessoaRepository {
	private Map<String, Pessoa> pessoas;
	private ValidadorPessoa validador;

	PessoaRepository() {
		this.pessoas = new HashMap<String, Pessoa>();
		this.validador = new ValidadorPessoa();
	}

	void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		this.validador.validacao(cpf, nome, habilidades);
		if (!this.existeCpf(cpf)) {
			Pessoa pessoa = new Pessoa(cpf, nome, habilidades);
			this.pessoas.put(cpf, pessoa);
		} else{
			throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema!");
		}
		
	}

	void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (this.existeCpf(cpf)) {
			this.pessoas.remove(cpf);
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
		
	}
	
	Optional<Pessoa> recuperarPessoa(String cpf) {
		Pessoa pessoa = null;
		this.validador.validacaoCpf(cpf);
		pessoa = this.pessoas.get(cpf);
		return Optional.ofNullable(pessoa);
	}

	String exibirPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (this.existeCpf(cpf)) {
			return this.pessoas.get(cpf).toString();
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
		
		
	}

	void alterarNomePessoa(String cpf, String novoNome) {
		this.validador.validacao(cpf, novoNome);
		if (this.existeCpf(cpf)) {
			this.pessoas.get(cpf).setNome(novoNome);
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
		
	}

	void alterarHabilidades(String cpf, String[] novasHabilidades) {
		this.validador.validacao(cpf, novasHabilidades);
		if (this.existeCpf(cpf)) {
			this.pessoas.get(cpf).setHabilidades(novasHabilidades);
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
	}

	void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		this.validador.validacao(cpf,comentario, autorCpf);
		if (this.existeCpf(cpf)) {
			this.pessoas.get(cpf).adicionarComentario(this.pessoas.get(autorCpf), comentario);
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
	}

	String listarComentariosPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (this.existeCpf(cpf)) {
			return this.pessoas.get(cpf).listarComentarios();
		} else {
			throw new IllegalArgumentException("O CPF informado não está cadastrado no sistema!");
		}
	}
	
	Boolean existeCpf(String cpf) {
		this.validador.validacaoCpf(cpf);
		for (String cpfCadastrado : this.pessoas.keySet()) {
			if(cpfCadastrado.equals(cpf)) {
				return true;
			}
		} return false;
		
	}

}
