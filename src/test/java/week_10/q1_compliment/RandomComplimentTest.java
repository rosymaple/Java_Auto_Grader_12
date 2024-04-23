package week_10.q1_compliment;

import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class RandomComplimentTest {

    private final int TIMEOUT = 180000;  // 180 seconds = 3 minutes, big timeout since API will be slow to wake up if not used for an hour.

    private static ObjectMapper originalMapper;

    @BeforeClass
    public static void saveOriginalMapper(){
        originalMapper = Unirest.config().getObjectMapper();
    }

    @After
    public void resetMapper() {
        Unirest.config().setObjectMapper(originalMapper);
    }


    @Test(timeout = TIMEOUT)
    public void getRandomComplimentUsesCorrectAPIURL() {

        // Mock Unirest's .get method and ensure it uses the correct URL.
        // Ensure it's called with the correct API string
        try( MockedStatic<Unirest> mockUnirest = mockStatic(Unirest.class) ) {

            String expectedURL = "https://random-compliment.azurewebsites.net/random";

            mockUnirest.when( () -> Unirest.get(expectedURL)).thenReturn(null);

            try {
                String whatever = RandomCompliment.getRandomCompliment();
            } catch (NullPointerException e) {
                // expecting a null pointer here because the mock methods return null when called
            }

            mockUnirest.verify(() -> Unirest.get(expectedURL));

        } catch (Exception e) {
            System.err.println(e);
            // Unirest get method not used in the expected way, or some other error.
            fail("Use Unirest to get the random compliment.");
        }
    }



    private Class findComplimentResponseClass() {

        try {
            Class complimentClass = Class.forName("week_10.q1_compliment.Compliment");
            System.out.println("Found Compliment class, standalone class as expected.");
            return complimentClass;
        } catch(Exception e) {

            // not found, check for nested class but tell student to move to standalone
            try {
                // nested classes not allowed. Can't create as easily with reflection.
                Class complimentResponse = Class.forName("week_10.q1_compliment.RandomCompliment$Compliment");
                System.err.println("Found Compliment class, defined as a nested class. " +
                        "\nMove the class definition outside of the RandomCompliment class.");
            } catch(Exception ex) {
                System.out.println("Did not find Compliment class as a nested class either");
            }
        }
        return null;
    }


    @Test(timeout = TIMEOUT)
    public void testStructureOfComplimentClassAndGetRandomCompliment() {

        System.out.println("This test makes an API call. " +
                "\nSince the API is slow to respond when it hasn't been used for an hour, this test may take several seconds to complete.");

        String exampleCompliment = "You are an outstandingly great Java programmer!";

        Class complimentResponseClass = findComplimentResponseClass();
        assertNotNull("Create a Compliment class", complimentResponseClass);

        Constructor[] constructors = complimentResponseClass.getDeclaredConstructors();

        // Expect one constructor, either the default constructor that is created if one is
        // not specified, or the student may define a constructor. It is not necessary to define
        // a constructor in the Compliment class for the purposes of using it to map JSON to
        // Java objects, but in a program where Java objects are mapped to JSON, then a constructor
        // may be convenient for creating Java objects within the program.

        if (constructors.length > 1) {
            fail("It is not necessary to create multiple constructors for the Compliment class");
        }

        Object randomComplimentObject = null;

        try {

            // Note that this test fails to discover and instantiate a Compliment object
            // when the Compliment class is nested. Instructions specify standalone class.

            Constructor c = constructors[0];

            Class[] constructorParameterTypes = c.getParameterTypes();

            if (constructorParameterTypes.length == 0) {
                // Maybe a no-args constructor...
                randomComplimentObject = c.newInstance();
            } else if (constructorParameterTypes.length == 1) {
                // or a constructor with a single String argument
                randomComplimentObject = c.newInstance(exampleCompliment);
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Can't create a mock response Compliment object because " + e.getMessage());
        }

        // Expect either one public text field, or a public setText method.

        boolean exampleTextSet = false;

        try {
            Field publicTextField = complimentResponseClass.getField("text");
            assertEquals("The text field in the Compliment class should be a String field",
                    publicTextField.getType(), String.class);
            publicTextField.set(randomComplimentObject, exampleCompliment);
            exampleTextSet = true;

        } catch (Exception e) {
            // No public text field, or unable to set. So try to find and call
            // a public setText method.  Also check there is a matching public getText method.

            try {
                Method setText = complimentResponseClass.getMethod("setText", String.class);
                Method getText = complimentResponseClass.getMethod("getText");
                assertEquals("A getText method in the Compliment class should return a String",
                        String.class, getText.getReturnType());

                setText.invoke(randomComplimentObject, exampleCompliment);
                exampleTextSet = true;
            } catch (Exception ex) {
                // unable to call the set text method either. May be wrong type of parameters, not public, or doesn't exist.
                System.out.println("Unable to find either a public text field, or getters and setters for a private text field.");
            }
        }

        assertTrue("The Compliment class must have either a public String text field " +
                "\nor a private String text field, plus public getter (getText) and setter (setText) methods.",
                exampleTextSet);

        // So should have an object with the pre-packaged string defined above,
        // regardless of what the API actually returns.

        // Replace default object mapper with one that always returns the mock
        // randomComplimentObject object with example data.
        Unirest.config().setObjectMapper(new MockObjectMapper(randomComplimentObject));

        String compliment = RandomCompliment.getRandomCompliment();

        assertEquals("Make an API call, and use the Compliment class to convert the the response into a Java object." +
                "\nReturn the text from the API response from getRandomCompliment.", exampleCompliment, compliment);
    }


    class MockObjectMapper implements ObjectMapper {

        private Object ob;
        MockObjectMapper(Object ob){
            this.ob = ob;
        }
        @Override
        public <T> T readValue(String s, Class<T> aClass) {
            // convert JSON string to object
            return (T) ob;
        }

        @Override
        public String writeValue(Object o) {
            return null;  // writes object to JSON string
        }
    }


    @Test(timeout = TIMEOUT)
    public void ComplimentResponseClassCreated() {

        // Ensure correct Compliment class is created
        // It can work as a nested class of RandomCompliment or a standalone but lab
        // requires standalone class.  Standalone classes are usually recommended anyway.

        // Ensure it has one field, a String text
        // or get and set text methods
        String msg = "Can't find Compliment class. Use this exact name for the class. " +
                "\nDefine this class in the same file as RandomCompliment (but not as a nested class) or a new file in the q1_compliment package (directory).";

        Class complimentResponseClass = findComplimentResponseClass();
        assertNotNull(msg, complimentResponseClass);

        Field[] fields = complimentResponseClass.getFields();

        // Student may use a public field,
        if (fields.length == 1) {
            String name = fields[0].getName();
            assertEquals("Compliment class should have one String field called 'text'", name, "text");

            Class type = fields[0].getType();
            assertEquals("Compliment class should have one String field called 'text'", String.class, type);
        }

        // Or, a private field of any name, and public or protected get and set methods. Private fields not returned by getFields
        else if (fields.length == 0) {
            try {
                Method getText = complimentResponseClass.getMethod("getText");
                Method setText = complimentResponseClass.getMethod("setText", String.class);
                assertEquals("If you use getter and setter methods, the return type from getText should be String.", String.class, getText.getReturnType());
            } catch (Exception e) {
                System.err.println(e);
                fail("Create a public text field; or a private text field with getter and setter methods");
            }
        }

        else {
            // Extra fields?  Not needed, so test fail
            fail("The Compliment class should only have the field(s) and/or methods needed to map to the JSON response.\n" +
                    "Don't add any other fields or methods");
        }
    }


    @Test(timeout = TIMEOUT)
    public void humanReview() {

        fail("This test will always fail.\n" +
                "The tests for this lab can't check everything about your code since it uses API calls and external APIs. \n" +
                "It is possible to write code that passes the tests, but does not meet the assignment expectations. \n" +
                "Carefully follow the instructions given. \n" +
                "Your code will be human-reviewed. Please email me if you would like me to check your work.");
    }
}