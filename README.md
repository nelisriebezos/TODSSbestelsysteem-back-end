# TODSS bestel-systeem backend repository
Dit is de backend repository voor TODSS van team support.

## Configure database
Maak een application.properties file aan onder src/main/resources en zet dit er in.
Vul zelf de variabelen in die jij nodig hebt
````properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
security.jwt.secret="8'@#.CvGA)B:{zZMLmorm{+7k|M|6+L#i{wV-|9<R}yn'QhppJ@wi}2{HTI{Cd]gtfr&23"
security.jwt.expiration-in-ms=359200000
````
Bijvoorbeeld:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todss
spring.datasource.username=todss
spring.datasource.password=todss
spring.jpa.hibernate.ddl-auto=update
security.jwt.secret="8'@#.CvGA)B:{zZMLmorm{+7k|M|6+L#i{wV-|9<R}yn'QhppJ@wi}2{HTI{Cd]gtfr&23"
security.jwt.expiration-in-ms=359200000
```

## Contributers
Martijn Voorwinden
Max van den Boogaard
Dave Nieberg
Donny van Walsem
Niels Riezebos
Luuk Verhoeven
