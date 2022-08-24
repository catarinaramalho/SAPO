package sapo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe JUnit Test Case para testes de sistema das operações realizadas no
 * Facade referentes à Classe Busca.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
class BuscaTest {

	private Facade facade;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
	}

	@Test
	void testExibirPessoas() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		this.facade.cadastrarPessoa("222.222.222-22", "Pedro Matheus", new String[] {});
		assertArrayEquals(new String[] { "Matheus Gaudencio – 111.111.111-11", "Pedro Matheus – 222.222.222-22" },
				this.facade.exibirPessoas("Matheus"));
	}

	@Test
	void testExibirPessoasCriterioNulo() {
		try {
			this.facade.exibirPessoas(null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testExibirPessoasCriterioVazio() {
		try {
			this.facade.exibirPessoas("");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testBuscarAtividade() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		this.facade.cadastrarAtividade("Fazer a Taylor mais rica",
				"Vamos todos nos unir pra fazer a maioral milionaria", "111.111.111-11");

		assertArrayEquals(this.facade.buscarAtividade("Taylor"),
				new String[] { "FZR-0: Fazer a Taylor mais rica\n" + "Responsável: Matheus Gaudencio – 111.111.111-11\n"
						+ "===\n" + "Vamos todos nos unir pra fazer a maioral milionaria\n" + "===\n"
						+ "Tarefas: 0/0" });
	}

	@Test
	void testBuscarTarefasString() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		String id = this.facade.cadastrarAtividade("Fazer a Taylor mais rica",
				"Vamos todos nos unir pra fazer a maioral milionaria", "111.111.111-11");
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });

		assertArrayEquals(this.facade.buscarTarefas("Taylor"), new String[] {});
	}

	@Test
	void testBuscarTarefasStringString() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		String id = this.facade.cadastrarAtividade("Fazer a Taylor mais rica",
				"Vamos todos nos unir pra fazer a maioral milionaria", "111.111.111-11");

		this.facade.cadastrarPessoa("222.222.222-22", "Lucas Leones", new String[] {});
		String id2 = this.facade.cadastrarAtividade("Fazer o jão mais pobre",
				"Vamos todos nos unir pra fazer o pioral mais pobre", "222.222.222-22");
		this.facade.cadastrarTarefa(id2, "Humilhar o pioral no tt", new String[] { "Twitter", "Alfabetização" });

		assertArrayEquals(this.facade.buscarTarefas(id, "pioral"), new String[] {});
	}

	@Test
	void testSugerirTarefas() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] { "java", "ensino" });
		String id = this.facade.cadastrarAtividade("Fazer a Taylor mais rica",
				"Vamos todos nos unir pra fazer a maioral milionaria", "111.111.111-11");

		this.facade.cadastrarTarefa(id, "ajudar no lab", new String[] { "java" });
		this.facade.cadastrarTarefa(id, "Humilhar o pioral no tt", new String[] { "Twitter", "Alfabetização" });
		this.facade.cadastrarTarefa(id, "aula de revisão pra final", new String[] { "ensino" });

		assertArrayEquals(this.facade.sugerirTarefas("111.111.111-11"),
				new String[] {
						"ajudar no lab - FZR-0-0\n" + "- Fazer a Taylor mais rica\n" + "java\n"
								+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "",
						"aula de revisão pra final - FZR-0-2\n" + "- Fazer a Taylor mais rica\n" + "ensino\n"
								+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "" });
	}

	@Test
	void testBuscasMaisRecentes() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		this.facade.cadastrarPessoa("222.222.222-22", "Pedro Matheus", new String[] {});
		this.facade.exibirPessoas("Matheus");
		this.facade.exibirPessoas("pedro");

		assertArrayEquals(this.facade.buscasMaisRecentes(1),
				new String[] { "PESSOA", "Pedro Matheus – 222.222.222-22" });
	}

	@Test
	void testExibirHistóricoBusca() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio", new String[] {});
		this.facade.cadastrarPessoa("222.222.222-22", "Pedro Matheus", new String[] {});
		this.facade.exibirPessoas("Matheus");
		this.facade.exibirPessoas("pedro");

		assertArrayEquals(this.facade.exibirHistóricoBusca(0),
				new String[] { "PESSOA", "Matheus Gaudencio – 111.111.111-11", "Pedro Matheus – 222.222.222-22" });
	}

}