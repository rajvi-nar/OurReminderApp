import com.google.api.client.util.DateTime;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SheetsQuickstart {
  public static final String SPREADSHEET_ID = "15gZhgEnynebN8_IMrCiXL2QFIMPq2OnC8Ni0eVuy0Po";
  public static final String CURRENT_RANGE_LOCATION = "C2:C2";

  public static void main(String[] args) throws IOException {
    Sheets service = SheetsHelper.getSheetsService();
    SheetsHelper sheetsHelper = new SheetsHelper(service, SPREADSHEET_ID);

    List<List<Object>> values = sheetsHelper.getValues(CURRENT_RANGE_LOCATION);
    String writeRange = values.get(0).get(0).toString();

    Request appendRequest = new Request();
    appendRequest.set("ValueInputOption", "USER_ENTERED");

    List<List<Object>> data = new LinkedList<>();
    LinkedList<Object> row1 = new LinkedList<>();
    row1.add("Reply Randy");
    row1.add(new DateTime("2016-08-22T11:59:00.00Z"));
    data.add(row1);

    sheetsHelper.writeToSheet(writeRange, data);
    sheetsHelper.updateWriteRange(writeRange);
  }
}
