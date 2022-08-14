package sapo.atividade;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class ValidadorAtividade extends ValidadorPadrao {

	private void validacaoDescricao(String descricao) {
		Objects.requireNonNull(descricao, "A descrição não pode ser nula");
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("A descrição não pode ser vazia");
		}
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
