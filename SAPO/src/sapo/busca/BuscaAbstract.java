package sapo.busca;

public abstract class BuscaAbstract implements BuscaInterface {
	private String[] criterio;
	
	public BuscaAbstract(String criterioBusca) {
		this.criterio = criterioBusca.split(" ");
	}
	
	public String[] getCriterio() {
		return this.criterio;
	}
}
