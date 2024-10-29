package Repository;

import Config.DBConfig;
import Controller.dto.CreateAlunoDto;
import Entity.Aluno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class AlunoRepository {
    public static int createAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (id_aluno, nome, data_nascimento) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = DBConfig.getConnection().prepareStatement(sql);
            ps.setString(1,String.valueOf(aluno.getIdAluno()));
            ps.setString(2,aluno.getNome());
            ps.setString(3,aluno.getDataNascimento().toString());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return aluno.getIdAluno();
    }

    public static Aluno getAluno(int id){
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        PreparedStatement ps = null;
        Aluno aluno = null;
        try {
            ps = DBConfig.getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                LocalDate localDate = LocalDate.parse(rs.getString("data_nascimento"));
                aluno = new Aluno(rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        localDate,
                        null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }

}
