package Entity;

import Controller.dto.NotaDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class Professor extends User{

    public List<String> disciplinas;


    public Professor() {
    }

    public Professor(int id, String password, String nome, LocalDate dataNascimento, Instant creationTimestamp, Instant updateTimestamp, List<String> disciplinas) {
        super(id, password, nome, dataNascimento, creationTimestamp, updateTimestamp);
        this.disciplinas = disciplinas;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }


}
