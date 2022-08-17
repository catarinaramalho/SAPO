package sapo.busca;

import java.util.List;

import sapo.pessoa.Pessoa;

public class BuscaController {
	
	private BuscaService buscaService;
	
	public BuscaController(BuscaService buscaService) {
		this.buscaService = buscaService;
	}
	
	public List<Pessoa> buscaPessoa(String criterioBusca) {
		//VALIDACAO CRITERIO
		return this.buscaService.busca(new BuscaPessoa(criterioBusca));
	}
	
}
