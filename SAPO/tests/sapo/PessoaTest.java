package sapo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe JUnit Test Case para testes de sistema das operações realizadas no
 * Facade referentes à Classe Pessoa.
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
class PessoaTest {

	private Facade facade;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
	}

	@Test
	void testCadastrarPessoa() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo",
				new String[] { "Desenvolvimento web", "Professor", "Programador" });
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11\n- Desenvolvimento web\n- Professor\n- Programador",
				this.facade.exibirPessoa("111.111.111-11"));
	}

	@Test
	void testCadastrarPessoaCpfJaExistente() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		try {
			this.facade.cadastrarPessoa("111.111.111-11", "Lívia Maria Rodrigues Sampaio Campos", new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarPessoaCpfNulo() {
		try {
			this.facade.cadastrarPessoa(null, "Matheus Gaudencio do Rêgo", new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarPessoaCpfVazio() {
		try {
			this.facade.cadastrarPessoa("", "Matheus Gaudencio do Rêgo", new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarPessoaNomeNulo() {
		try {
			this.facade.cadastrarPessoa("111.111.111-11", null, new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarPessoaNomeVazio() {
		try {
			this.facade.cadastrarPessoa("111.111.111-11", "", new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testExibirPessoaHabilidadesOrdenadas() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo",
				new String[] { "Desenvolvimento web", "Professor", "Programador" });
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11\n- Desenvolvimento web\n- Professor\n- Programador",
				this.facade.exibirPessoa("111.111.111-11"));
	}

	@Test
	void testExibirPessoaHabilidadesDesordenadas() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo",
				new String[] { "Professor", "Programador", "Desenvolvimento web" });
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11\n- Desenvolvimento web\n- Professor\n- Programador",
				this.facade.exibirPessoa("111.111.111-11"));
	}

	@Test
	void testExibirPessoaSemHabilidades() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11", this.facade.exibirPessoa("111.111.111-11"));
	}

	@Test
	void testExibirPessoaCpfNulo() {
		try {
			this.facade.exibirPessoa(null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testExibirPessoaCpfVazio() {
		try {
			this.facade.exibirPessoa("");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testExibirPessoaCpfInexistente() {
		try {
			this.facade.exibirPessoa("222.222.222-22");
		} catch (NoSuchElementException nsee) {

		}
	}

	@Test
	void testAlterarNomePessoa() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11", this.facade.exibirPessoa("111.111.111-11"));
		this.facade.alterarNomePessoa("111.111.111-11", "Lívia Maria Rodrigues Sampaio Campos");
		assertEquals("Lívia Maria Rodrigues Sampaio Campos - 111.111.111-11",
				this.facade.exibirPessoa("111.111.111-11"));
	}

	@Test
	void testAlterarNomePessoaCpfNulo() {
		try {
			this.facade.alterarNomePessoa(null, "Lívia Maria Rodrigues Sampaio Campos");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAlterarNomePessoaCpfVazio() {
		try {
			this.facade.alterarNomePessoa("", "Lívia Maria Rodrigues Sampaio Campos");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAlterarNomePessoaCpfInexistente() {
		try {
			this.facade.alterarNomePessoa("333.333.333-33", "Lívia Maria Rodrigues Sampaio Campos");
		} catch (NoSuchElementException nsee) {

		}
	}

	@Test
	void testAlterarNomePessoaNomeNulo() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		try {
			this.facade.alterarNomePessoa("111.111.111-11", null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAlterarNomePessoaNomeVazio() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		try {
			this.facade.alterarNomePessoa("111.111.111-11", "");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAlterarHabilidadesPessoa() {
		fail("Not yet implemented");
		// this.facade.alterarHabilidadesPessoa(cpf, novasHabilidades);
	}

	@Test
	void testRemoverPessoa() {
		fail("Not yet implemented");
		// this.facade.removerPessoa(cpf);
	}

	@Test
	void testAdicionarComentarioPessoa() {
		fail("Not yet implemented");
		// this.facade.adicionarComentarioPessoa(cpf, comentario, autorCpf);
	}

	void testListarComentariosPessoa() {
		fail("Not yet implemented");
		// this.facade.listarComentariosPessoa(cpf);
	}
}
