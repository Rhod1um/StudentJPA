FROM openjdk:17-ea-17-slim
#from henter et base image fra skyen som vi bygger oven på. For at køre java
#skal vi først have et operativ system, så det henter vi her
#vælger 17-ea-slim fordi erik har set en anden gøre det og fordi den er letvægtig, men alpine er
#åbenbart lettere
WORKDIR application
#man behøver ikke workdir(?) og vi laver en folder der hedder application i imaget?
#den laver target folder i projektet, ses, orange mappe
#hedder filnavn-noget-SNAPSHOT.

#jar filen for projektet skal ned i app folderen
COPY ../../../target/StudentJPA-0.0.1-SNAPSHOT.jar ./
#^hvad erik havde skrevet, den kunne ikke finde jar
#RUN mv ../../../target/StudentJPA-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "StudentJPA-0.0.1-SNAPSHOT.jar"]
#entrypoint er som terinal, en kommando

