package sapo.funcao;

/**
 * Representação de uma função Aluno. Classe Aluno que implementa a interface
 * Funcão. Todo aluno possui matrícula e período.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class Aluno implements Funcao {

	/**
	 * String que representa a matrícula do aluno.
	 */
	private String matr;
	/**
	 * Inteiro que representa o período do aluno. O período não pode um ser um
	 * número menor que 1.
	 */
	private int periodo;

	/**
	 * Constrói um Aluno a partir da matrícula e período. O período não pode ser
	 * menor que 1.
	 * 
	 * @param matr    String com a matrícula do aluno.
	 * @param periodo int preíodo do aluno.
	 */
	public Aluno(String matr, int periodo) {
		this.matr = matr;
		this.periodo = periodo;
	}

	/**
	 * Representação textual de um aluno. Retorna uma string que representa a função
	 * aluno textualmente. A representação tem o formato "Aluno - {matrícula} -
	 * {período}".
	 */
	@Override
	public String toString() {
		return "Aluno - " + matr + " - " + periodo;
	}
}
