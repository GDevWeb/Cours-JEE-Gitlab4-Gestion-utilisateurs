import com.entreprise.employee.*;
import com.entreprise.exception.InvalidEmployeeException;
import com.entreprise.util.EmployeeDataLoader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner scanner = new Scanner(System.in);

        // Chargement depuis le fichier CSV
        try {
            List<Employee> loaded = EmployeeDataLoader.loadEmployees("employees.csv");
            for (Employee e : loaded) {
                service.addEmployee(e);
            }
        } catch (IOException | InvalidEmployeeException e) {
            System.out.println("❌ Erreur au chargement du fichier : " + e.getMessage());
        }

        // Boucle principale du menu
        boolean running = true;
        while (running) {
            System.out.println("""
                
                ========= MENU =========
                1. Ajouter un employé
                2. Afficher tous les employés
                3. Filtrer par département
                4. Trier par salaire décroissant
                5. Calculer le total des salaires
                6. Afficher l'employé le mieux payé
                7. Quitter
                ========================
                """);

            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // consomme le retour à la ligne

            switch (choix) {
                case 1 -> {
                    try {
                        System.out.print("ID : ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nom : ");
                        String name = scanner.nextLine();
                        System.out.print("Département : ");
                        String dept = scanner.nextLine();
                        System.out.print("Salaire : ");
                        double salary = scanner.nextDouble();

                        Employee newEmp = new Employee(id, name, dept, salary);
                        service.addEmployee(newEmp);
                        System.out.println("✅ Employé ajouté !");
                    } catch (InvalidEmployeeException e) {
                        System.out.println("❌ Erreur : " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("❌ Saisie invalide.");
                        scanner.nextLine(); // vider la ligne
                    }
                }

                case 2 -> service.displayAllEmployees();

                case 3 -> {
                    System.out.print("Nom du département : ");
                    String dept = scanner.nextLine();
                    List<Employee> filtered = service.filterByDepartment(dept);
                    if (filtered.isEmpty()) {
                        System.out.println("Aucun employé trouvé dans ce département.");
                    } else {
                        filtered.forEach(System.out::println);
                    }
                }

                case 4 -> {
                    List<Employee> sorted = service.sortBySalaryDescending();
                    sorted.forEach(System.out::println);
                }

                case 5 -> System.out.println("💰 Total des salaires : " + service.calculateTotalSalary());

                case 6 -> {
                    Employee top = service.getHighestPaidEmployee();
                    if (top != null) {
                        System.out.println("🏆 Employé le mieux payé : " + top);
                    } else {
                        System.out.println("Aucun employé enregistré.");
                    }
                }

                case 7 -> {
                    System.out.println("👋 Au revoir !");
                    running = false;
                }

                default -> System.out.println("⛔ Choix invalide.");
            }
        }

        scanner.close();
    }
}
