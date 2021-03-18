package com.example.autogarage.Service;

import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Reparation;
import com.example.autogarage.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ReparationImpl implements ReparationService {
    @Autowired
    private ReparationRepository reparationRepository;

    @Override
    public long createReparation(Reparation reparation) {
        Reparation newReparation = reparationRepository.save(reparation);
        return newReparation.getId();
    }

    @Override
    public void updateReparation(long id, Reparation reparation) {
        if (!reparationRepository.existsById(id)) throw new RecordNotFoundException();
        Reparation existingReparation = reparationRepository.findById(id).get();
        existingReparation.setDate(reparation.getDate());
        existingReparation.setStatus(reparation.getStatus());
        reparationRepository.save(existingReparation);
    }

    @Override
    public void partialUpdateReparation(long id, Map<String, String> fields) {
        if (!reparationRepository.existsById(id)) throw new RecordNotFoundException();
        Reparation reparation = reparationRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "date":
                    reparation.setDate((String) fields.get(field));
                    break;
                case "status":
                    reparation.setStatus((String) fields.get(field));
                    break;
            }
        }
        reparationRepository.save(reparation);
    }

    @Override
    public void deleteReparation(long id) {

    }

    @Override
    public Collection<Reparation> getReparation() {
        {
            return reparationRepository.findAll();
        }
    }

    @Override
    public Optional<Reparation> getReparationById(long id) {
        return Optional.empty();
    }
}
