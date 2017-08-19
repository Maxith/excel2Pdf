import com.itextpdf.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintingPDF {
    /**
     * 打印
     * @param inputStream
     * @throws IOException
     * @throws DocumentException
     * @throws PrinterException
     */
    public static void print(InputStream inputStream) throws IOException, DocumentException, PrinterException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        List<ExcelObject> objects = new ArrayList<>();
        objects.add(new ExcelObject("",inputStream));
        Excel2Pdf pdf = new Excel2Pdf(objects , os);
        pdf.convert();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(os.toByteArray());
        PDDocument document = PDDocument.load(byteArrayInputStream);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.print();
    }
}
