package pdf_box.example_pocs;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class InsertingImage
{

    public static void main( String args[] ) throws Exception
    {
        //Loading an existing document
        File file = new File( "/home/saniya/testing/Signed Form.pdf" );
        PDDocument doc = PDDocument.load( file );

        //Retrieving the page
        PDPage page = doc.getPage( 2 );

        //Creating PDImageXObject object (give the path of any image you want to upload)
        PDImageXObject pdImage = PDImageXObject.createFromFile( "/home/saniya/testing/Text.png", doc );

        try {
            //create a content stream in append mode to append the image to the pdf.
            PDPageContentStream contentStream = new PDPageContentStream( doc, page, AppendMode.APPEND, false );

            //specify the co-ordinates to draw the image along with the height and width.
            contentStream.drawImage( pdImage, 130, 230, pdImage.getWidth() / 2, pdImage.getHeight() / 2 );
            contentStream.close();

            //save the document 
            doc.save( "/home/saniya/testing/Text_img.pdf" );
            doc.close();
        } catch ( Exception io ) {
            System.out.println( " -- fail --" + io );
        }

    }
}