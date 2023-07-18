package org.example.utilitarios;

public class LoggerUtil {

    public static void logInfo(String mensagem){
        System.out.println(mensagem);
    }

    public static void logInfoSemQuebraDeLinha(String mensagem){
        System.out.print(mensagem);
    }

    public static void logError(String mensagem){
        System.out.println(mensagem);
    }

    public static void limparConsole(){
        System.out.println("Digite qualquer tecla para continuar");
        InputOutputUtil teclado = new InputOutputUtil();
        teclado.inputString();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public static void limparConsoleSemEsperar(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
