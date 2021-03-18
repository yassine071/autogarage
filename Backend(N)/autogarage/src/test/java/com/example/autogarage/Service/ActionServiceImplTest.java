package com.example.autogarage.Service;

import com.example.autogarage.model.Action;
import com.example.autogarage.repository.ActionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.data.util.CastUtils;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ActionServiceImplTest {
    @InjectMocks
    private ActionServiceImpl actionService;

    @Mock
    private ActionRepository actionRepository;

    @Captor
    ArgumentCaptor<Action> actionCaptor;

    @Test
    void createAction() {
        Action action = new Action();
        action.setAction("Vervangen");
        action.setName("Band");
        action.setPrice(35.0);

        when(actionRepository.save(action)).thenAnswer(new Answer<Action>() {
            public Action answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Action action = CastUtils.cast(args[0]);
                action.setId(2L);
                return action;
            }
        });
        assertEquals(2L, actionService.createAction(action));
        verify(actionRepository).save(action);
    }
    @Test
    void updateAction() {
            when(actionRepository.existsById(2L)).thenReturn(true);
            Action action = new Action();
            action.setName("Motor");
            action.setAction("Motor toegevoegd");
            action.setPrice(2000.0);

            Action existingAction = new Action();
            existingAction.setId(2L);
            existingAction.setName("Motor reparatie");
            existingAction.setAction("motor chiptunning");
            existingAction.setPrice(2300.0);
            when(actionRepository.findById(2L)).thenReturn(Optional.<Action>of(existingAction));
            actionService.updateAction(2L, action);
            verify(actionRepository).save(actionCaptor.capture());
            assertEquals("Motor", actionCaptor.getValue().getName());
            assertEquals(2000.0, actionCaptor.getValue().getPrice());
    }

}