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

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.PessoaJuridica;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaJuridicaTest.
 */
public class PessoaJuridicaTest {

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Nao deve aceita cnpj nulo.
     */
    @Test
    public void nao_deve_aceita_cnpj_nulo() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertNotNull(pJuridica.getCnpj());
    }

    /**
     * Nao deve aceitar cnpj com mais de 14 caracteres.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_mais_de_14_caracteres() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertThat(pJuridica.getCnpj().length(), is(not(15)));
    }

    /**
     * Nao deve aceitar cnpj com menos de 14 caracteres.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_menos_de_14_caracteres() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertThat(pJuridica.getCnpj().length(), is(not(13)));
    }

    /**
     * So deve aceitar cnpj com 14 caracteres.
     */
    @Test
    public void so_deve_aceitar_cnpj_com_14_caracteres() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertTrue(pJuridica.getCnpj().length() == 14);
    }

    /**
     * Nao deve aceitar razao social com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_razao_social_com_mais_de_30_caracteres() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertThat(pJuridica.getRazaoSocial().length(), is(not(31)));
    }

    /**
     * Nao deve aceitar razao social com menos de 10 caracteres.
     */
    @Test
    public void nao_deve_aceitar_razao_social_com_menos_de_10_caracteres() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        assertThat(pJuridica.getRazaoSocial().length(), is(not(9)));
    }

    /**
     * Deve aceitar as regras do equals.
     */
    @Test
    public void deve_aceitar_as_regras_do_equals() {
        assertThat(PessoaJuridica.class, hasValidBeanEquals());
    }

    /**
     * Deve aceitar as regras do hashcode.
     */
    @Test
    public void deve_aceitar_as_regras_do_hashcode() {
        assertThat(PessoaJuridica.class, hasValidBeanHashCode());
    }

    /**
     * Deve aceitar as regras do contrutor.
     */
    @Test
    public void deve_aceitar_as_regras_do_contrutor() {
        assertThat(PessoaJuridica.class, hasValidBeanConstructor());
    }

    /**
     * Deve aceitar as regras do to string.
     */
    @Test
    public void deve_aceitar_as_regras_do_toString() {
        PessoaJuridica pJuridica = Fixture.from(PessoaJuridica.class).gimme("PJuridicaFixture");
        pJuridica.setCnpj("34755982000143");
        assertTrue(pJuridica.toString().contains("34755982000143"));
    }
}
