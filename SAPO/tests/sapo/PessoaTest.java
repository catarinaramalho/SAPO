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
	void testCadastrarPessoaHabilidadesNula() {
		try {
			this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", null);
		} catch (NullPointerException npe) {

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
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		assertEquals("Matheus Gaudencio do Rêgo - 111.111.111-11", this.facade.exibirPessoa("111.111.111-11"));
		this.facade.alterarHabilidadesPessoa("111.111.111-11", new String[] {});
	}

	@Test
	void testRemoverPessoa() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.removerPessoa("111.111.111-11");
		try {
			this.facade.exibirPessoa("111.111.111-11");
		} catch (NoSuchElementException nee) {

		}
	}

	@Test
	void testRemoverPessoaInexistente() {
		try {
			this.facade.removerPessoa("111.111.111-11");
		} catch (NoSuchElementException nee) {

		}
	}

	@Test
	void testRemoverPessoaCpfNulo() {
		try {
			this.facade.removerPessoa("111.111.111-11");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testRemoverPessoaCpfVazio() {
		try {
			this.facade.removerPessoa("111.111.111-11");
		} catch (IllegalArgumentException npe) {

		}
	}

	@Test
	void testAdicionarComentarioPessoa() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.cadastrarPessoa("222.222.222-22", "Lívia Maria Rodrigues Sampaio Campos", new String[] {});
		this.facade.adicionarComentarioPessoa("111.111.111-11", "É uma boa pessoa, só não tem muito juízo",
				"222.222.222-22");
		assertEquals(
				"Matheus Gaudencio do Rêgo – 111.111.111-11\n" + "Comentários:\n"
						+ "-- É uma boa pessoa, só não tem muito juízo (Lívia Maria Rodrigues Sampaio Campos)",
				this.facade.listarComentariosPessoa("111.111.111-11"));
	}
	
	@Test
	void testAdicionarComentarioPessoaInexistente() {
		try {
			this.facade.adicionarComentarioPessoa("111.111.111-11", "É uma boa pessoa, só não tem muito juízo",
					"222.222.222-22");
		} catch (NoSuchElementException nsee) {
			
		}
	}

	void testListarComentariosPessoa() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.cadastrarPessoa("222.222.222-22", "Lívia Maria Rodrigues Sampaio Campos", new String[] {});
		this.facade.adicionarComentarioPessoa("111.111.111-11", "É uma boa pessoa, só não tem muito juízo",
				"222.222.222-22");
		assertEquals(
				"Matheus Gaudencio do Rêgo – 111.111.111-11\n" + "Comentários:\n"
						+ "-- É uma boa pessoa, só não tem muito juízo (Lívia Maria Rodrigues Sampaio Campos)",
				this.facade.listarComentariosPessoa("111.111.111-11"));
	}
}
