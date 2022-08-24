package sapo.atividade;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * Representação de um Validador que trata entradadas de Atividade, que herda de
 * ValidadorPadrao.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class ValidadorAtividade extends ValidadorPadrao {
	/**
	 * Validação da entrada de descrição.
	 * 
	 * @param descricao String com a descrição.
	 */
	private void validacaoDescricao(String descricao) {
		Objects.requireNonNull(descricao, "A descrição não pode ser nula");
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("A descrição não pode ser vazia");
		}
	}

	/**
	 * Validação das entradas de nome, descricao e cpf.
	 * 
	 * @param nome      String com o nome da pessoa.
	 * @param descricao String com a descrição.
	 * @param cpf       String com a cpf da pessoa.
	 * 
	 */
	public void validacao(String nome, String descricao, String cpf) {
		this.validacaoNome(nome);
		this.validacaoDescricao(descricao);
		this.validacaoCpf(cpf);
	}

	/**
	 * Validação das entradas de atividadeId e descricao.
	 * 
	 * @param atividadeId String com o id da atividade.
	 * @param nome        String com o nome da pessoa.
	 */
	public void validacaoAlterarDescricaoAtividade(String atividadeId, String descricao) {
		this.validacaoId(atividadeId);
		this.validacaoDescricao(descricao);
	}
}
