package com.tcs.PHI.PDFcreatorService;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.pdf.PdfWriter;
import com.tcs.PHI.res.ResBean;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;



		
public class PdfMaker {


			/*ResBean response;
			String typeOfReport;
			
			public PdfMaker(ResBean response, String typeOfReport) {
				this.response= response;
				this.typeOfReport = typeOfReport;
			}
		
			public void exportToPDF() {
				
				 try {

		              OutputStream file = new FileOutputStream(new File("Sample.pdf"));
			          Document document = new Document();
			          PdfWriter.getInstance(document, file);
			          
//			          response = new ArrayList<HashMap<String,String>>();
//			          for(int i=0;i<3;i++) {
//			        	  
//			        	  	csvMap = new HashMap<String, String>();
//			        	  	csvMap.put("Tgl_Transaksi", "2017-09-19");
//			      		csvMap.put("No_Check", "2");
//			      		csvMap.put("Kode_outlet","16703");
//			      		csvMap.put("Jam_Bayar", "06:00");
//			      		csvMap.put("Tipe_Bayar", "Cash");
//			      		csvMap.put("Nilai_Pembayaran", "12");
//			      		csvMap.put("Nilai_Tip", "---Tidak diatur di iiko---");
//			      		csvMap.put("Nomor Kartu","(нет карты)");
//			      		csvMap.put("Pemilik_Kartu", "No Name");
//			      		csvMap.put("Bayar_Cashnya", "---tidak didukung di iiko---");
//			      		csvMap.put("Hapus_Payment", "False");
//			      		response.add(csvMap);
//			          }
			          
					//Inserting Image in PDF
					     Image image = Image.getInstance ("/Users/subhankarmaitra/Documents/pizzahut.jpg");
					     image.scaleAbsolute(120f, 60f);//image width,height	

					//Inserting Table in PDF
					      int numberOfColumns = this.response.getData().get(0).keySet().size();
					      PdfPTable table=new PdfPTable(numberOfColumns); 

			                   PdfPCell cell = new PdfPCell (new Paragraph (typeOfReport));

						      cell.setColspan (numberOfColumns);
						      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
						      cell.setPadding (10.0f);
						      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

						      table.addCell(cell);						               

//						      table.addCell("Name");
//						      table.addCell("Address");
//						      table.addCell("Country");
						      
						      List<String> headers = new ArrayList<String>(this.response.getData().get(0).keySet()); 
//						    		  Arrays.asList("Tgl_Transaksi","No_Check","Kode_outlet","Jam_Bayar",
//						    		  "Tipe_Bayar","Nilai_Pembayaran","Nilai_Tip","Nomor Kartu","Pemilik_Kartu","Bayar_Cashnya",
//						    		  "Hapus_Payment");
						      
						      //Generating table headers
						      headers.stream().forEach(i->{
						    	  	table.addCell(i);
						      });
						      
						     
						    	  	//Generating table contents
							      this.response.getData().stream().forEach(i->{	//Accessing each List item(Map)
							    	  	for(Map.Entry<String, String> entry : i.entrySet()) { //Accessing each Map element
							    	  		PdfPCell cell1 = new PdfPCell(new Paragraph(entry.getValue()));
							    	  		cell1.setPaddingTop(10.0f);cell1.setPaddingBottom(10.0f); 
							    	  		//cell1.setBorderWidth(5.0f); 
							    	  		table.addCell(cell1);
							    	  	}
							      });
						     
						      
//		                      table.addCell("Java4s");
//						      table.addCell("NC");
//						      table.addCell("United States");
						      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
						      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          

					 //Inserting List in PDF
//						      List list=new List(true,30);
//					          list.add(new ListItem("Java4s"));
//						      list.add(new ListItem("Php4s"));
//						      list.add(new ListItem("Some Thing..."));		

					 //Text formating in PDF
//			                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
//							chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
//							Chunk chunk1=new Chunk("Php4s.com");
//							chunk1.setUnderline(+4f,-8f);
//							chunk1.setBackground(new BaseColor (17, 46, 193));    

					 //Now Insert Every Thing Into PDF Document
				         document.open();//PDF document opened........			       

							document.add(image);

							document.add(Chunk.NEWLINE);   //Something like in HTML :-)

		                    document.add(new Paragraph(typeOfReport));
			                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

							document.add(table);

//							document.add(chunk);
//							document.add(chunk1);

							document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    

		       				//document.newPage();            //Opened new page
							//document.add(list);            //In the new page we are going to add list

				         document.close();

					     file.close();

		            System.out.println("Pdf created successfully..");

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}

//			public static void main(String[] args) {
//				new PdfGen().exportToPDF();
//			}
*/



}
