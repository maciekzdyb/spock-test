package com.example

import org.example.Colour
import org.example.Pallete
import org.example.Renderer
import org.example.Shape
import org.example.ShapeFactory
import org.example.exceptions.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject


class ExampleSpecification extends Specification{

//    @Subject
//    private Shape shape = new Shape(5) // general field to test in methods

    void setupSpec() {
        // setup code that needs to be tun once at the start
    }

    void setup() {
        // setup code that needs to be tun before every test method
    }

    def "should  be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        given:
        def shape = new Shape(4) // it can be use inline in when section

        when:
        int sides = shape.numberOfSides

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Shape(0)

        then:
        def expectation = thrown(TooFewSidesException)
        expectation.numberOfSides == 0
    }

    def "should expect an Exception to be thrown for invalid input: #sides"() {
        when:
        new Shape(sides)

        then:
        def expectation = thrown(TooFewSidesException)
        expectation.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"() {

        expect:
        new Shape(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 7, 14]
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a,b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "should be able to mock a concreate class"() {
        given:
//        def renderer = Mock(Renderer) // Groovy style
        Renderer renderer = Mock()  // Java style, needed net.bytebuddy
        @Subject        //witch object is  being tested, it's only mark object to test
        def shape =  new Shape(4,renderer)

        when:
        shape.draw()

        then:
        4* renderer.drawLine()  // mock, 4 times use drawLine
    }

    def "should be able to create stub"() {
        given: "a pallette with red as the primary colour"
        Pallete pallete = Stub()
        pallete.primatyColour() >> Colour.Red
        and: "a render initialised with the red palette" // splitter
        @Subject
        def renderer = new Renderer(pallete)

        expect: "the render to use the palette's colour as foreground"
        renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        with(shape){
            numberOfSides == 4
            renderer == renderer
        }

    }
    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        with(shape){// if test fail, not checking others
            numberOfSides == 4
            renderer == renderer
        }
    }
    def "should demonstrate verifyAll"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        verifyAll(shape){// check all test even one fail
            numberOfSides == 4
            renderer == renderer
        }
    }

    void cleanupSpec() {
        // code that tears down things at the end when all tests have run
    }

    void cleanup() {
        // code that tears down things at the end of a test method
    }
}
