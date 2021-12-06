package document;

import document.element.TextElement;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<TextElement> content;

    public Document() {
        content = new ArrayList<>();
    }

    public void add(TextElement e) {
        content.add(e);
    }

    public int countWords() {
        int result = 0;
        for (TextElement e : content) {
            result += e.getText().split(" ").length;
        }
        return result;
    }

    public String toText(DocumentVisitor<String> visitor) {
        return visitor.toString(content);
    }
}
