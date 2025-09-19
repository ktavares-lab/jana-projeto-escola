package org.example.jana.service.user;




import org.example.jana.dao.user.UsuarioComumDAO;
import org.example.jana.dtos.user.UsuarioComumCreateRequest;
import org.example.jana.dtos.user.UsuarioComumResponse;
import org.example.jana.dtos.user.UsuarioComumUpdateRequest;
import org.example.jana.exceptions.user.EmailJaExisteException;
import org.example.jana.exceptions.user.FalhaNaOperacaoException;
import org.example.jana.exceptions.user.UsuarioNaoEncontradoException;
import org.example.jana.model.user.UsuarioComum;

import java.sql.SQLException;
import java.util.List;

public class UsuarioComumService  {
    private final UsuarioComumDAO usuarioComumDAO;

    public UsuarioComumService(UsuarioComumDAO usuarioComumDAO) {
        this.usuarioComumDAO = usuarioComumDAO;
    }


    public List<UsuarioComumResponse> listar() throws SQLException {
        return usuarioComumDAO.listar().stream().map(this::usertoComumResponseDto).toList();
    }


    public boolean inserir(UsuarioComumCreateRequest usuario) throws SQLException {
        if(usuarioComumDAO.existePeloEmail(usuario.email())){
            throw new EmailJaExisteException("O email: " + usuario.email() + "ja existe!");
        }
        UsuarioComum usuarioComum = createDtoToComumUser(usuario);
        return usuarioComumDAO.inserir(usuarioComum);
    }

    public boolean atualizar(UsuarioComumUpdateRequest usuarioComumUpdateRequest, int id) throws SQLException, UsuarioNaoEncontradoException {
        UsuarioComum usuarioComum = usuarioComumDAO.procurarPorId(id);
        if(usuarioComum==null){
            throw new UsuarioNaoEncontradoException("Usuario com id: " + id + "nao encontrado");
        }
        if(usuarioComumDAO.existePeloEmail(usuarioComumUpdateRequest.email()) && !usuarioComum.getEmail().equals(usuarioComumUpdateRequest.email())){
            throw new EmailJaExisteException("Email: " + usuarioComumUpdateRequest.email() + "ja existe");
        }
        usuarioComum.setNome(usuarioComumUpdateRequest.nome());
        usuarioComum.setEmail(usuarioComumUpdateRequest.email());
        usuarioComum.setMatricula(usuarioComumUpdateRequest.matricula());
        usuarioComum.setSenhaHash(usuarioComumUpdateRequest.senha());
        return usuarioComumDAO.atualizar(usuarioComum);
    }


    public boolean deletar(int id) throws SQLException {
        if(!usuarioComumDAO.deletar(id)){
            throw new FalhaNaOperacaoException("Nao foi possivel deletar o usuario com id: " + id);
        }
        return true;
    }

    private UsuarioComumResponse usertoComumResponseDto(UsuarioComum usuarioComum){
        return new UsuarioComumResponse(usuarioComum.getId(), usuarioComum.getNome(), usuarioComum.getMatricula(),
                usuarioComum.getEmail(), usuarioComum.getPerfil());
    }
    private UsuarioComum createDtoToComumUser(UsuarioComumCreateRequest usuarioComumCreateRequest){
        return new UsuarioComum(usuarioComumCreateRequest.nome(),usuarioComumCreateRequest.matricula(),usuarioComumCreateRequest.email(),usuarioComumCreateRequest.senha(),usuarioComumCreateRequest.perfil());
    }
}
