package ro.ubbcluj.apm.am.processor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessorTest {

    @Test
     void toUppercase() {
        StringProcessor<String> toUppercase= String::toUpperCase;

        assertThat(toUppercase.process("hello")).isEqualTo("HELLO");
    }


    @Test
    void containsSubstring(){
        StringProcessor<Boolean> containsSubstring =
                element -> element.contains("abc");

        assertThat(containsSubstring.process("abc")).isEqualTo(true);
    }

    @Test
    void countNoWords(){
        StringProcessor<Integer> countWords =
                element -> element.split("([ ,.])").length;
        assertThat(countWords.process("abc sdf,hjk.huj")).isEqualTo(4);
    }

    @Test
    void squareRoot(){
        StringProcessor<Integer> countWords =
                element -> element.split("([ ,.])").length;
        StringProcessor<Double> squareRoot=
                element -> Math.sqrt(countWords.process(element));

        assertThat(squareRoot.process("abc sdf,hjk.huj")).isEqualTo(2.0);
    }



}
