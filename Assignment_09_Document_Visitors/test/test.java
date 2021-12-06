import document.Document;
import document.MarkdownStringVisitor;
import document.element.*;
import org.junit.Test;

/**
 * @author novo
 * @since 2021/12/5
 */
public class test {
    @Test
    public void test() {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new BoldText("bbbbbb"));
        paragraph.add(new ItalicText("cccccc"));
        paragraph.add(new Heading("dddddd", 1));
        paragraph.add(new Heading("eeeeeee", 2));
        paragraph.add(new HyperText("mmmmm", "www.google.com"));
        Document document = new Document();
        document.add(paragraph);
        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(new BoldText("ooooo"));
        paragraph2.add(new ItalicText("iiiii"));
        document.add(paragraph2);
        System.out.println("------\n" + document.toText(new MarkdownStringVisitor()) + "\n--------");
    }

    // test1
    // Top Level HeadingJust text is an important part of every document.Khoury College Website

    // test2
    //Top Level Heading
    //Just text is an important part of every document.
    //Khoury College Website
    //Link to Khoury College
    //Summary
    //Bold text so it's more important!
    //Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
}
