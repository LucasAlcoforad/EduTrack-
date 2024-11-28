package Repository;

import Config.DBConfig;
import Entity.Aluno;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
            aluno.*,
            disciplina.nome AS disciplina_nome,
            nota.valor AS nota_valor
        FROM 
            aluno 
        LEFT JOIN 
            nota ON aluno.id_aluno = nota.id_aluno
        LEFT JOIN 
            disciplina ON nota.id_disciplina = disciplina.id_disciplina
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
                                resultSet.getString("nome"),
                                resultSet.getDate("data_nascimento").toLocalDate(),
                                resultSet.getTimestamp("create_timestamp").toInstant(),
                                resultSet.getTimestamp("update_timestamp").toInstant(),
                                new HashMap<>()
                        );
                    }
                    String disciplina = resultSet.getString("disciplina_nome");
                    Double nota = resultSet.getDouble("nota_valor");
                    if (disciplina != null && nota != null) {
                        aluno.getDisciplinas()
                                .computeIfAbsent(disciplina, k -> new ArrayList<>())
                                .add(nota);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }

    public static Aluno getAlunoByNome(String nome){
        String sql = """
                SELECT
                    *
                FROM
                    aluno
                WHERE
                    aluno.nome = ?
                """;
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
