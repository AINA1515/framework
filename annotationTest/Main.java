public class Main {
    public static void main(String[] args) throws Exception {
        ServiceFichier monService = new ServiceFichier();
        System.out.println("--- TEST 1 : Un GUEST essaie de lire ---");
        ControlleurSecurite.executerMethode(monService, "lireFichier",
                "GUEST");
        // Attendu : Accès autorisé (Public)
        System.out.println("\n--- TEST 2 : Un GUEST essaie de supprimer---");
        ControlleurSecurite.executerMethode(monService,
                "supprimerFichier", "GUEST");
        // Attendu : Accès refusé ! Rôle ADMIN requis.

        System.out.println("\n--- TEST 3 : Un ADMIN essaie de supprimer---");
        ControlleurSecurite.executerMethode(monService,
                "supprimerFichier", "ADMIN");
        // Attendu : Accès autorisé
    }
}