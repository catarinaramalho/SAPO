package sapo.busca;

public abstract class BuscaAbstract {
	private String[] criterio;
	
	public BuscaAbstract(String criterioBusca) {
		this.criterio = criterioBusca.split(" ");
	}
	
	public String[] getCriterio() {
		return this.criterio;
	}
	
	public abstract String[] representaBusca();
	
}
