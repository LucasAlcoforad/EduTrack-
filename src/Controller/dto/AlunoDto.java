package Controller.dto;

import java.time.LocalDate;
import java.sql.Date;

public record AlunoDto(String nome, Date dataDeNascimento) {
}
