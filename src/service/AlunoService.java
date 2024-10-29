package service;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import Repository.AlunoRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class AlunoService {

    public int createAluno(AlunoDto createAlunoDto) throws SQLException {
        Random random = new Random();
        int min = 10000;
        int max = 99999;

        int randomNumber = random.nextInt(max - min + 1) + min;
        Aluno aluno = new Aluno(randomNumber,
                createAlunoDto.nome(),
                createAlunoDto.dataDeNascimento(),
                null
        );
        return AlunoRepository.createAluno(aluno);
    }

    public Aluno getAlunoById(int id){
        return AlunoRepository.getAluno(id);
    }
}
