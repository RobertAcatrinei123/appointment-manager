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
- [x] Provide the option of sorting the entities in the service, according to a sorting strategy
  (BubbleSort, MergeSort), which is provided via an AbstractSorter object, which offers a sort
  method.

### Seminar 3
For the problem defined at Seminar 2 do the following (see the UML diagram below):
- [x] The identifiable entity must be generic.
- [x] The classes corresponding to doctor and patient must implement the Comparable interface.
- [x] Define a generic interface IRepository, which provides all necessary operations for a repository.
- [x] The generic class MemoryRepository will implement the aforementioned Interface and stores
  generic identifiable objects in a Map (HashMap, TreeMap), where the objects identifiers are the
  keys and the values are the actual objects.
- [x] Encapsulate the doctorFilter creation in a FilterFactory class, which is responsible with the creation of
  concrete Filter objects, according to the provided parameters. The FilterFactory will provide a
  getFilter(String filterType, Object filterValue) method, which will instantiate concrete filters,
  according to its parameters.