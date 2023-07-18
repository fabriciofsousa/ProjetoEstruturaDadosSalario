package org.example.camadaInterface;

import org.example.serviceImpl.CadastrarIndividualmenteServiceImpl;
import org.example.dto.SalarioDTO;
import org.example.exceptions.SalarioException;
import org.example.serviceImpl.ImportarCSVServiceImpl;
import org.example.services.CadastrarIndividualmenteService;
import org.example.services.ImportarCSVService;
import org.example.utilitarios.InputOutputUtil;
import org.example.utilitarios.PropertiesReader;

import java.util.*;

import static org.example.utilitarios.LoggerUtil.*;

public class SalarioInterface {

    private InputOutputUtil inputOutputUtil;
    private List<SalarioDTO> salariosList = new ArrayList<>();
    private Set<SalarioDTO> salariosHashSet = new HashSet<>();

    public SalarioInterface() {
    }

    public SalarioInterface(InputOutputUtil usuario) {
        this.inputOutputUtil = usuario;
    }

    public void inicializar() {
        try {

            String opcaoUsuario = textoMenu();
            while (!"x".equals(opcaoUsuario.toLowerCase())) {
                menu(opcaoUsuario);
                opcaoUsuario = textoMenu();
            }
        } catch (SalarioException e) {
            logError(e.getLocalizedMessage());
        }

    }

    private String textoMenu() {
        logInfo("###############################################");
        logInfo("Escolha a opção que deseja: ");
        logInfo("1 - Cadastrar salario ");
        logInfo("2 - Cadastrar salarios em lote ");
        logInfo("3 - Verificar duplicidade  ");
        logInfo("4 - Listar salarios  ");
        logInfo("5 - Buscar salario por idade ");
        logInfo("6 - Buscar salario por genero");
        logInfo("7 - Buscar salario por escolaridade");
        logInfo("8 - Buscar salario por cargo");
        logInfo("9 - Buscar salario por tempo de experiencia");
        logInfo("10 - Buscar por valor do salário ");
        logInfo("X - Digite 'X' para sair ");
        logInfoSemQuebraDeLinha("Opção: ");

        return inputOutputUtil.inputString();
    }


    private void menu(String opcao) throws SalarioException {
        switch (opcao) {
            case "1": //Cadastrar salario
                CadastrarIndividualmenteService registros = new CadastrarIndividualmenteServiceImpl(inputOutputUtil);
                adicionar(registros.receberObjetoPopulado());
                limparConsole();
                break;
            case "2"://Cadastrar salarios em lote
                ImportarCSVService importarCSV = new ImportarCSVServiceImpl();
                PropertiesReader propertiesReader = new PropertiesReader();
                adicionar(importarCSV.carregarSalariosCSV(propertiesReader.getPropertiesValue("CSV.PATH")));
                logInfo("Salários carregados com sucesso!");
                break;
            case "3": //Verificar duplicidade
                if (salariosList.size() != salariosHashSet.stream().distinct().count()) {
                    logInfo("Tem duplicidade");
                } else {
                    logInfo("NÃO Tem duplicidade");
                }

                break;
            case "4": //Listar salarios
                if (salariosList.isEmpty()) {
                    logInfo("Lista Vazia!");
                    limparConsole();
                    break;
                }
                salariosList.forEach(salario -> System.out.println(salario));
                break;
            case "5"://Buscar salario por idade
                break;
            case "6"://Buscar salario por genero
                break;
            case "7": //Buscar salario por escolaridade
                break;
            case "8": //Buscar salario por cargo
                break;
            case "9": //Buscar salario por tempo de experiencia
                break;
            case "10": //Buscar por valor do salário
                break;
            default:
                break;
        }

    }

    private void adicionar(List<SalarioDTO> salarios) {
        for (SalarioDTO salario : salarios) {
            replicarApontamentosDeInstancias(salario);
        }
    }

    public void replicarApontamentosDeInstancias(SalarioDTO salarios) {
        this.salariosList.add(salarios);
        this.salariosHashSet.add(salarios);
    }

    public void removerDuplicidade(List<SalarioDTO> salarios) {
        List<SalarioDTO> listaSemDuplicados = (List<SalarioDTO>) new TreeSet<>(salarios);

    }


}
