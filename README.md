# home assignment
## Kotlin + MVVM + Clean Architecture + Coroutines + Hilt...

This project was made with the objective of creating a base structure for new apps, using tools and components supported by Google and by most of the Android development community.

## Clean Architecture

Layers

Clean Architecture basically consists of 5 layers.

### Presentation: Layers that interact with the user. This layer includes Activity, Fragment, ViewModel classes etc.

### Use Cases: The layer where the user’s work is defined. You can find a detailed explanation of this title in the following sections of the article.

### Domain: The layer where logic operations are performed. For example, entities, value object, exceptions, and logic operations are in this layer.

### Data: It is the layer where the source of all abstract data is. All data and models that the application can use are located in this layer.

### Framework: The layer that interacts with the SDK or framework. So that is, it implements the interaction with the Android SDK and provides custom implementations for the data layer.


## MVVM

(https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

The Model View ViewModel pattern helps with the separation of concerns, dividing the user interface with the logic behind. The decision to use this pattern is mainly based on the support Google has been giving to it. Not only they have created a ViewModel class to use as a parent to the viewmodels, there is also a huge use of the pattern in official Android presentations and samples. Moreover, MVVM is vastly used in today’s Android development, and combines very well with Android Architecture Components like LiveData and DataBindings.

### Model

As we are implementing MVVM alongside with Clean Architecture, we decided not to have a model class per se. The ViewModel interacts directly with the domain, utilizing the use cases.

### View Model

The orchestrator of the relationship between the data and the user interface of the application. The ViewModel has the logic to convert what the use cases provide into information that the view can understand and present. Furthermore, it has the logic to react to the user’s input, and call the pertinent use cases.

The most useful part of the Android’s ViewModel class is its lifecycle consciousness. It only communicates to the View with LiveData components, so it’s totally agnostic of contexts and activities: it can keep the information alive even against configuration changes like screen rotations or calls to background.

### View

The view in our implementation of MVVM is actually a Fragment or an Activity. The views enclose everything needed to handle the user interface. They observe the ViewModel, using LiveData components, and react to its changes as they need to.

### LiveData Architecture Component

The view uses LiveData to observe changes in the ViewModel. This has  several advantages:

* The UI matches the data state, and this keeps data up to date.
* Not having to worry about stopped activities and memory leaks. Live data objects are subscript to a lifecycle and automatically stop observing when that lifecycle is ended.
* Handles configuration changes properly.
* The same data could be shared between activities.

## Dependency Injection with Hilt

Dependency injection is closely related to two SOLID concepts: dependency inversion, which states that high level modules should not depend on low level modules, both should depend on abstractions; and single responsibility principle, which states that every class or module is responsible for just a single piece of functionality.
DI supports these goals by decoupling the creation and the usage of an object. It allows you to replace dependencies without changing the class that uses them and also reduces the risk of modifying a class because one of its dependencies changed.
This sample app uses Hilt as the dependency injection library.

## Coroutines

Coroutines are a new way of managing background threads that can simplify code by reducing the need for callbacks. They convert async callbacks for long-running tasks, such as database or network access, into sequential code.
We use coroutines to do tasks in a background thread. This goes very well with the idea of use cases, single actions that the ViewModel calls depending of its needs. The guideline should be that every task executed by a use case should be done in a background thread, so, in the main thread, we could show a loading screen or any alternative, and the UI doesn’t get blocked.

__Job__: a job is a cancellable task with a life-cycle that culminates in its completion. By default, a failure of any of the job’s children leads to an immediate failure of its parent and cancellation of the rest of its children. This behavior can be customized using SupervisorJob.

__Dispatchers__:
* Dispatchers.Default – is used by all standard builders by default. It uses a common pool of shared background threads. This is an appropriate choice for compute-intensive coroutines that consume CPU resources.
* Dispatchers.IO – uses a shared pool of on-demand created threads and is designed for offloading of IO-intensive blocking operations (like file I/O and blocking socket I/O).

## Other concepts

### AndroidX

AndroidX is a redesigned library, replacing the support library, to make package names more clear. Each androidx package has it own version, detached from the Android API version, so extension libraries can be developed independently.

Androidx also improves understanding of what is added to the app:

* android.* -> bundled in the platform.
* androidx.* -> extension library.
