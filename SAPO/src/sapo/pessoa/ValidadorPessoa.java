package sapo.pessoa;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * Representação de um Validador que trata entradadas de pessoa, que herda de
 * ValidadorPadrao.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class ValidadorPessoa extends ValidadorPadrao {
	/**
	 * Validação das entradas de cpf, nome e habilidades.
	 * 
	 * @param cpf         String com a cpf da pessoa.
	 * @param nome        String com o nome da pessoa.
	 * @param habilidades Array de lista com habilidades da pessoa.
	 */
	public void validacao(String cpf, String nome, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);

	}

	/**
	 * Validação das entradas de cpf e habilidades.
	 * 
	 * @param cpf         String com a cpf da pessoa.
	 * @param habilidades Array de lista com habilidades da pessoa.
	 */
	public void validacao(String cpf, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoHabilidades(habilidades);
	}

	/**
	 * Validação das entradas de cpf e nome
	 * 
	 * @param cpf  String com a cpf da pessoa.
	 * @param nome String com o nome da pessoa.
	 */
	public void validacao(String cpf, String nome) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);

	}

	/**
	 * Validação das entradas de cpf, comentario e cpf do autor.
	 * 
	 * @param cpf        String com a cpf da pessoa.
	 * @param comentario String com o comentário para a pessoa.
	 * @param cpf        String com o cpf do autor.
	 */
	public void validacao(String cpf, String comentario, String autorCpf) {
		this.validacaoCpf(cpf);
		this.validacaoCpf(autorCpf);
		this.validacaoComentario(comentario);

	}

	/**
	 * Validação da entrada de comentário, para que ele não seja nulo nem vazio.
	 * 
	 * @param comentario String com a entrada de comentario.
	 */
	public void validacaoComentario(String comentario) {
		Objects.requireNonNull(comentario, "O Comentário não pode ser nulo!");
		if (comentario.isBlank()) {
			throw new IllegalArgumentException("O Comentário não pode ser vazio!");
		}
	}
}
