package group2.monopoly.frontend;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
  * This class has a static method that translates the owned purchasable indices to the actual screen rectangle coordinates, and returns the texts.
 */
public class CreatePropertyDisplay {

    /**
      * This method returns the texts of the owned purchasable's texts
      * @param owned_purchasables The indices of the owned purchasables
      * @param propertyIndices The indices of the properties
      * @param portIndices The indices of the ports
      * @param propertyNames The names of the properties
      * @return List<String> The texts of the owned purchasables
      */
    public static List<String> findNames (JSONArray ownedPurchasables, JSONArray propertyIndices, JSONArray portIndices, List<String> propertyNames) {
        List<String> result = new ArrayList<String>();
        List<Object> ownedPurchasablesList = ownedPurchasables.toList();
        List<Object> propertyIndicesList = propertyIndices.toList();
        List<Object> portIndicesList = portIndices.toList();


        for(int i = 0; i < ownedPurchasables.length(); i++) {
            int propertyIndexInIndicesList = propertyIndicesList.indexOf(ownedPurchasablesList.get(i));
            if (propertyIndexInIndicesList == -1) {
                result.add("some port\n");
            }
            else{
                result.add(propertyNames.get(propertyIndexInIndicesList) + "\n");
            }
        }

        return result;
    }
}
