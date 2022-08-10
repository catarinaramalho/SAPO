package sapo.atividade;

import java.util.Objects;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class ValidadorAtividades {

	public void validacao(String atividadeId) {
		Objects.requireNonNull(atividadeId, "O id da atividade não pode ser nulo");
		if (atividadeId.isBlank()) {
			throw new IllegalArgumentException("O id da atividade não pode ser vazio");
		}
	}

	public void validacao(String nome, String descricao, String cpf) {
		Objects.requireNonNull(nome, "Nome da atividade não pode ser nulo");
		if (nome.isBlank()) {
			throw new IllegalArgumentException("Nome da atividade não pode ser vazio.");
		}
		Objects.requireNonNull(descricao, "Descrição da atividade não pode ser nula");
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("Descrição da atividade não pode ser vazia.");
		}
		Objects.requireNonNull(cpf, "CPF da pessoa responsável não pode ser nulo");
		if (cpf.isBlank()) {
			throw new IllegalArgumentException("CPF da pessoa responsável não pode ser vazio.");
		}
	}
}
