package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.matchesRegex;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Funcionario;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncionarioTest.
 */
public class FuncionarioTest {

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Gerar data.
     */
    public void gerarData() {
        registerValueGenerator(new ValueGenerator<DateTime>() {
            public DateTime generate() {
                return new DateTime(new Random().nextLong()).withMillisOfSecond(0);
            }
        }, DateTime.class);
    }

    /**
     * Nao deve aceitar salario nulo.
     */
    @Test
    public void nao_deve_aceitar_salario_nulo() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertNotNull(funcionario.getSalario());
    }

    /**
     * Nao deve conter salarios acima de 9999.
     */
    @Test
    public void nao_deve_conter_salarios_acima_de_9999() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getSalario(), is(not(greaterThan((short) 9999))));
    }

    /**
     * Nao deve conter salarios abaixo de 1000.
     */
    @Test
    public void nao_deve_conter_salarios_abaixo_de_1000() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getSalario(), is(not(lessThan((short) 1000))));
    }

    /**
     * Pode aceitar comissao nula.
     */
    @Test
    public void pode_aceitar_comissao_nula() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        funcionario.setComissao(null);
        assertNull(funcionario.getComissao());
    }

    /**
     * Nao deve conter comissoes acima de 500.
     */
    @Test
    public void nao_deve_conter_comissoes_acima_de_500() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getComissao(), is(not(greaterThan((short) 500))));
    }

    /**
     * Nao deve conter comissoes abaixo de 0.
     */
    @Test
    public void nao_deve_conter_comissoes_abaixo_de_0() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getComissao(), is(not(lessThan((short) 0))));
    }
    
    @Test
    public void deve_respeitar_as_regras_do_regex() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getDepartamento(), matchesRegex("^([a-zA-Z])+$"));
    }

    /**
     * Nao deve aceitar departamento nulo.
     */
    @Test
    public void nao_deve_aceitar_departamento_nulo() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertNotNull(funcionario.getDepartamento());
    }

    /**
     * Nao deve conter departamento com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_conter_departamento_com_mais_de_30_caracteres() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getDepartamento().length(), is(not(greaterThan(30))));
    }

    /**
     * Nao deve conter departamento com menos de 1 caracteres.
     */
    @Test
    public void nao_deve_conter_departamento_com_menos_de_1_caracteres() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertThat(funcionario.getDepartamento().length(), is(not(lessThan(10))));
    }

    /**
     * Deve respeitar o construtor.
     */
    @Test
    public void deve_respeitar_o_construtor() {
        assertThat(Funcionario.class, hasValidBeanConstructor());
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        gerarData();
        assertThat(Funcionario.class, hasValidBeanEquals());
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        gerarData();
        assertThat(Funcionario.class, hasValidBeanHashCode());
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {
        Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
        assertTrue(funcionario.toString().contains("Desenvolvimento"));
    }
}
