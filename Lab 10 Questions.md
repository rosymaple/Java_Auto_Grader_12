## Lab 10: Java APIs

**Dependencies**  
The necessary dependencies have already been added to this project for you, so you will not need to modify pom.xml.

**Code needs to meet the requirements given in the instructions**  
The tests for this lab can't check everything about your code since it uses API calls and external APIs. 
It is possible to pass the tests but not meet the assignment expectations. Follow the instructions given carefully. 
Your code will be human-reviewed. Email me if you would like me to check your work.

**Accounts, access keys?**   
The APIs in this lab are open access. 
None of the APIs used here require accounts or authentication. 

**Error handling**   
In a real program, we would need to add error-handling code that anticipates possible errors when making API requests.
For this lab, we will assume that the user enters valid data, 
that the computer running the code has an internet connection, 
the API is working, and that the API server accepts the request 
and provides a response in the expected format.

Obviously, there are several possibly ways that things can go wrong - as you work with APIs in future programs, 
you'll think about how to handle these errors. For this lab, assume that the requests will work. 
Do make sure you have an internet connection as you work on this lab.


### Random Compliment API

Finish the program that displays a random compliment.

API documentation: https://github.com/claraj/compliments_API

The API URL you will use is https://random-compliment.azurewebsites.net/random

The API server "sleeps" when it hasn't been used for an hour, and will take a few seconds to wake up. 
So, the first request your program makes can take 60 seconds to complete. This is normal, and if your program correctly displays the compliment, your code is working. 

### GitHub User Information API

Finish this program that displays information about a GitHub user. 

A user will enter the name of a GitHub user.  

API documentation: https://docs.github.com/en/rest/overview/endpoints-available-for-github-apps

Your program will connect to the GitHub API and request information about a GitHub user,
* Their name
* Their login name
* Their location
* For each one of their repositories, 
  * The name, 
  * language, 
  * size 

You will need to create a URL that includes a GitHub username. 
 
If we want information about Guido Van Rossum, the Python language creator, using his GitHub username, **gvanrossum**.

User information can be fetched with the URL,
https://api.github.com/users/gvanrossum

List of repositories can be fetched with the URL,
https://api.github.com/users/gvanrossum/repos

Replace the **gvanrossum** part of each URL with the username of the user that you want information about. 

Some other usernames you can try are,

* Linus Torvalds's (created the Linux operating system) GitHub username is **torvalds**
* Moxie Marlinspike's (computer security researcher) GitHub username is **moxie0**
* Your own GitHub username
