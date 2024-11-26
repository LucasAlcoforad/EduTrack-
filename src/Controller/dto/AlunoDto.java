package Controller.dto;

import java.time.LocalDate;

public record AlunoDto(String nome, LocalDate dataDeNascimento, String password) {
}
