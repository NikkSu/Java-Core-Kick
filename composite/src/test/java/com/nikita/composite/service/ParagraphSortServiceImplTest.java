package com.nikita.composite.service;

import com.nikita.composite.entity.ComponentType;
import com.nikita.composite.entity.TextComponent;
import com.nikita.composite.service.impl.ParagraphSortServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParagraphSortServiceImplTest {

    @InjectMocks
    private ParagraphSortServiceImpl sortService;

    @Mock
    private TextComponent rootText;

    @Mock
    private TextComponent paragraph1;

    @Mock
    private TextComponent paragraph2;

    @Test
    void testSortParagraphsByLetter_ValidRoot_CallsRemoveAndAddInCorrectOrder() {
        char targetLetter = 'a';

        when(rootText.getType()).thenReturn(ComponentType.TEXT);

        List<TextComponent> children = Arrays.asList(paragraph1, paragraph2);
        when(rootText.getChildren()).thenReturn(children);

        when(paragraph1.reconstruct()).thenReturn("a");
        when(paragraph2.reconstruct()).thenReturn("a a a");

        sortService.sortParagraphsByLetter(rootText, targetLetter);

        verify(rootText).remove(paragraph1);
        verify(rootText).remove(paragraph2);

        var inOrder = inOrder(rootText);
        inOrder.verify(rootText).add(paragraph2);
        inOrder.verify(rootText).add(paragraph1);
    }
}