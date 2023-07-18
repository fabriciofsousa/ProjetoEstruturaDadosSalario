package org.example.serviceImpl;

import org.example.dto.SalarioDTO;
import org.example.services.ImportarCSVService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportarCSVServiceImpl implements ImportarCSVService {
    @Override
    public List<SalarioDTO> carregarSalariosCSV(String caminho) {
        List<SalarioDTO> salarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            br.readLine();

            String linha = "";
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                SalarioDTO salario = new SalarioDTO(Integer.parseInt(dados[0].trim()), dados[1].trim(),
                        dados[2].trim().toString(),
                        dados[3].trim().toString(),
                        Double.parseDouble(dados[4].trim()),
                        Double.parseDouble(dados[5].trim()));
                salarios.add(salario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salarios;
    }
}
