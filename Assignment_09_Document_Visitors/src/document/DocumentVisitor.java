package document;

import document.element.*;

import java.util.List;

public interface DocumentVisitor<R> {

    public R visitBasicText(BasicText current);

    public R visitBoldText(BoldText current);

    public R visitHeading(Heading current);

    public R visitHyperText(HyperText current);

    public R visitItalicText(ItalicText current);

    public R visitParagraph(Paragraph current);

    public String toString(List<TextElement> content);
}
