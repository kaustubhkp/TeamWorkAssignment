# TeamWorkAssignment


| ![My image](https://github.com/kaustubhkp/TeamWorkAssignment/blob/master/screenshots/list.jpeg)] | ![My image](https://github.com/kaustubhkp/TeamWorkAssignment/blob/master/screenshots/detail-info.jpeg)
|:---:|:---:|

# Quick Start:
- Clone project<br>
- Build and Run<br>

# External Libraries:
- RxJava <br>
- Retrofit <br>
- Dagger <br>
- Butter knife <br>
- Picasso <br>
- Mockito <br>
- Power Mockito <br>

# Architecture:
This project uses MVP pattern. It allowed for rapid iteration and it would seamlessly support any unit or instrumented tests down the line due to the separation of concerns. The project is further organised into different modules based on functionality and their usage and classification is explained below. These sections also feature any improvements that could be done to these respective modules.

<br>

# App:
Initialises Dagger’s parent component to make them available for the entire lifecycle of the app.<br>

Improvements: Implementing LeakCanary to detect memory leaks

# Dagger:
Dependency Injection is done via Dagger. AppComponent serves as the parent component and exposes the Retrofit Service and FusedLocationProviderClient its dependent components. All the components provided here are scoped to be a Singleton and the dependent components (implemented for each screen) have scopes restricting them to their respective modules, enabling modularity of the codebase. <br>

Improvements: Using Dagger-Android to make the activities more agnostic about their injections. This would also reduce the dependency of Activity having to provide the view to initialise the dependent module. <br>

# Home:
This application uses MVP architecture. It first initialise dependency modules via Dagger. Presenter is responsible for checking internet connection and then fetch project list from server using TeamWork APIs. Once list is fetched It will inform view and view will load data on screen using recycler view. On list It will show project icon, project name, start date, end date and project status(Green: active, Red: inactive). Picasso is used to show project icon. Once user click on project It will redirect to detail screen where we show project icon, project name, start date, end date, project status and project description. Parceler is used for passing object between the activities.

# Model:
POJOs representing the response model from the API.<br>

# API Key:
To store TeamWork API key securely on device I put it inside gradle.properties and access it via BuildConfig file at runtime. As gradle.properties is not part of apk when we create release apk so I used this approach to save API key. Best way is to get It from server which is not feasible in our case<br>



# Net:
Interface for Retrofit to communicate with the API. The Retrofit instance provided by Dagger uses OkHttp’s and uses RxJava adapter to return the results in form of an Observable. <br>

# Lint:
Lint ran on the code and removed most of the error's and warnings <br>

# Tests:
Code is tested using instrumentation and unit testing.
1. Instrumentation tests - Project list and project detail list is tested using espresso framework provided by google. It test clicking of recycler view as well.
2. Uint test - Utils class and presenter is tested using Junit. In presenter, API calling is tested using powerMockito.


# General Improvements:
- Support for landscape/tablet UI and Improve UI<br>

- Use clean architecture.