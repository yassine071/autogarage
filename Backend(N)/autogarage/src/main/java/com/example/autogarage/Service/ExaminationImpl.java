package com.example.autogarage.Service;

import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Examination;
import com.example.autogarage.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ExaminationImpl implements ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;

    @Override
    public long createExamination(Examination examination) {
        Examination newExamination = examinationRepository.save(examination);
        return newExamination.getId();
    }

    @Override
    public void updateExamination(long id, Examination examination) {
        if (!examinationRepository.existsById(id)) throw new RecordNotFoundException();
        Examination existingExamination = examinationRepository.findById(id).get();
        existingExamination.setDate(examination.getDate());
        existingExamination.setAppointment(examination.getAppointment());
        existingExamination.setCostumer(examination.getCostumer());
        examinationRepository.save(existingExamination);
    }

    @Override
    public void partialUpdateExamination(long id, Map<String, String> fields) {
        if (!examinationRepository.existsById(id)) throw new RecordNotFoundException();
        Examination examination = examinationRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "date":
                    examination.setDate((String) fields.get(field));
                    break;
                case "appointment":
                    examination.setAppointment((String) fields.get(field));
                    break;
                case "costumer":
                    examination.setCostumer((String) fields.get(field));
                    break;
            }
        }
        examinationRepository.save(examination);
    }

    @Override
    public void deleteExamination(long id) {

    }

    @Override
    public Collection<Examination> getExamination() {
        {
            return examinationRepository.findAll();
        }
    }

    @Override
    public Optional<Examination> getExaminationById(long id) {
        return Optional.empty();
    }

}
