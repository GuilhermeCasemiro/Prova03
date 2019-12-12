package br.com.contmatic.testes;

import static br.com.contmatic.easy.random.FuncionarioRandom.gerarFuncionario;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.models.Funcionario;

/**
 * The Class FuncionarioTest.
 */
public class FuncionarioTest {

    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /** The funcionario. */
    private Funcionario funcionario = gerarFuncionario();

    /** The funcionario 2. */
    private Funcionario funcionario2 = new Funcionario();

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
     * Nao deve conter salarios acima de 9999.
     */
    @Test
    public void nao_deve_conter_salarios_acima_de_8500() {
        funcionario.setSalario(9000L);
        assertFalse(isValid(funcionario, "O salário não pode ser maior que R$: 8500.00."));
    }

    /**
     * Nao deve conter salarios abaixo de 1000.
     */
    @Test
    public void nao_deve_conter_salarios_abaixo_de_1000() {
        funcionario.setSalario(900L);
        assertFalse(isValid(funcionario, "O salário não pode ser menor que a base de R$: 1000.00."));
    }

    /**
     * Nao deve conter comissoes acima de 500.
     */
    @Test
    public void nao_deve_conter_comissoes_acima_de_500() {
        funcionario.setComissao(1100L);
        assertFalse(isValid(funcionario, "A comissão não pode ser maior que R$: 500.00."));
    }

    /**
     * Nao deve conter comissoes abaixo de 0.
     */
    @Test
    public void nao_deve_conter_comissoes_abaixo_de_0() {
        funcionario.setComissao(-1L);
        assertFalse(isValid(funcionario, "A comissão não pode ser menor que R$: 0.00."));
    }

    /**
     * Nao deve aceitar departamento com caracteres especiais.
     */
    @Test
    public void nao_deve_aceitar_departamento_com_caracteres_especiais() {
        funcionario.setDepartamento("D&senvolviment#");
        assertFalse(isValid(funcionario, "A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
    }

    /**
     * Nao deve aceitar departamento nulo.
     */
    @Test
    public void nao_deve_aceitar_departamento_nulo() {
        funcionario.setDepartamento(null);
        assertFalse(isValid(funcionario, "Ter um departamento é obrigatório."));
    }

    /**
     * Nao deve aceitar departamento vazio.
     */
    @Test
    public void nao_deve_aceitar_departamento_vazio() {
        funcionario.setDepartamento(EMPTY);
        assertFalse(isValid(funcionario, "Ter um departamento é obrigatório."));
    }

    /**
     * Nao deve aceitar departamento com numeros.
     */
    @Test
    public void nao_deve_aceitar_departamento_com_numeros() {
        funcionario.setDepartamento(random(20, true, true));
        assertFalse(isValid(funcionario, "A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
    }

    /**
     * Nao deve aceitar departamento com acentos.
     */
    @Test
    public void nao_deve_aceitar_departamento_com_acentos() {
        funcionario.setDepartamento("Comunicação");
        assertFalse(isValid(funcionario, "A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
    }

    /**
     * Deve aceitar um departamento valido.
     */
    @Test
    public void deve_aceitar_um_departamento_valido() {
        funcionario.setDepartamento("Desenvolvimento");
        assertTrue(isValid(funcionario, "Desenvolvimento"));
    }

    /**
     * Nao deve conter departamento com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_conter_departamento_com_mais_de_30_caracteres() {
        funcionario.setDepartamento(random(35));
        assertFalse(isValid(funcionario, "O departamento não pode conter menos de 5 e mais de 30 caracteres."));
    }

    /**
     * Nao deve conter departamento com menos de 5 caracteres.
     */
    @Test
    public void nao_deve_conter_departamento_com_menos_de_5_caracteres() {
        funcionario.setDepartamento(random(4, true, false));
        assertFalse(isValid(funcionario, "O departamento não pode conter menos de 5 e mais de 30 caracteres."));
    }

    /**
     * Deve aceitar departamento com ate 30 caracteres.
     */
    @Test
    public void deve_aceitar_departamento_com_ate_30_caracteres() {
        funcionario.setDepartamento(random(25, true, false));
        assertTrue(isValid(funcionario, funcionario.getDepartamento()));
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
        assertThat(Funcionario.class, hasValidBeanEqualsFor());
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        gerarData();
        assertThat(Funcionario.class, hasValidBeanHashCodeFor());
    }

    /**
     * Deve retornar falso se o cpf dos funcionarios forem diferentes.
     */
    @Test
    public void deve_retornar_falso_se_o_cpf_dos_funcionarios_forem_diferentes() {
        funcionario.setCpf("756.953.220-14");
        funcionario2.setCpf("781.040.350-88");
        assertFalse(funcionario.equals(funcionario2));
    }

    /**
     * Deve retornar verdadeiro se os funcionarios forem iguais.
     */
    @Test
    public void deve_retornar_verdadeiro_se_os_funcionarios_forem_iguais() {
        funcionario.setCpf("070.678.110-40");
        funcionario2.setCpf("070.678.110-40");
        assertTrue(funcionario.equals(funcionario2));
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {
        funcionario.setDepartamento("Desenvolvimento");
        assertTrue(funcionario.toString().contains("Desenvolvimento"));
    }

    /**
     * Checks if is valid.
     *
     * @param funcionario the funcionario
     * @param mensagem the mensagem
     * @return true, if is valid
     */
    public boolean isValid(Funcionario funcionario, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }
}
