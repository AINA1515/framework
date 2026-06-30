package core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.ControllerResultDTO;
import dto.UrlMappingDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class DispatcherServlet extends HttpServlet {
    List<Class<?>> controllerClasses = new ArrayList<>();
    Map<UrlMappingDTO, ControllerResultDTO> map;

    @Override
    public void init() throws ServletException {
        try {
            String controllersPackage = getServletConfig().getInitParameter("controller");
            controllerClasses = utils.ControllerUtils.getControllerClasses(controllersPackage);

            map = utils.ControllerUtils.getAllMapUrlMethod(controllerClasses);

            System.out.println("Classes de contrôleur trouvées dans le package '" + controllersPackage + "':");
            for (Class<?> clazz : controllerClasses) {
                System.out.println(clazz.getName());
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors du scan des contrôleurs", e);
        }
    }

    public void affichage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        String path = request.getPathInfo();

        if (path == null || path.isEmpty()) {
            path = request.getServletPath();
        }

        if (path == null || path.isEmpty()) {
            path = "/";
        }

        String httpMethod = request.getMethod();

        UrlMappingDTO key = new UrlMappingDTO(path, httpMethod);

        ControllerResultDTO found = map.get(key);

        // =========================
        // URL CONNUE
        // =========================
        if (found != null) {

            Method method = found.getMethod();
            Class<?> controllerClasse = found.getClasse();

            response.getWriter().println("=== ROUTE TROUVEE ===");
            response.getWriter().println("URL : " + path);
            response.getWriter().println("HTTP : " + httpMethod);
            response.getWriter().println("Controller : " + controllerClasse.getName());
            response.getWriter().println("Méthode Java : " + method.getName());
            response.getWriter().println();

            try {
                Object controllerInstance = controllerClasse.getDeclaredConstructor().newInstance();

                // Sprint 2 — méthode simple sans paramètre
                if (method.getParameterCount() == 0) {

                    Object result = method.invoke(controllerInstance);

                    response.getWriter().println("=== RESULTAT DE L'EXECUTION ===");

                    if (method.getReturnType().equals(Void.TYPE)) {
                        response.getWriter().println("(méthode void exécutée avec succès)");
                    } else {
                        response.getWriter().println(String.valueOf(result));
                    }

                    // Sprint 3 — méthode GET/POST avec (HttpServletRequest, HttpServletResponse)
                } else {

                    Class<?>[] paramTypes = method.getParameterTypes();

                    if (paramTypes.length == 2
                            && paramTypes[0].getName().equals("jakarta.servlet.http.HttpServletRequest")
                            && paramTypes[1].getName().equals("jakarta.servlet.http.HttpServletResponse")) {

                        method.invoke(controllerInstance, request, response);

                    } else {
                        response.getWriter().println("=== ERREUR ===");
                        response.getWriter().println("Signature non supportée : " + method.getName());
                    }
                }

            } catch (Exception e) {
                response.getWriter().println("=== ERREUR D'EXECUTION ===");
                response.getWriter().println(e.getCause() != null ? e.getCause().toString() : e.toString());
            }

            return;
        }

        // =========================
        // URL INCONNUE
        // =========================
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().println(" Route introuvable");
        response.getWriter().println("URL : " + path);
        response.getWriter().println("HTTP : " + httpMethod);
        response.getWriter().println();

        response.getWriter().println("=== ROUTES DISPONIBLES ===");
        for (UrlMappingDTO mapping : map.keySet()) {
            ControllerResultDTO r = map.get(mapping);
            response.getWriter().println(
                    mapping.getMethod()
                            + " "
                            + mapping.getUrl()
                            + " -> "
                            + r.getClasse().getSimpleName()
                            + "."
                            + r.getMethod().getName());
        }

        response.getWriter().println();
        response.getWriter().println("=== CONTROLLERS ===");
        for (Class<?> controllerClass : controllerClasses) {
            response.getWriter().println(controllerClass.getName());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        affichage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        affichage(request, response);
    }
}