package Controller.dto;

import java.time.LocalDate;

public record NotaDto(String disciplina, Integer idAluno, double nota, LocalDate data) {
}
