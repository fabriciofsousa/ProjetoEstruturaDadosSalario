package org.example.services;

import org.example.dto.SalarioDTO;

import java.util.List;

public interface ImportarCSVService {

    List<SalarioDTO> carregarSalariosCSV(String caminho);
}
