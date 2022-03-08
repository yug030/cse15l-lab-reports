// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class OurMarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ](, then take up to the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextExclamationOpenBracket = markdown.indexOf("![", currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracketAndOpenParen = markdown.indexOf("](", nextOpenBracket);
            int closeParen = markdown.indexOf(")", nextCloseBracketAndOpenParen);
            if (currentIndex > closeParen
                    || (nextExclamationOpenBracket == nextOpenBracket-1 && nextOpenBracket != 0)
                    || nextCloseBracketAndOpenParen < 0) {
                break;
            } else if (currentIndex < closeParen) {
                currentIndex = closeParen + 1;
            }
            String link = markdown.substring(nextCloseBracketAndOpenParen + 2, closeParen);
            if (!link.contains(" ")) {
                toReturn.add(link);
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}