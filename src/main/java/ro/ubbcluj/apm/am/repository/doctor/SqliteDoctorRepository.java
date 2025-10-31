package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.config.ApplicationConfig;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.Repository;
import ro.ubbcluj.apm.am.util.FileUtil;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SqliteDoctorRepository implements Repository<Integer, Doctor> {
    private final String connectionUrl;

    public SqliteDoctorRepository() {
        try {
            String dbPath = ApplicationConfig.getInstance().getDoctorRepositoryResourcePath("sqlite");
            connectionUrl = "jdbc:sqlite:" + FileUtil.getFilePath(dbPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS doctors (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "specialty TEXT NOT NULL," +
                    "location TEXT NOT NULL," +
                    "grade DOUBLE NOT NULL" +
                    ");";
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Doctor add(Doctor value) {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            String sql = "INSERT INTO doctors (name, specialty, location, grade) VALUES (?, ?, ?, ?);";
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, value.getName());
            preparedStatement.setString(2, value.getSpecialty());
            preparedStatement.setString(3, value.getLocation());
            preparedStatement.setDouble(4, value.getGrade());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Doctor addedDoctor = new Doctor();
                addedDoctor.setId(generatedKeys.getInt(1));
                addedDoctor.setName(value.getName());
                addedDoctor.setSpecialty(value.getSpecialty());
                addedDoctor.setLocation(value.getLocation());
                addedDoctor.setGrade(value.getGrade());
                return addedDoctor;
            }

            throw new RuntimeException("No doctor has been added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Collection<Doctor> findAll() {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            String sql = "SELECT id, name, specialty, location, grade FROM doctors;";
            var preparedStatement = connection.prepareStatement(sql);
            var resultSet = preparedStatement.executeQuery();
            List<Doctor> doctors = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setSpecialty(resultSet.getString("specialty"));
                doctor.setLocation(resultSet.getString("location"));
                doctor.setGrade(resultSet.getDouble("grade"));
                doctors.add(doctor);
            }
            return doctors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Doctor value) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
