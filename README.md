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
- Watch previous match highlights.

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
 
### Persist data locally with Room & Flow 
 - We want to let the users continue to use our application even if the device they’re using doesn’t have an internet connection. However, even if the user is connected to the network, we can save a lot of bandwidth and keep the network traffic to a minimum. Most of the time the users don’t want to waste time looking at some loading screens, a case in which we can display previous data instantly while fetching new data in the background. So when doing that, we can greatly improve the user experience. [Reference](https://medium.com/androiddevelopers/room-coroutines-422b786dc4c5)
 
 - In the feature Show all participating teams, I use the Room + Kotlin Flow — a modern Android architecture:
 <img width="865" alt="image" src="https://user-images.githubusercontent.com/100013592/227413948-d855cc1e-8cfa-452d-af76-1ebfb2bc6bf9.png">

 
 - 

 
## Screenshots

<img width="336" alt="image" src="https://user-images.githubusercontent.com/100013592/227468458-662cb3ba-9c9f-4927-a713-bc95bab96cc5.png"> <img width="333" alt="image" src="https://user-images.githubusercontent.com/100013592/227468759-ca30e4e1-6ec6-48d5-b604-335e7b9309d7.png">


