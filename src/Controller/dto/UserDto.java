package Controller.dto;

import java.time.LocalDate;

public record UserDto(String nome, LocalDate dataDeNascimento, String password) {
}
