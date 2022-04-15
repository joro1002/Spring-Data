import Model.Employee;
import Model.Town;

import javax.persistence.EntityManager;
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
        employeesFromDepartment();
    }

    private void employeesFromDepartment() {
        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.name = 'Research and Development' ORDER BY e.salary, e.id", Employee.class)
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
