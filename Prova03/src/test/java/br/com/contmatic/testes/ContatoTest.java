package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.Telefone;
import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Contato;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class ContatoTest.
 */
public class ContatoTest {

    /**
     * Sets the up.
     */
    @BeforeClass()
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertNotNull(contato.getTelefone());
    }

    /**
     * Nao deve aceitar telefone fixo com mais de 8 digitos.
     */
    @Test
    public void nao_deve_aceitar_telefone_fixo_com_mais_de_8_digitos() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertThat(contato.getTelefone().getTamanho(), is(not((9))));
    }

    /**
     * Nao deve aceitar telefone celular com mais de 9 digitos.
     */
    @Test
    public void nao_deve_aceitar_telefone_celular_com_mais_de_9_digitos() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoCelularFixture");
        assertThat(contato.getTelefone().getTamanho(), is(not((10))));
    }

    /**
     * Nao deve conter ddd nulo.
     */
    @Test
    public void nao_deve_conter_ddd_nulo() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertNotNull(contato.getDdd());
    }

    /**
     * Nao deve conter ddd com mais de 2 caracteres.
     */
    @Test
    public void nao_deve_conter_ddd_com_mais_de_2_caracteres() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertThat(contato.getDdd().getDDD().length(), is(not((3))));
    }

    /**
     * Nao deve conter ddd com menos de 2 caracteres.
     */
    @Test
    public void nao_deve_conter_ddd_com_menos_de_2_caracteres() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertThat(contato.getDdd().getDDD().length(), is(not((1))));
    }

    /**
     * Deve conter ddd igual a 2 caracteres.
     */
    @Test
    public void deve_conter_ddd_igual_a_2_caracteres() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertTrue(contato.getDdd().getDDD().length() == 2);
    }

    /**
     * Deve respeitar o construtor.
     */
    @Test
    public void deve_respeitar_o_construtor() {
        assertThat(Contato.class, hasValidBeanConstructor());
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Contato.class, hasValidBeanEquals());
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        assertThat(Contato.class, hasValidBeanHashCode());
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {
        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        contato.setTelefone(Telefone.FIXO);
        assertTrue(contato.toString().contains("FIXO"));
    }

}
