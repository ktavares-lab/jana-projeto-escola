package org.example.jana.controllers.user;




import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jana.dao.user.UsuarioComumDAO;
import org.example.jana.service.user.UsuarioComumService;
import org.example.jana.utils.RespostaUtils;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

@WebServlet("/api/usuarios")
public class UsuarioComumController extends HttpServlet {
    private UsuarioComumService usuarioComumService;
    Gson gson = new Gson();
    @Override
    public void init() {
        this.usuarioComumService= new UsuarioComumService(new UsuarioComumDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            var usuarios = usuarioComumService.listar();
            RespostaUtils.sendSuccess(resp, 200, usuarios);
        } catch (SQLException e) {
            RespostaUtils.sendError(resp, 500, "Erro ao buscar usuarios: " + e.getMessage());
        }

    }
}
