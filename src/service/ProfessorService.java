package service;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import Entity.Professor;
import Exceçoes.UserNaoEncontradoException;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;
import Repository.ProfessorRepository;

import java.sql.SQLException;
import java.util.Random;

public class ProfessorService {
    public int createProfessor(String nome) throws SQLException {
        Random random = new Random();
        int min = 10000;
        int max = 99999;

        int randomNumber = random.nextInt(max - min + 1) + min;
        Professor professor = new Professor(randomNumber,
                nome,
                null
        );
        return ProfessorRepository.createProfessor(professor);
    }

    public Professor getProfessorById(int id){

        Professor professor = ProfessorRepository.getProfessor(id);
        if (professor == null){
            throw new UserNaoEncontradoException("Professor com id " + id + " não encontrado.");
        }
        return professor;
    }

    public boolean updateProfessor(String nome,
                                   int id){
        Professor professor = ProfessorRepository.getProfessor(id);
        if (professor == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        professor.setNome(nome);
        return ProfessorRepository.updateProfessor(professor);
    }

    public boolean deleteProfessor(int id){
        return ProfessorRepository.deleteProfessor(id);
    }
}
