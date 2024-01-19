# Guess The Breed App
<img src=https://github.com/alanvan-theiconic/GuessTheBreed/assets/101151538/33c0f859-31f4-475d-860f-b01f1b51f80d height=500/>
<img src=https://github.com/alanvan-theiconic/GuessTheBreed/assets/101151538/04c95ac1-a365-478a-b656-661a0468fd8e height=500/>

## <a name="contents"></a>Contents

- [Architecture Overview](#architecture)
- [Dependencies](#dependencies)
- [Testing](#testing)

## <a name="architecture"></a>Architecture Overview
<sup>[top](#contents)</sup>

The app architecture follows the clean architecture principle. There are three separate modules, i.e.
data, domain and app. With this architecture, the app is testable and maintainable. 
With separate modules, the separation of concerns are more seriously enforced.

## <a name="dependencies"></a>Dependencies
<sup>[top](#contents)</sup>

- Networking: Retrofit
- Dependency Injection: Koin
- Reactive programming: RxJava3
- Serialization/Deserialization: Gson
- Presentation layer: 
  - MVVM
  - Jetpack Compose
- Animation: Lottie
- Image loading: Coil
- 
## <a name="testing"></a>Testing
<sup>[top](#contents)</sup>

Unit tests are written extensively for the following types of classes:
- Repositories
- UseCases
- ViewModels
