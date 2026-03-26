package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class QuestStep {
    private String text;
    private Map<String, String> choices;
    private boolean isFinal;
    private boolean isWin;

    public QuestStep(String text, Map<String, String> choices) {
        this.text = text;
        this.choices = new LinkedHashMap(choices);
        this.isFinal = false;
    }

    public QuestStep(String text, boolean isWin) {
        this.text = text;
        this.isFinal = true;
        this.isWin = isWin;
    }

    public String getText() {
        return this.text;
    }

    public Map<String, String> getChoices() {
        return this.choices;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public boolean isWin() {
        return this.isWin;
    }
}
