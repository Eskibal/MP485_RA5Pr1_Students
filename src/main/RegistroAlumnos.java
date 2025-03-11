/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import lib.ManageFile;
import java.util.*;

/**
 *
 * @author jeanp
 */

public class RegistroAlumnos {

    public static ArrayList<Alumno> students = new ArrayList<>();
    public static ArrayList<String> lines = new ArrayList<>();
    private static Scanner scn = new Scanner(System.in);
    private static ManageFile manage = new ManageFile();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RegistroAlumnos regist = new RegistroAlumnos();

        manage.mkDirFile(); // Creates file and folder
        
        for (String line : manage.readFileToList()) {
            String[] data = line.split(";");
            
            if (data.length == 5) {
                try {
                    String name = data[0];
                    String surname = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];
                    String dni = data[4];
                    
                    students.add(new Alumno(name, surname, age, course, dni));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        int opt;
        boolean exit = false;

        do {
            System.out.println("\n-- Registro Alumnos --");
            System.out.println("1/ Agregar alumno");
            System.out.println("2/ Mostrar registros");
            System.out.println("3/ Eliminar alumno");
            System.out.println("4/ Buscar por DNI");
            System.out.println("5/ Salir");

            System.out.print("\n /: ");
            opt = scn.nextInt();
            scn.nextLine();

            switch (opt) {
                case 1:
                    regist.registerStudent();
                    break;

                case 2:
                    regist.showList();
                    break;

                case 3:
                    regist.deleteStudent();
                    break;

                case 4:
                    regist.findStudent();
                    break;

                case 5:
                    System.out.println("\nSaliendo..");
                    exit = true;
                    break;
            }

        } while (!exit);
    }

    /**
     * Creates new student and adds to students list
     */
    public void registerStudent() {
        System.out.println("\n-- Registrar Alumno --");
        System.out.print("\nNombre/: ");
        String name = scn.nextLine();
        System.out.print("Apellido/: ");
        String surname = scn.nextLine();
        System.out.print("Edad/: ");
        int age = scn.nextInt();
        scn.nextLine();
        System.out.print("Curso/: ");
        String course = scn.nextLine();
        System.out.print("DNI/: ");
        String dni = scn.nextLine();

        
        if (manage.findLine(dni) != null && !manage.findLine(dni).isEmpty()) {
            System.out.println("\nEl alumno debe tener un DNI unico\n");
            return;
        }
        
        Alumno student = new Alumno(name, surname, age, course, dni);
        
        students.add(student);
        manage.writeFile(student.toString());
        System.out.println("\nAlumno registrado!");
    }

    /**
     * Shows students list
     */
    public void showList() {
        System.out.println("\n-- Registro de Alumnos --");
        manage.readFile();
    }

    /**
     * Deletes student by DNI
     */
    public void deleteStudent() {
        System.out.println("\n-- Eliminar Alumno --");
        manage.readFile();
        System.out.println("\nIntroduzca su DNI");
        System.out.print("/: ");
        String dni = scn.nextLine();
        
        
        manage.removeLineContaining(dni);
        System.out.println("\nAlumno eliminado del registro!");
    }

    /**
     * Finds student by DNI
     */
    public void findStudent() {
        System.out.println("\n-- Buscar Alumno --");
        System.out.println("\nIntroduzca su DNI");
        System.out.print("/: "); String dni = scn.nextLine();
        
        System.out.println("\nAlumno encontrado!");
        System.out.println(manage.findLine(dni));
    }

}
