package Controller;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import Entity.Professor;
import service.AlunoService;
import service.ProfessorService;

import java.sql.SQLException;

public class ProfessorController {
    public int createProfessor(String nome) throws SQLException {
        return new ProfessorService().createProfessor(nome);
    }
    public Professor getProfessorById(int id) {
        return new ProfessorService().getProfessorById(id);
    }
    public boolean updateProfessor(String nome,
                               Integer id){
        return new ProfessorService().updateProfessor(nome, id);
    }
    public boolean deleteProfessor(int id){
        return new ProfessorService().deleteProfessor(id);
    }
}
