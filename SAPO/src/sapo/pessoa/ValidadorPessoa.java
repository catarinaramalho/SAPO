package sapo.pessoa;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorPessoa extends ValidadorPadrao{
	
	public void validacao(String cpf, String nome, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
		
	}
	public void validacao(String cpf, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoHabilidades(habilidades);
	}
	
	public void validacao(String cpf, String nome) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);
		
	}
	
	public void validacaoComentario(String comentario) {
		Objects.requireNonNull(comentario, "O Comentário não pode ser nulo!");
		if (comentario.isBlank()) {
			throw new IllegalArgumentException("O Comentário não pode ser vazio!");
		}
	}
	
	public void validacao(String cpf, String comentario, String autorCpf) {
		this.validacaoCpf(cpf);
		this.validacaoCpf(autorCpf);
		this.validacaoComentario(comentario);
		
	}
}
