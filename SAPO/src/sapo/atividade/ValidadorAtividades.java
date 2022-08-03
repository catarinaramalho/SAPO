package sapo.atividade;

public class ValidadorAtividades {

	public void validacao(String nome, String descricao, String cpf) {
		if (nome.equals(null)) {
			throw new NullPointerException("Nome não pode ser nulo.");
		} else if (nome.isBlank()) {
			throw new IllegalArgumentException("Nome não pode ser vazio.");
		} else if (descricao.equals(null)) {
			throw new NullPointerException("Descrição não pode ser nula.");
		} else if (descricao.isBlank()) {
			throw new IllegalArgumentException("Descrição não pode ser vazia.");
		} else if (cpf.equals(null)) {
			throw new NullPointerException("CPF do responsável não pode ser nulo.");
		} else if (cpf.isBlank()) {
			throw new IllegalArgumentException("CPF do responsável não pode ser vazio.");
		}
	}
}
