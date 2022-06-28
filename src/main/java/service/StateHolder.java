package service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateHolder {
    private int currentStringPosition;
    private int currentWordPosition;
    private long globalPosition;
    private boolean isDivided;
    private int subStringPosition;
    private int frameStartPosition;

    public void increaseStringPosition() {
        currentStringPosition++;
    }
    
    public void increaseWordPosition() {
        currentWordPosition++;
    }

    public void increaseGlobalPosition() {
        globalPosition++;
    }

    public void increaseFrameStartPosition() {
        frameStartPosition++;
    }

    public void decreaseFrameStartPosition() {
        frameStartPosition--;
    }

    public void reset() {
        this.currentStringPosition = 0;
        this.currentWordPosition = 0;
        this.globalPosition = 0;
        this.isDivided = false;
        this.subStringPosition = 0;
    }
}
