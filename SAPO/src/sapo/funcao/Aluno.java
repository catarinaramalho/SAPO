package sapo.funcao;

public class Aluno {

	private String matr;
	private int periodo;

	public Aluno(String matr, int periodo) {
		this.matr = matr;
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Aluno - " + matr + " - " + periodo;
	}
}
