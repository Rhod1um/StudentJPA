package com.example.studentjpa.model;

import com.example.studentjpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*; //nogle gange skal man skrive denne manuelt
//den gør at assert equals virker

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest //husk spring boot test annotation, gør forarbejde, ikke nødvendig
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    //man kan højreklikke på hvilken som heslt klasse i venstre sidebar og klikke Run

    @Test
    void testName(){
        //find navn
        //starter med find by id som er intrincsiv metode
        //senere find by name
        var obj = studentRepository.findById(1);
        Optional<Student> student = studentRepository.findById(1);
        //optional er en wrapper klasse, kan indeholde hvilken som heslt klasse
        //der stpr Optional<Student> da den kan returnere to forskellige typer, Student eller null
        //da man ikke kan specificere at begge kan retiurneres, skriver vi optional student
        //Optional er et meget simpelt objekt som både kan pege på et objekt og pege på ingenting
        if (student.isPresent()) { //ikke så godt stillet op for hvis der ikke er det objekt kommer den ikke
            //ind her, så bliver testen positiv. Lav else
            Student s1 = student.get();
            assertEquals(1, s1.getId());
        } else {
            assertEquals("det gik ikke", ""); //underlig hardkodning som bare gør at testen fejler
            //hvis den ikke fandt den studerende
        }
    }

    @Test
    void testNameOneLine(){
        assertEquals(1, studentRepository.findById(1).orElse(new Student()).getId());
        //finbyid returnere optional object og det har metode orElse som laver en ny Student
        //findbyid optional objekt, enten finder den noget eller også finder den ikke noget
        //bemærk, besvær med de sidste paranteser (new Student()).getId());, gav fejl først
        //new Student gøres bare så testen fejler, det er ikke fordi der lægges en new student i databasen
    }

    //findByName metode bruges ofte til eksamen i 24t
    //se repo niterface, der laves den
    @Test
    void testStudentByName(){ //nu kan den finde findByName metode fordi vi har lavet den i repo interfacet
        assertEquals("V", studentRepository.findByName("V").orElse(new Student()).getName());
        //hvis den ikke finder en studerende med navn V så skal orElse udføres og bare fejle.
        //det er lidt mærkeligt men der laves bare en ny student, som i hvert fald ikke har navnet V
        //den har et tomt navn og derfor fejler testen.

        //entitetsklasser skal her have en tom constructor så ovennævne kan gøres, ellers skal man
        //skrive et navn når man laver new student. Kan man også gøre og skrive et eller andet som
        //gør at testen fejler
    }

    //Between er sql keyword bruges til datoer for at få rækker som ligger mellem de givne datoer
    //kan også bruges for tal generelt

    @Test
    void testStudentByNameExist(){ //test om den overhoved findes, den studerende
        assertEquals(true, studentRepository.findByName("V").isPresent());
    }

}