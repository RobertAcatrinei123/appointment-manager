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

## Tasks

### Seminar 1

- [x] Write a Java class that keeps information about a doctor: name, specialty, location, grade from
  reviews.
- [x] Define a repository which uses a data structure from Java to store all doctors.
- [ ] Implement a service and a UI for CRUD operations.

### Seminar 2
- [x] Define a superclass/interface for any identifiable entity. This will represent any entity having an
ID (e.g. doctor, patient, appointment).
- [x] Write a Java class that keeps information about a patient: name, address, phone number.
- [x] Define a repository which uses a data structure from Java to store all doctors/patients and make
  sure to provide an iterator for it.
- [x] Filter doctors by various criteria (e.g. specialty, location). Use a Filter interface with a method
  accept and implement this interface in various classes, according to the required filters.
- [ ] Define a class for a sorted repository, which stores sorted entities. The sorting is made according
  to a sorting strategy (BubbleSort, MergeSort), which is provided via an AbstractSorter object
  (encapsulated in the sorted repository), which offers a sort method.