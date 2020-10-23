package com.cooperativismo.domain.externo;

import com.cooperativismo.domain.enums.HabilitacaoParaVoto;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HabilitacaoVotoTest {

    @Test
    public void isDisponivelTest() {
        try {
            assertEquals(new HabiitacaoVoto().isDisponivel("02692471067"), HabilitacaoParaVoto.DESABILITADO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}