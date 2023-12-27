package Repository;
import Configuration.ConnectConfig;

import java.sql.*;


public class UtilizatorRepository {
    public static int autentificare(String email, String parola) {
    try {
        Connection connection = ConnectConfig.getConnection();
        String sqlQuery = "{call Autentificare(?, ?)}";  // Presupunând că procedura stocată returnează un rezultat
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, parola);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int rezultatAutentificare = resultSet.getInt(1);
                    return rezultatAutentificare;
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return 0;  // În cazul în care nu există rezultate
}

    public static void adaugareStudent(String email,String parola, String cnp, String nume, String prenume, String adresa, String nrTel,  int nrContract, String iban, int anStudiu, int nrOre) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall="{call AdaugareStudent(?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, parola);
                callableStatement.setString(3, cnp);
                callableStatement.setString(4, nume);
                callableStatement.setString(5, prenume);
                callableStatement.setString(6, adresa);
                callableStatement.setString(7, nrTel);
                callableStatement.setInt(8, nrContract);
                callableStatement.setString(9, iban);
                callableStatement.setInt(10, anStudiu);
                callableStatement.setInt(11, nrOre);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void adaugareProfesor(String email,String parola, String cnp, String nume, String prenume, String adresa,  String nrTel, int nrContract, String iban,  int nrMinOre, int nrMaxOre, String departament) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall="{call AdaugareProfesor(?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, parola);
                callableStatement.setString(3, cnp);
                callableStatement.setString(4, nume);
                callableStatement.setString(5, prenume);
                callableStatement.setString(6, adresa);
                callableStatement.setString(7, nrTel);
                callableStatement.setInt(8, nrContract);
                callableStatement.setString(9, iban);
                callableStatement.setInt(10, nrMinOre);
                callableStatement.setInt(11, nrMaxOre);
                callableStatement.setString(12, departament);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void adaugareAdministrator(String email,String parola, String cnp, String nume, String prenume, String adresa,  String nrTel, int nrContract, String iban, boolean superAdmin) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall="{call AdaugareAdministrator(?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, parola);
                callableStatement.setString(3, cnp);
                callableStatement.setString(4, nume);
                callableStatement.setString(5, prenume);
                callableStatement.setString(6, adresa);
                callableStatement.setString(7, nrTel);
                callableStatement.setInt(8, nrContract);
                callableStatement.setString(9, iban);
                callableStatement.setBoolean(10, superAdmin);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String vizualizareDatePersonale(String email) {
        try {
            Connection connection = ConnectConfig.getConnection();
            String storedProcedureCall = "{call VizualizareDatePersonale(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, email);

                // Executăm procedura stocată și returnăm rezultatul
                if (callableStatement.execute()) {
                    ResultSet resultSet = callableStatement.getResultSet();
                    if (resultSet != null && resultSet.next()) {
                        // Construim un șir de caractere cu datele
                        String rezultat = "Email: " + resultSet.getString("email")+ "\n" +
                                "Nume: " + resultSet.getString("nume") + "\n" +
                                "Prenume: " + resultSet.getString("prenume") + "\n" +
                                "CNP: " + resultSet.getString("cnp") + "\n" +
                                "Adresa: " + resultSet.getString("adresa") + "\n" +
                                "Numar telefon: " + resultSet.getString("nr_tel")+ "\n" +
                                "IBAN: " + resultSet.getString("iban")+ "\n" +
                                "Numar contract: " + resultSet.getString("nr_contract");

                        // Întoarcem rezultatul sub forma unui șir de caractere
                        return rezultat;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Nu s-au găsit date personale pentru adresa de email specificată.";
    }


}
