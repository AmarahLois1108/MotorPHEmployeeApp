package motor_PH;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class PanelPrinter implements Printable {

    private JPanel panel;

    public PanelPrinter(JPanel panel) {
        this.panel = panel;
    }

    public void printPanel() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Print Panel");

        PageFormat pageFormat = printerJob.defaultPage();
        pageFormat.setOrientation(PageFormat.LANDSCAPE);

        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        float margin = 10;
        attributes.add(new MediaPrintableArea(margin, margin, (float) pageFormat.getWidth() - 2 * margin, (float) pageFormat.getHeight() - 2 * margin, MediaPrintableArea.MM));

        printerJob.setPrintable(this, pageFormat);
        printerJob.setCopies(1);

        if (printerJob.printDialog(attributes)) {
            try {
                printerJob.print(attributes);
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        panel.printAll(graphics);

        return PAGE_EXISTS;
    }
}