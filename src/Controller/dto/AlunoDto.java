package Controller.dto;

import java.time.LocalDate;
import java.util.Date;

public record AlunoDto(String nome, LocalDate dataDeNascimento) {
}
