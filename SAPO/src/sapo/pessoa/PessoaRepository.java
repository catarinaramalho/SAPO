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
		} else {
			throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema!");
		}
	}

	void cadastrarPessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		this.validador.validacao(cpf, nome, habilidades);
		if (!this.existeCpf(cpf)) {
			Pessoa pessoa = new Pessoa(cpf, nome, habilidades, funcao);
			this.pessoas.put(cpf, pessoa);
		} else {
			throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema!");
		}
	}

	void removerPessoa(String cpf) {
		this.validador.validacaoCpf(cpf);
		if (this.existeCpf(cpf)) {
			this.pessoas.remove(cpf);
		} else {
			throw new NoSuchElementException("O CPF informado não está cadastrado no sistema!");
		}
	}

	Optional<Pessoa> recuperarPessoa(String cpf) {
		Pessoa pessoa = null;
		this.validador.validacaoCpf(cpf);
		pessoa = this.pessoas.get(cpf);
		return Optional.ofNullable(pessoa);
	}

	Boolean existeCpf(String cpf) {
		this.validador.validacaoCpf(cpf);
		for (String cpfCadastrado : this.pessoas.keySet()) {
			if (cpfCadastrado.equals(cpf)) {
				return true;
			}
		}
		return false;
	}

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
				if (this.pessoas.get(chave).getNome().toUpperCase().equals(criterio.toUpperCase())) {
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
