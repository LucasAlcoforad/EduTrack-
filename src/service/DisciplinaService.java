package service;

import Entity.Aluno;
import Entity.Disciplina;
import Entity.Professor;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;
import Repository.DisciplinaRepository;

import java.sql.SQLException;
import java.util.Random;

public class DisciplinaService {
    public Disciplina getDisciplina(int id) throws SQLException {
        Disciplina disciplina = DisciplinaRepository.getDisciplina(id);
        if (disciplina == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return disciplina;
    }

    public Disciplina getDisciplinaById(String nome) throws SQLException {
        Disciplina disciplina = DisciplinaRepository.getDisciplinaByNome(nome);
        return disciplina;
    }

    public boolean updateDisciplina(String nome, String disciplinaNome) throws SQLException {
        Disciplina disciplina = DisciplinaRepository.getDisciplinaByNome(disciplinaNome);
        if (disciplina == null){
            throw new UserNaoEncontradoException("Aluno com id " + nome + " não encontrado.");
        }
        disciplina.setNome(nome);
        return DisciplinaRepository.updateDisciplina(disciplina);
    }

    public Disciplina createDisciplina(String nome, Professor professor) throws SQLException {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        if(DisciplinaRepository.getDisciplina(randomNumber)!=null){
            return this.createDisciplina(nome, professor);
        }
        return DisciplinaRepository.createDisciplina(new Disciplina(randomNumber,nome, professor.getNome(), null),professor.getId());
    }

    public boolean deleteDisciplina(int id){
        return DisciplinaRepository.deleteDisciplina(id);
    }
}
