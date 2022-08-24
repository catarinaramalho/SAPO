package sapo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TarefaGerencialTest {

	private Facade facade;
	private String idAtividade;
	private String idTarefa;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo",
				new String[] { "Desenvolvimento web", "Professor", "Programador" });
		idAtividade = this.facade.cadastrarAtividade("Dar stream na maioral", "Ouvir Taylors bersion de 1989",
				"111.111.111-11");
		idTarefa = this.facade.cadastrarTarefa(idAtividade, "Divulgar a maioral no tt",
				new String[] { "Spotify", "Audição" });
	}

	@Test
	void testCadastrarTarefaGerencial() {
		String idTarefaGerencial = this.facade.cadastrarTarefaGerencial(idAtividade, "Gerenciar streams na taylor",
				new String[] {}, new String[] { idTarefa });
		assertEquals(idTarefaGerencial, "DRS-0-1");
	}

	@Test
	void testAdicionarNaTarefaGerencial() {
		String idTarefaGerencial = this.facade.cadastrarTarefaGerencial(idAtividade, "Gerenciar streams na taylor",
				new String[] {}, new String[] {});
		this.facade.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);

		assertEquals(this.facade.exibirTarefa(idTarefaGerencial),
				"Gerenciar streams na taylor - DRS-0-1\n" + "- Dar stream na maioral\n" + "gestão\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "\n" + "===\n" + "Tarefas:\n"
						+ "Divulgar a maioral no tt - DRS-0-0");
	}

	@Test
	void testRemoverDaTarefaGerencial() {
		String idTarefaGerencial = this.facade.cadastrarTarefaGerencial(idAtividade, "Gerenciar streams na taylor",
				new String[] {}, new String[] {});

		this.facade.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);
		this.facade.removerDaTarefaGerencial(idTarefaGerencial, idTarefa);

		assertEquals(this.facade.exibirTarefa(idTarefaGerencial),
				"Gerenciar streams na taylor - DRS-0-1\n" + "- Dar stream na maioral\n" + "gestão\n"
						+ "(0 hora(s) executada(s))\n" + "===\n" + "Equipe:\n" + "\n" + "===\n" + "Tarefas:\n" + "");
	}

	@Test
	void testContarTodasTarefasNaTarefaGerencial() {
		String idTarefaGerencial = this.facade.cadastrarTarefaGerencial(idAtividade, "Gerenciar streams na taylor",
				new String[] {}, new String[] {});

		this.facade.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);
		this.facade.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);

		
		assertEquals(this.facade.contarTodasTarefasNaTarefaGerencial(idTarefaGerencial), 2);
	}

}