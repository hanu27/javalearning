package com.javalearning.person.service;

import com.javalearning.person.Date;
import com.javalearning.person.Person;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PersonHelper {

    public static void main(String[] args) throws IOException {
        List<Person> personList = List.of(
                new Person(1, "John", 25, 175.5f, true, "123 Main St", List.of("555-1111", "555-2222"), null, new Date(1998, 6, 15), Map.of("programming", "Java", "design", "Photoshop")),
                new Person(2, "Alice", 30, 160.0f, false, "456 Elm St", List.of("555-3333"), 5000.0f, new Date(1993, 9, 21), Map.of("writing", "Copywriting", "marketing", "SEO")),
                new Person(3, "Bob", 40, 185.3f, true, "789 Oak St", List.of("555-4444", "555-5555", "555-6666"), 8000.0f, new Date(1983, 3, 8), Map.of("programming", "Python", "data analysis", "SQL"))
        );
        // Serialize persons to disk
        File personsListSerializedFile = new File("persons.avro");
        serializePersons(personList, personsListSerializedFile);
    }

    private static void serializePersons(List<Person> personList, File personListSerializedFile) throws IOException, IOException {
        // We create a DatumWriter, which converts Java objects into an in-memory serialized format.
        // The SpecificDatumWriter class is used with generated classes and extracts the schema from the specified generated type.
        DatumWriter<Person> datumWriter = new SpecificDatumWriter<>(Person.class);
        // Next we create a DataFileWriter, which writes the serialized records, as well as the schema, to the file specified in the dataFileWriter.create() call.
        // We write our persons to the file via calls to the dataFileWriter.append method. When we are done writing, we close the data file.
        DataFileWriter<Person> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(personList.get(0).getSchema(), personListSerializedFile);
        personList.forEach(person -> {
            try {
                dataFileWriter.append(person);
            } catch (IOException e) {
                System.err.println("Error writing person with id: " + person.getId());
            }
        });
        dataFileWriter.close();
    }

}
