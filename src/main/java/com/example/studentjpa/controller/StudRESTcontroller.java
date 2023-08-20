package com.example.studentjpa.controller;

import com.example.studentjpa.model.Student;
import com.example.studentjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudRESTcontroller {

    @Autowired
    StudentRepository studentRepository;

    //@GetMapping("/stud1/{name}") //tager navn som parameter
    //public Student getStud1ByName(@PathVariable String name){
       // return new Student(name);
    //}
    //er udkommenteret fordi der er ambigous mapping så det ike kan køre

    @GetMapping("/virker") //tager navn som parameter
    public String virker(){
        return "virker";
    }

    @GetMapping("/stud1/{name}") //tager navn som parameter
    public Student getStud1ByName(@PathVariable Optional<String> name){ //virker ikke, kommer ikke ind på det her endpoint
        //ved at bruge Optional som wrapper en string gør vi at man kan komme hen
        //på /stud1 endpointet uden der er specificeret en variabel til name.
        //hvis ikke man gør dette kan man ikke komme hen på /stud1 uden at specificere navn, giver 404
        //404: url not found, url kendes ikke
        if (name.isPresent()){
            return new Student(name.get());
        } else {
            return new Student("");
        }
        //return new Student(name); - stod der før if else
    }

    @GetMapping("/stud3/{name}")
    public Student getstud3ByName(@PathVariable String name){ //vil finde student i databasen her
        var obj = studentRepository.findByName(name).orElse(new Student(name + "not found."));
        //enten finder vi student med name i databasen eller også udskriver vi en fake studerende,
        //som gøres så vi kan udskrive at name not found
        //det bryder http reglerne for vi returnere noget og får http kode 200 for success i browseren
        //selvom der jo ikke var sådan en student i databasen. Vi skal gøre så den returnere en fejlkode
        //frem for at returnere en fake studerende
        return obj;
    }

    @GetMapping("/stud4/{name}")
    public Student getstud4ByName(@PathVariable String name){
        //orElseThrow - throw exception, det er aggressivt
        return studentRepository.findByName(name).orElseThrow(() -> new RuntimeException("Not Found"));
        //man thrower en underklasse af Throwable, men man skal lave en metode som skal kaldes
        //og det gider vi ikke så vi bruger lambda, en anonym metode, en metode uden navn
        //gør man meget i javascript men også en del i java
        //lambda som i en switch
        //parantesen er tom fordi den anonyme metode ikke tager en parameter
        //vi thrower her en RunTimeException for så behøver compileren ikke catche den
        //dvs programmet stopper ikke bare fordi der kommer en exception. Det gør det med
        //de andre exceptions

        //nu får vi status kode 500 pga runtimeexeption, og inspect -> network så er filen rød
        //http kode er 500


        //nu har vi lavet vores egen student not found exception

        //sådan ville man gøre det:
        //return studentRepository.findByName(name).orElseThrow(() -> new StudentNotFoundException("name"));
    }

    //kald egen http kode
    @GetMapping("/stud5/{name}")
    public ResponseEntity<Student> getstud5ByName(@PathVariable String name){
        //responseEntity er også en wrapper klasse som pakker Student ind. Til http koder. Til browser
        //Optionalwrapper er ikke til browseren. Har ikke noget med hinanden at gøre

        var stud = studentRepository.findByName(name);
        //brug var hvis datatypen og variabelnavnet er mega langt. Det er ok selvom
        //man ikke gør så get i java

        if (stud.isPresent()){
            return new ResponseEntity<>(stud.get(), HttpStatus.OK);
            //reponse entity finder den studerende samt giver en http kode
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //httpstatus er bare fejlkoden som OK eller NOT_FOUND eller whatever har
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    //tager json ind. Request body laver json om til java. så gemmes det
    //med restcontroller displayer java objekter som json i browseeren, response status gør det modsatte
    //gøres i stedet for at manuelt lave json til java
    public Student postStudent(@RequestBody Student student){
        //her bruger vi ikke response entity for at se forskellen
        System.out.println(student);
        return studentRepository.save(student);
    }
    //Postman:
    //tryk new til højre for scratch pad, tryk Collection, kald den Student,
    //ved POST tryk body og raw og skriv json. Skriver du ikke et id laver den et nyt
    //fordi jpa save både kan insert og update kan vi både lave nye og opdatere entities i postman
    //eller hvor ellers man vil sætte objekter ind fra
    //internationale standarder siger at POST skal lave et nyt objekt, ikke opdatere
    //vi kan bruge det til begge men skulle api'en ud i verden skal man nok overholde de
    //internationale standarder
    //En browser kan ikke umiddelbart lave POST requests, derfor bruger vi postman
    //ellers skulle lave et front end og bruge thymeleaf

    //til 24 timers eksamen - lav service lag. Skal bare have enkelt linje med at
    //returnere om det går godt eller dårligt med boolean. I controller
    //returneres så http kode alt efter om det gik godt eller dårligt
    //gør at google viser results inden for 1-2 år

    //REST:
    //lavet ud fra at http protokollen her verber og så bygge ud fra det
    //REST - representational state transfer
    //representation - json eller xml
    //state transfer - typically via http
    //før rest brugte man soap hvilket havde rigtig meget kode der fortalte om hvilke objekter man overførte

    //http verber: get, put, post, delete
    //messages: the payload of the action, json, xml
    //URI - uniform resource identifier - unik string der identificere ressourcen, fx student/1
    //for student med id 1
    //idempotence: det samme kald til serveren skal give samme resultat. Fx hente bestemt student
    //eller refreshe side (GET). POST er self ikke idempotent da man indsætter nyt objekt
    //stateless: serveren ved ikke noget om browseren, fx den ved ikke hvad man tidligere har søgt efter
    //ingen tilstand på back enden/serveren. fordel hvis man har travlt og serveren outsourcer en til andre
    //servere. Så er det ligegysligt hvilken server man bruger. Dog cacher serveren de objekter man
    //lægger ind i en vis tid
    // HATEOAS - nasa dagens billede - får bare url tilbage i en url. json indeholder links til flere ressourcer/data/whatever
    //fx href: url...i kommune-json som linker til tilhørende region json objekt. de har også link til sig selv
    //http get
    //http put - insert og update ligesom med post, men post må officielt jo ikke updatere. brug put
    //dog er der ikke nogen formel specifikation for rest

    //når nyt projekt laves så tag H2 database med til hvis det skal op i skyen(?)

    @DeleteMapping("/student/{id}")//brug delete mapping til at slette
    public ResponseEntity<Student> delete(@PathVariable Integer id){
        //response entity pakker ind. http har respons body som Student objekt kommer ind i
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //returnere bare http statuskode
    }

}
