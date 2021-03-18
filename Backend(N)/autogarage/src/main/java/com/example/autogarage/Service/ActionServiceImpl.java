package com.example.autogarage.Service;

import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Action;
import com.example.autogarage.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public long createAction(Action action) {
        Action newAction = actionRepository.save(action);
        return newAction.getId();
    }

    @Override
   public void updateAction(long id, Action action) {
        if (!actionRepository.existsById(id)) throw new RecordNotFoundException();
        Action existingAction = actionRepository.findById(id).get();
        existingAction.setAction(action.getAction());
        existingAction.setName(action.getName());
        existingAction.setPrice(action.getPrice());
        actionRepository.save(existingAction);
    }

    @Override
    public void partialUpdateAction(long id, Map<String, String> fields, Map<Double, Double> fields2) {
        if (!actionRepository.existsById(id)) throw new RecordNotFoundException();
        Action action = actionRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "action":
                    action.setAction((String) fields.get(field));
                    break;
                case "name":
                    action.setName((String) fields.get(field));
                    break;
                case "price":
                    action.setPrice((double) fields2.get(field));
                    break;
            }
        }
        actionRepository.save(action);
    }




    @Override
    public void deleteAction(long id) {
        if (!actionRepository.existsById(id)) throw new RecordNotFoundException();
        actionRepository.deleteById(id);
    }

    @Override
    public Collection<Action> getActions() {
        return actionRepository.findAll();
    }

    @Override
    public Optional<Action> getActionById(long id) {
        if (!actionRepository.existsById(id)) throw new RecordNotFoundException();
        return actionRepository.findById(id);
    }


}
