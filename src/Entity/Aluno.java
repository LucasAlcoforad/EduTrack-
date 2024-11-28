package Entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class Aluno extends User{

    public Map<String, List<Double>> disciplinas;


    public Aluno() {
    }

    public Aluno(int id, String password, String nome, LocalDate dataNascimento, Instant creationTimestamp, Instant updateTimestamp, Map<String, List<Double>> disciplinas) {
        super(id, password, nome, dataNascimento, creationTimestamp, updateTimestamp);
        this.disciplinas = disciplinas;
    }

    public Map<String, List<Double>> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Map<String, List<Double>> disciplinas) {
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
