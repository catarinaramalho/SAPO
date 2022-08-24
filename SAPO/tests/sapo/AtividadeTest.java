package sapo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {

	private Facade facade;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo",
				new String[] { "Desenvolvimento web", "Professor", "Programador" });
	}

	@Test
	void testCadastrarAtividade() {
		assertEquals(this.facade.cadastrarAtividade("Nome Atividade 1", "Descricao 1", "111.111.111-11"), "NMT-0");
		assertEquals(this.facade.cadastrarAtividade("Outro nome Atividade 2", "Descricao 2", "111.111.111-11"), "TRN-1");
	}
	
	@Test
	void testCadastrarAtividade0Consoantes() {
		assertEquals(this.facade.cadastrarAtividade("Aeiou", "Descricao", "111.111.111-11"), "XXX-0");
	}
	
	@Test
	void testCadastrarAtividade1Consoantes() {
		assertEquals(this.facade.cadastrarAtividade("BAeiou", "Descricao", "111.111.111-11"), "BXX-0");
	}
	
	@Test
	void testCadastrarAtividade2Consoantes() {
		assertEquals(this.facade.cadastrarAtividade("BAeiouc", "Descricao", "111.111.111-11"), "BCX-0");
	}

	@Test
	void testCadastrarAtividadeNomeNulo() {
		try {
			this.facade.cadastrarAtividade(null, "Descricao", "111.111.111-11");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAtividadeNomeVazio() {
		try {
			this.facade.cadastrarAtividade("", "Descricao", "111.111.111-11");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAtividadeDescricaoNula() {
		try {
			this.facade.cadastrarAtividade("Nome", null, "111.111.111-11");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAtividadeDescricaoVazio() {
		try {
			this.facade.cadastrarAtividade("Nome", "", "111.111.111-11");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAtividadeCpfNulo() {
		try {
			this.facade.cadastrarAtividade("Nome", "Descricao", null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAtividadeCpfVazio() {
		try {
			this.facade.cadastrarAtividade("Nome", "Descricao", "");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAtividadeCpfInexistente() {
		try {
			this.facade.cadastrarAtividade("Nome", "Descricao", "222.222.222-22");
		} catch (NoSuchElementException nsee) {

		}
	}

	@Test
	void testEncerrarAtividadeEncerrada() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.encerrarAtividade("NMT-0");
		try {
			this.facade.encerrarAtividade("NMT-0");
		} catch (IllegalStateException ise) {

		}
	}

	@Test
	void testEncerrarAtividadeTarefasPendentes() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.cadastrarTarefa("NMT-0", "Tarefa", new String[] {});
		try {
			this.facade.encerrarAtividade("NMT-0");
		} catch (IllegalStateException ise) {

		}
	}

	@Test
	void testDesativarAtividadeDesativada() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.desativarAtividade("NMT-0");
		try {
			this.facade.desativarAtividade("NMT-0");
		} catch (IllegalStateException ise) {

		}
	}

	@Test
	void testDesativarAtividadeTarefasPendentes() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.cadastrarTarefa("NMT-0", "Tarefa", new String[] {});
		try {
			this.facade.desativarAtividade("NMT-0");
		} catch (IllegalStateException ise) {

		}
	}

	@Test
	void testReabrirAtividadeAberta() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.reabrirAtividade("NMT-0");
		} catch (IllegalStateException ise) {

		}
	}
	
	@Test
	void testReabrirAtividade() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.encerrarAtividade("NMT-0");
		this.facade.reabrirAtividade("NMT-0");
	}

	@Test
	void testExibirAtividadeSemTarefa() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		assertEquals("NMT-0: Nome Atividade\n" + "Responsável: Matheus Gaudencio do Rêgo – 111.111.111-11\n" + "===\n"
				+ "Descricao\n" + "===\n" + "Tarefas: 0/0", this.facade.exibirAtividade("NMT-0"));
	}

	@Test
	void testExibirAtividade() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.cadastrarTarefa("NMT-0", "Tarefa A", new String[] {});
		this.facade.cadastrarTarefa("NMT-0", "Tarefa B", new String[] {});
		assertEquals("NMT-0: Nome Atividade\n" + "Responsável: Matheus Gaudencio do Rêgo – 111.111.111-11\n" + "===\n"
				+ "Descricao\n" + "===\n" + "Tarefas: 0/2\n" + "- Tarefa A - NMT-0-0\n" + "- Tarefa B - NMT-0-1",
				this.facade.exibirAtividade("NMT-0"));
	}

	@Test
	void testAlterarDescricaoAtividade() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.alterarDescricaoAtividade("NMT-0", "Nova Descricao");
		assertEquals("NMT-0: Nome Atividade\n" + "Responsável: Matheus Gaudencio do Rêgo – 111.111.111-11\n" + "===\n"
				+ "Nova Descricao\n" + "===\n" + "Tarefas: 0/0", this.facade.exibirAtividade("NMT-0"));
	}

	@Test
	void testAlterarDescricaoAtividadeNula() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.alterarDescricaoAtividade("NMT-0", null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAlterarDescricaoAtividadeVazia() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.alterarDescricaoAtividade("NMT-0", "");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAlterarResponsavelAtividade() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		this.facade.cadastrarPessoa("222.222.222-22", "Lívia Maria", new String[] {});
		this.facade.alterarResponsavelAtividade("NMT-0", "222.222.222-22");
		assertEquals("NMT-0: Nome Atividade\n" + "Responsável: Lívia Maria – 222.222.222-22\n" + "===\n" + "Descricao\n"
				+ "===\n" + "Tarefas: 0/0", this.facade.exibirAtividade("NMT-0"));
	}
	
	@Test
	void testAlterarResponsavelAtividadeNulo() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.alterarResponsavelAtividade("NMT-0", null);
		} catch (NullPointerException npe) {
			
		}
	}
	
	@Test
	void testAlterarResponsavelAtividadeVazio() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.alterarResponsavelAtividade("NMT-0", "");
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testAlterarResponsavelAtividadeInexistente() {
		this.facade.cadastrarAtividade("Nome Atividade", "Descricao", "111.111.111-11");
		try {
			this.facade.alterarResponsavelAtividade("NMT-0", "222.222.222-22");
		} catch (NoSuchElementException nsee) {
			
		}
	}

}
