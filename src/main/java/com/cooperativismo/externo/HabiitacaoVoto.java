package com.cooperativismo.externo;

public class HabiitacaoVoto {

    public boolean isDisponivel(String cpf){
        //TODO consultaexterna
        return true;
    }
    public boolean isIndisponivel(String cpf){
        return !isDisponivel(cpf);
    }
}
