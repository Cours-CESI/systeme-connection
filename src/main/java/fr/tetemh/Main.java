package fr.tetemh;

import fr.tetemh.cclass.Student;
import fr.tetemh.managers.StudentManager;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    @Getter
    private static StudentManager studentManager;
    @Getter @Setter
    private static Student currentStudent;

    public static void main(String[] args) {
        Main.studentManager = new StudentManager();

        Main.recur();

    }

    private static void recur() {
        Scanner in = new Scanner(System.in);
        System.out.println("Login / Register / Logout");
        String choice = in.next();

        switch(choice) {
            case "login":
                System.out.print("Username : ");
                String username = in.next();
                System.out.print("Mot de passe : ");
                String password = in.next();

                if(username == null && password == null) {
                    System.out.println("Veuillez renseigner un utilisateur et un mot de passe");
                    Main.recur();
                }
                Student student = Main.getStudentManager().getStudents().get(username);

                if(student == null) {
                    System.out.println("Cette utilisateur n'existe pas !");
                    Main.recur();
                }
                if(!student.getPassword().equals(password)) {
                    System.out.println("Le mot de passe renseigner est faux !");
                    Main.recur();
                }

                Main.setCurrentStudent(Main.getStudentManager().getStudents().get(username));
                System.out.println("Tu es connecté avec : " + Main.getCurrentStudent().getName());

                Main.recur();
                break;
            case "register":
                System.out.print("Utilisateur : ");
                String reg_username = in.next();
                if(reg_username == null) {
                    System.out.println("Veuillez renseigner un utilisateur !");
                    Main.recur();
                }
                if(Main.getStudentManager().getStudents().get(reg_username) != null) {
                    System.out.println("Cette utilisateur existe déjà !");
                    Main.recur();
                }

                int reg_age = 0;
                do {
                    System.out.print("Age : ");
                    reg_age = in.nextInt();
                } while (reg_age == 0);

                String reg_password = "";
                do {
                    System.out.print("Password : ");
                    reg_password = in.next();
                } while (reg_password.isEmpty());

                Main.setCurrentStudent(Main.getStudentManager().createStudent(reg_username, reg_age, reg_password));

                Main.recur();
                break;
            case "logout":
                if(Main.getCurrentStudent() == null) {
                    System.out.println("Vous n'êtes pas");
                } else {
                    Main.setCurrentStudent(null);
                }
                Main.recur();
                break;

            case "info":
                if(Main.getCurrentStudent() == null) {
                    System.out.println("Vous n'êtes pas");
                } else {
                    System.out.println("Nom : " + Main.getCurrentStudent().getName());
                    System.out.println("Age : " + Main.getCurrentStudent().getAge());
                }

                Main.recur();
                break;
        }
    }
}