package com.cooperativismo.domain.externo;

import com.cooperativismo.domain.exceptions.NegocioException;
import com.cooperativismo.domain.enums.HabilitacaoParaVoto;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HabiitacaoVoto {

    private final String url_externo = "https://user-info.herokuapp.com/users/";

    public HabilitacaoParaVoto isDisponivel(String cpf) throws IOException, JSONException {
        return isHabilitado(cpf) ?
                HabilitacaoParaVoto.HABILITADO : HabilitacaoParaVoto.DESABILITADO;
    }

    private boolean isHabilitado(String cpf) throws IOException, JSONException {
        URL obj = new URL(url_externo.concat(cpf));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        switch (con.getResponseCode()) {
            case 200:
                return verificaHabilitacao(con);
            case 500:
                throw new NegocioException("Não foi possível fazer a consulta externa à habilitação do CPF.");
            default:
                return false;
        }
    }
    private boolean verificaHabilitacao(HttpURLConnection con) throws IOException, JSONException {
        StringBuffer response = new StringBuffer();
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject json = new JSONObject(response.toString());
        return "ABLE_TO_VOTE".equals(json.get("status"));
    }


}
