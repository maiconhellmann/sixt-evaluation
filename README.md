## User story
Small Android app that displays a group of cars on a map and a list.

The board for the project can be found on the github repository:  
[Project board](https://github.com/maiconhellmann/sixt-evaluation/projects/1)

#### Api
Url=https://cdn.sixt.io/codingtask/cars 
 
## Architeture
MVVM(Model View ViewModel) - using Repository, UseCase and ViewModel  
Modularization to make gradle build faster

This project is based on another architecture project I have developed:  [Github](https://github.com/maiconhellmann/hellmann-architecture)  
This evaluation was also an opportunity to go further and improve my concepts using modularization, navigation, use case, repository, ViewModel and DataBinding.

### Modules
* app: Handle views, navigation, events, states, etc
* data: Implements the local(cache) and remote repository
* domain: Defines the repository interface and the UseCase

### Language
Kotlin

### Stack
* Retrofit
* Koin - Dependency Injection
* RxJava - reactive programming
* Data binding
* Room - ORM
* LiveData 
* Navigation library

### Tests
#### app module  
|Class name|Type|Description|
|---|---|---|
|CarListFragmentUnitTest.kt|Unit test|Example of navigation tests|
|CarListViewModelTest.kt|Instrumented test|Example of a ViewModel test|

#### data module  

|Class name|Type|Description|
|---|---|---|
|CarPayloadMapperTest.kt|Unit test|Example of a mapper test|
|RemoteDataSourceTest.kt|Unit test|Example of a remote data source test|
|CarDaoTest.kt|Instrumented test|Example of a database test|


## To be improved
* Open a car detail screen when tap on them(either map or list of cars)
* Domain module testing
* Default error handling for internet connection, permissions and api requests
* App icon
* Javadoc
* Location updates
* Create a component to observe the location updates the update the distance of the user and the car in real time

## Libraries
* Koin  
Dependency injection
* Gson  
Convert json to object
* Glide  
An image loading and caching library for Android focused on smooth scrolling
* Room  
Abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite
* Room RxJava  
Allows returning Rx from a room query
* Google maps  
Provide API to use a map
* Google location  
Provide GPS localization APIs
* Google Map utils  
Provide APIs to customize the google maps, specially markers
* Retrofit  
REST Client for Android
* OkHttp  
Efficient HTTP client
* OkHttp logging interceptor  
Logger for OkHttp  
* RxJava(kotlin, etc)  
Reactive Programming library    
* DataBinding  
Bind UI components in layouts to data sources  
* ViewModel  
Designed to help developers solve common Android Lifecycle challenges and to make apps more maintainable and testable  
* Lifecycle  
* CardView  
* RecyclerView  
* ConstraintLayout
* Navigation  
Google navigation framework  
* Espresso
* Mockito  
Test mocking  
* Koin test  
Dependency injection for testing  
* AndroidX test
* Android Arch test


## Download
The file can be downloaded clicking on the following link:  
[Download 1.0.0 version](https://github.com/maiconhellmann/sixt-evaluation/tree/master/releases/1.0.0/app-debug.apk)