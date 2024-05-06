package week_10.q1_compliment;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class RandomCompliment {


    public static void main(String[] args) {
        // You don't need to modify the main method.
        String compliment = getRandomCompliment();
        System.out.println(compliment);
    }

    public static class Compliment {        // our Compliment class needs to be public static to use in our program
        public String text;         // our Compliment will contain one String variable "text"
    }       // end of Compliment class

    public static String getRandomCompliment() {        // our getRandomCompliment method

        String url = "https://random-compliment.azurewebsites.net/random";      // our api will run from this URL
        // sets URL to a variable we can work with.

        Compliment complimentObject = Unirest.get(url).asObject(Compliment.class).getBody();
        // declare complimentObject from the body of the URL object

//        System.out.println(complimentObject.text);    // instead of using this we will simply use a return statement
        return complimentObject.text; // return to main
        /*
        * TODO use Unirest to make a request to the Random Compliment API, using the URL
        *
        * https://random-compliment.azurewebsites.net/random
        *
        * Tip: Paste the URL into your browser address bar to visualize the structure
        * of the response.  You'll see a different compliment every time. An example
        * response looks like this,
        *
        *    {
        *        "text": "You are so creative!"
        *    }
        *
        * TODO Create a Java class that maps to the structure of the response.
         *  The Java class should be called Compliment.
         *  The Compliment class should NOT be a nested class.
        *    Define this class outside of the RandomCompliment class in this file, or in a separate file.
        *    If you create a new class file, make sure you add it to your Git repository
        *    so it is committed and uploaded to your lab repository.
        *
        *  The Compliment class should have a public field for the text property,
        *  OR public get and set methods for the text property.
        *
        * Remember that field(s) in classes used to store API responses,
        * need to to match the names of properties in the JSON response.
        *
        * TODO Make a request to the API and store the response in a Compliment object.
        *
        * TODO Extract and return the text - the compliment text.
        *
        * If you see errors when making the API call, please try the API URL
        * https://random-compliment.azurewebsites.net/random
        * in your browser. If you see an error message, please let me know ASAP. Thank you!
        * If the API seems to be working - you see a random compliment - then check your code
        * for errors, and please email me if you need any help troubleshooting.
        * */
    }       // end of getRandomCompliment method
}       // end of public class
