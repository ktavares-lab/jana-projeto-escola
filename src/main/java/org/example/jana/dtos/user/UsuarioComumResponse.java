package org.example.jana.dtos.user;


import org.example.jana.model.user.enums.Perfil;

public record UsuarioComumResponse(
        int id,
        String nome,
        String matricula,
        String email,
        Perfil perfil
) {
}
