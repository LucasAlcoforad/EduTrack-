package Controller.dto;

import java.time.LocalDate;

public record NotaDto(Integer idDisciplina, Integer idAluno, double nota, LocalDate data) {
}
