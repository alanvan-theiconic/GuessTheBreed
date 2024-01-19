# Guess The Breed App

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
