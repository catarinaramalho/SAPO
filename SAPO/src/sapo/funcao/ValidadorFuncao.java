package sapo.funcao;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorFuncao extends ValidadorPadrao {

	public void validacao(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.validacaoAluno(cpf, matr, periodo);
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}

	public void validacao(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.validacaoProfessor(cpf, siape, disciplinas);
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}

	public void validacaoAluno(String cpf, String matr, int periodo) {
		this.validacaoCpf(cpf);
		this.validacaoMatr(matr);
		this.validacaoPeriodo(periodo);
	}

	public void validacaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.validacaoCpf(cpf);
		this.validacaoSiape(siape);
		this.validacaoDisciplinas(disciplinas);
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

	private void validacaoDisciplinas(String[] disciplinas) {
		Objects.requireNonNull(disciplinas, "O array de disciplinas não pode ser nulo");
		if (disciplinas.length == 0) { // Pode não ter disciplinas?
			throw new IllegalArgumentException("O array de disciplinas não pode ser vazio");
		}
		for (String disciplina : disciplinas) {
			Objects.requireNonNull(disciplina, "Não podem haver disciplinas nulas");
			if (disciplina.isBlank()) {
				throw new IllegalArgumentException("Não podem haver disciplinas vazias");
			}
		}
	}
}
