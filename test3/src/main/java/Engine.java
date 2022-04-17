import Model.Address;
import Model.Employee;
import Model.Project;
import Model.Town;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Engine implements Runnable{
    private final EntityManager entityManager;
    private final Scanner scanner;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        // changeCasingEx2();
        //containsEmployeeEx3();
       // employeesWithSalaryOver50000();
        //employeesFromDepartment();
       // addressesWithEmployeeCount();
       // getEmployeeWithProject();
        findLatest10Projects();
       // increaseSalaries();
        //findEmployeesByFirstName();
       // employeesMaximumSalaries();
    }

    private void employeesMaximumSalaries() {
         List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "GROUP BY e.department.id " +
                        "HAVING " +
                        "MAX(e.salary) NOT BETWEEN 30000 AND 70000", Employee.class)
                 .getResultList();
    }

    private void findEmployeesByFirstName() {
        entityManager
                .createQuery("SELECT e FROM Employee  e WHERE e.firstName LIKE 'SA%'", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s - %s - %.2f%n",
                            employee.getFirstName(), employee.getLastName(), employee.getJobTitle(),employee.getSalary());
                });

    }

    private void increaseSalaries() {
        entityManager.getTransaction().begin();
       int affectedRows =  entityManager
                .createQuery("UPDATE Employee e " +
                        "SET e.salary = e.salary * 1.12" +
                        "WHERE e.department.id IN (1,2,4,11)")
                        .executeUpdate();

        entityManager.getTransaction().commit();
        System.out.println("Affected Rows" + affectedRows);
        entityManager
                .createQuery("SELECT e FROM Employee  e " +
                        "WHERE e.department.id IN (1,2,4,11)", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s (%.2f)%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });
    }

    private void findLatest10Projects() {
        List<Project> projects = entityManager
                .createQuery("SELECT p FROM Project p ORDER BY p.id DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.sort(Comparator.comparing(Project::getName));
        projects.forEach(project -> {
            System.out.printf("Project name: %s%n", project.getName());
            System.out.printf("\tProject Description: %s%n", project.getDescription());
            System.out.printf("\tProject Start Date: %s%n", project.getStartDate());
            System.out.printf("\tProject End Date: %s%n", project.getEndDate());
        });
    }

    private void getEmployeeWithProject() {
        int id = Integer.parseInt(scanner.nextLine());
        Employee employee = entityManager
                .find(Employee.class, id);
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee
                .getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("\t%s%n", project.getName());
                });
    }

    private void addressesWithEmployeeCount() {
        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses
                .forEach(address -> {
                    System.out.printf("%s %s %d%n",
                            address.getText(), address.getTown().getName(), address.getEmployees().size());
                });

    }


    private void employeesFromDepartment() {
        entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = 'Research and Development' ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(Employee -> {
                    System.out.printf("%s %s from Research and Development - $%.2f%n",
                            Employee.getFirstName(), Employee.getLastName(), Employee.getSalary());
                });
    }

    private void employeesWithSalaryOver50000() {
        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void containsEmployeeEx3() {
        String fullName = scanner.nextLine();
        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e WHERE concat(e.firstName, ' ', e.lastName) = :name", Employee.class)
                .setParameter("name", fullName)
                .getResultList();
        System.out.println(employees.size() == 0 ? "No" : "Yes");

    }

    private void changeCasingEx2() {
        List<Town> towns = entityManager.
                createQuery("SELECT t FROM Town t WHERE length(t.name) <= 5", Town.class)
                .getResultList();
        entityManager.getTransaction().begin();
        towns.forEach(entityManager::detach);

        for (Town town: towns) {
            town.setName(town.getName().toLowerCase());
        }
        towns.forEach(entityManager::merge);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
