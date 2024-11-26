package Entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Nota {
    public Integer id;

    public Integer idDisciplina;

    public Integer idAluno;

    public double nota;

    public LocalDate data;

    public Nota() {
    }

    public Nota(Integer id, Integer idDisciplina, Integer idAluno, double nota, LocalDate data) {
        this.id = id;
        this.idDisciplina = idDisciplina;
        this.idAluno = idAluno;
        this.nota = nota;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
