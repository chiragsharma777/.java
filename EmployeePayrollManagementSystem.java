import java.util.Scanner;

class Employee {

    int id;
    String name;
    String department;
    double basicSalary;
    double hra;
    double da;
    double grossSalary;
    double tax;
    double netSalary;

    Employee(int id, String name, String department, double basicSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    void calculateSalary() {

        hra = basicSalary * 0.20;
        da = basicSalary * 0.10;

        grossSalary = basicSalary + hra + da;

        if (grossSalary >= 100000) {
            tax = grossSalary * 0.15;
        } else if (grossSalary >= 50000) {
            tax = grossSalary * 0.10;
        } else if (grossSalary >= 30000) {
            tax = grossSalary * 0.05;
        } else {
            tax = 0;
        }

        netSalary = grossSalary - tax;
    }

    void display() {

        calculateSalary();

        System.out.println("\n================================");
        System.out.println("       EMPLOYEE DETAILS");
        System.out.println("================================");
        System.out.println("Employee ID     : " + id);
        System.out.println("Employee Name   : " + name);
        System.out.println("Department      : " + department);
        System.out.println("--------------------------------");
        System.out.println("Basic Salary    : Rs. " + basicSalary);
        System.out.println("HRA             : Rs. " + hra);
        System.out.println("DA              : Rs. " + da);
        System.out.println("Gross Salary    : Rs. " + grossSalary);
        System.out.println("Tax             : Rs. " + tax);
        System.out.println("Net Salary      : Rs. " + netSalary);
        System.out.println("================================");
    }
}

public class EmployeePayrollManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details of Employee " + (i + 1));

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Basic Salary: ");
            double salary = sc.nextDouble();

            employees[i] =
                    new Employee(id, name, department, salary);
        }

        int choice;

        do {

            System.out.println("\n========== PAYROLL MENU ==========");
            System.out.println("1. Display All Employees");
            System.out.println("2. Search Employee");
            System.out.println("3. Display Highest Salary");
            System.out.println("4. Calculate Total Payroll");
            System.out.println("5. Exit");
            System.out.println("==================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    for (int i = 0; i < employees.length; i++) {
                        employees[i].display();
                    }

                    break;

                case 2:

                    System.out.print("Enter Employee ID: ");
                    int searchId = sc.nextInt();

                    boolean found = false;

                    for (int i = 0; i < employees.length; i++) {

                        if (employees[i].id == searchId) {
                            employees[i].display();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Employee not found.");
                    }

                    break;

                case 3:

                    Employee highest = employees[0];

                    highest.calculateSalary();

                    for (int i = 1; i < employees.length; i++) {

                        employees[i].calculateSalary();

                        if (employees[i].netSalary > highest.netSalary) {
                            highest = employees[i];
                        }
                    }

                    System.out.println("\nEmployee with Highest Salary:");
                    highest.display();

                    break;

                case 4:

                    double totalPayroll = 0;

                    for (int i = 0; i < employees.length; i++) {
                        employees[i].calculateSalary();
                        totalPayroll =
                                totalPayroll + employees[i].netSalary;
                    }

                    System.out.println(
                            "\nTotal Monthly Payroll: Rs. "
                            + totalPayroll
                    );

                    break;

                case 5:
                    System.out.println(
                            "Exiting Payroll Management System..."
                    );
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}