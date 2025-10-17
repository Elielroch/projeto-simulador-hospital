package com.hospital;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private static final int NUM_DOCTORS = 5;
    private static final int NUM_SURGERY_ROOMS = 2;
    private static final int NUM_BEDS = 10;

    private Semaphore doctors;
    private Semaphore surgeryRooms;
    private Semaphore beds;

    private BlockingQueue<Doctor> availableDoctors;
    private BlockingQueue<SurgeryRoom> availableSurgeryRooms;
    private BlockingQueue<Bed> availableBeds;

    public Hospital() {
        doctors = new Semaphore(NUM_DOCTORS, true);
        surgeryRooms = new Semaphore(NUM_SURGERY_ROOMS, true);
        beds = new Semaphore(NUM_BEDS, true);

        availableDoctors = new ArrayBlockingQueue<>(NUM_DOCTORS);
        for (int i = 0; i < NUM_DOCTORS; i++) {
            availableDoctors.add(new Doctor("Dr. " + (i + 1)));
        }

        availableSurgeryRooms = new ArrayBlockingQueue<>(NUM_SURGERY_ROOMS);
        for (int i = 0; i < NUM_SURGERY_ROOMS; i++) {
            availableSurgeryRooms.add(new SurgeryRoom("Sala de Cirurgia " + (i + 1)));
        }

        availableBeds = new ArrayBlockingQueue<>(NUM_BEDS);
        for (int i = 0; i < NUM_BEDS; i++) {
            availableBeds.add(new Bed("Leito " + (i + 1)));
        }
    }

    public Doctor acquireDoctor() throws InterruptedException {
        doctors.acquire();
        return availableDoctors.take();
    }

    public void releaseDoctor(Doctor doctor) {
        availableDoctors.offer(doctor);
        doctors.release();
    }

    public SurgeryRoom acquireSurgeryRoom() throws InterruptedException {
        surgeryRooms.acquire();
        return availableSurgeryRooms.take();
    }

    public void releaseSurgeryRoom(SurgeryRoom room) {
        availableSurgeryRooms.offer(room);
        surgeryRooms.release();
    }

    public Bed acquireBed() throws InterruptedException {
        beds.acquire();
        return availableBeds.take();
    }

    public void releaseBed(Bed bed) {
        availableBeds.offer(bed);
        beds.release();
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        List<Thread> patientThreads = new ArrayList<>();
        int numPatients = 25; // Conforme especificado no PDF

        System.out.println("Simulação de Hospital Iniciada com " + numPatients + " pacientes.");
        System.out.println("Recursos disponíveis: " + NUM_DOCTORS + " médicos, " + NUM_SURGERY_ROOMS + " salas de cirurgia, " + NUM_BEDS + " leitos.");

        for (int i = 0; i < numPatients; i++) {
            Patient patient = new Patient("Paciente " + (i + 1), hospital);
            Thread patientThread = new Thread(patient);
            patientThreads.add(patientThread);
            patientThread.start();
        }

        for (Thread thread : patientThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Erro ao aguardar thread do paciente: " + e.getMessage());
            }
        }

        System.out.println("Simulação de Hospital Concluída.");
    }
}

