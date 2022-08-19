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
		return this.buscaService.buscaPessoas(new BuscaPessoa(criterioBusca));
	}
	
	public List<String> buscaAtividade(String criterioBusca){
		//VALIDACAO CRITERIO
		return this.buscaService.buscaAtividades(new BuscaAtividade(criterioBusca));
	}
	
	public List<String> buscaTarefa(String criterioBusca){
		//VALIDACAO CRITERIO
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca));
	}
	
}
