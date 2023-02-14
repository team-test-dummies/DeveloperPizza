FROM openjdk
ADD target/dev-pizza.jar dev-pizza
ENTRYPOINT ["java", "-jar","dev-pizza"]
EXPOSE 8080