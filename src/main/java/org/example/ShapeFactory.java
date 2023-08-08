package org.example;

public class ShapeFactory {
    private final Renderer renderer;

    public ShapeFactory(Renderer renderer) {
        this.renderer = renderer;
    }

    public Shape createDefaultPolygon(){
        return new Shape(4, renderer);
    }
}
