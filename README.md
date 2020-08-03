# CandySpaceTest
### Main Screen

Display an input field and Button to search for users by name.  
Display up to 20 users alphabetically and show their reputation and username.  
When tapped, open a new `Activity` to display more information about the
user.

```
+--------------------+
|  AppName           |
|--------------------+
| __________  SEARCH | - input and button  
| ------------------ |
| 123  Username1     |
| ------------------ |
| 390  Username2     | -----*tap* -----> user details screen
| ------------------ |
|   0  Username3     |
| ------------------ |
| 275  Username4     |
| ------------------ |
| 122  Username5     |
+--------------------+
```

### User Details screen

Displays additional information about the user.

```
+--------------------+
| < User             | - up button and page title
|--------------------+
|                    |
|   +-----------+    |
|   |           |    |
|   |   Avatar  |    |
|   |           |    |
|   +-----------+    |
|   User Name        |
|   Reputation       |
|   Badges           |
|                    |
|   Location         |
|   Age              |
|   Creation Date    |
|                    |
+--------------------+
```

## Requirements and Constraints
  * Minimum Android API 19
  * Use RxJava(2) for handling async network calls
  * Use Retrofit2 for http requests
  * Use dagger2 for dependancy Injection
  * Use Kotlin instead of Java
  
  Testing Constraints
  Use Mokito for mock an object
  Use Junit4 for android test runner
  

## References
Retrofit2 - http://square.github.io/retrofit/  
RxJava - https://github.com/ReactiveX/RxJava
Dagger- https://github.com/google/dagger
StackExchange API - https://api.stackexchange.com/docs  

Pjoject Pattern: MVVM

Model–view–viewmodel is a software architectural pattern that facilitates the separation of the development of the graphical user interface 
be it via a markup language or GUI code – from the development of the business logic or back-end logic so that the view is not dependent on any specific model platform

