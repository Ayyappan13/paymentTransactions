From openjdk:8
copy ./target/paymentTransactions-0.0.1-SNAPSHOT.jar paymentTransactions-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","paymentTransactions-0.0.1-SNAPSHOT.jar"]