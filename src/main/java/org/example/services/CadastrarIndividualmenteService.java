package org.example.services;

import org.example.dto.SalarioDTO;
import org.example.exceptions.SalarioException;

import java.util.List;

public interface CadastrarIndividualmenteService {

    List<SalarioDTO> receberObjetoPopulado() throws SalarioException;
}
