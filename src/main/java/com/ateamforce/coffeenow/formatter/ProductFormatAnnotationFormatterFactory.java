package com.ateamforce.coffeenow.formatter;

import com.ateamforce.coffeenow.annotation.ProductFormat;
import com.ateamforce.coffeenow.model.Product;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 *
 * @author Sakel
 */
public final class ProductFormatAnnotationFormatterFactory
        implements AnnotationFormatterFactory<ProductFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        List<Product> productList = new ArrayList<Product>();
        return new HashSet<Class<?>>(asList(new Class<?>[] {
            productList.getClass()
        }));
    }

    @Override
    public Printer<List<Product>> getPrinter(ProductFormat annotation, Class<?> fieldType) {
        return new ProductFormatAnnotationFormatter();
    }

    @Override
    public Parser<List<Product>> getParser(ProductFormat annotation, Class<?> fieldType) {
        return new ProductFormatAnnotationFormatter();
    }

}