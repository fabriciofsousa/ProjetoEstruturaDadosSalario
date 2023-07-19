package org.example.camadaInterface;

import org.example.utilitarios.InputOutputUtil;

import static org.example.utilitarios.LoggerUtil.logInfo;
import static org.example.utilitarios.LoggerUtil.logInfoSemQuebraDeLinha;

public final class Menus {

    static InputOutputUtil inputOutputUtil = new InputOutputUtil();


    public static final String menuCadastro() {
        logInfo("###############################################");
        logInfo("Escolha a opção que deseja: ");
        logInfo("1 - Cadastrar individualmente");
        logInfo("2 - Cadastrar em lote  ");
        logInfo("3 - Voltar  ");
        logInfoSemQuebraDeLinha("Opção: ");
        return inputOutputUtil.inputString();
    }

    public static final String menuBuscar() {
        logInfo("###############################################");
        logInfo("Escolha a opção que deseja: ");
        logInfo("1 - Buscar salario por idade ");
        logInfo("2 - Buscar salario por genero");
        logInfo("3 - Buscar salario por escolaridade");
        logInfo("4 - Buscar salario por cargo");
        logInfo("5 - Buscar salario por tempo de experiencia");
        logInfo("6 - Buscar por valor do salário ");
        logInfo("7 - Voltar  ");
        logInfoSemQuebraDeLinha("Opção: ");
        return inputOutputUtil.inputString();
    }

    public static final String menuOrdenar() {
        logInfo("###############################################");
        logInfo("Escolha a opção que deseja: ");
        logInfo("1 - Ordenar salario por idade ");
        logInfo("2 - Ordenar salario por genero");
        logInfo("3 - Ordenar salario por escolaridade");
        logInfo("4 - Ordenar salario por cargo");
        logInfo("5 - Ordenar salario por tempo de experiencia");
        logInfo("6 - Ordenar por valor do salário ");
        logInfo("7 - Voltar  ");
        logInfoSemQuebraDeLinha("Opção: ");
        return inputOutputUtil.inputString();
    }

    public static final String textoMenu() {

        logInfo("###############################################");
        logInfo("Escolha a opção que deseja: ");
        logInfo("1 - Cadastrar");
        logInfo("2 - Verificar duplicidade  ");
        logInfo("3 - Listar todos os salarios  ");
        logInfo("4 - Buscar salario ");
        logInfo("5 - Ordenar salario ");
        logInfo("x - Para encerrar ");
        logInfoSemQuebraDeLinha("Opção: ");

        return inputOutputUtil.inputString();
    }

    public static final boolean textoConfirmacao(String texto) {
        logInfo(texto);
        String confirmacao = inputOutputUtil.inputString();
        return confirmacao.toUpperCase().equals("S");
    }
}
