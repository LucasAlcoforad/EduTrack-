package service;

import Controller.dto.UserDto;
import Entity.Aluno;
import Entity.Professor;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;
import Repository.ProfessorRepository;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Random;

public class ProfessorService {
    public int createProfessor(UserDto createProfessorDto) throws SQLException {
        if (createProfessorDto.dataDeNascimento()==null||
                createProfessorDto.nome()==null||
                createProfessorDto.password()==null){
            //Erro
        }
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        if(ProfessorRepository.getProfessor(randomNumber)!=null){
            return this.createProfessor(createProfessorDto);
        }
        Professor professor = new Professor(randomNumber,
                createProfessorDto.password(),
                createProfessorDto.nome(),
                createProfessorDto.dataDeNascimento(),
                Instant.now(),
                Instant.now(),
                null);
        return ProfessorRepository.createProfessor(professor);
    }

    public Professor getProfessorById(int id){
        Professor professor = ProfessorRepository.getProfessor(id);
        return professor;
    }

    public boolean updateProfessor(UserDto dto,
                               int id){
        Professor professor = ProfessorRepository.getProfessor(id);
        if (professor == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        if (dto.nome()!=null){
            professor.setNome(dto.nome());
        }
        if (dto.password()!=null){
            professor.setPassword(dto.password());
        }
        if (dto.dataDeNascimento()!=null){
            professor.setDataNascimento(dto.dataDeNascimento());
        }
        professor.setUpdateTimestamp(Instant.now());
        return ProfessorRepository.updateProfessor(professor);
    }

    public boolean deleteProfessor(int id){
        Professor professor = ProfessorRepository.getProfessor(id);
        if (professor == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return ProfessorRepository.deleteProfessor(id);
    }
}
