package sapo.funcao;

import java.util.Arrays;

/**
 * Representação de uma função Professor. Classe Professor que implementa a
 * interface Funcão. Todo professor possui um código SIAPE e um array com as
 * diciplinas que ele leciona.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class Professor implements Funcao {

	/**
	 * String com código SIAPE do professor.
	 */
	private String siape;
	/**
	 * Array de String com detalhes das disciplinas oferecidas pelo professor.
	 */
	private String[] disciplinas;

	/**
	 * Constrói um Professor a partir do código SIAPE e do detalhamento das
	 * disciplinas oferecidas pelo professor. O código SIAPE não pode ser nulo ou
	 * vazio.
	 * 
	 * @param siape       String com código SIAPE do professor.
	 * @param disciplinas Array de strings com o detalhamento das disciplinas
	 *                    oferecidas pelo professor.
	 */
	public Professor(String siape, String[] disciplinas) {
		this.siape = siape;
		this.disciplinas = disciplinas;
	}
	
	public String[] getDisciplinas() {
		return this.disciplinas;
	}

	@Override
	public String toString() {
		return "Professor - " + siape + " - " + Arrays.toString(disciplinas);
	}
}
