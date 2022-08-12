package sapo.atividade;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class Validador extends ValidadorPadrao {

	public void validacaoId(String id) {
		Objects.requireNonNull(id, "O id não pode ser nulo");
		if (id.isBlank()) {
			throw new IllegalArgumentException("O id não pode ser vazio");
		}
	}

	private void validacaoDescricao(String descricao) {
		Objects.requireNonNull(descricao, "A descrição não pode ser nula");
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("A descrição não pode ser vazia");
		}
	}

	public void validacao(String nome, String[] habilidades) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}

	public void validacao(String nome, String descricao, String cpf) {
		this.validacaoNome(nome);
		this.validacaoDescricao(descricao);
		this.validacaoCpf(cpf);
	}

	public void validacaoAlterarDescricaoAtividade(String atividadeId, String descricao) {
		this.validacaoId(atividadeId);
		this.validacaoDescricao(descricao);
	}
}
