package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicService {

    // --- DOKTOR İŞLEMLERİ ---

    // Doktor Ekleme
    public void addDoctor(String name, String branch) {
        String query = "INSERT INTO doctors (name, branch) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, branch);
            pstmt.executeUpdate();
            System.out.println("✅ Doktor başarıyla kaydedildi: Dr. " + name);
        } catch (SQLException e) {
            System.out.println("❌ Doktor eklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Doktorları Listeleme
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("branch");
                doctors.add(new Doctor(id, name, branch));
            }
        } catch (SQLException e) {
            System.out.println("❌ Doktorlar listelenirken hata: " + e.getMessage());
        }
        return doctors;
    }

    // --- HASTA İŞLEMLERİ ---

    // Hasta Ekleme
    public void addPatient(String name, String phone) {
        String query = "INSERT INTO patients (name, phone) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.executeUpdate();
            System.out.println("✅ Hasta başarıyla kaydedildi: " + name);
        } catch (SQLException e) {
            System.out.println("❌ Hasta eklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Hastaları Listeleme
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                patients.add(new Patient(id, name, phone));
            }
        } catch (SQLException e) {
            System.out.println("❌ Hastalar listelenirken hata: " + e.getMessage());
        }
        return patients;
    }

    // --- RANDEVU İŞLEMLERİ ---

    // Randevu Oluşturma
    public void createAppointment(int doctorId, int patientId, String date) {
        String query = "INSERT INTO appointments (doctor_id, patient_id, appointment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctorId);
            pstmt.setInt(2, patientId);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
            System.out.println("🎉 Randevu başarıyla oluşturuldu!");
        } catch (SQLException e) {
            System.out.println("❌ Randevu oluşturulurken hata: " + e.getMessage());
        }
    }

    // Randevuları Listeleme
    public void displayAppointments() {
        String query = "SELECT a.id, d.name AS doctor_name, p.name AS patient_name, a.appointment_date " +
                "FROM appointments a " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN patients p ON a.patient_id = p.id";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- AKTİF RANDEVULAR ---");
            boolean hasAppointment = false;
            while (rs.next()) {
                hasAppointment = true;
                System.out.println("Randevu ID: " + rs.getInt("id") +
                        " | Dr. " + rs.getString("doctor_name") +
                        " | Hasta: " + rs.getString("patient_name") +
                        " | Tarih: " + rs.getString("appointment_date"));
            }
            if (!hasAppointment) {
                System.out.println("⚠️ Henüz kayıtlı bir randevu bulunmuyor.");
            }
            System.out.println("----------------------------------------");
        } catch (SQLException e) {
            System.out.println("❌ Randevular listelenirken hata: " + e.getMessage());
        }
    }
}
