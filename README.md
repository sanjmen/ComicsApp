# ComicsApp

Android Application that allows you to browse comics, series and stories from Marvel's vast library of comics—from what's coming up, to 70 years ago.

## Configuration

Before build:
* rename ```keystore.properties.sample``` file as ```keystore.properties```
* update ```API_PUBLIC_KEY``` and ```API_SECRET_KEY``` with your own credentials from your [Marvel Developer Account](https://developer.marvel.com/)

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



