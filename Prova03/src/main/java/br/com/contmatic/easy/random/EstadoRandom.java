package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Estado;

public class EstadoRandom {

    public static Estado gerarEstado() {
        EasyRandomParameters parametroEstado = new EasyRandomParameters();

        parametroEstado.charset(forName("UTF-8"));
        parametroEstado.stringLengthRange(5, 30);
        parametroEstado.randomizationDepth(1);
        parametroEstado.collectionSizeRange(5, 30);
        parametroEstado.ignoreRandomizationErrors(true);
        parametroEstado.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroEstado);
        Estado estado = easyRandom.nextObject(Estado.class);
        return estado;
    }

}
