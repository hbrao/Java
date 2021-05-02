package language.cloning;

import java.io.*;
import java.util.*;

public final class Employee implements Serializable , Cloneable {
    private String id;
    private String firstName;
    private String lastName;
    private Double salary;
    private List<Project> projects;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", projects=" + projects.size() +
                '}';
    }

    private Employee(EmpBuilder builder) {
        this.id = builder.id.toString();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.projects = builder.projects;
    }

    public static EmpBuilder getEmployeeBuilder(String firstName, String lastName) {
        return new Employee.EmpBuilder(firstName, lastName);
    }

    private static  class Project implements Cloneable,  Serializable {
        String projectName;
        String projectDescription;

        public Project(String projectName, String projectDescription) {
            this.projectName = projectName;
            this.projectDescription = projectDescription;
        }
    }

    private static class EmpBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private Double salary;
        private List<Project> projects;

        public EmpBuilder(String firstName, String lastName) {
            this.id = UUID.randomUUID();
            this.firstName = firstName;
            this.lastName = lastName;
            this.projects = new ArrayList<>();
        }

        public EmpBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public EmpBuilder project(String name, String description) {
            this.projects.add(new Project(name, description));
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public static void main(String[] args) {
        Employee emp1 = Employee.getEmployeeBuilder("Manasa", "Devi")
                                .salary(100_000.00)
                                .project("Transfers", "BDD implementation")
                                .build();

        //Shallow clone. emp1Clone and emp1 point to same project list.
        Employee emp1Clone = null;
        try {
             emp1Clone = (Employee) emp1.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        //Deep clone via serialization.
        Employee emp1Clone2 = cloneThroughSerialization(emp1);

        emp1.firstName = "Manasaa";
        emp1.projects.add(new Project("Transfers", "Manual testing"));

        System.out.println("Employee obj:" + emp1);
        System.out.println("Employee obj clone:" + emp1Clone);
        System.out.println("Employee obj deep clone:" + emp1Clone2);
    }

    public  static <T> T cloneThroughSerialization(T obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            //Write object graph to byte output stream
            oos.writeObject(obj);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException ex ) {
            ex.printStackTrace();
            return null;
        }
    }
}
