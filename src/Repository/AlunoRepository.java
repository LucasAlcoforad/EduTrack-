package Repository;

import Config.DBConfig;
import Controller.dto.AlunoDto;
import Entity.Aluno;
import Exceçoes.UserNaoEncontradoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class AlunoRepository {
    public static int createAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (id_aluno, nome, data_nascimento) VALUES (?,?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1,aluno.getIdAluno());
            ps.setString(2,aluno.getNome());
            ps.setDate(3,aluno.getDataNascimento());
            ps.execute();
            return aluno.getIdAluno();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Aluno getAluno(int id){
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?"; // armazena o nosso comando
        Aluno aluno = null; // inicializa o aluno
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1,id); //
            try (ResultSet resultSet = ps.executeQuery()){
                if (resultSet.next()){
                    aluno = new Aluno(resultSet.getInt("id_aluno"),
                            resultSet.getString("nome"),
                            resultSet.getDate("data_nascimento"),
                            null);
                }
                return aluno;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateAluno(Aluno aluno){
        String sql = "UPDATE aluno SET nome = ?, data_nascimento = ? WHERE id_aluno = ?";
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setString(1,aluno.getNome());
            statement.setDate(2, aluno.getDataNascimento());
            statement.setInt(3, aluno.getIdAluno());
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
