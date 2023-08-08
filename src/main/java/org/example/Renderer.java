package org.example;

public class Renderer {
    private final Pallete pallete;

    public Renderer(Pallete pallete) {
        this.pallete = pallete;
    }

    public void drawLine(){
    }
    public Colour getForegroundColour(){
        return pallete.getPrimatyColour();
    }
}
