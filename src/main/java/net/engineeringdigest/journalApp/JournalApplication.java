package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // ye wo wla methods dundha hai jo transaction management ke liye use hote hai aur unko enable karne ke liye ye annotation use hota hai
//ye wo sab ko dundh kr har method ke corresponding ek transactional context bana dega using platformTransactionManager and MongoDBTransactionManager
@EnableMongoRepositories(basePackages = "net.engineeringdigest.journalApp.Repository")
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager  transactionManager(MongoDatabaseFactory dbFactory) {  //MongoDatabaseFactory is an interface that provides access to a MongoDB database instance.
        return new MongoTransactionManager(dbFactory);
    }


}