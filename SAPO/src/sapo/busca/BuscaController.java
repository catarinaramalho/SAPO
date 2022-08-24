package sapo.busca;

/**
 * Classe responsável por fazer validações na hora de iniciar uma nova busca e
 * pela delegação de operações envolvendo cada objeto de busca para a classe
 * responsável, chamando os metodos de buscaService que possuem a logica para
 * executar as operações.
 * 
 * @author Lucas Leones Costa dos Santos
 *
 */
public class BuscaController {

	/**
	 * BuscaService que realizará a logica do gerenciamento das operações de busca.
	 */
	private BuscaService buscaService;
	/**
	 * Validador de uma busca, que vai analisar os criterios e os numeros de busca
	 * para ver se essa busca é validad e ser executada.
	 */
	private ValidadorBusca validadorBusca;

	/**
	 * Construtor padrão de BuscaController, que define seu atributo "buscaService"
	 * e cria um novo objeto ValidadorBusca.
	 * 
	 * @param buscaService O buscaService que foi passado pelo Facade para realizar
	 *                     as operações logicas.
	 */
	public BuscaController(BuscaService buscaService) {
		this.buscaService = buscaService;
		this.validadorBusca = new ValidadorBusca();
	}

	/**
	 * Metodo que passa por todas as pessoas cadastradas no sistema e retorna todas
	 * as pessoas que possuem o critério da busca no seu nome, nas suas habilidades
	 * ou no seu cpf.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com os atributos da
	 *                      pessoa.
	 * @return Retorna um array com strings que representam as pessoas que possuem o
	 *         criterio da busca.
	 */
	public String[] buscarPessoas(String criterioBusca) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaPessoas(new BuscaPessoa(criterioBusca));
	}

	/**
	 * Metodo que passa por todas as atividades cadastradas no sistema e retorna
	 * todas as representações textuais das atividades que possuem o critério da
	 * busca no seu nome, no seu codigo ou na sua descrição.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com os atributos da
	 *                      atividade.
	 * @return Retorna um array com as representações textuais das atividades que
	 *         possuem o criterio da busca.
	 */
	public String[] buscarAtividades(String criterioBusca) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaAtividades(new BuscaAtividade(criterioBusca));
	}

	/**
	 * Metodo que passa por todas as tarefas cadastradas no sistema e retorna todas
	 * as representações textuais das tarefas que possuem o critério da busca no seu
	 * nome.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com o nome da
	 *                      tarefa.
	 * @return Retorna um array com as representações textuais das tarefas que
	 *         possuem o criterio da busca.
	 */
	public String[] buscarTarefas(String criterioBusca) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca));
	}

	/**
	 * Metodo que passa por todas as tarefas cadastradas em uma atividade em
	 * especifico e retorna todas as representações textuais das tarefas que possuem
	 * o critério da busca no seu nome.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com o nome da
	 *                      tarefa.
	 * @param idAtividade   Id da atividade cadastrada no sistema onde a busca por
	 *                      tarefas deve acontecer.
	 * @return Retorna um array com as representações textuais das tarefas que
	 *         possuem o criterio da busca.
	 */
	public String[] buscarTarefas(String criterioBusca, String idAtividade) {
		this.validadorBusca.validaCriterio(criterioBusca);
		return this.buscaService.buscaTarefas(new BuscaTarefa(criterioBusca), idAtividade);
	}

	/**
	 * Metodo que recupera uma pessoa e, analisando suas habilidades, lista as
	 * tarefas que são mais conpativeis com ela.
	 * 
	 * @param cpf Cpf da pessoa em que as habilidades serão o criterio para busca de
	 *            tarefas.
	 * @return Retorna um array com as representações textuais das tarefas mais
	 *         compativeis com o criterio da busca.
	 */
	public String[] sugerirTarefas(String cpf) {
		this.validadorBusca.validacaoCpf(cpf);
		return this.buscaService.sugerirTarefas(new SugerirTarefa(), cpf);
	}

	/**
	 * Metodo que acessa o historico de buscas do sistema e retorna os dados sobre a
	 * n busca mais recente.
	 * 
	 * @param nBuscas O numero de qual busca mais recente o metodo vai retornar.
	 * @return Retorna um array de strings onde o primeiro elemento é o tipo de
	 *         busca e os demais elementos são os resultados obtidos pela busca.
	 */
	public String[] buscasMaisRecentes(int nBuscas) {
		this.validadorBusca.validaNBuscas(nBuscas);
		return this.buscaService.buscasMaisRecentes(nBuscas);
	}

	/**
	 * Metodo que acessa o historico de buscas do sistema e retorna os dados sobre
	 * uma busca de indice especifico entre todas as buscas.
	 * 
	 * @param indexBusca O indice da busca que deve ser retornada.
	 * @return Retorna um array de strings onde o primeiro elemento é o tipo de
	 *         busca e os demais elementos são os resultados obtidos pela busca.
	 */
	public String[] exibirHistoricoBusca(int indexBusca) {
		return this.buscaService.exibirHistoricoBusca(indexBusca);
	}
}
