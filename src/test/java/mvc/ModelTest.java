package mvc;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import service.FrameLine;
import service.StateHolder;
import service.TextService;
import service.TextServiceImpl;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class ModelTest {
    private Model model;
    private final View view = Mockito.mock(View.class);
    private final StateHolder stateHolder = new StateHolder();

    @Test
    public void processBackwardTest() {
        //given
        model = new Model();
        model.setFrame(TestUtil.getTestFrame());
        stateHolder.setFrameStartPosition(3);
        model.setStateHolder(stateHolder);
        ArgumentCaptor<List<FrameLine>> captor = ArgumentCaptor.forClass(List.class);

        when(view.getBounds()).thenReturn(new Rectangle(100, 20));
        when(view.getInsets()).thenReturn(new Insets(10, 5, 5, 5));
        when(view.getVerticalSpacer()).thenReturn(5);

        //when
        model.processBackward(view);
        verify(view).paint(captor.capture());
        //then
        List<FrameLine> actualValue = captor.getValue();
        assertTrue(actualValue.size() == 2);
        assertEquals("Third", actualValue.get(0).getWordList().get(0));
    }

    @Test
    public void processBackwardWhenFrameStartPositionIsZero() {
        //given
        model = new Model();
        stateHolder.setFrameStartPosition(0);
        model.setStateHolder(stateHolder);

        //when
        model.processBackward(view);

        //then
        verify(view, times(0)).paint(anyList());
    }

    @Test
    public void processForwardTest() {
        //given
        model = new Model();
        model.setFrame(TestUtil.getTestFrame());
        stateHolder.setFrameStartPosition(0);
        model.setStateHolder(stateHolder);
        ArgumentCaptor<List<FrameLine>> captor = ArgumentCaptor.forClass(List.class);

        when(view.getBounds()).thenReturn(new Rectangle(100, 20));
        when(view.getInsets()).thenReturn(new Insets(10, 5, 5, 5));
        when(view.getVerticalSpacer()).thenReturn(5);

        //when
        model.processForward(view);
        verify(view).paint(captor.capture());
        //then
        List<FrameLine> actualValue = captor.getValue();
        assertTrue(actualValue.size() == 2);
        assertEquals("Second", actualValue.get(0).getWordList().get(0));
    }

    @Test
    public void processResizedTest() {
        //given
        TextService textService = Mockito.mock(TextServiceImpl.class);
        model = new Model();
        model.setTextService(textService);
        model.setStringList(TestUtil.getList());
        stateHolder.setFrameStartPosition(0);
        model.setStateHolder(stateHolder);
        ArgumentCaptor<List<FrameLine>> captor = ArgumentCaptor.forClass(List.class);

        when(view.getBounds()).thenReturn(new Rectangle(100, 30));
        when(view.getInsets()).thenReturn(new Insets(10, 5, 5, 5));
        when(view.getVerticalSpacer()).thenReturn(5);
        when(textService.computeFrame(TestUtil.getList(), view, stateHolder)).thenReturn(TestUtil.getTestFrame());

        //when
        model.processResized(view);
        verify(view).paint(captor.capture());
        //then
        List<FrameLine> actualValue = captor.getValue();
        assertTrue(actualValue.size() == 4);
        assertEquals("First", actualValue.get(0).getWordList().get(0));

    }
}