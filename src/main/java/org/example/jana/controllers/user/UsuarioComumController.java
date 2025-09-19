package org.example.jana.controllers.user;




import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jana.dao.user.UsuarioComumDAO;
import org.example.jana.service.user.UsuarioComumService;


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
        resp.setContentType("application/json");
        try {
            var usuario = usuarioComumService.listar();
            if(usuarios.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.getWriter().write(gson.toJson(usuarios));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, String> error = new HashMap<>();
            error.put("Erro", e.getMessage());
            resp.getWriter().write(gson.toJson(error));
        }
    }
}
