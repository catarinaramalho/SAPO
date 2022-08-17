package sapo.busca;

public class BuscaController {
	
	private BuscaService buscaService;
	
	public BuscaController(BuscaService buscaService) {
		this.buscaService = buscaService;
	}
	
	public String[] busca(String criterioBusca) {
		return this.buscaService.busca(criterioBusca);
	}
}
