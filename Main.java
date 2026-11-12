package org.example;
import java.util.List;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClinicService clinicService = new ClinicService();
        boolean exit = false;

        System.out.println("KLİNİK RANDEVU VE HASTA TAKİP SİSTEMİ");

        while (!exit){
            System.out.println("\n--- KLİNİK ANA MENÜ ---");
            System.out.println("1. Yeni Doktor Ekle");
            System.out.println("2. Doktorları Listele");
            System.out.println("3. Yeni Hasta Kaydı Yap");
            System.out.println("4. Hastaları Listele");
            System.out.println("5. Randevu Oluştur");
            System.out.println("6. Randevuları Listele");
            System.out.println("7. Çıkış");
            System.out.print("Seçiminiz (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer fix

            switch (choice) {
                case 1:
                    System.out.print("Doktor Adı: ");
                    String docName = scanner.nextLine();
                    System.out.print("Doktor Uzmanlık/Branş (Örn: Kardiyoloji): ");
                    String docBranch = scanner.nextLine();
                    clinicService.addDoctor(docName, docBranch);
                    break;

                case 2:
                    System.out.println("\n--- DOKTORLAR LİSTESİ ---");
                    List<Doctor> doctors = clinicService.getAllDoctors();
                    if (doctors.isEmpty()) {
                        System.out.println("⚠️ Kayıtlı doktor bulunmuyor.");
                    } else {
                        for (Doctor d : doctors) {
                            d.displayDoctor();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Hasta Adı: ");
                    String patName = scanner.nextLine();
                    System.out.print("Hasta Telefon Numarası: ");
                    String patPhone = scanner.nextLine();
                    clinicService.addPatient(patName, patPhone);
                    break;

                case 4:
                    System.out.println("\n--- HASTALAR LİSTESİ ---");
                    List<Patient> patients = clinicService.getAllPatients();
                    if (patients.isEmpty()) {
                        System.out.println("⚠️ Kayıtlı hasta bulunmuyor.");
                    } else {
                        for (Patient p : patients) {
                            p.displayPatient();
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- RANDEVU OLUŞTURMA ---");
                    // Doktor seçimi için önce doktorları listeleyelim
                    List<Doctor> docList = clinicService.getAllDoctors();
                    if (docList.isEmpty()) {
                        System.out.println("❌ Önce sisteme doktor kaydetmelisiniz!");
                        break;
                    }
                    System.out.println("Mevcut Doktorlar:");
                    for (Doctor d : docList) {
                        d.displayDoctor();
                    }
                    System.out.print("Randevu almak istediğiniz Doktor ID'sini girin: ");
                    int selectedDocId = scanner.nextInt();
                    scanner.nextLine();

                    // Hasta seçimi için hastaları listeleyelim
                    List<Patient> patList = clinicService.getAllPatients();
                    if (patList.isEmpty()) {
                        System.out.println("❌ Önce sisteme hasta kaydetmelisiniz!");
                        break;
                    }
                    System.out.println("\nMevcut Hastalar:");
                    for (Patient p : patList) {
                        p.displayPatient();
                    }
                    System.out.print("Randevu alacak Hasta ID'sini girin: ");
                    int selectedPatId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Randevu Tarihi ve Saati (Örn: 2026-06-10 14:30): ");
                    String appDate = scanner.nextLine();

                    clinicService.createAppointment(selectedDocId, selectedPatId, appDate);
                    break;

                case 6:
                    clinicService.displayAppointments();
                    break;

                case 7:
                    exit = true;
                    System.out.println("Klinik sisteminden çıkılıyor. Sağlıklı günler dileriz!");
                    break;

                default:
                    System.out.println("❌ Geçersiz seçim! Lütfen 1-7 arasında bir sayı girin.");
            }
        }
        scanner.close();
        }
    }