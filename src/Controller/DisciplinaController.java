package Controller;

import Entity.Disciplina;
import Entity.Professor;
import service.DisciplinaService;

import java.sql.SQLException;

public class DisciplinaController {
    public Disciplina createDisciplina(String nome, Professor professor) throws SQLException {
        return new DisciplinaService().createDisciplina(nome, professor);
    }
    public Disciplina getDisciplina(int id) throws SQLException {
        return new DisciplinaService().getDisciplina(id);
    }
    public Disciplina getDisciplinaByName(String nome) throws SQLException {
        return new DisciplinaService().getDisciplinaById(nome);
    }
    public boolean updateDisciplina(String nome, String nomeDisciplina) throws SQLException {
        return new DisciplinaService().updateDisciplina(nome, nomeDisciplina);
    }
    public boolean deleteDisciplina(int id){
        return new DisciplinaService().deleteDisciplina(id);
    }
}
