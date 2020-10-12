package com.cooperativismo.valida;

import org.json.JSONException;

import java.io.IOException;

public interface Valida {

    boolean valida() throws IOException, JSONException;
}
