package sapo.funcao;

import java.util.Arrays;

public class Professor {

	private String siape;
	private String[] disciplinas;

	public Professor(String siape, String[] disciplinas) {
		this.siape = siape;
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Professor - " + siape + " - " + Arrays.toString(disciplinas);
	}
}
