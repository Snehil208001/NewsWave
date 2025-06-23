✨ NewsWave: Your Daily News Companion 📰
NewsWave is a modern Android application built with Jetpack Compose that brings the latest news directly to your fingertips. Stay informed with a sleek, intuitive interface and a seamless reading experience.

🚀 Features
Latest News Fetching: Pulls real-time news articles from a powerful news API.
Interactive Article List: Browse through headlines with clean, easy-to-read cards.
In-App Article Viewing: Read full articles directly within the app using an integrated WebView.
Responsive UI: Crafted with Jetpack Compose for a beautiful and consistent user experience across devices.
Loading & Error Handling: Provides clear feedback during data fetching and in case of network issues.
📸 Demo
(https://drive.google.com/drive/folders/1CbmQkgeJnYDIeBWfj88lBXK77xG5fWVq)

🛠️ Technologies Used
Kotlin: The primary programming language for Android development.
Jetpack Compose: Modern toolkit for building native Android UI.
Retrofit: Type-safe HTTP client for Android and Java for fetching data from the News API.
Kotlinx Serialization: For efficient JSON parsing of API responses.
Coil: An image loading library for Android, optimized for Compose.
Jetpack Navigation Compose: For managing in-app navigation.
⚙️ Setup & Installation
To get a local copy up and running, follow these simple steps:

Clone the repository:
Bash

git clone [Your-Repo-URL-Here]
Open in Android Studio: Navigate to the cloned directory and open the project in Android Studio.
Get an API Key: NewsWave uses the News API to fetch articles. You'll need to obtain a free API key from their website.
Update API Key: Open the ApiServices.kt file located at app/src/main/java/com/example/newswave/network/ApiServices.kt and replace YOUR_API_KEY_HERE with your actual News API key:
Kotlin

interface ApiServices {
    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "2025-06-21",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "YOUR_API_KEY_HERE" // Replace this line
    ): Response<NewsModel>
}
Build and Run: Sync Gradle files and run the application on an emulator or a physical device.
⬇️ Download APK
You can download the latest APK directly from here:https://drive.google.com/drive/folders/1Cj5tzkEwKfF7fg2lkByy3GjiwDt18-Yx

Download NewsWave APK

🤝 Contributing
Contributions are always welcome! If you have any suggestions, bug reports, or want to contribute to the codebase, feel free to open an issue or submit a pull request.

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

🙏 Acknowledgments
News API for providing the news data.
Jetpack Compose team for the amazing UI toolkit.
