package org.example.jana.model.user;


import org.example.jana.model.user.enums.Perfil;

import java.util.Objects;

public abstract class Usuario {
    private int id;
    private String nome;
    private String matricula;
    private String email;
    private String senhaHash;
    private Perfil perfil;

    public Usuario(int id, String nome, String matricula, String email, String senhaHash, Perfil perfil) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.matricula = Objects.requireNonNull(matricula, "Matrícula não pode ser nula");
        this.email = validarEmail(email);
        this.senhaHash = Objects.requireNonNull(senhaHash, "Senha não pode ser nula");
        this.perfil = Objects.requireNonNull(perfil, "Perfil não pode ser nulo");
    }

    public Usuario(String nome, String matricula, String email, String senhaHash, Perfil perfil) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.matricula = Objects.requireNonNull(matricula, "Matrícula não pode ser nula");
        this.email = validarEmail(email);
        this.senhaHash = Objects.requireNonNull(senhaHash, "Senha não pode ser nula");
        this.perfil = Objects.requireNonNull(perfil, "Perfil não pode ser nulo");
    }

    private String validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email deve conter '@'");
        }
        return email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = Objects.requireNonNull(matricula, "Matrícula não pode ser nula");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarEmail(email);
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = Objects.requireNonNull(senhaHash, "Senha não pode ser nula");
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = Objects.requireNonNull(perfil, "Perfil não pode ser nulo");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", email='" + email + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}