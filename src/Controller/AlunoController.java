package Controller;

import Controller.dto.UserDto;
import Entity.Aluno;
import service.AlunoService;

import java.sql.SQLException;
import java.util.Optional;

public class AlunoController {
    public Aluno createAluno(UserDto createAlunoDto) throws SQLException {
        return new AlunoService().createAluno(createAlunoDto);
    }
    public Aluno getAlunoById(int id) {
        return new AlunoService().getAlunoById(id);
    }
    public Aluno getAlunoByNome(String nome) {
        return new AlunoService().getAlunoByNome(nome);
    }
    public boolean updateAluno(UserDto dto,
                               Integer id){
        return new AlunoService().updateAluno(dto, id);
    }
    public boolean deleteAluno(int id){
        return new AlunoService().deleteAluno(id);
    }
}
