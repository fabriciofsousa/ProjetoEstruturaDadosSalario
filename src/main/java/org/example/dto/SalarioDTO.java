package org.example.dto;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public record SalarioDTO(Integer idade,
                         String genero,
                         String escolaridade,
                         String cargo,
                         Double tempoDeExperienciaEmAnos,
                         Double salario)
        implements Comparable<SalarioDTO> {


    @Override
    public String toString() {
        return "["+
                "idade=" + idade +
                ", genero='" + genero + '\'' +
                ", escolaridade='" + escolaridade + '\'' +
                ", cargo='" + cargo + '\'' +
                ", tempoDeExperienciaEmAnos=" + tempoDeExperienciaEmAnos +
                ", salario= " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(salario) +
                "] \n";
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

    public static class ComparadorIdade implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return Integer.compare(o1.idade(), o2.idade());

        }
    }

    public static class ComparadorGenero implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return o1.genero().compareTo(o2.genero());

        }
    }

    public static class ComparadorEscolaridade implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return o1.escolaridade().compareTo(o2.escolaridade());

        }
    }

    public static class ComparadorCargo implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return o1.cargo().compareTo(o2.cargo());

        }
    }

    public static class ComparadorTempoDeExperiencia implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return Double.compare(o1.tempoDeExperienciaEmAnos(), o2.tempoDeExperienciaEmAnos());

        }
    }

    public static class ComparadorValorDoSalario implements Comparator<SalarioDTO>{

        @Override
        public int compare(SalarioDTO o1, SalarioDTO o2) {
            return Double.compare(o1.salario(), o2.salario());

        }
    }
}
