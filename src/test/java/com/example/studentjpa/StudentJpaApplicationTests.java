package com.example.studentjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@DataJpaTest //spring boot test køre hele projektet først, så fejler det i skyen
    //fordi spring boot test køre application.properties hvor den prøver at connecte
    //til en mysql som ikke er der i skyen (lokalt)
    //datajpatest bruges når den skal køre h2 i skyen


    //test i maven menuen til højre kan godt bruges til at køre
    //det da den bruger intellij's maven, man kan ikke teste i terminal
    //da man så skal have installeret javac, jdk og maven på ens com

    //husk alle testklasser skal annoteres med datajpatest hvis man vil bruge h2!
    //denne testklasse autogeneres når man laver et projekt
    //den gør ingenting som udgangpunkt
    //dog kan denne testklasse bruges til kun at teste enkelte metoder
    //dog kun en ad gangen. Så køres ikke hele programmet men kun den metode der køres
class StudentJpaApplicationTests {

    @Test
    void contextLoads() {
    }

}
