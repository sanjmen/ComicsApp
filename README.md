# ComicsApp

Android Application that allows you to browse comics, series and stories from Marvel's vast library of comics—from what's coming up, to 70 years ago.

## Dependencies

* Android SDK API 27
* Android Build Tools 27.0.1
* Android Gradle Plugin 3.0.0
* Android Support Libraries 27.0.0
* Dagger-Android 2.12
* RxJava 2.1.6
* RxAndroid 2.0.1
* Gson 2.8.2
* Retrofit 2.3.0
* Picasso 2.5.2

Look at [dependencies.gradle](dependencies.gradle) for a full list of libraries

## Basic setup

Before you start you should:

* Create a [keystore.properties](keystore.properties) file ```cp keystore.properties.sample keystore.properties```
* Get your API key pair from your [Marvel Developer Account](https://developer.marvel.com/) and change in keystore.properties file

```
MARVEL_API_KEY = "your_api_key"
MARVEL_SECRET_KEY = "your_secret"
```

* To to generate a signed APK you must create a key and a keystore. [How to generate a keystore](https://developer.android.com/studio/publish/app-signing.html#generate-key)
* then update keystore.properties with your own key and store credentials 

````
STORE_PASSWORD="my store password"
KEY_PASSWORD="my key password"
KEY_ALIAS="my key alias"
STORE_FILE="/path/to/my/store/file"
````

* now you can build and install the application

## Build and Install

```
$ ./gradlew assembleRelease
```

This creates an APK named app-release.apk in project_name/app/build/outputs/apk/.
This APK file is signed with the private key specified in your build.gradle file and aligned with zipalign.

Also you can build, align, sign, and install the release APK on an emulator or device all with the installRelease task.
 
```
$ ./gradlew installRelease
```

## Screenshots
![Comic List](/screenshots/comic_list.png?raw=true "Comic List")
![Comic Detail](/screenshots/comic_detail.png?raw=true "Comic Detail")

## Static Code Analysis Tools

Keep your code healthy and maintain code quality

#### Lint
*The lint tool checks your Android project source files for potential bugs and optimization improvements for correctness, security, performance, usability, accessibility, and internationalization.*

run lint with
```
$ ./gradlew lint
   
Execution failed for task ':app:lint.
Lint found errors in the project; aborting build.
Wrote HTML report to: template/app/build/outputs/lint/lint.html
```

#### Findbugs
*Static code analysis tool that analyses Java bytecode and detects a wide range of problems.*

run findbugs with
```
$ ./gradlew findbugs

Execution failed for task ':app:findbugs'.
FindBugs rule violations were found. 
See the report at: template/app/build/outputs/findbugs/findbugs.html
```

#### PMD
*PMD is a source code analyzer. It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth.*

run pmd with:
```
$ ./gradlew pmd

Execution failed for task ':app:pmd.
7 PMD rule violations were found. 
See the report at: template/app/build/outputs/pmd/pmd.html
```



