package com.example.springboot;

import java.util.Arrays;

// debugging
import java.util.PriorityQueue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.thoughtworks.xstream.XStream;

public class FoobarXStream {

    private XStream xstream = new XStream();

    public FoobarXStream() {

        try {
            // ruleid: fromXML
            Object pq = xstream.fromXML(read);
        } catch (Exception e) {
            System.out.println(read);
            System.out.println(e);
        }
    }

}

public class FoobarXStreamAgain {

    private XStream xstream;

    public FoobarXStreamAgain() {
        xstream = new XStream();
    }

    public void doSmth() {
        try {
            // ruleid: fromXML
            Object pq = xstream.fromXML(read);
        } catch (Exception e) {
            System.out.println(read);
            System.out.println(e);
        }
    }
}

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            // XXX Berney
            System.out.println("XXX Berney Start of Stuff");
            // should launch an executable
            //Egg egg = new Egg();

            // https://x-stream.github.io/tutorial.html
            System.out.println("XXX Berney XStream stuff");
            XStream xstream = new XStream();

            Path path = Paths.get("bad.xml");
            String read = Files.readString(path);
            System.out.format("\n\nRead: -\n%s\n\n", read);
            try {
                // ruleid: fromXML
                Object pq = xstream.fromXML(read);
            } catch (Exception e) {
                System.out.println(read);
                System.out.println(e);
            }

            // Now, to make the XML outputted by XStream more concise, you can create aliases for your custom class names to XML element names
            xstream.alias("person", Person.class);
            //xstream.alias("phonenumber", PhoneNumber.class);

            // Serializing an object to XML
            // Let's create an instance of Person and populate its fields:
            Person joe = new Person("Joe", "Walnes");
            joe.setPhone(new PhoneNumber(123, "1234-456"));
            joe.setFax(new PhoneNumber(123, "9999-999"));

            // Now, to convert it to XML, all you have to do is make a simple call to XStream:
            String xml = xstream.toXML(joe);
            System.out.println(xml);

            // Deserializing an object back from XML
            // ruleid: fromXML
            Person newJoe = (Person)xstream.fromXML(xml);
            System.out.println("Deserialised newJoe");

            // Deserialise Egg (should execute a process)
            String maliciousXml = "<com.example.springboot.Egg/>";
            // Egg isn't a Person but we'll see if it still gets constructed before getting cast
            // ruleid: fromXML
            Person badJoe = (Person)xstream.fromXML(maliciousXml);

        };
    }

}
