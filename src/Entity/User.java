package Entity;

import java.time.Instant;
import java.time.LocalDate;

public abstract class User {

    public int id;

    public String password;

    public String nome;

    public LocalDate dataNascimento;

    public Instant creationTimestamp;

    public Instant updateTimestamp;

    public User() {
    }

    public User(int id, String password, String nome, LocalDate dataNascimento, Instant creationTimestamp, Instant updateTimestamp) {
        this.id = id;
        this.password = password;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
