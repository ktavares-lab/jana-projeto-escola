package org.example.jana.dao.user;





import org.example.jana.model.user.UsuarioComum;
import org.example.jana.model.user.enums.Perfil;
import org.example.jana.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioComumDAO {
    private static final String PROCURAR_POR_ID_SQL =
            "SELECT id, nome, matricula, email, senha_hash, perfil FROM usuario WHERE id = ?";
    private static final String LISTAR_SQL =
            "SELECT id, nome, matricula, email, senha_hash, perfil FROM usuario";
    private static final String INSERIR_SQL =
            "INSERT INTO usuario (nome, matricula, email, senha_hash, perfil) VALUES (?, ?, ?, ?, ?)";
    private static final String ATUALIZAR_SQL =
            "UPDATE usuario SET nome = ?, matricula = ?, email = ?, senha_hash = ?, perfil = ? WHERE id = ?";
    private static final String DELETAR_SQL =
            "DELETE FROM usuario WHERE id = ?";
    private static final String PROCURAR_POR_EMAIL_SQL = "SELECT id from usuario WHERE email = ?";

    public UsuarioComum procurarPorId(int id) throws SQLException {
        try(Connection connection = Conexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PROCURAR_POR_ID_SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    return resultSetToUser(resultSet);
                }
            }
        }
        return null;
    }


    public List<UsuarioComum> listar() throws SQLException {
        List<UsuarioComum> usuarios = new ArrayList<>();
        try(Connection connection = Conexao.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(LISTAR_SQL);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                usuarios.add(resultSetToUser(resultSet));
            }
        }
        return usuarios;
    }


    public boolean deletar(int id) throws SQLException{
        try(Connection connection = Conexao.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETAR_SQL)){
            preparedStatement.setInt(1, id);
            int linhasAfetadas = preparedStatement.executeUpdate();
            if(linhasAfetadas==1){
                return true;
            }
        }
        return false;
    }


    public boolean inserir(UsuarioComum usuario) throws SQLException{
        try(Connection connection = Conexao.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_SQL)){
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2,usuario.getMatricula());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4,usuario.getSenhaHash());
            preparedStatement.setString(5,usuario.getPerfil().name());
            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas>0;
        }

    }


    public boolean atualizar(UsuarioComum usuario) throws SQLException {
        try(Connection connection = Conexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ATUALIZAR_SQL)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getMatricula());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenhaHash());
            preparedStatement.setString(5, usuario.getPerfil().name());
            preparedStatement.setInt(6, usuario.getId());

            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
    public boolean existePeloEmail(String email) throws SQLException {
        try(Connection connection = Conexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PROCURAR_POR_EMAIL_SQL)){
            preparedStatement.setString(1,email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    private UsuarioComum resultSetToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String matricula = resultSet.getString("matricula");
        String email = resultSet.getString("email");
        String senhaHash = resultSet.getString("senha_hash");
        Perfil perfil = Perfil.valueOf(resultSet.getString("perfil"));
        return new UsuarioComum(id, nome, matricula, email, senhaHash, perfil);
    }
}
