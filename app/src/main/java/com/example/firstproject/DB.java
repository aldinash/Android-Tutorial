package com.example.firstproject;

import java.util.ArrayList;

public class DB {
    private static DB database = null;
    public ArrayList<Course> courses;
    public ArrayList<Exam> exams;
    public ArrayList<Assignment> assignments;
    public ArrayList<Task> tasks;

    private DB() {
        courses = new ArrayList<>();
        exams = new ArrayList<>();
        assignments = new ArrayList<>();
        tasks = new ArrayList<>();
        courses.add(new Course("CS2340", "Objects and Design", "Pedro Feijoo"));
        courses.add(new Course("CS1332", "Data Structures and Algorithms", "Frederick Faulkner"));
        exams.add(new Exam("CS1332", "02/05/2024", "IC103", "10:30"));
        exams.add(new Exam("CS2110", "02/14/2024", "COC17", "8:20"));
        assignments.add(new Assignment("First Project", "02/06/2024", "CS 2340"));
        assignments.add(new Assignment("HW3", "02/11/2024", "CS 2110"));
        tasks.add(new Task("Email 2340 Professor"));
        tasks.add(new Task("Sign up for Summer courses"));
    }

    public static DB DB() {
        if (database == null) {
            database = new DB();
        }
        return database;
    }
}
