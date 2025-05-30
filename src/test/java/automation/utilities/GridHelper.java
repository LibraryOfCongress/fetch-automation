package automation.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class GridHelper extends GenericHelper{

    private static final Logger oLog = LogManager.getLogger(GridHelper.class);


    public String getHeaderXpath(String tableIdoRxPath){
        oLog.debug(tableIdoRxPath);
        return IsElementPresentQuick(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//thead" : tableIdoRxPath + "//thead";
    }

    public String getTableBodyXpath(String tableIdoRxPath){
        oLog.debug(tableIdoRxPath);
        return IsElementPresentQuick(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//tbody" : tableIdoRxPath + "//tbody";
    }

    public WebElement getGridElement(String tableIdoRxPath, int row, int column){

        WebElement element;

        if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]//a"))) != null){
            oLog.debug(element);
            return element;
        }
        else if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/input"))) != null){
            oLog.debug(element);
            return element;
        }
        else if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/button"))) != null){
            oLog.debug(element);
            return element;
        }
        else if ((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/a"))) != null){
            oLog.debug(element);
            return element;
        }
        else if((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null){
            oLog.debug(element);
            return element;
        }
        return null;
    }

    public int searchInGrid(String description,String tableIdoRxPath,int row,final int column){

        WebElement element;

        while((element = getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null){

            if(!element.getText().isEmpty()){
                oLog.info(element.getText());
                if(element.getText().trim().contains(description))
                    return row;
            }
            row++;
        }

        throw new IllegalArgumentException("No matching description found : " + description);
    }

    public List<String> getGridHeading(String tableIdoRxPath){
        List<String> header = new LinkedList<String>();

        int j = 1;
        WebElement element;

        while((element = (getElementWithNull(By.xpath(getHeaderXpath(tableIdoRxPath) + "//tr[1]//th[" + j + "]")))) != null){
            if(!element.getText().isEmpty()){
                header.add(element.getText().trim());
                oLog.info(element.getText().trim());
            }
            j++;
        }
        return header;
    }

    public String getGridColumnText(String tableIdoRxPath,int row,int column) {
        oLog.info(tableIdoRxPath + "," + row + "," + column);
        WebElement element = getGridElement(tableIdoRxPath,row,column);
        return element == null ? "" : element.getText().trim();
    }

    public void typeInGrid(String tableIdoRxPath,int row,int column,String value) {
        oLog.info(tableIdoRxPath + "," + row + "," + column + "," + value);
        WebElement element = getGridElement(tableIdoRxPath,row,column);
        element.clear();
        element.sendKeys(value);
    }

    public void typeInGrid(String description,String tableIdoRxPath,int row,int column,String value) {
        oLog.info(tableIdoRxPath + "," + row + "," + column + "," + value + "," + description);
        int index = searchInGrid(description,tableIdoRxPath,row,column);
        typeInGrid(tableIdoRxPath,index,3,value);
    }
}