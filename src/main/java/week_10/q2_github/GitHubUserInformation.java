package week_10.q2_github;


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

        return null;   // todo delete and replace with your own code

    }

    public static GitHubRepository[] getRepositories(GitHubUser user) {

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

        return null;  // TODO delete and replace with your code
    }


    public static void displayGitHubUserInformation(GitHubUser user, GitHubRepository[] repositories) {

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

    }
}
