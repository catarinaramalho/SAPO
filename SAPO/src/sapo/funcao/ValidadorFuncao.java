package sapo.funcao;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorFuncao extends ValidadorPadrao {

	public void validacaoCadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);
		this.validacaoMatr(matr);
		this.validacaoPeriodo(periodo);
		this.validacaoHabilidades(habilidades);
	}

	private void validacaoMatr(String matr) {
		Objects.requireNonNull(matr, "A matrícula não pode ser nula");
		if (matr.isBlank()) {
			throw new IllegalArgumentException("A matrícula não pode ser vazia");
		}
	}

	private void validacaoPeriodo(int periodo) {
		if (periodo < 1) {
			throw new IllegalArgumentException("O período não pode ser menor que 1");
		}
	}
	
	private void validacaoSiape(String siape) {
		Objects.requireNonNull(siape, "O SIAPE não pode ser nulo");
		if (siape.isBlank()) {
			throw new IllegalArgumentException("O SIAPE  não pode ser vazio");
		}
	}
}
