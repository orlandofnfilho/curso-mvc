FROM openjdk:11

COPY ./target/recipe-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch recipe-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","recipe-0.0.1-SNAPSHOT.jar"]