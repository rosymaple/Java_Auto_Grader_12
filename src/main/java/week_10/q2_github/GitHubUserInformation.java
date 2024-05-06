package week_10.q2_github;

import kong.unirest.Unirest;


import java.util.Map;

import static input.InputUtils.stringInput;

public class GitHubUserInformation {

    public static void main(String[] args) {

        // You don't need to modify this method.
        String username = stringInput("Enter a GitHub user's name: ");
        GitHubUser user = getUserInformation(username);
        GitHubRepository[] repositories = getRepositories(user);
        displayGitHubUserInformation(user, repositories);

    }

    public static GitHubUser getUserInformation(String gitHubUserName) {

        String url = "https://api.github.com/users/" + gitHubUserName;      // the API URL we must read from

        Map<String, Object> response = Unirest.get(url).asObject(Map.class).getBody();
        // our map of our response from our API url, pull from the body of our response.

        GitHubUser user = new GitHubUser();     // new GitHubUser object
        user.login = (String) response.get("login");        // pull this data from the URL. looks for "login"
        user.name = (String) response.get("name");          // pull this data from the URL. looks for "name"
        user.location = (String) response.get("location");      // pull this data from the URL. looks for "location"

        return user;        // returns this whole method getUserInformation as "user"
        /*
        *  The URL to request information about a user is in the form
        *   https://api.github.com/users/{{username}}
        * Where {{username}} should be replaced with the actual username of the GitHub user.
        *
        * For example, for a GitHub user gvanrossum
        * The URL will be https://api.github.com/users/gvanrossum
        *
        * For example, for a GitHub user moxie0
        * The URL will be https://api.github.com/users/moxie0
        *
        * You can try this URL with your own GitHub username.
        *
        * TODO use the gitHubUserName parameter to create a string URL
        *  that can be used to request information about this user.
        *
        * TODO complete the GitHubUser class
        *  GitHubUser should have public fields for
        *  The login  (the name used to log into GitHub, AKA username)
        *  The user's location
        *  The user's name  (their human name)
        *
        * Ensure the names and types of these fields match the data returned in an API response.
        *
        * TODO make a request to the API
        *  Convert the response to a GitHubUser object.
        *  This object will store data from the API response.
        *  Return the GitHubUser object.
        *
        *
        * If you see errors when making the API call, please try example API URLs
        * https://api.github.com/users/gvanrossum
        * in your browser. If you see an error message, please let me know ASAP. Thank you!
        * If the API seems to be working - you see GitHub user information - then check your code
        * for errors, and please email me if you need any help troubleshooting.
        * Tip: if the URLs seem to work in your browser but not in code, try printing the
        * URL you generate and copy and paste that into your browser - is it in the correct format?
        *
        * If you make a request for information about a user who doesn't exist, your code will
        *  probably crash. In a real program, you would definitely need to handle this possibility.
        * For this lab, we'll assume the user will only enter login usernames of people who do have GitHub accounts.
        * */

    }       // end of getUserInformation method

    public static GitHubRepository[] getRepositories(GitHubUser user) {     // getRepositories method

        String url = "https://api.github.com/users/" + user.login + "/repos";
        // pulls from each user's own repositories. this is code that our user uploaded to github.

        Map<String, Object>[] response = Unirest.get(url).asObject(Map[].class).getBody();
        // create a map object from our url variable. pulls from body of URL

        GitHubRepository[] repositories = new GitHubRepository[response.length];        // create an array...
        // from our url.
        // our Map object holds our data from our api url. it pulls info from the body of the api.

        for (int i = 0; i < response.length; i++) {     // for loop over our map of API responses...
            Map<String, Object> repo = response[i];         // create another map object called repo
            // represents a single response in our variable "response" aka a single repository
            GitHubRepository repository = new GitHubRepository();       // new GitHubRepository object
            repository.name = (String) repo.get("name");            // add name to object
            repository.language = (String) repo.get("language");        // add language to object
            repository.size = (Double) repo.get("size");        // add size to object...all in our loop.
            repositories[i] = repository;           // add element (a user) to our repository object.
        }       // end of for loop...

        /*
         *  The URL to request information about a user's repositories is in the form
         *   https://api.github.com/users/{{login}}/repos
         *
         * Where {{login}} is replaced with the actual login username of the GitHub user.
         *
         * For example, for a GitHub user with login gvanrossum
         * The URL will be https://api.github.com/users/gvanrossum/repos
         *
         * For example, for a GitHub user with login moxie0
         * The URL will be https://api.github.com/users/moxie0/repos
         *
         * You can try this URL with your own GitHub login username.  If you have just created your
         * GitHub account for this class, you may not have any public repositories yet - that's ok,
         * you'll see an empty list. The labs for this class are not public repositories.
         * Otherwise, you'll see a list of your public repositories.
         *
         * Notice that the response is a LIST of repository objects.
         * Each repository object has several properties. For this program,
         * we are interested in the repository name, the language, and the size.
         *
         * TODO use the gitHubUser parameter to create a string URL
         *  that can be used to request information about this user.
         *
         * TODO complete the GitHubRepository class
         *  GitHubRepository should have public fields for
         *  The repository name
         *  The repository language
         *  The repository size.
         *
         * Ensure the names and types of these fields match the data returned in an API response.
         *
         * TODO make a request to the API
         *  Convert the response to an array of GitHubRepository objects.
         *  These object will store data about each repository from the API response.
         *  Return the GitHubRepository array.
         *
         * If you see errors when making the API call, please try example API URLs
         * https://api.github.com/users/gvanrossum/repos
         * in your browser. If you see an error message, please let me know ASAP. Thank you!
         * If the API seems to be working - you see GitHub repository information - then check your code
         * for errors, and please email me if you need any help troubleshooting.
         *
         * Tip: if the URLs seem to work in your browser but not in code, try printing the
         * URL you generate and copy and paste that into your browser - is it in the correct format?
         *
         * If you make a request for information about a user who doesn't exist, your code will
         * probably crash. In a real program, you would definitely need to handle this possibility.
         * For this lab, we'll assume the user will only enter login usernames of people who do have GitHub accounts.
         *
         * (Optional) However, you are welcome to investigate how to deal with usernames not found, and implement
         * code that determines if a user is not found, and handles this event appropriately - for example,
         * display a "Not found" message to the user, and not crash.
         * */

        return repositories;
    }       // end of GitHubRepository method. returns each "user" repository


    public static void displayGitHubUserInformation(GitHubUser user, GitHubRepository[] repositories) {

        System.out.println("Login: " + user.login);         // we need to print these variables for our user
        System.out.println("Name: " + user.name);           // these string print lines show our user information
        System.out.println("Location: " + user.location);       // about each github user we are using this method for
        System.out.println("\nRepositories:");                  // title our repositories info, and line break

        for (GitHubRepository repository: repositories) {       // loop through our repositories array.
            // this array holds all of the information known about each git hub user.
            System.out.println("Repository name: " + repository.name);      // print string for our user
            System.out.println("Language: " + repository.language);     // print string for user
            System.out.println("Size: " + repository.size + " KB");     // print string
        }

        // TODO display information about the GitHub user.
        //  Neatly display (print)
        //      The GitHub user's login,
        //      Their name
        //      Their location

        // TODO for each repository,
        //  Neatly display
        //      the repository's name
        //      the repository's language
        //      the repository's size. Include the units, KB, with the number.
        //          So, if a repository's size is 100, display "100 KB" with a space between the number and "KB".

        // You do not need to make any API calls in this method.
        // You do not need to return anything from this method.

    }       // end of displayGitHubUserInformation method. prints information for our user.
}       // end of public class GitHubUserInformation
