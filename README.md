# Appointment Manager

Project for APM seminar 2025

## Description

This is an application that facilitates searching and communication between doctors and patients.
It will allow patients to:

- search for doctors (by location, specialty, grade from reviews)
- get their records history
- see which doctors they consulted
- add doctor reviews
- edit their profile

Doctors will be able to:

- add, remove, modify, get patient records for their patients
- edit their profile
- plan appointments

## Requirements

To build and run this application you need to install the followings:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/) (or newer)
- [Maven](https://maven.apache.org/install.html)

On macOS, you can use these commands (assuming that [Homebrew](https://brew.sh/) is already installed):

```bash
brew install openjdk@21
brew install --ignore-dependencies maven
```

## Usage

### 1. Build the project

```bash
mvn clean install
```
