package framework;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        processRequest(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");

        String path = req.getPathInfo();

        // CAS /app-test/
        if (path == null || path.equals("/")) {
            resp.getWriter().println("FrontController fonctionne !");
            return;
        }

        resp.getWriter().println("Route : " + path);
    }
}