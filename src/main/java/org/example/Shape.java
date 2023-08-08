package org.example;

import org.example.exceptions.TooFewSidesException;

public class Shape {
    private final int numberOfSides;
    private Renderer renderer;

    public Shape(int numberOfSides) throws TooFewSidesException {
        if (numberOfSides <=2) {
            throw new TooFewSidesException("You can't have fewer than 3 sides for this shape", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }


    public Shape(int numberOfSides, Renderer renderer){
        this.numberOfSides = numberOfSides;
        this.renderer = renderer;
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void draw(){
        for (int i = 0; i < numberOfSides; i++){
            renderer.drawLine();
        }
    }
}
