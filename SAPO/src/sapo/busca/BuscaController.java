package sapo.busca;

import java.util.List;

import sapo.pessoa.Pessoa;

public class BuscaController {
	
	private BuscaService buscaService;
	private ValidadorBusca validadorBusca;
	
	public BuscaController(BuscaService buscaService) {
		this.buscaService = buscaService;
		this.validadorBusca = new ValidadorBusca();
	}
	
	public List<Pessoa> buscarPessoas(String criterioBusca) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaPessoas(new BuscaPessoa(criterioBusca));
	}
	
	public List<String> buscarAtividades(String criterioBusca){
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaAtividades(new BuscaAtividade(criterioBusca));
	}
	
	public List<String> buscarTarefas(String criterioBusca){
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca));
	}
	
	public List<String> buscarTarefas(String criterioBusca, String idAtividade){
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca), idAtividade);
	}
	
	public String[] sugerirTarefas(String cpf) {
		this.validadorBusca.validacaoCpf(cpf);
		return this.buscaService.sugerirTarefas(new SugerirTarefa(), cpf);
	}
	
	public String[] buscasMaisRecentes(int nBuscas) {
		this.validadorBusca.validaNBuscas(nBuscas);
		return this.buscaService.buscasMaisRecentes(nBuscas);
	}
	
	public String[] exibirHistóricoBusca(int indexBusca) {
		this.validadorBusca.validaNBuscas(indexBusca);
		return this.buscaService.exibirHistóricoBusca(indexBusca);
	}
}
