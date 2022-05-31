package group2.monopoly.frontend;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CreatePropertyDisplay {

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
