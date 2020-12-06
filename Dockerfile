FROM openjdk:11
WORKDIR /home
COPY ./target/quotes-0.0.1-SNAPSHOT.jar /usr/src/app/quotes-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/quotes-0.0.1-SNAPSHOT.jar"]