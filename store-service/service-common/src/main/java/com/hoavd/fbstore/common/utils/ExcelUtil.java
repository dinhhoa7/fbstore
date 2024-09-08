package com.hoavd.fbstore.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.List;

public abstract class ExcelUtil<E> {
  protected Workbook workbook;
  protected static final Integer START_DATA_ROW = 1;
  private int startDataRow = START_DATA_ROW;
  private final String FONT_NAME = "Times New Roman";

  public int getStartDataRow() {
    return startDataRow;
  }

  public void setStartDataRow(int startDataRow) {
    this.startDataRow = startDataRow;
  }

  protected CellStyle getHeaderStyle() {
    CellStyle style = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setColor(IndexedColors.WHITE.index);
    headerFont.setBold(true);

    style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
    style.setFont(headerFont);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderTop(BorderStyle.THIN);
    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderLeft(BorderStyle.THIN);
    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderRight(BorderStyle.THIN);
    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderBottom(BorderStyle.THIN);
    style.setVerticalAlignment(VerticalAlignment.CENTER);

    return style;
  }

  protected CellStyle getNormalStyle() {
    CellStyle style = workbook.createCellStyle();

    Font fontBody = workbook.createFont();
    fontBody.setFontName(FONT_NAME);

    style.setFont(fontBody);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderLeft(BorderStyle.THIN);
    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderRight(BorderStyle.THIN);
    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderTop(BorderStyle.THIN);
    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    style.setAlignment(HorizontalAlignment.LEFT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setWrapText(true);

    return style;
  }

  protected CellStyle getCellNumberStyle() {
    CellStyle style = workbook.createCellStyle();

    Font fontBody = workbook.createFont();
    fontBody.setFontName(FONT_NAME);

    style.setBorderBottom(BorderStyle.THIN);
    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderLeft(BorderStyle.THIN);
    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderRight(BorderStyle.THIN);
    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
    style.setBorderTop(BorderStyle.THIN);
    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    style.setAlignment(HorizontalAlignment.RIGHT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);

    return style;
  }

  public Sheet createSheet(String sheetName, String[] header, List<E> data) throws ParseException {
    if (workbook == null)
      workbook = new SXSSFWorkbook();

    Sheet newSheet = workbook.createSheet(sheetName);

    fillHeader(newSheet, header);
    fillData(newSheet, data);

    return newSheet;
  }

  public Sheet createSheet(String sheetName, String[] header, List<E> data, Object object) {
    if (workbook == null)
      workbook = new SXSSFWorkbook();

    Sheet newSheet = workbook.createSheet(sheetName);

    fillHeader(newSheet, header);
    fillData(newSheet, data, object);

    return newSheet;
  }

  public Sheet createSheet(String sheetName, String[] header, Object object) {
    if (workbook == null)
      workbook = new SXSSFWorkbook();

    Sheet newSheet = workbook.createSheet(sheetName);

    fillHeader(newSheet, header);
    fillData(newSheet, object);

    return newSheet;
  }

  public final byte[] export() throws Exception {
    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    workbook.write(outByteStream);

    return outByteStream.toByteArray();
  }

  private void fillHeader(Sheet sheet, String[] columns) {
    CellStyle headerStyle = getHeaderStyle();
    for (int rownum = 0; rownum < 1; rownum++) {
      Row row = sheet.createRow(rownum);
      for (int cellnum = 0; cellnum < columns.length; cellnum++) {
        Cell cell = row.createCell(cellnum);
        cell.setCellValue(columns[cellnum]);
        cell.setCellStyle(headerStyle);
        sheet.setDefaultRowHeight((short) 500);
      }
    }
  }

  protected abstract void fillData(Sheet sheet, List<E> dataList) throws ParseException;

  protected abstract void fillData(Sheet sheet, List<E> dataList, Object object);

  protected abstract void fillData(Sheet sheet, Object object);
}
