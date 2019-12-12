package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Funcionario;

public class FuncionarioRandom {
    
    public static Funcionario gerarFuncionario() {
        EasyRandomParameters parametroFuncionario = new EasyRandomParameters();
        
        parametroFuncionario.charset(forName("UTF-8"));
        parametroFuncionario.stringLengthRange(5, 30);
        parametroFuncionario.ignoreRandomizationErrors(true);
        parametroFuncionario.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroFuncionario);
        Funcionario funcionario = easyRandom.nextObject(Funcionario.class);
        return funcionario;
    }
}
