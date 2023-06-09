# About the App

An Android App for a hypothetical sports event.

## Features
- Show all participating teams.
- Show all previous and upcoming matches.
- User can select a team and filter matches per team.
- Watch previous match highlights.
- Users can set a reminder for an upcoming match.
- Notify the user when the match is about to start.

## Need to improve
- Using Jetpack Compose.

## Built With 
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewDataBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
 
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Coil](https://coil-kt.github.io/coil) - An image loading library for Android backed by Kotlin Coroutines
- [Mockito](https://github.com/mockito/mockito) - Most popular mocking framework
- [ExoPlayer](https://github.com/google/ExoPlayer) - An application level media player for Android.
- [Kotlin DSL](https://medium.com/android-dev-hacks/kotlin-dsl-gradle-scripts-in-android-made-easy-b8e2991e2ba): Gradle scripts in Android made easy

## Architecture
This app uses [MVVM with Clean Architecture](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) .

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
![image](https://miro.medium.com/v2/resize:fit:1204/format:webp/1*QRSeKDCITJL2GG6RLVO2iQ.png)

### Advantages of Using Clean Architecture

 - The code is even more easily testable than with plain MVVM.

 - The code is further decoupled (the biggest advantage.)

 - The package structure is even easier to navigate.

 - The team can add new features even more quickly.

 - The project is even easier to maintain.
 
 ### In the project
 <img width="319" alt="image" src="https://user-images.githubusercontent.com/100013592/227702904-afecbdfe-f474-438b-836c-ff723c3b4954.png">
 In the app's build gradle
 <img width="342" alt="image" src="https://user-images.githubusercontent.com/100013592/227703231-867e37c9-8fb6-49fe-b369-494ea6f995d8.png">

 - Layers of MVVM
    * Domain: The app’s business logic is stored here.
    * Data: All data sources are defined in a broad sense.
    * Presentation: This is a layer that interacts with the (UI)user interface.
 
### Persist data locally with Room & Flow 
 - We want to let the users continue to use our application even if the device they’re using doesn’t have an internet connection. However, even if the user is connected to the network, we can save a lot of bandwidth and keep the network traffic to a minimum. Most of the time the users don’t want to waste time looking at some loading screens, a case in which we can display previous data instantly while fetching new data in the background. So when doing that, we can greatly improve the user experience. [Reference](https://medium.com/androiddevelopers/room-coroutines-422b786dc4c5)
 
 - In the feature Show all participating teams, I use the Room + Kotlin Flow — a modern Android architecture:
 <img width="893" alt="image" src="https://user-images.githubusercontent.com/100013592/227840393-f8646fc9-6adf-4d67-aed0-7e3fa3772601.png">

 
 
## Unit Test

### Test API with MockWebServer
- Test the API Get All Team
<img width="654" alt="image" src="https://user-images.githubusercontent.com/100013592/227704089-b56333a4-cd19-407c-846d-0e62573d62af.png">

## Screenshots

<img width="332" alt="image" src="https://user-images.githubusercontent.com/100013592/227764258-dd73c50d-e26a-40db-85c4-b9df2fcb9c15.png">
<img width="330" alt="image" src="https://user-images.githubusercontent.com/100013592/227469363-609edf7c-a421-426e-8826-94174e3d6094.png">
<img width="333" alt="image" src="https://user-images.githubusercontent.com/100013592/227469652-652404e5-6f34-46ce-894a-00d022eb6233.png">
<img width="332" alt="image" src="https://user-images.githubusercontent.com/100013592/227764284-dce72e42-4336-436a-a088-e2cac7f10040.png">






