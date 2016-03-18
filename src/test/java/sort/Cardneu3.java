/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import org.templat.Templates;

public class Cardneu3 {

    FontBuilder builder = stl.font().setFontName("Arial");
    StyleBuilder fo = stl.style(builder);
    StyleBuilder fett = stl.style(fo).bold();
    StyleBuilder fett2 = stl.style().bold();
    StyleBuilder fettundgroß = stl.style(fett2).setFontSize(22);
    StyleBuilder fettundnichtganzsogroß = stl.style(fett2).setFontSize(18);
    @SuppressWarnings("deprecation")
    StyleBuilder gelb = stl.style().setBackgroundColor(Color.ORANGE.darker());
    StyleBuilder gruen = stl.style().setBackgroundColor(Color.GREEN);
    StyleBuilder blau = stl.style().setBackgroundColor(Color.BLUE);
    StyleBuilder hellblau = stl.style().setBackgroundColor(Color.CYAN);
    StyleBuilder rahmen = stl.style().setBorder(stl.penDotted());
    //StyleBuilder rahmen3 = stl.style().setBorder(stl.penThin());
    StyleBuilder rahmen3 = stl.style().setFontName("Arial");
    StyleBuilder rahmen2 = stl.style(rahmen3).setForegroundColor(Color.BLACK);
    StyleBuilder rahmen4 = stl.style(rahmen2).setLeftPadding(10);
    StyleBuilder rahmen5 = stl.style(rahmen4).setRightPadding(5);
    StyleBuilder rahmen6 = stl.style(rahmen5).setBottomPadding(5);
    StyleBuilder rahmen7 = stl.style(rahmen6).setTopPadding(5);
    StyleBuilder gelb2 = stl.style(rahmen7).setForegroundColor(Color.ORANGE.darker());
    StyleBuilder gruen2 = stl.style(rahmen7).setForegroundColor(Color.GREEN);
    StyleBuilder blau2 = stl.style(rahmen7).setForegroundColor(Color.BLUE);
    StyleBuilder hellblau2 = stl.style(rahmen7).setForegroundColor(Color.CYAN);
    StyleBuilder gelb3 = stl.style(gelb2).bold();
    StyleBuilder gruen3 = stl.style(gruen2).bold();
    StyleBuilder blau3 = stl.style(blau2).bold();
    StyleBuilder hellblau3 = stl.style(hellblau2).bold();
    StyleBuilder fettundgroß2 = stl.style(fett2).setFontName("Arial");
    StyleBuilder fettundgroß3 = stl.style(fettundgroß2).setFontSize(30);
    StyleBuilder groß18 = stl.style(rahmen3).setFontSize(18);
    StyleBuilder groß17 = stl.style(rahmen3).setFontSize(17);
    StyleBuilder groß16 = stl.style(rahmen3).setFontSize(16);
    StyleBuilder groß15 = stl.style(rahmen3).setFontSize(15);
    StyleBuilder groß14 = stl.style(rahmen3).setFontSize(14);
    StyleBuilder groß13 = stl.style(rahmen3).setFontSize(13);
    StyleBuilder groß19 = stl.style(rahmen3).setFontSize(19);
    StyleBuilder groß20 = stl.style(rahmen3).setFontSize(20);
    StyleBuilder groß21 = stl.style(rahmen3).setFontSize(21);
    StyleBuilder groß22 = stl.style(rahmen3).setFontSize(22);
    StyleBuilder groß12 = stl.style(rahmen3).setFontSize(12);
    StyleBuilder groß11 = stl.style(rahmen3).setFontSize(11);
    StyleBuilder groß10 = stl.style(rahmen3).setFontSize(10);
    StyleBuilder groß17undfett = stl.style(groß17).bold();
    StyleBuilder groß16undrahmen = stl.style(rahmen3).setFontSize(16).setBorder(stl.pen1Point());
    StyleBuilder kreis1 = stl.style().setRadius(20);
    StyleBuilder kreisblau = stl.style().setBackgroundColor(Color.BLUE);
    StyleBuilder kreishellblau = stl.style().setBackgroundColor(Color.CYAN.darker());
    StyleBuilder kreisgruen = stl.style().setBackgroundColor(Color.GREEN.darker());
    StyleBuilder kreisgelb = stl.style().setBackgroundColor(Color.ORANGE);
    StyleBuilder kreisblau1 = stl.style(kreisblau).setBorder(stl.penThin());
    StyleBuilder kreishellblau1 = stl.style(kreishellblau).setBorder(stl.penThin());
    StyleBuilder kreisgruen1 = stl.style(kreisgruen).setBorder(stl.penThin());
    StyleBuilder kreisgelb1 = stl.style(kreisgelb).setBorder(stl.penThin());
    StyleBuilder umrundungkreis = stl.style().setBorder(stl.pen1Point()).setPadding(5);
//   StyleBuilder kreisblau3 = stl.style(kreisblau1).setBorder(stl.penThin());
//   StyleBuilder kreishellblau3 = stl.style(kreishellblau1).setBorder(stl.penThin());
//   StyleBuilder kreisgruen3 = stl.style(kreisgruen1).setBorder(stl.penThin());
//   StyleBuilder kreisgelb3 = stl.style(kreisgelb1).setBorder(stl.penThin());
    StyleBuilder neuneu = stl.style().setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5);
    StyleBuilder neuneuneu = stl.style().setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5);

    //StyleBuilder rahmen = stl.style().setBorder(stl.penDotted());
    //StyleBuilder rahmen2 = stl.style().setBorder(stl.penThin());
    //StyleBuilder gelbundkasten = stl.style(rahmen2).setForegroundColor(Color.YELLOW);
    public Cardneu3() {
        build();
    }

    private void build() {
        ComponentBuilder<?, ?> cardComponent = createCardComponent();
        HorizontalListBuilder cards = cmp.horizontalList();
        for (int i = 0; i < 1; i++) {
            cards.add(cardComponent);
        }

        try {
            report()
                    .setTemplate(Templates.reportTemplate)
                    .setTextStyle(stl.style())
                    .setPageFormat(PageType.A4)
                    .title(
                            cards)
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private ComponentBuilder<?, ?> createCardComponent() {
        BufferedImage img4 = null;
        BufferedImage img5 = null;
        BufferedImage img6 = null;
        BufferedImage img7 = null;
        try {
            img4 = ImageIO.read(new File("C:/Users/BockMar01/Documents/ITE-neu/Bild1.PNG"));
            img5 = ImageIO.read(new File("C:/Users/BockMar01/Documents/ITE-neu/Bild2.PNG"));
            img6 = ImageIO.read(new File("C:/Users/BockMar01/Documents/ITE-neu/Bild3.PNG"));
            img7 = ImageIO.read(new File("C:/Users/BockMar01/Documents/ITE-neu/Bild4.PNG"));
        } catch (Exception e) {
            //do nothing ;)
            e.printStackTrace();
        }

        StyleBuilder cardStyle = stl.style(stl.pen1Point())
                .setPadding(10);
        StyleBuilder rahmen = stl.style().setBorder(stl.penDotted());
        StyleBuilder rahmen2 = stl.style().setBorder(stl.penThin());

        //ImageBuilder image = cmp.image(img4).setFixedDimension(60, 60);
        //cardComponent.add(cmp.hListCell(image).heightFixedOnMiddle());
        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder centerStyle = stl.style().setBottomBorder(stl.penDashed()).setVerticalAlignment(VerticalAlignment.MIDDLE);
        //eigentlicher Inhalt
        VerticalListBuilder content = cmp.verticalList();
        HorizontalListBuilder fusszeile = cmp.horizontalList();
        HorizontalListBuilder fusszeile2 = cmp.horizontalList();
        HorizontalListBuilder fusszeile12 = cmp.horizontalList();
        HorizontalListBuilder fusszeile16 = cmp.horizontalList();
        HorizontalListBuilder rechtespalte = cmp.horizontalList();
        VerticalListBuilder test = cmp.verticalList().setStyle(umrundungkreis);
        test.setGap(27);
        HorizontalListBuilder schere = cmp.horizontalList();
        //schere.setStyle(cardStyle2);

        content.add(cmp.text("some Text"));      //.setStyle(fettundgroß3));
        content.add(cmp.filler().setHeight(30));
        fusszeile12.add(cmp.text("some Text"));      //.setStyle(groß18));
        fusszeile16.add(cmp.text("some Text"));      //.setStyle(groß17undfett));
        fusszeile.add(cmp.text("some Text"));      //.setStyle(groß17undfett));
        fusszeile2.add(cmp.text("some Text"));      //.setStyle(groß17undfett));
        content.add(fusszeile12);
        content.add(cmp.filler().setHeight(10));
        content.add(fusszeile16);
        content.add(fusszeile);
        content.add(fusszeile2);
        content.setStyle(cardStyle);
        //content.add(rechtespalte);
        rechtespalte.add(content);
        test.add(cmp.vListCell(cmp.image(img4).setFixedDimension(55, 55))); //.setStyle()));
        test.add(cmp.line().setFixedWidth(55));
        test.add(cmp.vListCell(cmp.image(img5).setFixedDimension(55, 55))); //.setStyle()));
        test.add(cmp.line().setFixedWidth(55));
        test.add(cmp.vListCell(cmp.image(img6).setFixedDimension(55, 55))); //.setStyle()));
        test.add(cmp.line().setFixedWidth(55));
        test.add(cmp.vListCell(cmp.image(img7).setFixedDimension(55, 55))); //.setStyle()));
        rechtespalte.add(test);
        return rechtespalte;
    }

    public static void main(String[] args) {
        new Cardneu3();
    }
}
