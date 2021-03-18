package com.example.autogarage.Service;

import com.example.autogarage.model.Reparation;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ReparationService {
    public abstract long createReparation(Reparation reparation);
    public abstract void updateReparation(long id, Reparation reparation);
    public abstract void partialUpdateReparation(long id, Map<String, String > fields);
    public abstract void deleteReparation(long id);
    public abstract Collection<Reparation> getReparation();
    public abstract Optional<Reparation> getReparationById(long id);
}
