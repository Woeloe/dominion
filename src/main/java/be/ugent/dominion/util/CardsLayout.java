package be.ugent.dominion.util;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CardsLayout extends Pane {

    private final double cardHeight;
    private final double cardWidth;
    private final double overlap;
    private final double verticalShift;
    private final int shifts;

    private final double stretch;

    public CardsLayout(double cardHeight, double cardWidth, double overlap, double verticalShift, int shifts) {
        this.cardHeight = cardHeight;
        this.cardWidth = cardWidth;
        this.overlap = overlap;
        this.verticalShift = verticalShift;
        this.shifts = shifts;
        this.stretch = 1.0 + (shifts - 1) * verticalShift;
    }

    @Override
    protected double computePrefHeight(double dummy) {
        return cardHeight * stretch + getInsets().getTop() + getInsets().getBottom();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double left = getInsets().getLeft();
        double width = getWidth() - left - getInsets().getRight();
        int nr = getChildren().size();
        if (nr != 0) {
            double totalWidth = nr*cardWidth;
            totalWidth = totalWidth * (1.0 - overlap) + cardWidth * overlap;
            double offset = cardWidth * overlap + left;
            double extra;
            if (width >= totalWidth || nr == 1) {
                // genoeg plaats om de kaarten lichtjes overlappend te schikken
                extra = 0.0;
            } else {
                // niet genoeg plaats
                extra = (width - totalWidth) / (nr - 1);
            }
            int modifier = 0;
            for (Node child : getChildren()) {
                offset -= cardWidth * overlap;
                child.relocate(offset, getInsets().getTop() + modifier * cardHeight * verticalShift);
                offset += cardWidth + extra;
                modifier = (modifier + 1) % shifts;
            }
        }

    }

}
