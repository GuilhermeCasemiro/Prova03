package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Contato;
import br.com.contmatic.models.Endereco;
import br.com.contmatic.models.Pessoa;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaTest.
 */
public class PessoaTest {

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Nao deve ter nome nulo.
     */
    @Test
    public void nao_deve_ter_nome_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNotNull(pessoa.getNome());
    }

    @Test
    public void deve_respeitar_o_regex_nome() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertThat(pessoa.getNome(), matchesPattern("^[[ ]|\\p{L}*]+$"));
    }

    /**
     * Nao deve aceitar nome com menos de 2 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_com_menos_de_2_caracteres() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertThat(pessoa.getNome().length(), is(not(1)));
    }

    /**
     * Nao deve aceitar nome com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_com_mais_de_30_caracteres() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertThat(pessoa.getNome().length(), is(not(31)));
    }

    /**
     * Nao deve ter email nulo.
     */
    @Test
    public void nao_deve_ter_email_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNotNull(pessoa.getEmail());
    }

    /**
     * Nao deve ter contato nulo.
     */
    @Test
    public void nao_deve_ter_contato_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNotNull(pessoa.getContatos());
    }

    /**
     * Nao deve conter endereco nulo.
     */
    @Test
    public void nao_deve_conter_endereco_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNotNull(pessoa.getEndereco());
    }

    /**
     * Deve retornar verdadeiro se o endereco nao for nulo.
     */
    @Test
    public void deve_retornar_verdadeiro_se_o_endereco_nao_for_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertTrue(pessoa.acicionarEndereco(endereco));
    }

    /**
     * Deve dar erro se o endereco for nulo.
     */
    @Test(expected = NullPointerException.class)
    public void deve_dar_erro_se_o_endereco_for_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNull(pessoa.acicionarEndereco(null));
    }

    /**
     * Deve retornar verdadeiro se o contato nao for nulo.
     */
    @Test
    public void deve_retornar_verdadeiro_se_o_contato_nao_for_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertTrue(pessoa.acicionarContato(contato));
    }

    /**
     * Deve dar erro se o contato for nulo.
     */
    @Test(expected = NullPointerException.class)
    public void deve_dar_erro_se_o_contato_for_nulo() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertNull(pessoa.acicionarContato(null));
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Pessoa.class, hasValidBeanEquals());
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        assertThat(Pessoa.class, hasValidBeanHashCode());
    }

    /**
     * Deve respeitar os gets e sets.
     */
    @Test
    public void deve_respeitar_os_gets_e_sets() {
        assertThat(Pessoa.class, hasValidGettersAndSetters());
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {
        Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        assertTrue(pessoa.toString().contains("contmatic"));
    }
}
