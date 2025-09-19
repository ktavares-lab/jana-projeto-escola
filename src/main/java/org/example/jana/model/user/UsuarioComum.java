package org.example.jana.model.user;


import org.example.jana.model.user.enums.Perfil;

public class UsuarioComum extends Usuario {

    public UsuarioComum(int id, String nome, String matricula, String email, String senhaHash, Perfil perfil) {
        super(id, nome, matricula, email, senhaHash, perfil);
    }
    public UsuarioComum( String nome, String matricula, String email, String senhaHash, Perfil perfil) {
        super(nome, matricula, email, senhaHash, perfil);
    }
    @Override
    public String toString() {
        return "Comum{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", perfil=" + getPerfil() +
                '}';
    }
}