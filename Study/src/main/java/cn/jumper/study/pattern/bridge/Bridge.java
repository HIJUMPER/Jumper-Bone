package cn.jumper.study.pattern.bridge;

/**
 * @author Jumper
 * 2020/2/29
 */
public class Bridge {

}

abstract class Shape {
    public abstract String getName();
}

class Triangle extends Shape {
    @Override
    public String getName() {
        return "Triangle";
    }
}

class Square extends Shape {

    private Renderer renderer;

    Square(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public String getName() {
        return "Square";
    }

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", getName(),renderer.whatToRenderAs());
    }
}

class VectorSquare extends Square {

    VectorSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString() {
        return String.format("Drawing %s as lines", getName());
    }
}

class RasterSquare extends Square {

    RasterSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString() {
        return String.format("Drawing %s as pixels", getName());
    }
}

interface Renderer {
    String whatToRenderAs();
}

class VectorRenderer implements Renderer {
    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements Renderer {
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}
