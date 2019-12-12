package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Cidade;

public class CidadeRandom {

    public static Cidade gerarCidade() {
        EasyRandomParameters parametroCidade = new EasyRandomParameters();

        parametroCidade.charset(forName("UTF-8"));
        parametroCidade.stringLengthRange(5, 100);
        parametroCidade.randomizationDepth(1);
        parametroCidade.ignoreRandomizationErrors(true);
        parametroCidade.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroCidade);
        Cidade cidade = easyRandom.nextObject(Cidade.class);
        return cidade;
    }

}
