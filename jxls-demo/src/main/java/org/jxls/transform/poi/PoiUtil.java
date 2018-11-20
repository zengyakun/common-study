package org.jxls.transform.poi;

import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.apache.poi.ss.usermodel.*;

/**
 * POI utility methods
 * @author Leonid Vysochyn
 */
public class PoiUtil {
    @SuppressWarnings("rawtypes")
	public static void setCellComment(Cell cell, String commentText, String commentAuthor, ClientAnchor anchor){
        Sheet sheet = cell.getSheet();
        Workbook wb = sheet.getWorkbook();
        Drawing drawing = sheet.createDrawingPatriarch();
        CreationHelper factory = wb.getCreationHelper();
        if( anchor == null ){
            anchor = factory.createClientAnchor();
            anchor.setCol1(cell.getColumnIndex() + 1);
            anchor.setCol2(cell.getColumnIndex() + 3);
            anchor.setRow1(cell.getRowIndex());
            anchor.setRow2(cell.getRowIndex() + 2);
        }
        Comment comment = drawing.createCellComment(anchor);
        comment.setString(factory.createRichTextString(commentText));
        comment.setAuthor(commentAuthor != null ? commentAuthor : "");
        cell.setCellComment( comment );
    }

    public WritableCellValue hyperlink(String address, String link, String linkTypeString){
        return new WritableHyperlink(address, link, linkTypeString);
    }

    public WritableCellValue hyperlink(String address, String title){
        return new WritableHyperlink(address, title);
    }

    public static void copySheetProperties(Sheet src, Sheet dest){
        dest.setAutobreaks(src.getAutobreaks());
        dest.setDisplayGridlines(src.isDisplayGridlines());
        dest.setVerticallyCenter(src.getVerticallyCenter());
        dest.setFitToPage(src.getFitToPage());
        dest.setForceFormulaRecalculation(src.getForceFormulaRecalculation());
        dest.setRowSumsRight(src.getRowSumsRight());
        dest.setRowSumsBelow( src.getRowSumsBelow() );
        //增加页边距保存 2017-6-21
        dest.setMargin(Sheet.TopMargin, src.getMargin(Sheet.TopMargin));
        dest.setMargin(Sheet.LeftMargin, src.getMargin(Sheet.LeftMargin));
        dest.setMargin(Sheet.RightMargin, src.getMargin(Sheet.RightMargin));
        dest.setMargin(Sheet.BottomMargin, src.getMargin(Sheet.BottomMargin));
        /*
        dest.setMargin(Sheet.TopMargin, 0.1);
        dest.setMargin(Sheet.LeftMargin, 0.1);
        dest.setMargin(Sheet.RightMargin, 0.1);
        dest.setMargin(Sheet.BottomMargin, 0.1);
        */
        copyPrintSetup(src, dest);
    }

    private static void copyPrintSetup(Sheet src, Sheet dest) {
        PrintSetup srcPrintSetup = src.getPrintSetup();
        PrintSetup destPrintSetup = dest.getPrintSetup();
        destPrintSetup.setCopies(srcPrintSetup.getCopies());
        destPrintSetup.setDraft(srcPrintSetup.getDraft());
        destPrintSetup.setFitHeight(srcPrintSetup.getFitHeight());
        destPrintSetup.setFitWidth(srcPrintSetup.getFitWidth());
        destPrintSetup.setFooterMargin(srcPrintSetup.getFooterMargin());
        destPrintSetup.setHeaderMargin(srcPrintSetup.getHeaderMargin());
        destPrintSetup.setHResolution(srcPrintSetup.getHResolution());
        destPrintSetup.setLandscape(srcPrintSetup.getLandscape());
        destPrintSetup.setLeftToRight(srcPrintSetup.getLeftToRight());
        destPrintSetup.setNoColor(srcPrintSetup.getNoColor());
        destPrintSetup.setNoOrientation(srcPrintSetup.getNoOrientation());
        destPrintSetup.setNotes(srcPrintSetup.getNotes());
        destPrintSetup.setPageStart(srcPrintSetup.getPageStart());
        destPrintSetup.setPaperSize(srcPrintSetup.getPaperSize());
        destPrintSetup.setScale(srcPrintSetup.getScale());
        destPrintSetup.setUsePage(srcPrintSetup.getUsePage());
        destPrintSetup.setValidSettings(srcPrintSetup.getValidSettings());
        destPrintSetup.setVResolution( srcPrintSetup.getVResolution() );
    }

    public static boolean isJxComment(String cellComment) {
        if(cellComment == null ) return false;
        String[] commentLines = cellComment.split("\\n");
        for (String commentLine : commentLines) {
            if( (commentLine != null) && XlsCommentAreaBuilder.isCommandString( commentLine.trim() ) ){
                return true;
            }
        }
        return false;
    }
}
