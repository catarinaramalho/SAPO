package sapo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		fail("Not yet implemented");
	}

	@Test
	void testBuscarTarefasString() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscarTarefasStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testSugerirTarefas() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscasMaisRecentes() {
		fail("Not yet implemented");
	}

	@Test
	void testExibirHistóricoBusca() {
		fail("Not yet implemented");
	}

}