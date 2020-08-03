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
  * Use dagger2 for dependency Injection
  * Use Kotlin instead of Java
  
  Testing Constraints
  Use Mockito for mock an object
  Use Junit4 for android test runner
  

## References
Retrofit2 - http://square.github.io/retrofit/  
RxJava - https://github.com/ReactiveX/RxJava
Dagger- https://github.com/google/dagger
StackExchange API - https://api.stackexchange.com/docs  

## Compile project
Download or clone project from the below link:
https://github.com/KirtiKashyap/CandySpaceTest/tree/master
open this project in your IDE.

Here I use 
Android Studio 4.0.1
Android Gradle Plugin version 4.0.1
Gradle 6.1.1
Minimum SdkVersion 19
kotlin_version 1.3.72



## Project Pattern: MVVM

Model–view–viewmodel is a software architectural pattern that facilitates the separation of the development of the graphical user interface 
be it via a markup language or GUI code – from the development of the business logic or back-end logic so that the view is not dependent on any specific model platform

## Dagger
Dagger is a fully static, compile-time dependency injection framework for both Java and Android.

## Retrofit
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) 
via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. 
Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols. 
Retrofit uses the OkHttp library for HTTP requests.

## RxJava
RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together decoratively
while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.
