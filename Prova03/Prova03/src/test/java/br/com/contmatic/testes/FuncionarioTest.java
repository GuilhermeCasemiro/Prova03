package br.com.contmatic.testes;

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
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Funcionario;
import br.com.six2six.fixturefactory.Fixture;

/**
 * The Class FuncionarioTest.
 */
public class FuncionarioTest {

	/** The validator. */
	private Validator validator;

	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");

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
		funcionario.setSalario(null);
		assertFalse(isValid(funcionario, "Salário é obrigatório."));
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
	 * Pode aceitar comissao nula.
	 */
	@Test
	public void pode_aceitar_comissao_0() {
		funcionario.setComissao(0L);
		assertTrue(isValid(funcionario, funcionario.getComissao().toString()));
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

	@Test
	public void nao_deve_aceitar_departamento_com_caracteres_especiais() {
		funcionario.setDepartamento("D&senvolviment#");
		assertFalse(isValid(funcionario,
				"A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
	}

	/**
	 * Nao deve aceitar departamento nulo.
	 */
	@Test
	public void nao_deve_aceitar_departamento_nulo() {
		funcionario.setDepartamento(null);
		assertFalse(isValid(funcionario, "Ter um departamento é obrigatório."));
	}

	@Test
	public void nao_deve_aceitar_departamento_vazio() {
		funcionario.setDepartamento(EMPTY);
		assertFalse(isValid(funcionario, "Ter um departamento é obrigatório."));
	}

	@Test
	public void nao_deve_aceitar_departamento_com_numeros() {
		funcionario.setDepartamento(random(20, true, true));
		assertFalse(isValid(funcionario,
				"A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
	}

	@Test
	public void nao_deve_aceitar_departamento_com_acentos() {
		funcionario.setDepartamento("Comunicação");
		assertFalse(isValid(funcionario,
				"A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais."));
	}

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

	@Test
	public void deve_retornar_falso_se_o_cpf_dos_funcionarios_forem_diferentes() {
		funcionario.setCpf("65847784015");
		funcionario.setDepartamento("Desenvolvimento");
		Funcionario funcionario2 = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
		funcionario2.setCpf("28985012045");
		funcionario2.setDepartamento("Insfraestrutura");
		assertFalse(funcionario.equals(funcionario2));

	}

	@Test
	public void deve_retornar_falso_se_os_funcionarios_forem_iguais() {
		funcionario.setCpf("28512130083");
		funcionario.setDepartamento("Insfraestrutura");
		Funcionario funcionario2 = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
		funcionario2.setCpf("28985012045");
		funcionario2.setDepartamento("Insfraestrutura");
		assertTrue(funcionario.equals(funcionario2));

	}

	/**
	 * Deve respeitar o to string.
	 */
	@Test
	public void deve_respeitar_o_toString() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("FuncionarioFixture");
		assertTrue(funcionario.toString().contains("Desenvolvimento"));
	}

	public boolean isValid(Funcionario funcionario, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;

		return valido;
	}
}
