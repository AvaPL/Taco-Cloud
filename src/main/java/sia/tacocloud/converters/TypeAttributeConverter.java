package sia.tacocloud.converters;

import sia.tacocloud.Ingredient;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypeAttributeConverter implements AttributeConverter<Ingredient.Type, String> {

    @Override
    public String convertToDatabaseColumn(Ingredient.Type type) {
        return type.toString();
    }

    @Override
    public Ingredient.Type convertToEntityAttribute(String name) {
        return Ingredient.Type.valueOf(name);
    }

}
