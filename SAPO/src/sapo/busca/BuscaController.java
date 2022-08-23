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
	
	public String[] buscarPessoas(String criterioBusca) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaPessoas(new BuscaPessoa(criterioBusca));
	}
	
	public String[] buscarAtividades(String criterioBusca){
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaAtividades(new BuscaAtividade(criterioBusca));
	}
	
	public String[] buscarTarefas(String criterioBusca){
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca));
	}
	
	public String[] buscarTarefas(String criterioBusca, String idAtividade){
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
	
	public String[] exibirHistoricoBusca(int indexBusca) {
		this.validadorBusca.validaNBuscas(indexBusca);
		return this.buscaService.exibirHistoricoBusca(indexBusca);
	}
}
