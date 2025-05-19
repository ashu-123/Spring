package com.learning.service.doctor;

import com.learning.dao.doctor.DoctorRepository;
import com.learning.email.EmailService;
import com.learning.model.doctor.Doctor;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final EmailService emailService;

    public DoctorService(DoctorRepository doctorRepository, EmailService emailService) {
        this.doctorRepository = doctorRepository;
        this.emailService = emailService;
    }

    @PostConstruct
    public void initializeListOfDoctors() {
        this.doctorRepository.saveAll(
                Stream.of(new Doctor(1, "Ashu"),
                          new Doctor(2, "Ashutosh"))
                        .toList());
    }

    public List<Doctor> getDoctors() {
        emailService.sendEmail();
        return doctorRepository.findAll();
    }
}
