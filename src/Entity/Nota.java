package Entity;

import java.util.Date;

public class Nota {

    public int idAluno;

    public int idDisciplina;

    public float nota;

    public Date data;

    public Nota() {
    }

    public Nota(int idAluno, int idDisciplina, float nota, Date data) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.nota = nota;
        this.data = data;
    }
}
