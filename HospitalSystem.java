import java.util.*;

// Doctor Class
class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}

// Patient Class
class Patient {
    int id;
    String name;
    int age;

    Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

// Appointment Class
class Appointment {
    Doctor doctor;
    Patient patient;
    String timeSlot;
    String status;

    Appointment(Doctor doctor, Patient patient, String timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.status = "Booked";
    }
}

// Appointment Manager
class AppointmentManager {

    // Check slot availability
    boolean isSlotAvailable(Doctor doc, String time) {
        for (Appointment a : appointments) {
            if (a.doctor.id == doc.id &&
                a.timeSlot.equalsIgnoreCase(time) &&
                a.status.equals("Booked")) {
                return false;
            }
        }
        return true;
    }

    // Book Appointment
    void bookAppointment(Doctor doc, Patient pat, String time) {
        if (isSlotAvailable(doc, time)) {
            appointments.add(new Appointment(doc, pat, time));
            System.out.println("✅ Appointment booked successfully!");
        } else {
            System.out.println("❌ Slot not available!");
        }
    }

    // Cancel Appointment
    void cancelAppointment(int patientId, String time) {
        for (Appointment a : appointments) {
            if (a.patient.id == patientId &&
                a.timeSlot.equalsIgnoreCase(time) &&
                a.status.equals("Booked")) 
                
                a.status = "Cancelled";
                System.out.println("✅ Appointment cancelled!");
                return;
            }
        }
        System.out.println("❌ Appointment not found!");
    }

    // Display Appointments
    void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment a : appointments) {
            System.out.println("\nDoctor: " + a.doctor.name);
            System.out.println("Patient: " + a.patient.name);
            System.out.println("Time: " + a.timeSlot);
            System.out.println("Status: " + a.status);
        }
    }
}

// Main Class
public class HospitalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Sample Data
        doctors.add(new Doctor(1, "Dr. Sharma", "Cardiologist"));
        doctors.add(new Doctor(2, "Dr. Mehta", "Dentist"));

        patients.add(new Patient(1, "Rahul", 22));
        patients.add(new Patient(2, "Priya", 25));

        AppointmentManager manager = new AppointmentManager();

        while (true) {
            System.out.println("\n===== Hospital Appointment System =====");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Doctor ID: ");
                    int docId = sc.nextInt();

                    System.out.print("Enter Patient ID: ");
                    int patId = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Time Slot: ");
                    String time = sc.nextLine();

                    Doctor selectedDoctor = null;
                    Patient selectedPatient = null;

                    for (Doctor d : doctors) {
                        if (d.id == docId) {
                            selectedDoctor = d;
                            break;
                        }
                    }

                    for (Patient p : patients) {
                        if (p.id == patId) {
                            selectedPatient = p;
                            break;
                        }
                    }

                    if (selectedDoctor != null && selectedPatient != null) {
                        manager.bookAppointment(selectedDoctor, selectedPatient, time);
                    } else {
                        System.out.println("❌ Invalid Doctor or Patient ID!");
                    }
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int cancelId = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Time Slot: ");
                    String cancelTime = sc.nextLine();

                    manager.cancelAppointment(cancelId, cancelTime);
                    break;

                case 3:
                    manager.displayAppointments();
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
