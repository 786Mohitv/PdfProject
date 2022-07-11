package com.itextPdf.CreatePdfProgrammatically;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.PdfPrinterGraphics2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Enclose the code in try-catch block as it throws FileNotFoundException //and
		// DocumentException
		try {
			Document document = new Document();

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("f:\\Sample.pdf"));

			document.open();

			// get instance of the image using get.Instance method in Image class
			Image image1 = Image.getInstance("cropped-Allps-logo-2-1-1.png");

			Image image2 = Image.getInstance("test.png");

			// pass the height and width of the image to scaleToFit
			image1.scaleToFit(150, 150);

			// setting the alignment of the image
			image1.setAlignment(Element.ALIGN_CENTER);

			// passing the reference of image to add method to add in document
			document.add(image1);

			// to add a paragraph we need to pass the a paragraph object which
			// Takes string as argument.
			document.add(new Paragraph(" "));

			// To add fonts we have FontFactory class and which has a method
			// getFont(String fontname, float size, int style, BaseColor color)
			document.add(new Paragraph("Scale your business with ALLPS IT Teams",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.RED)));

			document.add(new Paragraph(
					"Hire high quality, cost-effective and dedicated remote IT teams through our extensive developer network. We are a Swiss company with Tech hub in India."));

			document.add(new Paragraph(" "));

			// To create a list we need to make a list object which takes Boolean for
			// numbered as first argument and up to which number as second.
			List list = new List(true, 10);

			// adding list item one by one
			list.add("IT Developers");

			list.add("Data Scientists");

			list.add("IT Architects");

			list.add("Project Managers");

			list.add("Automation Engineers");

			list.add("Niche skill Developers");

			// At last add the list to the document.
			document.add(list);

			document.add(new Paragraph(" "));

			// Create a table object and pass number of columns to constructor
			PdfPTable table = new PdfPTable(3);

			// create a pdf cell for the title of the table
			PdfPCell cell = new PdfPCell(new Paragraph("Milestones"));

			// defining the columns span for the cell
			cell.setColspan(4);

			// setting horizontal alignment of the cell
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			// setting the background colour for the title cell
			cell.setBackgroundColor(BaseColor.GREEN);

			// add the cell to the table
			table.addCell(cell);

			table.addCell("Clients");

			table.addCell("Registered developers");

			table.addCell("Allps teams");

			table.addCell("16");

			table.addCell("40");

			table.addCell("7524");

			// adding table to the document
			document.add(table);

			document.add(new Paragraph(" "));

			image2.scaleToFit(150, 150);

			image2.setAlignment(Element.ALIGN_CENTER);

			document.add(image2);

			

			// We will define the data for the Pie Chart Using the Code below
			// Declare dataset object using the code below
			DefaultPieDataset myPiedataset = new DefaultPieDataset();
			// Define Values for the Pie Chart - Programming Languages
			// Percentage Difficulty
			myPiedataset.setValue("Java", 12.9);
			myPiedataset.setValue("C++", 37.9);
			myPiedataset.setValue("C", 86.5);
			myPiedataset.setValue("VB", 80.5);
			myPiedataset.setValue("Shell Script", 19.5);

			// With the dataset defined for Pie Chart, we can invoke a method in
			// //ChartFactory object to create Pie Chart and Return a JFreeChart //object
			// This method returns a JFreeChart object back to us
			// We specify the chart title, dataset, legend, tooltip and URLs in
			// this method as input
			JFreeChart PDFPieChart = ChartFactory.createPieChart("Programming - Pie Chart Example", myPiedataset, true,
					true, false);

			// We have a Pie chart object, and now need to find a procedure to insert it
			// into PDF using iText
			int width = 640;
			// Width of our chart

			int height = 480;
			// Height of our chart

			
			document.addKeywords("iText,PieChart,JFreeChart,PDF,Example Tutorial");

			// Get Direct Content of the PDF document for writing
			PdfContentByte Add_Chart_Content = writer.getDirectContent();

			// Create a template using the PdfContent Byte object
			PdfTemplate template_Chart_Holder = Add_Chart_Content.createTemplate(width, height);

			// Create a 2D graphics object and Rectangle object as before to
			// write on the template
			PdfPrinterGraphics2D graphics2d = new PdfPrinterGraphics2D(template_Chart_Holder, width, height, null);
			Graphics2D Graphics_Chart = graphics2d;
			Rectangle2D Chart_Region = new Rectangle(30, 80, 540, 380);

			// Invoke the draw method passing the Graphics and Rectangle 2D object to //draw
			// the piechart
			PDFPieChart.draw(Graphics_Chart, Chart_Region);
			Graphics_Chart.dispose();

			// Add template to PdfContentByte and then to the PDF document
			Add_Chart_Content.addTemplate(template_Chart_Holder, 0, 0);
			
			document.close();

			System.out.println("created");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}

	}

}
