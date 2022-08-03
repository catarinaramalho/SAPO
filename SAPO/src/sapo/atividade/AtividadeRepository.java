package sapo.atividade;

import java.util.HashMap;
import java.util.Map;

class AtividadeRepository {

	private Map<String, Atividade> atividades;
	
	public AtividadeRepository() {
		this.atividades = new HashMap<>();
	}
}
