package document;

import document.element.*;

import java.util.List;

/**
 * @author novo
 * @since 2021/12/5
 */
public class MarkdownStringVisitor implements DocumentVisitor<String> {
    @Override
    public String visitBasicText(BasicText current) {
        return current.getText();
    }

    @Override
    public String visitBoldText(BoldText current) {
        return "**" + current.getText() + "**";
    }

    @Override
    public String visitHeading(Heading current) {
        int level = current.getLevel();
        StringBuilder sb = new StringBuilder();
        while (level-- != 0) {
            sb.append("#");
        }
        sb.append(" ");
        return sb.append(current.getText()).toString();
    }

    @Override
    public String visitHyperText(HyperText current) {
        return "[" + current.getText() + "](" + current.getUrl() + ")";
    }

    @Override
    public String visitItalicText(ItalicText current) {
        return "*" + current.getText() + "*";
    }

    @Override
    public String visitParagraph(Paragraph current) {
        List<BasicText> content = current.getContent();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            BasicText e = content.get(i);
            sb.append(e.accept(this));
            if (i != content.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString(List<TextElement> content) {
        StringBuilder sb = new StringBuilder();
//        StringBuilder debug = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            TextElement e = content.get(i);
            sb.append(e.accept(this));
            if (i != content.size() - 1) {
                sb.append("\n");
            }
//            debug.append(e.getText()).append("\n");
        }
//        if (content.size() > 4) {
//            throw new IllegalStateException(debug.toString());
//        }
        return sb.toString();
    }
}
