package com.example.autogarage.Service;

import com.example.autogarage.model.Examination;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ExaminationService {
    public abstract long createExamination(Examination examination);
    public abstract void updateExamination(long id, Examination examination);
    public abstract void partialUpdateExamination(long id, Map<String, String > fields);
    public abstract void deleteExamination(long id);
    public abstract Collection<Examination> getExamination();
    public abstract Optional<Examination> getExaminationById(long id);
}
