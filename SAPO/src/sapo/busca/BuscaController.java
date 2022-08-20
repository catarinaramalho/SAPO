package sapo.busca;

import java.util.List;

import sapo.pessoa.Pessoa;

public class BuscaController {
	
	private BuscaService buscaService;
	
	public BuscaController(BuscaService buscaService) {
		this.buscaService = buscaService;
	}
	
	public List<Pessoa> buscarPessoas(String criterioBusca) {
		//VALIDACAO CRITERIO
		return this.buscaService.buscaPessoas(new BuscaPessoa(criterioBusca));
	}
	
	public List<String> buscarAtividades(String criterioBusca){
		//VALIDACAO CRITERIO
		return this.buscaService.buscaAtividades(new BuscaAtividade(criterioBusca));
	}
	
	public List<String> buscarTarefas(String criterioBusca){
		//VALIDACAO CRITERIO
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca));
	}
	
	public List<String> buscarTarefas(String criterioBusca, String idAtividade){
		//VALIDACAO CRITERIO
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca), idAtividade);
	}
	
	public String[] sugerirTarefas(String cpf) {
		return this.buscaService.sugerirTarefas(new SugerirTarefa(), cpf);
	}
	
}
