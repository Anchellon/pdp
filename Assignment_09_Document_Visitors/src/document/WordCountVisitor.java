package document;

import document.element.*;

import java.util.List;

/**
 * @author novo
 * @since 2021/12/5
 */
public class WordCountVisitor implements DocumentVisitor<Integer>{

    @Override
    public Integer visitBasicText(BasicText current) {
        return current.getText().split(" ").length;
    }

    @Override
    public Integer visitBoldText(BoldText current) {
        return current.getText().split(" ").length;
    }

    @Override
    public Integer visitHeading(Heading current) {
        return current.getText().split(" ").length;
    }

    @Override
    public Integer visitHyperText(HyperText current) {
        return current.getText().split(" ").length;
    }

    @Override
    public Integer visitItalicText(ItalicText current) {
        return current.getText().split(" ").length;
    }

    @Override
    public Integer visitParagraph(Paragraph current) {
        return current.getText().split(" ").length;
    }

    @Override
    public String toString(List<TextElement> content) {
        return null;
    }
}
