package com.room.sharing.service.member.converter;

import com.room.sharing.service.member.constant.GenderType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderTypeConverter implements AttributeConverter<GenderType, String> {

  @Override
  public String convertToDatabaseColumn(GenderType attribute) {
    return attribute.getValue();
  }

  @Override
  public GenderType convertToEntityAttribute(String value) {
    return GenderType.ofValue(value);
  }
}
