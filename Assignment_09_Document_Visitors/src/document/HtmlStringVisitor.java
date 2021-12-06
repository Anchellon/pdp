package document;

import document.element.*;

import java.util.List;

/**
 * @author novo
 * @since 2021/12/5
 */
public class HtmlStringVisitor implements DocumentVisitor<String> {
    @Override
    public String visitBasicText(BasicText current) {
        return current.getText();
    }

    @Override
    public String visitBoldText(BoldText current) {
        return "<b>" + current.getText() + "</b>";
    }

    @Override
    public String visitHeading(Heading current) {
        return "<h" + current.getLevel() + ">" + current.getText() + "</h" + current.getLevel() + ">";
    }

    @Override
    public String visitHyperText(HyperText current) {
        // <a href="url">Link text to click on</a>
        return "<a href=\"" + current.getUrl() + "\">" + current.getText() + "</a>";
    }

    @Override
    public String visitItalicText(ItalicText current) {
        return "<i>" + current.getText() + "</i>";
    }

    @Override
    public String visitParagraph(Paragraph current) {
        List<BasicText> content = current.getContent();
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        for (BasicText e : content) {
            sb.append(e.accept(this)).append("\n");
        }
        sb.append("</p>");
        return sb.toString();
    }

    @Override
    public String toString(List<TextElement> content) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            TextElement e = content.get(i);
            sb.append(e.accept(this));
            if (i != content.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
