package sapo;

import static org.junit.jupiter.api.Assertions.*;

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
		assertEquals(this.facade.cadastrarAtividade("Dar stream na maioral", "Ouvir Taylors bersion de 1989", "111.111.111-11"), "DRS-0");
	}

	@Test
	void testEncerrarAtividade() {
		this.facade.cadastrarAtividade("Dar stream na maioral", "Ouvir Taylors bersion de 1989", "111.111.111-11");
		//TODO
		
	}

	@Test
	void testDesativarAtividade() {
		fail("Not yet implemented");
	}

	@Test
	void testReabrirAtividade() {
		fail("Not yet implemented");
	}

	@Test
	void testExibirAtividade() {
		fail("Not yet implemented");
	}

	@Test
	void testAlterarDescricaoAtividade() {
		fail("Not yet implemented");
	}

	@Test
	void testAlterarResponsavelAtividade() {
		fail("Not yet implemented");
	}

}
