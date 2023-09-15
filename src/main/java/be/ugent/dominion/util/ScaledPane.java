package be.ugent.dominion.util;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.transform.Transform;

public class ScaledPane extends Region {

    private final double scale;

    public ScaledPane(double scale, Node... nodes) {
        getChildren().addAll(nodes);
        this.scale = scale;
        getTransforms().add(Transform.scale(scale, scale));
    }

    @Override
    protected double computePrefWidth(double height) {
        return super.computePrefWidth(height)*scale;
    }

    @Override
    protected double computePrefHeight(double width) {
        return super.computePrefHeight(width)*scale;
    }

    @Override
    protected void layoutChildren() {
        for (Node child : getManagedChildren()) {
            child.relocate(0,0);
            child.resize(getWidth()/scale, getHeight()/scale);
        }
    }
}
