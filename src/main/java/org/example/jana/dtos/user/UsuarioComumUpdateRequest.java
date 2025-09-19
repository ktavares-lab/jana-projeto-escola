package org.example.jana.dtos.user;


import org.example.jana.model.user.enums.Perfil;

public record UsuarioComumUpdateRequest(
        String nome,
        String matricula,
        String email,
        String senha,
        Perfil perfil
) {
}
