# Étape 1 : Construire l'application
FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /opt/app

# Configurer UTF-8
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

# Copier Maven wrapper et fichiers associés
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

# Télécharger les dépendances en mode hors ligne
RUN ./mvnw dependency:go-offline

# Copier le code source
COPY src ./src

# Construire l'application sans tests
RUN ./mvnw clean package -DskipTests

# Étape 2 : Exécuter l'application dans une image minimale
FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app

# Copier le JAR depuis l'étape de construction
COPY --from=build /opt/app/target/*.jar /opt/app/app.jar

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
