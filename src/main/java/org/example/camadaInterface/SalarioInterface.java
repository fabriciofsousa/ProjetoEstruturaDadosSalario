package org.example.camadaInterface;

import org.example.dto.SalarioDTO;
import org.example.exceptions.SalarioException;
import org.example.serviceImpl.CadastrarIndividualmenteServiceImpl;
import org.example.serviceImpl.ImportarCSVServiceImpl;
import org.example.services.CadastrarIndividualmenteService;
import org.example.services.ImportarCSVService;
import org.example.utilitarios.InputOutputUtil;
import org.example.utilitarios.PropertiesReader;

import java.util.*;

import static org.example.camadaInterface.Menus.textoConfirmacao;
import static org.example.utilitarios.LoggerUtil.*;

public class SalarioInterface {

    private InputOutputUtil inputOutputUtil;
    private List<SalarioDTO> salariosList = new ArrayList<>();
    private Set<SalarioDTO> salariosHashSet = new HashSet<>();

    private Map<Integer, SalarioDTO> salarioPorIdade = new HashMap<>();
    private Map<String, SalarioDTO> salarioPorGenero = new HashMap<>();
    private Map<String, SalarioDTO> salarioPorEscolaridade = new HashMap<>();
    private Map<String, SalarioDTO> salarioPorCargo = new HashMap<>();
    private Map<Double, SalarioDTO> salarioPorTempoDeExperienciaEmAnos = new HashMap<>();

    private Map<Double, SalarioDTO> salarioPorValorDoSalario = new HashMap<>();
    private boolean dadosDuplicadosRemovidos = false;

    public SalarioInterface() {
    }

    public SalarioInterface(InputOutputUtil usuario) {
        this.inputOutputUtil = usuario;
    }

    public void inicializar() {
        try {

            String opcaoUsuario = "";
            while (!"x".equals(opcaoUsuario.toLowerCase())) {
                opcaoUsuario = Menus.textoMenu();
                menu(opcaoUsuario);
            }
        } catch (SalarioException e) {
            logError(e.getLocalizedMessage());
        }

    }


    private void menu(String opcao) throws SalarioException {
        switch (opcao) {
            case "1": //Cadastrar salario
                String opcapCadastro = Menus.menuCadastro();
                switch (opcapCadastro) {
                    case "1": //Cadastrar individualmente
                        CadastrarIndividualmenteService registros = new CadastrarIndividualmenteServiceImpl(inputOutputUtil);
                        adicionar(registros.receberObjetoPopulado());
                        limparConsole();
                        break;
                    case "2": // cadastrar em lote
                        ImportarCSVService importarCSV = new ImportarCSVServiceImpl();
                        PropertiesReader propertiesReader = new PropertiesReader();
                        adicionar(importarCSV.carregarSalariosCSV(propertiesReader.getPropertiesValue("CSV.PATH")));
                        logInfo("Salários carregados com sucesso!");
                        break;
                    default:
                        limparConsoleSemEsperar();
                        break;
                }

                break;
            case "2"://Cadastrar salarios em lote
                dadosDuplicadosRemovidos = true;
                if (salariosList.size() != salariosHashSet.stream().distinct().count()) {
                    logInfo("Encontrei dados duplicados. Removendo...");
                    removerDuplicidade(salariosList);
                    limparConsole();
                } else {
                    logInfo("Não há dados duplicados!");
                    limparConsole();
                }
                break;
            case "3": //listar todos os salarios
                if (salariosList.isEmpty()) {
                    logInfo("Lista Vazia!");
                    limparConsole();
                    break;
                }
                salariosList.forEach(salario -> System.out.println(salario));
                limparConsole();
                break;
            case "4": //buscar
                String opcaoBuscar = Menus.menuBuscar();
                switch (opcaoBuscar) {
                    case "1": //Buscar salario por idade
                        boolean buscarNovamenteIdade = true;
                        while (buscarNovamenteIdade) {
                            logInfoSemQuebraDeLinha("informe a idade: ");
                            Integer idade = inputOutputUtil.inputInt();
                            logInfo(salarioPorIdade.get(idade) != null ? salarioPorIdade.get(idade).toString() : "Registro não localizado");

                            buscarNovamenteIdade = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "2"://Buscar salario por genero
                        boolean buscarNovamenteGenero = true;
                        while (buscarNovamenteGenero) {
                            logInfoSemQuebraDeLinha("informe o genero: ");
                            String genero = inputOutputUtil.inputString();
                            logInfo(salarioPorGenero.get(genero) != null ? salarioPorGenero.get(genero).toString() : "Registro não localizado");
                            buscarNovamenteGenero = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "3"://Buscar salario por escolaridade
                        boolean buscarNovamenteEscolaridade = true;
                        while (buscarNovamenteEscolaridade) {
                            logInfoSemQuebraDeLinha("informe a escolaridade: ");
                            String escolaridade = inputOutputUtil.inputString();
                            logInfo(salarioPorEscolaridade.get(escolaridade) != null ? salarioPorEscolaridade.get(escolaridade).toString() : "Registro não localizado");
                            buscarNovamenteEscolaridade = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "4"://Buscar salario por cargo
                        boolean buscarNovamenteCargo = true;
                        while (buscarNovamenteCargo) {
                            logInfoSemQuebraDeLinha("informe o cargo: ");
                            String cargo = inputOutputUtil.inputString();
                            logInfo(salarioPorCargo.get(cargo) != null ? salarioPorCargo.get(cargo).toString() : "Registro não localizado");
                            buscarNovamenteCargo = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "5"://Buscar salario por tempo de experiencia
                        boolean buscarNovamenteTempoExp = true;
                        while (buscarNovamenteTempoExp) {
                            logInfoSemQuebraDeLinha("informe o tempo de experiencia em anos: ");
                            Double anos = inputOutputUtil.inputDouble();
                            logInfo(salarioPorTempoDeExperienciaEmAnos.get(anos) != null ? salarioPorTempoDeExperienciaEmAnos.get(anos).toString() : "Registro não localizado");
                            buscarNovamenteTempoExp = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "6"://Buscar salario por salario
                        boolean buscarNovamenteSalario = true;
                        while (buscarNovamenteSalario) {
                            logInfoSemQuebraDeLinha("informe o valor do salario: R$ ");
                            Double salario = inputOutputUtil.inputDouble();
                            logInfo(salarioPorValorDoSalario.get(salario) != null ? salarioPorValorDoSalario.get(salario).toString() : "Registro não localizado");
                            buscarNovamenteSalario = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    default:
                        limparConsoleSemEsperar();
                        break;
                }
                limparConsole();

                break;
            case "5"://ordenar
                String opcaoOrdenar = Menus.menuOrdenar();
                switch (opcaoOrdenar) {
                        case "1": //Ordenar salario por idade
                    boolean ordenarIdade = true;
                            while (ordenarIdade) {
                            salariosList.sort(new SalarioDTO.ComparadorIdade());
                            salariosList.forEach(salario -> System.out.println(salario));
                            ordenarIdade = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                    }
                    break;
                    case "2"://Ordenar salario por genero
                        boolean ordenarGenero = true;
                        while (ordenarGenero) {
                            salariosList.sort(new SalarioDTO.ComparadorGenero());
                            salariosList.forEach(salario -> System.out.println(salario));
                            ordenarGenero = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "3"://Ordenar salario por escolaridade
                        boolean ordenarEscolaridade = true;
                        while (ordenarEscolaridade) {
                            salariosList.sort(new SalarioDTO.ComparadorEscolaridade());
                            salariosList.forEach(salario -> System.out.println(salario));
                            ordenarEscolaridade = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "4"://Ordenar salario por cargo
                        boolean ordenarCargo = true;
                        while (ordenarCargo) {
                            salariosList.sort(new SalarioDTO.ComparadorCargo());
                            salariosList.forEach(salario -> System.out.println(salario));
                            ordenarCargo = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "5"://Ordenar salario por tempo de experiencia
                        boolean ordenarExp = true;
                        while (ordenarExp) {
                            salariosList.sort(new SalarioDTO.ComparadorTempoDeExperiencia());
                            salariosList.forEach(salario -> System.out.println(salario));
                            ordenarExp = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    case "6"://Ordenar salario por salario
                        boolean OrdenarSalario = true;
                        while (OrdenarSalario) {
                            salariosList.sort(new SalarioDTO.ComparadorValorDoSalario());
                            salariosList.forEach(salario -> System.out.println(salario));
                            OrdenarSalario = textoConfirmacao("Deseja buscar Novamente? (S/N)");
                        }
                        break;
                    default:
                        limparConsoleSemEsperar();
                        break;
                }
                limparConsole();

                break;
            default:
                limparConsoleSemEsperar();
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

        this.salarioPorValorDoSalario.put(salarios.salario(), salarios);
        this.salarioPorTempoDeExperienciaEmAnos.put(salarios.tempoDeExperienciaEmAnos(), salarios);
        this.salarioPorCargo.put(salarios.cargo(), salarios);
        this.salarioPorEscolaridade.put(salarios.escolaridade(), salarios);
        this.salarioPorGenero.put(salarios.genero(), salarios);
        this.salarioPorIdade.put(salarios.idade(), salarios);
    }

    public void removerDuplicidade(List<SalarioDTO> salarios) {
        List<SalarioDTO> listaSemDuplicados = (List<SalarioDTO>) new TreeSet<>(salarios);

    }


}
