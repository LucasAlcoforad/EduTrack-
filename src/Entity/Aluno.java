package Entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class Aluno extends User{

    public List<String> disciplinas;


    public Aluno() {
    }

    public Aluno(int id, String password, String nome, LocalDate dataNascimento, Instant creationTimestamp, Instant updateTimestamp, List<String> disciplinas) {
        super(id, password, nome, dataNascimento, creationTimestamp, updateTimestamp);
        this.disciplinas = disciplinas;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", disciplinas=" + disciplinas +
                ", creationTimestamp=" + creationTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }
}
