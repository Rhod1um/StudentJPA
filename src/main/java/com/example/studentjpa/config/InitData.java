package com.example.studentjpa.config;

import com.example.studentjpa.model.Student;
import com.example.studentjpa.repository.StudentRepository;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class InitData implements CommandLineRunner { //hvorfor gør vi dette
    //commandlinerunner er bare en måde at gøre noget automatisk når
    //programmet starter. Her sætter vi automatisk list data ind i databasen
    //fordi vi laver spring.jpa.hibernate.ddl-auto=create-drop under application.properties
    //Hvorfor hedder det commandlinerunner? det er nok fra game dage siger erik. Det har intet med commandline at gøre
    //ved aflevering af 24t eksamen så laver underviserne deres egen database da de jo ikke har adgang til vores
    //for at de får lidt data ind automatisk kan man enten bruge commandlinerunner eller en sql fil med inserts
    //som de køre. Det er alt efter hvad man vil.

    //ved at ændre create-drop til update så sletter den ikke data når man starter og så skal
    //denne klasse ikke køres

    @Autowired
    StudentRepository studentRepository; //autowire implementere interface
    //alternativ til constructor
    //der er lavet et StudentRepository "bag om" som bruges her ud fra vores interface

    @Override
    public void run(String... args) throws Exception {

        if (3<4){} //erik gør sådan at han har en simpel if som alt det nedenunder som putter data i databasen
        //står inden i. Så kan han nemt ændre iffen så den enten køre eller ikke køre alt efter hvad man ønsker

        Student s1 = new Student();
        s1.setId(1);
        s1.setName("V");
        s1.setBornDate(LocalDate.now());
        s1.setBornTime(LocalTime.now());
        studentRepository.save(s1);   //save laver både insert og update, update hvis den allerede har en PK
        System.out.println("saves students");
        List<Student> students = studentRepository.findAll();
        System.out.println("Studerende: " + students.toString());
        System.out.println("Amound of students: " + students.size());
    } //ofte vil man ikke bruge getmapping hvis man vil gemme ngoet i datatbasen men erik er ligeglas
    //dog er det en ting at vide til databasen
}
