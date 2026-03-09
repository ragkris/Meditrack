# MediTrack -- Java Setup Instructions

This document explains how to set up the Java environment required to
run the MediTrack application.

### JDK Version Used

- Java Development Kit (JDK): JDK 17

The installed JDK version :
```
java -version
```
![JavaVersion](images/java_version.jpg)
---

------------------------------------------------------------------------

##  Compile the Project

Using Maven:

    mvn clean compile

Or compile manually:

    javac -d out src/com/airtribe/meditrack/Main.java

------------------------------------------------------------------------

## 6. Run the Application

    java com.airtribe.meditrack.Main

To load existing CSV data:

    java com.airtribe.meditrack.Main --loadData

------------------------------------------------------------------------

## 8. Project Dependencies

The project uses only **standard Java libraries** and does not require
external dependencies.

