package com.example.studentjpa.controller;

import com.example.studentjpa.model.Student;
import com.example.studentjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//Rest controller = json, har det noget med RESTful API at gøre?

@RestController //husk restcontroller til json
//i gamle dage brugte man xml og SOAP. json er meget simplere
//json syntax:
//tuborgparantes er object
//key-value: "name": "Anna"
//har ingen typer. navn + værdi. ligeglad med datatyper
//men er der ikke gåseøjne om er det i hvert fald et tal
public class StudentRestController {
    //spring.h2.console.enabled=true
    //skal skrives i application.properties under ressources
    //man kan køre sql fil automatisk hvis det ligger i ressourcer pakken

    //spring.jpa.hibernate.ddl-auto=create-drop - den her sletter alt når man starter programmet. Godt
    //til Unit tests
    //spring.jpa.hibernate.ddl-auto=update - sletter ikke det forrige, ved ændringer så lægges de
    //"ovenpå" det der var i forvejen, tilføjes, frem for det hele slettes og laves på ny. Gemmer data
    //spring.jpa.hibernate.ddl-auto=none -

    //spring.jpa.defer-datasource-initialization=true
    //spring.sql.init.mode=always - de to sætninger samt den øvre på none gør at sql filer i template
    //automatisk kører

    //i pom.xml kan man lægge dependencien org.hibernate.validator. Der er normal hibernator og
    //så er dette en ekstra ud over den, som gør noget ekstra. Søren brugte det

    @Autowired
    StudentRepository studentRepository; //interfacet må hedde det man vil, IStudentRepo fx.
    //den skal ikke hedde noget bestemt, Spring boot laver stadig den underliggende klasse for repo som bruges

    //erik brugte debuggeren til at se på den klasse som laves under interface-repoet


    @GetMapping("/students") //man må ikke skrive add, ingen verbum i get metode
    public List<Student> students() {
        return studentRepository.findAll();
    }


    @GetMapping("/teststudents") //man må ikke skrive add, ingen verbum i get metode
    public List<Student> testStudents() {
        Student s1 = new Student();
        s1.setName("V");
        s1.setBornDate(LocalDate.now());
        s1.setBornTime(LocalTime.now());
        studentRepository.save(s1);   //save laver både insert og update, update hvis den allerede har en PK
        System.out.println("saves students");
        List<Student> students = studentRepository.findAll();
        System.out.println("Studerende: " + students.toString());
        System.out.println("Amound of students: " + students.size());
     //ofte vil man ikke bruge getmapping hvis man vil gemme ngoet i datatbasen men erik er ligeglas
    //dog er det en ting at vide til databasen

        return studentRepository.findAll();
    }
}
