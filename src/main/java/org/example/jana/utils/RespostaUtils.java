package org.example.jana.utils;



import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jana.dtos.utils.ErrorResponseDto;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.time.Instant;
import org.slf4j.Logger;

public class RespostaUtils {
    private static final Gson gson = new Gson();
    private static final String contentType = "application/json";
    private static final Logger logger = LoggerFactory.getLogger(RespostaUtils.class);

    private RespostaUtils(){
        throw new UnsupportedOperationException("Classe utilitaria nao deve ser instanciada");
    }

    public static void sendError(HttpServletResponse response, int codeHttp, String message) {
        try {
            response.setStatus(codeHttp);
            response.setContentType(contentType);
            var errorResponseDto = new ErrorResponseDto(message, Instant.now().toString(), codeHttp);
            response.getWriter().write(gson.toJson(errorResponseDto));
            logger.info("Erro enviado: HTTP {} - {}", codeHttp, message);
        } catch (IOException e){
            logger.error("Erro de IOException", e);
        } catch (Exception e){
            logger.error("Erro interno", e);
        }
    }
    public static void sendSuccess(HttpServletResponse response, int codeHttp, Object body) {
        try {
            response.setStatus(codeHttp);
            response.setContentType(contentType);
            response.getWriter().write(gson.toJson(body));
            response.getWriter().flush();
            logger.info("json enviado com sucesso!");
        } catch (IOException e) {
            logger.error("Erro ao enviar json de sucesso", e);
        }
    }


}
