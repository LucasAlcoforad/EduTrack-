package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    private static final String url = "jdbc:mysql://localhost:3306/unifor";
    private static final String user = "myuser";
    private static final String password = "123";

    public static Connection connection;

    public static Connection getConnection(){
        try {
            if (connection==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,user,password);
                String createAlunoTable = "CREATE TABLE IF NOT EXISTS aluno(" +
                        "    id_aluno INT PRIMARY KEY," +
                        "    password TEXT," +
                        "    nome TEXT," +
                        "    data_nascimento DATE," +
                        "    create_timestamp TIMESTAMP," +
                        "    update_timestamp TIMESTAMP" +
                        ")";
                connection.createStatement().execute(createAlunoTable);
                String createProfessorTable = "CREATE TABLE IF NOT EXISTS professor(" +
                        "    id_professor INT PRIMARY KEY," +
                        "    password TEXT," +
                        "    nome TEXT," +
                        "    data_nascimento DATE," +
                        "    create_timestamp TIMESTAMP," +
                        "    update_timestamp TIMESTAMP" +
                        ")";
                connection.createStatement().execute(createProfessorTable);
                String createDisciplinaTable = "CREATE TABLE IF NOT EXISTS disciplina(" +
                        "    id_disciplina INT PRIMARY KEY," +
                        "    nome TEXT," +
                        "    id_professor INT," +
                        "    FOREIGN KEY (id_professor) REFERENCES professor(id_professor) ON DELETE CASCADE" +
                        ")";
                connection.createStatement().execute(createDisciplinaTable);
                String createNotaTable = """
                        CREATE TABLE IF NOT EXISTS nota (
                            id_aluno INT,
                            id_disciplina INT,
                            id_nota INT AUTO_INCREMENT,
                            valor FLOAT NOT NULL,
                            data DATE,
                            PRIMARY KEY (id_nota),
                            FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno) ON DELETE CASCADE,
                            FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina) ON DELETE CASCADE
                        )
                        """;
                connection.createStatement().execute(createNotaTable);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //String createProfessorTable = "CREATE TABLE IF NOT EXISTS professor(" +
    //                        "    id_professor INT PRIMARY KEY," +
    //                        "    nome TEXT" +
    //                        ")";
    //                connection.createStatement().execute(createProfessorTable);
    //                String createDisciplinaTable = "CREATE TABLE IF NOT EXISTS disciplina(" +
    //                        "    id_disciplina INT PRIMARY KEY," +
    //                        "    nome TEXT" +
    //                        ")";
    //                connection.createStatement().execute(createDisciplinaTable);
    //                String createContratoTable = "CREATE TABLE IF NOT EXISTS contrato(" +
    //                        "    id_professor INT NOT NULL," +
    //                        "    id_disciplina INT NOT NULL," +
    //                        "    PRIMARY KEY (id_professor, id_disciplina)," +
    //                        "    FOREIGN KEY (id_professor) REFERENCES professor(id_professor) ON DELETE CASCADE," +
    //                        "    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina) ON DELETE CASCADE" +
    //                        ")";
    //                connection.createStatement().execute(createContratoTable);
}
