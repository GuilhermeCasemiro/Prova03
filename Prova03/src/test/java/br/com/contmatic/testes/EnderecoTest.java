package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.DDD;
import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Endereco;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Nao deve aceitar bairro vazio.
     */
    @Test
    public void nao_deve_aceitar_bairro_vazio() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertNotNull(endereco.getBairro());
    }

    /**
     * Deve aceitar as regras do regex bairro.
     */
    @Test
    public void deve_aceitar_as_regras_do_regex_bairro() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getBairro(), matchesPattern("^([a-zA-z0-9])*$"));
    }

    /**
     * Nao deve conter bairro com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_conter_bairro_com_mais_de_30_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getBairro().length(), is(not(greaterThan((31)))));
    }

    /**
     * Nao deve conter bairro com menos de 5 caracteres.
     */
    @Test
    public void nao_deve_conter_bairro_com_menos_de_5_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getBairro().length(), is(not(lessThan((4)))));
    }

    /**
     * Nao deve conter uf nulo.
     */
    @Test
    public void nao_deve_conter_uf_nulo() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getUf(), notNullValue());
    }

    /**
     * Nao deve conter uf com mais de 2 caracteres.
     */
    @Test
    public void nao_deve_conter_uf_com_mais_de_2_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getUf().getDDD().length(), is(not(greaterThan(3))));

    }

    /**
     * Nao deve conter uf com menos de 2 caracteres.
     */
    @Test
    public void nao_deve_conter_uf_com_menos_de_2_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getUf().getDDD().length(), is(not(lessThan((1)))));

    }

    /**
     * Nao deve aceitar cep nulo.
     */
    @Test
    public void nao_deve_aceitar_cep_nulo() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertNotNull(endereco.getCep());
    }

    /**
     * Deve aceitar as regras do regex cep.
     */
    @Test
    public void deve_aceitar_as_regras_do_regex_cep() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getCep(), matchesPattern("[0-9]{5}-[\\d]{3}"));
    }

    /**
     * Nao deve conter cep com mais de 9 caracteres.
     */
    @Test
    public void nao_deve_conter_cep_com_mais_de_9_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getCep().length(), is(not(greaterThan(10))));
    }

    /**
     * Pode aceitar complemento vazio.
     */
    @Test
    public void pode_aceitar_complemento_vazio() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        endereco.setComplemento(null);
        assertNull(endereco.getComplemento());
    }

    /**
     * Nao deve aceitar complemento com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_complemento_com_mais_de_30_caracteres() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertThat(endereco.getComplemento().length(), is(not(greaterThan(31))));
    }

    /**
     * Deve respeitar o construtor.
     */
    @Test
    public void deve_respeitar_o_construtor() {
        assertThat(Endereco.class, hasValidBeanConstructor());
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Endereco.class, hasValidBeanEquals());
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        assertThat(Endereco.class, hasValidBeanHashCode());
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        endereco.setUf(DDD.SAO_PAULO);
        assertTrue(endereco.toString().contains("SAO_PAULO"));
    }

}
