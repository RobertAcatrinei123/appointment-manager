Changelog
=========

All notable changes to this project will be documented in this file.

### Seminar 7

- [ ] Implement cascade delete logic, to ensure that the delete operation maintains data consistency that all related
  entities are deleted automatically, when needed (e.g. when a doctor/patient is deleted, all his/her appointments are deleted as well).
- [ ] Add a multiple undo/redo functionality for the Java application we have been working with. Implement this functionality using
  inheritance and polymorphism, an example is illustrated in the figure below. The performed operations (actions) can be memorized in
  undo/redo stacks.

### Seminar 6

- [x] create an Appointment entity, which includes references to the Doctor, Patient and date and time information
- [x] All appointments of a doctor, for a given date, sorted by time
- [x] Names of all patients which will be consulted by a doctor on a given date
- [x] All appointments for a patient, sorted by date and time
- [x] Grades of all doctors a patient will visit

### Seminar 5

- [x] Implement MergeSorter with Comparable
- [x] Implement MergeSorter with Comparator
- [x] storing and retrieving doctors data to/from a binary file
- [x] store doctors in JSON format
- [x] storing and retrieving doctors data to/from a DB (Sqlite)

### Seminar 4

- [x] storing and retrieving doctors data to/from a text file
- [ ] storing and retrieving doctors data to/from a binary file
- [x] The decision of which repositories are employed, as well as the location of the repository input
  files will be made available via the program’s _settings.properties_ file and the Java Properties class.
- [x] Use the Java exception mechanism to handle exceptional situations.
- [x] Use JUnit to test functionalities from the domain and repository layers and assertThat methods.

### Seminar 3

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

### Seminar 1

- [x] Write a Java class that keeps information about a doctor: name, specialty, location, grade from
  reviews.
- [x] Define a repository which uses a data structure from Java to store all doctors.
- [ ] Implement a service and a UI for CRUD operations.


