package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import Utilities.Table;
import models.GradesofCourses;
import models.GradePoint;
import models.UnitAttained;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Number of courses you offer?: ");
        int numberOfCourses = getTotalCourse();

        String[][] courseProfile = new String[numberOfCourses][3];
        for (int i = 0; i < numberOfCourses; i++) {
            String[] newProfile = new String[1];
            newProfile = getCourseProfile(i + 1);
            courseProfile[i] = newProfile;
        }
        
        String[][] gradedCourseProfile = GradesofCourses.getGrade(courseProfile);
        String[][] gradedUnitCourseProfile = UnitAttained.getUnitAttained(gradedCourseProfile);
        double gradePointAverage = GradePoint.getGradePoint(gradedUnitCourseProfile);

        Table.printTable(gradedUnitCourseProfile);

        String formattedGPA = String.format("%.2f", gradePointAverage);
        System.out.printf("\n\nYour GPA is = %s to 2 decimal places.\n", formattedGPA);

        scanner.close();
    }

    private static int getTotalCourse() {
        int numberOfCourses = 0;
        try {
            numberOfCourses = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Kindly insert a number not letters or alphabets");
            scanner.nextLine();
            numberOfCourses = getTotalCourse();
        }
        return numberOfCourses;
    }
    
    private static String[] getCourseProfile(int index) {
        String[] courseProfile = new String[3];
        String courseName = "";
        int courseCredit = 0;
        int score = 0;

        try {

            System.out.println("Enter name of course " + index + ": ");
            if (scanner.hasNextLine()) {
                courseName = scanner.nextLine();
            }

            System.out.println("Enter the credit unit: ");
            while (!scanner.hasNextInt()) {
                System.err.println("kindly input a number not alphabet or symbol.");
                scanner.nextLine();
            }
            courseCredit = scanner.nextInt();
            scanner.nextLine();
            while (courseCredit <= 0) {
                System.err.println("Kindly insert a course credit greater than zero");
                courseCredit = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Enter the score attained: ");
            while (!scanner.hasNextInt()) {
                System.err.println("Kindly input a valid score");
                scanner.nextLine();
            }
            score = scanner.nextInt();
            scanner.nextLine();
            while (score < 0 || score > 100) {
                System.err.println("Kindly insert a score within 0 and 100");
                score = scanner.nextInt();
                scanner.nextLine();
            }

            courseProfile[0] = courseName;
            courseProfile[1] = String.valueOf(courseCredit); 
            courseProfile[2] = String.valueOf(score);
        } catch (InputMismatchException e) {
            System.err.println("Kindly input correct details");
            getCourseProfile(index);
        }

        return courseProfile;
    }
}