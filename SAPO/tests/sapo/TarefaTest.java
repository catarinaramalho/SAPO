package sapo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TarefaTest {

	private Facade facade;
	private String id;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		this.facade.cadastrarPessoa("111.111.111-11", "Lucas Leones",
				new String[] { "Fã de taylor", "Bom gosto pra homens", "Mono jungle" });
		this.id = this.facade.cadastrarAtividade("Deixar a Taylor bem rica",
				"Vamos fazer varias coisas pra deixar a Taylor Swift mais rica", "111.111.111-11");
	}

	@Test
	void testCadastrarTarefa() {
		assertEquals(this.facade.cadastrarTarefa(id, "Dar stream na maioral", new String[] { "Spotify", "Audição" }),
				"DXR-0-0");
	}

	@Test
	void testAlterarNomeTarefa() {

		this.facade.cadastrarTarefa(id, "Dar stream na maioral", new String[] { "Spotify", "Audição" });
		this.facade.alterarNomeTarefa("DXR-0-0", "Divulgar a maioral no tt");

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}

	@Test
	void testAlterarHabilidadesTarefa() {
		this.facade.cadastrarTarefa(id, "Dar stream na maioral", new String[] { "Spotify", "Audição" });
		this.facade.alterarHabilidadesTarefa("DXR-0-0", new String[] { "Twitter", "Dedos" });

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Dar stream na maioral - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Twitter, Dedos\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}

	@Test
	void testAdicionarHorasTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.adicionarHorasTarefa("DXR-0-0", 1);

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(1 hora(s) executada(s))\n" + "===\n" + "Equipe:\n");
	}

	@Test
	void testRemoverHorasTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.adicionarHorasTarefa("DXR-0-0", 1);
		this.facade.removerHorasTarefa("DXR-0-0", 1);

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}

	@Test
	void testRemoverHorasNegativasTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		try {
			this.facade.removerHorasTarefa("DXR-0-0", 1);
		} catch (IllegalArgumentException iae) {
		}

	}

	@Test
	void testConcluirTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.concluirTarefa("DXR-0-0");

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}

	@Test
	void testRemoverTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.removerTarefa("DXR-0-0");

		try {
			this.facade.exibirTarefa("DXR-0-0");
		} catch (Exception e) {
		}
	}

	@Test
	void testExibirTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}

	@Test
	void testAssociarPessoaTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.associarPessoaTarefa("111.111.111-11", "DXR-0-0");

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "Lucas Leones – 111.111.111-11");
	}

	@Test
	void testRemoverPessoaTarefa() {
		this.facade.cadastrarTarefa(id, "Divulgar a maioral no tt", new String[] { "Spotify", "Audição" });
		this.facade.associarPessoaTarefa("111.111.111-11", "DXR-0-0");
		this.facade.removerPessoaTarefa("111.111.111-11", "DXR-0-0");

		assertEquals(this.facade.exibirTarefa("DXR-0-0"),
				"Divulgar a maioral no tt - DXR-0-0\n" + "- Deixar a Taylor bem rica\n" + "Spotify, Audição\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "");
	}
}