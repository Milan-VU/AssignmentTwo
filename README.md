# Assignment Two - Android Application Development Project

## Summary
This Android application demonstrates API integration, user interface design, and Android development best practices. The app includes **Login**, **Dashboard**, and **Details** screens.

### Prerequisites
Ensure you have the following tools and versions installed:

- **Android Studio**: Latest version is recommended.
- **Java Development Kit (JDK)**: Version 8 or above.
- **Kotlin**: Version 1.9.0
- **Gradle**: Version 8.7
- **Android Gradle Plugin (AGP)**: Version 8.1.0
- **Retrofit**: Version 2.9.0 
- **Moshi**: Version 1.12.0 
- **Hilt**: Version 2.48 
- **JUnit 4**: Version 4.13.2
- **Mockito**: Version 3.11.2 
- **Coroutines**: Version 1.7.3 

### Setup Instructions To Build and Run

1. **Open the Project in Android Studio:**
   - Download and extract the project files.
   - Launch Android Studio.
   - Select **`Open an existing project`** and navigate to the project directory.

2. **Sync and Build:**
   - Android Studio should automatically sync the project. If not, use **`File > Sync Project with Gradle Files`**.
   - To build, select **`Build > Make Project`** or click the **`Build`** button.

3. **Run the Application:**
   - Connect an Android device or start an Android emulator.
   - Click the **`Run`** button (green arrow) in the toolbar, or go to **`Run > Run 'app'`**.

### Usage Instructions

1. **Login:**
   - Use **Milan** as the username and **s4663796** as the password.
   - Upon successful login, you will be navigated to the Dashboard screen.

2. **Dashboard:**
   - The Dashboard displays a list of entities retrieved from the API.
   - Click on any item to navigate to the Details screen, where you can view more details about the selected entity.

3. **Details:**
   - The Details screen shows comprehensive information about the selected entity, including a description.
   - Swipe back to return to the Dashboard.

## Dependencies
The project uses the following libraries and tools:

- **Hilt:** For Dependency Injection
- **Retrofit:** For API calls
- **Moshi:** For JSON parsing
- **Coroutines:** For asynchronous tasks
- **JUnit 4:** For unit testing
- **Mockito:** For mocking in tests
- **AndroidX Libraries:** For lifecycle management, LiveData, ViewModel, RecyclerView, etc.

## Important Notes
- This app is configured to work specifically with the credentials **Milan** (username) and **s4663796** (password). Ensure you use these credentials to successfully log in and access the application's features.

## Author
- **Milan Mitrovic**
- **s4663796:** 

