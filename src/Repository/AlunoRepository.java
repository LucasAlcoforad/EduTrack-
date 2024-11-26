package Repository;

import Config.DBConfig;
import Controller.dto.AlunoDto;
import Entity.Aluno;
import Exceçoes.UserNaoEncontradoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AlunoRepository {
    public static int createAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (id_aluno, password, nome, data_nascimento, create_timestamp, update_timestamp) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1,aluno.getId());
            ps.setString(2,aluno.getPassword());
            ps.setString(3,aluno.getNome());
            ps.setDate(4,Date.valueOf(aluno.getDataNascimento()));
            ps.setTimestamp(5, Timestamp.from(aluno.getCreationTimestamp()));
            ps.setTimestamp(6, Timestamp.from(aluno.getUpdateTimestamp()));
            ps.execute();
            return aluno.getId();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Aluno getAluno(int id) {
        String sql = """
        SELECT 
            aluno.id_aluno,
            aluno.password,
            aluno.nome AS aluno_nome,
            aluno.data_nascimento,
            aluno.create_timestamp,
            aluno.update_timestamp,
            disciplina.nome AS disciplina_nome
        FROM 
            aluno 
        LEFT JOIN 
            matricula ON aluno.id_aluno = matricula.id_aluno
        LEFT JOIN 
            disciplina ON matricula.id_disciplina = disciplina.id_disciplina
        WHERE 
            aluno.id_aluno = ?;
        """;
        Aluno aluno = null;
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    if (aluno == null) {
                        aluno = new Aluno(
                                resultSet.getInt("id_aluno"),
                                resultSet.getString("password"),
                                resultSet.getString("aluno_nome"),
                                resultSet.getDate("data_nascimento").toLocalDate(),
                                resultSet.getTimestamp("create_timestamp").toInstant(),
                                resultSet.getTimestamp("update_timestamp").toInstant(),
                                new ArrayList<>()
                        );
                    }
                    String disciplina = resultSet.getString("disciplina_nome");
                    if (disciplina != null) {
                        aluno.getDisciplinas().add(disciplina);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }

    public static boolean updateAluno(Aluno aluno){
        String sql = "UPDATE aluno SET nome = ?, password = ?, data_nascimento = ?, update_timestamp = ? WHERE id_aluno = ?";
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setString(1,aluno.getNome());
            statement.setString(2, aluno.getPassword());
            statement.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            statement.setTimestamp(4, Timestamp.from(aluno.getUpdateTimestamp()));
            statement.setInt(5,aluno.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        try(PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
