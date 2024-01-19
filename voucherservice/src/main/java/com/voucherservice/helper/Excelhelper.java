package com.voucherservice.helper;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.voucherservice.entity.Voucher;



public class Excelhelper {
	
	public static boolean checkExcelFormat(MultipartFile file)
	{
		
		String contentType = file.getContentType();
		
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
	}
	
	public static Date convertToDateFromString(String date) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d =new Date();
		
	    d = dateFormat.parse(date);
		 return d;
	}
	
	public static List<Voucher> convertExcelToListOfVoucher(InputStream is)
	{
		List<Voucher> list = new ArrayList<Voucher>();
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet =workbook.getSheet("data");
			int rowNumber=0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext())
			{
				Row row = iterator.next();
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				Iterator<Cell> cell = row.iterator();
				int cid =0;
				Voucher voucher = new Voucher();
				while(cell.hasNext())
				{
					Cell c = cell.next();
					
					switch(cid)
					{
					
					case 0 :
						voucher.setExamName(c.getStringCellValue()); 
						break;
					case 1:
						voucher.setCloudPlatform(c.getStringCellValue().trim());
						break;
					case 2:
						voucher.setVoucherCode(c.getStringCellValue().trim());
						break;
					case 3:{
						Date d =c.getDateCellValue();
						// Convert Date to Instant
				        Instant instant = d.toInstant();

				        // Convert Instant to ZonedDateTime to get LocalDate
				        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
				        LocalDate localDate = zonedDateTime.toLocalDate();
						voucher.setIssuedDate(localDate);
						break;
					}
					case 4:{
						Date  d =c.getDateCellValue();
						// Convert Date to Instant
				        Instant instant = d.toInstant();

				        // Convert Instant to ZonedDateTime to get LocalDate
				        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
				        LocalDate localDate = zonedDateTime.toLocalDate();
						voucher.setExpiryDate(localDate);
						break;
					}
					
					default:
						break;
					
					}
					cid++;
				}
				System.out.println(voucher);
				list.add(voucher);
			}
			
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}


}
