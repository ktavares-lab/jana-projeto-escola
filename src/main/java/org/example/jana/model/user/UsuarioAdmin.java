package org.example.jana.model.user;


import org.example.jana.model.user.enums.Perfil;

public class UsuarioAdmin extends Usuario {
    private String cpf;

    public UsuarioAdmin(int id, String nome, String matricula, String cpf, String email, String senhaHash, Perfil perfil) {
        super(id, nome, matricula, email, senhaHash, perfil);
        this.cpf = validarCPF(cpf);
    }

    private String validarCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        String cpfLimpo = cpf.replaceAll("\\D", "");
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }
        return cpfLimpo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = validarCPF(cpf);
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + getEmail() + '\'' +
                ", perfil=" + getPerfil() +
                '}';
    }
}