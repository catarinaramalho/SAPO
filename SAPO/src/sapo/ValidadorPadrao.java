package sapo;

import java.util.Objects;

public class ValidadorPadrao {

	public void validacaoHabilidades(String[] habilidades) {
		Objects.requireNonNull(habilidades, "Habilidades não pode ser nulo!!!");
		for (String habilidade : habilidades) {
			Objects.requireNonNull(habilidade, "Não pode habilidade nula!!!");
			if (habilidade.isBlank()) {
				throw new IllegalArgumentException("Não pode habilidade vazia!");
			}
		}
	}

	public void validacaoNome(String nome) {
		Objects.requireNonNull(nome, "O nome não pode ser nulo");
		if (nome.isBlank()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
	}

	public void validacaoCpf(String cpf) {
		Objects.requireNonNull(cpf, "O CPF não pode ser nulo");
		if (cpf.isBlank()) {
			throw new IllegalArgumentException("O CPF não pode ser vazio");
		}
	}
	
	public void validacaoId(String id) {
		Objects.requireNonNull(id, "O id não pode ser nulo");
		if (id.isBlank()) {
			throw new IllegalArgumentException("O id não pode ser vazio");
		}
	}
}
