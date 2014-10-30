/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

/**
 *
 * @author frank
 */
public class PDrawSwing {

    private DrawPanel drawPanel;
    private Graphics2D g2d;
    private LinkedList<AffineTransform> transformerFifo = new LinkedList<>();
    private AffineTransform transformer;
    private float strokeWidth = 0;
    private Color strokeColor = Color.black;
    private boolean noStroke = false;
    private Color fillColor = Color.white;
    private GeneralPath path = null;
    private boolean firstVertex = false;
    private final int defaultTextSize = 14;
    private int textSize = defaultTextSize;
    private final String defaultTextFont = "TimesRoman";
    private String textFont = defaultTextFont;
    private final int defaultTextStyle = Font.PLAIN;
    private int textStyle = defaultTextStyle;

    public enum ShapeMode {

        CLOSE;
    }

    public enum EllipseMode {

        CENTER;
    }

    public PDrawSwing(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        this.g2d = drawPanel.getGraphics2D();
        transformer = g2d.getTransform();
        g2d.setFont(new Font(textFont, Font.PLAIN, textSize));
    }

    public void background(Color c) {
        g2d.setBackground(c);
        g2d.clearRect(0, 0, drawPanel.width, drawPanel.height);
    }

    public void setColor(Color c) {
        g2d.setPaint(c);
    }

    public void setColor(int r, int g, int b, int a) {
        g2d.setPaint(new Color(r, g, b, a));
    }

    public void setColor(int grayLevel) {
        g2d.setColor(new Color(grayLevel << 16, grayLevel << 8, grayLevel));
    }

    public Color getGrayColor(int grayLevel) {
        return new Color(grayLevel, grayLevel, grayLevel);
    }

    public Color getGrayColorWithAlpha(int grayLevel, int alpha) {
        return new Color(grayLevel, grayLevel, grayLevel, alpha);
    }

    // Thickness of line borders of shapes
    public void strokeWeight(float s) {
        noStroke = false;
        g2d.setStroke(new BasicStroke(s));
    }

    public void noStroke() {
        noStroke = true;
    }

    // Color of line borders of shapes
    public void stroke(Color c) {
        strokeColor = c;
        noStroke = false;
    }

    public void stroke(int r, int g, int b) {
        strokeColor = new Color(r, g, b);
        noStroke = false;
    }

    public void stroke(int r, int g, int b, int alpha) {
        strokeColor = new Color(r, g, b, alpha);
        noStroke = false;
    }

    public void stroke(int gray) {
        strokeColor = getGrayColor(gray);
        noStroke = false;
    }

    public void stroke(int gray, int alpha) {
        strokeColor = getGrayColorWithAlpha(gray, alpha);
        noStroke = false;
    }

    public void noFill() {
        fill(0, 0, 0, 0);
    }

    // Color used to fill in shape
    public void fill(Color c) {
        fillColor = c;
    }

    public void fill(int r, int g, int b) {
        fillColor = new Color(r, g, b);
    }

    public void fill(int r, int g, int b, int alpha) {
        fillColor = new Color(r, g, b, alpha);
    }

    public void fill(int grayLevel) {
        fillColor = getGrayColor(grayLevel);
    }

    public void fill(int gray, int alpha) {
        fillColor = getGrayColorWithAlpha(gray, alpha);
    }

    public void rect(double x, double y, double width, double height) {
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(fillColor);
        g2d.fill(rect);
        if (!noStroke) {
            g2d.setColor(strokeColor);
            g2d.draw(rect);
        }
    }

    public void pushMatrix() {
        transformerFifo.add(transformer);
        transformer = new AffineTransform();
        g2d.setTransform(transformer);
    }

    public void popMatrix() {
        transformer = transformerFifo.removeFirst();
        g2d.setTransform(transformer);
    }

    public void translate(double x, double y) {
        g2d.translate(x, y);
    }

    public void rotate(double theta) {
        g2d.rotate(theta);
    }

    public void scale(double h, double w) {
        transformer.scale(h, w);
    }

    public void beginShape() {
        path = new GeneralPath();
        firstVertex = true;
    }

    public void endShape(ShapeMode mode) {
        if (path != null) {
            if (mode == ShapeMode.CLOSE) {
                path.closePath();
            }
            g2d.setColor(fillColor);
            g2d.fill(path);
            if (!noStroke) {
                g2d.setColor(strokeColor);
                g2d.draw(path);
            }
        }
    }

    public void vertex(double x, double y) {
        if (path != null) {
            if (firstVertex) {
                path.moveTo(x, y);
                firstVertex = false;
            } else {
                path.lineTo(x, y);
            }
        }
    }

    public void point(double x, double y) {
        line(x, y, x, y);
    }

    public void line(double x1, double y1, double x2, double y2) {
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        g2d.setColor(fillColor);
        g2d.fill(line);
        if (!noStroke) {
            g2d.setColor(strokeColor);
            g2d.draw(line);
        }
    }

    public void ellipseMode(EllipseMode ellipseMode) {
    }

    public void ellipse(double x, double y, double xDiameter, double yDiameter) {
        Ellipse2D.Double ellipse =
                new Ellipse2D.Double(x - xDiameter / 2.0, y - yDiameter / 2.0, xDiameter, yDiameter);
        g2d.setColor(fillColor);
        g2d.fill(ellipse);
        if (!noStroke) {
            g2d.setColor(strokeColor);
            g2d.draw(ellipse);
        }
    }

    public void text(String str, double x, double y) {
        g2d.drawString(str, (int) x, (int) y);
    }

    public void textSize(int textSize) {
        this.textSize = textSize;
        g2d.setFont(new Font(textFont, textStyle, textSize));
    }

    public void textFont(String textFont) {
        this.textFont = textFont;
        g2d.setFont(new Font(textFont, textStyle, textSize));
    }

    public void textStyle(final int textStyle) {
        this.textStyle = textStyle;
        g2d.setFont(new Font(textFont, textStyle, textSize));
    }
}
