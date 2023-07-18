package org.example.dto;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public record SalarioDTO(Integer idade, String genero, String escolaridade, String cargo,
                         Double tempoDeExperienciaEmAnos, Double salario)
        implements Comparable<SalarioDTO> {


    @Override
    public String toString() {
        return "[" +
                ", idade=" + idade +
                ", genero='" + genero + '\'' +
                ", escolaridade='" + escolaridade + '\'' +
                ", cargo='" + cargo + '\'' +
                ", tempoDeExperienciaEmAnos=" + tempoDeExperienciaEmAnos +
                ", salario= " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(salario) +
                "}] \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalarioDTO that = (SalarioDTO) o;
        if (idade == that.idade &&
                Double.compare(that.tempoDeExperienciaEmAnos, tempoDeExperienciaEmAnos) == 0 &&
                Double.compare(that.salario, salario) == 0 &&
                genero.equals(that.genero) &&
                escolaridade.equals(that.escolaridade) &&
                cargo.equals(that.cargo)) {
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(idade, genero, escolaridade, cargo, tempoDeExperienciaEmAnos, salario);
    }

    @Override
    public int compareTo(SalarioDTO outroSalario) {
        int resultado = this.idade.compareTo(outroSalario.idade());

        if (resultado == 0) {
            resultado = this.genero.compareTo(outroSalario.genero());
        }
        if (resultado == 0) {
            resultado = this.escolaridade.compareTo(outroSalario.escolaridade());
        }
        if (resultado == 0) {
            resultado = this.cargo.compareTo(outroSalario.cargo());
        }
        if (resultado == 0) {
            resultado = this.tempoDeExperienciaEmAnos.compareTo(outroSalario.tempoDeExperienciaEmAnos());
        }
        if (resultado == 0) {
            resultado = this.salario.compareTo(outroSalario.salario());
        }
        return resultado;
    }
}
