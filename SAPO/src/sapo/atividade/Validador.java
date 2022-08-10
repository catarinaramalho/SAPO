package sapo.atividade;

import java.util.Objects;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class Validador {

	public void validacao(String atividadeId) {
		Objects.requireNonNull(atividadeId, "O id da atividade não pode ser nulo");
		if (atividadeId.isBlank()) {
			throw new IllegalArgumentException("O id da atividade não pode ser vazio");
		}
	}

	public void validacao(String nome, String[] habilidades) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}

	private void validacaoHabilidades(String[] habilidades) {
		for (String habilidade : habilidades) {
			if (habilidade.isBlank()) {
				throw new IllegalArgumentException("O id da atividade não pode ser vazio");
			}
			if (habilidade.isBlank()) {
				throw new IllegalArgumentException("Não pode habilidade vaziaa!!!");
			}
		}
	}

	public void validacao(String nome, String descricao, String cpf) {
		this.validacaoNome(nome);
		this.validacaoDescricao(descricao);
		this.validacaoCpf(cpf);
	}

	private void validacaoNome(String nome) {
		Objects.requireNonNull(nome, "O nome não pode ser nulo");
		if (nome.isBlank()) {
			throw new IllegalArgumentException("O nome não pode ser vazio.");
		}
	}

	private void validacaoDescricao(String descricao) {
		Objects.requireNonNull(descricao, "A descrição não pode ser nula");
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("A descrição não pode ser vazia.");
		}
	}

	private void validacaoCpf(String cpf) {
		Objects.requireNonNull(cpf, "O CPF da pessoa responsável não pode ser nulo");
		if (cpf.isBlank()) {
			throw new IllegalArgumentException("O CPF da pessoa responsável não pode ser vazio.");
		}
	}
}
