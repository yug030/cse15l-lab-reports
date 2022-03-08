import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// javac -cp ".;lib/\*" OurMarkdownParseTest.java
// java -cp ".;lib/\*" org.junit.runner.JUnitCore OurMarkdownParseTest

public class OurMarkdownParseTest {
    
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testSnippet1() throws IOException{
        Path fileName = Path.of("Snippet1.md");
	    String contents = Files.readString(fileName);
        List<String> expected = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expected, OurMarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet2() throws IOException{
        Path fileName = Path.of("Snippet2.md");
	    String contents = Files.readString(fileName);
        List<String> expected = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expected, OurMarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException{
        Path fileName = Path.of("Snippet3.md");
	    String contents = Files.readString(fileName);
        List<String> expected = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expected, OurMarkdownParse.getLinks(contents));
    }

    
}