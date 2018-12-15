package pdf_box.example_pocs;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;


/**
 * Adds a textual signature (or just text) to the pdf.
 * @author saniya
 *
 */
public class TextSignature
{
    public static void main( String args[] ) throws FontFormatException
    {
        try {
            //Load the pdf
            File file = new File( "/home/saniya/testing/Signed Form.pdf" );
            PDDocument doc = PDDocument.load( file );

            //Retrieving the page
            PDPage page = doc.getPage( 2 );

            //Select a font type
            PDFont font = PDType1Font.HELVETICA;
            PDPageContentStream contentStream = new PDPageContentStream( doc, page, PDPageContentStream.AppendMode.APPEND,
                true );

            //set font and font-size
            contentStream.setFont( font, 10 );
            contentStream.beginText();

            //start the line from an offset
            contentStream.newLineAtOffset( 130, 252 );
            contentStream.showText( "Signed by:" );
            Encoding encoding = Encoding.getInstance( COSName.WIN_ANSI_ENCODING );

            //Select a custom .ttf font to make the signature text appear different.
            PDFont customFont = PDTrueTypeFont.load( doc, new File( "/home/saniya/testing/AguafinaScript-Regular.ttf" ),
                encoding );

            //set the new custom font
            contentStream.setFont( customFont, 11 );
            contentStream.showText( "Saniya Simeen" );
            contentStream.setFont( font, 8 );

            //for the next line, the offset should be 0 for x-coordinate and +(or -) the font size
            contentStream.newLineAtOffset( 0, -10 );
            Date date = new Date( System.currentTimeMillis() );
            contentStream.showText( "Date:" + date );
            contentStream.endText();
            contentStream.close();
            
            //Save the new signed document
            doc.save( "/home/saniya/testing/Text_Sig.pdf" );
            doc.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
