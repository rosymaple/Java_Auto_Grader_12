package week_10.q2_github;

import org.junit.BeforeClass;
import org.junit.Test;
import week_10.test_utils.PrintUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GitHubUserInformationTest {

    static Class userClass;
    static Field loginField;
    static Field locationField;
    static Field userNameField;

    static Class repoClass;
    static Field languageField;
    static Field repoNameField;
    static Field repoSizeField;

    private final int TIMEOUT = 60000;  // 60 seconds, big timeout to account for slow internet connections, slow responses from API etc.


    @BeforeClass
    public static void gitHubRepositoryList() {

        try {
             userClass = GitHubUser.class;
             loginField = userClass.getField("login");
             locationField = userClass.getField("location");
             userNameField = userClass.getField("name");

             repoClass = GitHubRepository.class;
             languageField = repoClass.getField("language");
             repoNameField = repoClass.getField("name");
             repoSizeField = repoClass.getField("size");
        } catch (Exception e) {
            System.err.println(e);
            fail("""
        This part of the test looks at the GitHubUser and GitHubRepository classes and checks that they have the expected fields. 
        If an error occurs here, make sure your fields in the GitHubUser and GitHubRepository classes are public.
        And, make sure the field names in your classes match the property names in the JSON response. """);
        }
    }


    @Test(timeout = TIMEOUT)
    public void getUserNameAndLocation() {

        try {

            GitHubUser user = GitHubUserInformation.getUserInformation("claraj");

            assertNotNull("Finish the getUserInformation method to make the API call, " +
                    "\n and create and return a GitHubUser object containing information from the API response", user);

            String msgTemplate = "The %s value from the GitHub API response should be stored in the %s field in the GitHubUer object.";
            String loginName = (String) loginField.get(user);
            assertEquals(String.format(msgTemplate, "login", "login"), "claraj", loginName);

            String location = (String) locationField.get(user);
            assertEquals(String.format(msgTemplate, "location", "location"), "Minneapolis, MN", location);

            String name = (String) userNameField.get(user);
            assertEquals(String.format(msgTemplate, "name", "name"), "Clara", name);


            user = GitHubUserInformation.getUserInformation("hello-java-class");

            assertNotNull("Finish the getUserInformation method to make the API call, " +
                    "\n and create and return a GitHubUser object containing information from the API resppnse", user);

            loginName = (String) loginField.get(user);
            assertEquals(String.format(msgTemplate, "login", "login"), "hello-java-class", loginName);

            location = (String) locationField.get(user);
            assertEquals(String.format(msgTemplate, "location", "location"), "Canada", location);

            name = (String) userNameField.get(user);
            assertEquals(String.format(msgTemplate, "name", "name"), "Hello! Java! Class!!", name);

        } catch (Exception e) {
            System.err.println(e);
            fail("""
                This part of the test looks at the GitHubUser class and checks that it has the expected fields, and the 
                values of those fields are the expected values, with data from the API response. 
                If an error occurs here, make sure your fields are public.
                And, make sure the field names in your classes match the property names in the JSON response.
                And, make sure the fields have the expected types. """);
        }
    }


    @Test(timeout = TIMEOUT)
    public void getRepositories()  {

        try {
            GitHubUser user = new GitHubUser();
            loginField.set(user, "hello-java-class");

            GitHubRepository[] repositories = GitHubUserInformation.getRepositories(user);

            assertNotNull("Finish the getRepositories method to make the API call, " +
                    "\n and create and return an array of GitHubRepository objects containing information from the API response", repositories);

            assertEquals("If an example user has 2 repositories, the length of the array returned should be 2", 2, repositories.length);

            // Expect repositories in alphabetical order, so owls, then spells.

            GitHubRepository owlRepo = repositories[0];
            String repoName = (String) repoNameField.get(owlRepo);
            assertEquals("Store the name of the repository in the name field in the GitHubRepository object", "owls", repoName);
            String repoLang = (String) languageField.get(owlRepo);
            assertEquals("Store the language of the repository in the language field in the GitHubRepository object", "Python", repoLang);
            int repoSize = repoSizeField.getInt(owlRepo);
            assertEquals("Store the size of the repository in the size field in the GitHubRepository object", 1, repoSize);

            GitHubRepository spellsRepo = repositories[1];
            repoName = (String) repoNameField.get(spellsRepo);
            assertEquals("Store the name of the repository in the name field in the GitHubRepository object", "spells", repoName);
            repoLang = (String) languageField.get(spellsRepo);
            assertEquals("Store the language of the repository in the language field in the GitHubRepository object", "C#", repoLang);
            repoSize = repoSizeField.getInt(spellsRepo);
            assertEquals("Store the size of the repository in the size field in the GitHubRepository object", 16, repoSize);

        } catch (Exception e) {
            System.err.println(e);
            fail("""
                This part of the test looks at the GitHubRepository class and checks that it has the expected fields, and the 
                values of those fields are the expected values, with data from the API response. 
                If an error occurs here, make sure your fields are public.
                And, make sure the field names in your classes match the property names in the JSON response.
                And, make sure the fields have the expected types. """);
        }
    }


    @Test(timeout = TIMEOUT)
    public void displayGitHubUserInformation() {

        GitHubUser exampleUser = new GitHubUser();
        GitHubRepository repo1 = new GitHubRepository();
        GitHubRepository repo2 = new GitHubRepository();

        try {
            // Set up the user,
            loginField.set(exampleUser, "Wednesday Addams");
            locationField.set(exampleUser, "New Jersey, USA");

            // and two repositories,
            repoNameField.set(repo1, "Hats");
            languageField.set(repo1, "Swift");
            repoSizeField.set(repo1, 404);

            repoNameField.set(repo2, "Bats");
            languageField.set(repo2, "Ruby");
            repoSizeField.set(repo2, 12345678);

        } catch (Exception e) {
            System.err.println(e);
            fail("""
                    This part of the test looks at the GitHubRepository class and checks that it has the expected fields, and the 
                    values of those fields are the expected values, with data from the API response. 
                    If an error occurs here, make sure your fields are public.
                    And, make sure the field names in your classes match the property names in the JSON response.
                    And, make sure the fields have the expected types. """);
        }

        GitHubRepository[] repos = { repo1, repo2 };

        PrintUtils.catchStandardOut();
        GitHubUserInformation.displayGitHubUserInformation(exampleUser, repos);
        String textPrinted = PrintUtils.resetStandardOut();

        assertTrue("Print the user's name", textPrinted.contains("Wednesday Addams"));
        assertTrue("Print the user's location", textPrinted.contains("New Jersey, USA"));

        assertTrue("Print the name of each repository", textPrinted.contains("Hats"));
        assertTrue("Print the language used by each repository.", textPrinted.contains("Swift"));
        assertTrue("Print the size of each repository. Include the units, KB", textPrinted.contains("404 KB"));

        assertTrue("Print the name of each repository", textPrinted.contains("Bats"));
        assertTrue("Print the size of each repository. Include the units, KB", textPrinted.contains("12345678 KB"));
        assertTrue("Print the language used by each repository.", textPrinted.contains("Ruby"));

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