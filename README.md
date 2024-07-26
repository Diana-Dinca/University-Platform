### User roles
#### The designed application provides an IT system for managing a study platform through a graphical interface. Its functionality covers the management of four types of users: students, teachers, administrators and super-administrators. They can perform different functions, having different ranks. 
All the users share the ability to view their own personal data, but the admin type is the only one who can modify the data and delete a student or teacher user, while the super-admin has maximum privileges.
####
The student user has functionalities such as: enrolling or withdrawing from a course; view his notes; viewing, enrolling or canceling activities within a course, as well as viewing the activities to which he is enrolled; viewing, joining or opting out of a study group and activities organized by that group, as well as exchanging messages between members.
#### 
The teacher-type user has functionalities such as: viewing activities, scheduling activities, setting grade percentages for the different activities of the course (laboratory, seminar, course), accessing the catalog where they can filter students by course and add grades to them.
#### 
The administrator type user has functionalities such as: searching for users by name, filtering all users by type, searching for a course and assigning teachers to it, viewing all students enrolled in a certain course.
### Implementation
#### 
In the designed application I used 15 tables to retain the entire set of data required for an optimal operation of the database. The created model involves *6 ManytoMany* relationships, implemented by adding intermediate tables and all the functionalities were implemented using specific procedures, which I later called in Java, to be able to link the users of the application to the database.
#### 
To work as efficiently as possible, I structured the Java code into several packages:
###### •	*Configuration*- provides the connection between the database and the working platform in Java
###### •	*Databases*- contains the model classes for the database tables
###### •	*Repository*- contains the database procedure calls
###### •	*Service*- contains the interfaces of each tab and functionality of the application
#### 
For each type of user I have implemented *CRUD* operations, found in the Repository package, in a total number of 37 procedures.
###### Here are some examples of methods called from SQL in Java:
```java
/// Autentification method
 public static int autentification(String email, String passw) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String sqlQuery = "{call Autentificare(?, ?)}";  
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, passw);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int resultAutentification = resultSet.getInt(1);
                        return resultAutentification;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0; //No results found
    }
/// Create a new Teacher identity
  public static int addProf(String email, String passw, String cnp, String name, String surname, String address, String phone, int nrContract, String iban, int nrMinHour, int nrMaxHour, String departament) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall = "{call addProf(?,?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, passw);
                callableStatement.setString(3, cnp);
                callableStatement.setString(4, name);
                callableStatement.setString(5, surname);
                callableStatement.setString(6, address);
                callableStatement.setString(7, phone);
                callableStatement.setInt(8, nrContract);
                callableStatement.setString(9, iban);
                callableStatement.setInt(10, nrMinHour);
                callableStatement.setInt(11, nrMaxHour);
                callableStatement.setString(12, departament);

                callableStatement.execute();
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int result = resultSet.getInt(1);
                        return result;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
/// View Study Group activities
  public static String viewStudyGroupActivities(String courseName) {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (Connection connection = ConnectConfig.getConnection()) {
            String storedProcedureCall = "{call ViewStudyGroupActivities(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, courseName);

                if (callableStatement.execute()) {
                    ResultSet resultSet = callableStatement.getResultSet();
                    while (resultSet.next()) {
                        resultStringBuilder.append("Id Activitie: ").append(resultSet.getInt("id_activitie")).append("\n")
                                .append("Id Group: ").append(resultSet.getInt("id_group")).append("\n")
                                .append("Description: ").append(resultSet.getString("description")).append("\n")
                                .append("Date: ").append(resultSet.getDate("date")).append("\n")
                                .append("Nr Min Participants: ").append(resultSet.getInt("nr_min_participants")).append("\n")
                                .append("Duration: ").append(resultSet.getInt("duration")).append("\n")
                                .append("Nr Participants: ").append(resultSet.getInt("nrStudents")).append("\n").append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resultStringBuilder.length() > 0) {
            return resultStringBuilder.toString();
        } else {
            return "There are no groups/ activities for this Course!";
        }
    }
/// Cancel Course activitie method
    public static String cancelCourseActivitie(int idActivitie, String emailStudent) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall = "{call CancelCourseActivitie(?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, idActivitie);
                callableStatement.setString(2, emailStudent);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Error at cancelation";
    }
```
###### My favourite functionality of the application represents the possibility of real time messaging between users from the same course.
```java
 public static String messages(String courseName) {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (Connection connection = ConnectConfig.getConnection()) {
            String storedProcedureCall = "{call messages(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, numeCurs);

                if (callableStatement.execute()) {
                    ResultSet resultSet = callableStatement.getResultSet();
                    while (resultSet.next()) {
                        resultStringBuilder.append(resultSet.getString("name")).append(" ")
                                .append(resultSet.getString("surname")).append(": ")
                                .append(resultSet.getString("message")).append(" ----")
                                .append(resultSet.getString("date")).append("\n\n");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (resultStringBuilder.length() > 0) {
            return resultStringBuilder.toString();
        } else {
            return "There are no messages/group for this course!";
        }
    }

    public static void addMessage(String message, String emailStudent, String courseName) {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "username"; 
        String password = "password";

        try (Connection connection = ConnectConfig.getConnection()) {
            String callProcedure = "{CALL AddMessage(?, ?, ?)}";

            try (PreparedStatement statement = connection.prepareCall(callProcedure)) {
                statement.setString(1, message); 
                statement.setString(2, emailStudent);
                statement.setString(3, courseName); 
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

```
### User Manual
When starting the application, the login window opens. If we already have an account, fill in the email and password fields, otherwise press the "New User" button, which will take us to a page for filling out personal data, specific to the type of user previously chosen: "Student", "Teacher" or "Administrator". After an account has been created, the Home page opens.
###
On the Home page, the user can choose from several functionalities, with a simple push of a button:
###
1. View data, which allows the user to view personal data
###
2. School status that allows the student to search, enroll or drop courses, see the grades obtained in the course, the seminar, the respective laboratory and the final grade
###
3. Activity View, which allows the student to search for, sign up for, or opt out of activities within a course
###
4. Study groups, which allow the student to search for, join or leave a study group, but also to communicate with other students within the group
###
5. Deauthentication, which allows the user to log out and leave the application, returning to the login page
###
In addition to these functions, the application also presents "Back" buttons, which send the user to the previous page.
#### The application offers a variety of functionalities and a simple and intuitive design. It is easy to use, with suggestive buttons, making it perfect for use in an educational setting. 
