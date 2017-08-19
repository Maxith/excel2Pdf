import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;

public class ExcelUtils {


    /**
     * 获取cell的string字符串
     *
     * @param cell
     * @return
     */
    public static String getStringValue(Cell cell) {
        Object value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    return null;
                case Cell.CELL_TYPE_NUMERIC:
                    Double doubleValue = cell.getNumericCellValue();
                    if (doubleValue == null) {
                        doubleValue = 0.0;
                    }
                    value = new BigDecimal(doubleValue).toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    try {
                        value = cell.getStringCellValue();
                    } catch (Exception e) {
                        value = String.valueOf(cell.getNumericCellValue());
                    }
                    if (value.toString() == null || value.toString().length() == 0) {
                        value = cell.getRichStringCellValue().getString();
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
            }
            return String.valueOf(value);
        }
        return null;
    }
}
