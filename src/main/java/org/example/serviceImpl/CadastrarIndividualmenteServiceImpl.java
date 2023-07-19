package org.example.serviceImpl;

import org.example.dto.SalarioDTO;
import org.example.exceptions.SalarioException;
import org.example.services.CadastrarIndividualmenteService;
import org.example.utilitarios.InputOutputUtil;
import org.example.utilitarios.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class CadastrarIndividualmenteServiceImpl implements CadastrarIndividualmenteService {

    private InputOutputUtil usuario;
    private SalarioDTO salarioDTO;
    private List<SalarioDTO> salarios;

    public CadastrarIndividualmenteServiceImpl(InputOutputUtil usuario) {
        LoggerUtil.limparConsole();
        this.usuario = usuario;
        salarios = new ArrayList<>();
        receberDadosEPopularLista();
    }

    public List<SalarioDTO> receberObjetoPopulado() throws SalarioException {
        if (salarios == null || salarios.isEmpty()) {
            throw new SalarioException("Erro técnico, por favor, contacte o administrador do sustema");
        } else {
            return salarios;
        }

    }


    private void receberDadosEPopularLista() {
        boolean preencherNovamente = true;
        while (preencherNovamente == true) {
            int idade;
            do {
                System.out.print("Digite a idade: ");
                idade = usuario.inputInt();
            } while (!consistenciaIdade(idade));

            String genero;
            do {
                System.out.print("Digite o gênero: ");
                genero = usuario.inputString();
            } while (!consistenciaString(genero));

            String escolaridade;
            do {
                System.out.print("Digite a escolaridade: ");
                escolaridade = usuario.inputString();
            } while (!consistenciaString(escolaridade));

            String cargo;
            do {
                System.out.print("Digite o cargo: ");
                cargo = usuario.inputString();
            } while (!consistenciaString(cargo));

            double tempoDeExperienciaEmAnos;
            do {
                System.out.print("Digite o tempo de experiência em anos: ");
                tempoDeExperienciaEmAnos = usuario.inputDouble();
            } while (!consistenciaTempoDeExperiencia(tempoDeExperienciaEmAnos));

            double salario;
            do {
                System.out.print("Digite o salário: ");
                salario = usuario.inputDouble();
            } while (!consistenciaSalario(salario));
            salarioDTO = new SalarioDTO(idade, genero, escolaridade, cargo, tempoDeExperienciaEmAnos, salario);
            salarios.add(salarioDTO);

            LoggerUtil.logInfo("Deseja cadastrar um novo salario? (S/N)");

            String preencherNovamenteString = usuario.inputString();
            if ("S".equals(preencherNovamenteString.toUpperCase())) {
                preencherNovamente = true;
            } else {
                preencherNovamente = false;
            }
        }

    }

    private static boolean consistenciaIdade(int idade) {
        try {
            if (idade < 0) {
                LoggerUtil.logInfo("A idade não pode ser negativa. Por favor, tente novamente.");
            }
            return true;
        } catch (Exception e) {
            LoggerUtil.logInfo("Por favor, insira um dado válido.");
            return false;
        }
    }

    private static boolean consistenciaString(String campo) {
        try {
            if (campo.isEmpty()) {
                LoggerUtil.logInfo("O campo não pode estar vazio. Por favor, tente novamente.");
            }
            return true;
        } catch (Exception e) {
            LoggerUtil.logInfo("Por favor, insira um dado válido.");
            return false;
        }
    }

    private static boolean consistenciaTempoDeExperiencia(double tempoDeExperienciaEmAnos) {
        try {
            if (tempoDeExperienciaEmAnos < 0) {
                LoggerUtil.logInfo("O tempo de experiência não pode ser negativo. Por favor, tente novamente.");
            }
            return true;
        } catch (Exception e) {
            LoggerUtil.logInfo("Por favor, insira um dado válido.");
            return false;
        }
    }

    private static boolean consistenciaSalario(double salario) {
        try {
            if (salario < 0) {
                LoggerUtil.logInfo("O salário não pode ser negativo. Por favor, tente novamente.");
            }
            return true;
        } catch (Exception e) {
            LoggerUtil.logInfo("Por favor, insira um dado válido.");
            return false;
        }
    }
}
