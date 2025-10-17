package com.hospital;

import java.util.Random;

public class Patient implements Runnable {
    private String name;
    private Hospital hospital;
    private Random random = new Random();

    public Patient(String name, Hospital hospital) {
        this.name = name;
        this.hospital = hospital;
    }

    @Override
    public void run() {
        System.out.println(name + " chegou ao hospital.");
        try {
            // Consulta
            consultation();

            // Exames (opcional)
            if (random.nextBoolean()) {
                exams();
            }

            // Cirurgia (opcional, após exames)
            if (random.nextBoolean()) {
                surgery();
            }

            // Leito (opcional, após cirurgia ou se precisar de repouso)
            if (random.nextBoolean()) {
                bedRest();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " foi interrompido.");
        } finally {
            System.out.println(name + " deixou o hospital.");
        }
    }

    private void consultation() throws InterruptedException {
        System.out.println(name + " buscando um médico para consulta.");
        Doctor doctor = hospital.acquireDoctor();
        try {
            System.out.println(name + " em consulta com " + doctor.getName() + ".");
            Thread.sleep(random.nextInt(2000) + 1000); // Consulta dura entre 1-3 segundos
            System.out.println(name + " terminou a consulta com " + doctor.getName() + ".");
        } finally {
            hospital.releaseDoctor(doctor);
        }
    }

    private void exams() throws InterruptedException {
        System.out.println(name + " realizando exames.");
        Thread.sleep(random.nextInt(1500) + 500); // Exames duram entre 0.5-2 segundos
        System.out.println(name + " terminou os exames.");
    }

    private void surgery() throws InterruptedException {
        System.out.println(name + " buscando sala de cirurgia e médico.");
        Doctor doctor = hospital.acquireDoctor();
        try {
            SurgeryRoom room = hospital.acquireSurgeryRoom();
            try {
                System.out.println(name + " em cirurgia com " + doctor.getName() + " na " + room.getName() + ".");
                Thread.sleep(random.nextInt(4000) + 2000); // Cirurgia dura entre 2-6 segundos
                System.out.println(name + " terminou a cirurgia com " + doctor.getName() + " na " + room.getName() + ".");
            } finally {
                hospital.releaseSurgeryRoom(room);
            }
        } finally {
            hospital.releaseDoctor(doctor);
        }
    }

    private void bedRest() throws InterruptedException {
        System.out.println(name + " buscando um leito e supervisão médica.");
        Doctor doctor = hospital.acquireDoctor();
        try {
            Bed bed = hospital.acquireBed();
            try {
                System.out.println(name + " ocupando " + bed.getName() + " sob supervisão de " + doctor.getName() + ".");
                Thread.sleep(random.nextInt(3000) + 1000); // Repouso no leito dura entre 1-4 segundos
                System.out.println(name + " liberou " + bed.getName() + ".");
            } finally {
                hospital.releaseBed(bed);
            }
        } finally {
            hospital.releaseDoctor(doctor);
        }
    }
}
