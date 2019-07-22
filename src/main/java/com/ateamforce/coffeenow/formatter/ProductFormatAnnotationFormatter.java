package com.ateamforce.coffeenow.formatter;

import com.ateamforce.coffeenow.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.json.JSONObject;

/**
 *
 * @author Sakel
 */
public final class ProductFormatAnnotationFormatter implements Formatter<List<Product>> {
    
    private final static Logger LOGGER = Logger.getLogger(ProductFormatAnnotationFormatter.class);

    @Override
    public String print(List<Product> productsList, Locale locale) {
        if (productsList == null) {
            return "";
        }
        String str = "";
        for( Product e : productsList ){
            str += e.getId().toString() + " - " + e.getTitle() + " - " + e.getDescription() + " - " + e.isHasimage() + " | ";
        }

        return str;
    }

    @Override
    public List<Product> parse(String formatted, Locale locale) throws ParseException {
        if (formatted.length() == 0) {
            return null;
        }
        LOGGER.error(formatted);
        List<Product> listOfProducts = new ArrayList<Product>();
        String[] formattedParts = formatted.split(",");
        
        Arrays.asList(formattedParts).forEach((e) -> {
            JSONObject obj = new JSONObject(e);
            Integer id = obj.getInt("id");
            String title = obj.getString("title");
            String description = obj.getString("description");
            boolean hasimage = obj.getBoolean("hasimage");
            listOfProducts.add(new Product(id, title, description, hasimage));
        });
        
        return listOfProducts;
    }

}