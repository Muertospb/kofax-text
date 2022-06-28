package mvc;

import lombok.Getter;
import lombok.Setter;
import service.FrameLine;
import service.StateHolder;
import service.TextService;
import service.TextServiceImpl;

import java.util.List;

@Getter
@Setter
public class Model {
    private TextService textService = new TextServiceImpl();
    private List<List<String>> stringList;
    private StateHolder stateHolder;
    private List<FrameLine> frame;

    public void processBackward(View view) {
        if (stateHolder.getFrameStartPosition() > 0) {
            stateHolder.decreaseFrameStartPosition();
            List<FrameLine> viewList = frame.subList(stateHolder.getFrameStartPosition(), stateHolder.getFrameStartPosition() + calculateNumberOfLines(view));
            view.paint(viewList);
        }
    }

    public void processForward(View view) {
        if (stateHolder.getFrameStartPosition() + calculateNumberOfLines(view) < frame.size()) {
            stateHolder.increaseFrameStartPosition();
            List<FrameLine> viewList = frame.subList(stateHolder.getFrameStartPosition(), stateHolder.getFrameStartPosition() + calculateNumberOfLines(view));
            view.paint(viewList);
        }
    }

    public void processResized(View view) {
        frame = textService.computeFrame(stringList, view, stateHolder);
        int linesNumber = calculateNumberOfLines(view);
        if (stateHolder.getFrameStartPosition() + linesNumber > frame.size()) {
            int newFrameStartPosition = frame.size() - linesNumber;
            stateHolder.setFrameStartPosition(newFrameStartPosition >= 0 ? newFrameStartPosition : 0);
        }
        List<FrameLine> viewList;
        if (stateHolder.getFrameStartPosition() + linesNumber > frame.size()) {
            viewList = frame;
        } else {
            viewList = frame.subList(stateHolder.getFrameStartPosition(), stateHolder.getFrameStartPosition() + linesNumber);
        }
        view.paint(viewList);
    }

    private int calculateNumberOfLines(View view) {
        double height = view.getBounds().getHeight() - view.getInsets().top;
        return (int) height / view.getVerticalSpacer();
    }
}
