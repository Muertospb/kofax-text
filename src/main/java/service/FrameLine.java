package service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrameLine {
    private List<Integer> fontList = new ArrayList<>();
    private List<String> wordList = new ArrayList<>();
    private List<Integer> spacesList = new ArrayList<>();
}
