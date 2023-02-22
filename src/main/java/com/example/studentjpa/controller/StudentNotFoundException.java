package com.example.studentjpa.controller;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String name){
        super("Student not found: " + name);
    } //gammeldags måde at lave sin egen exception. Erik vil senere vise en ny måde
}
