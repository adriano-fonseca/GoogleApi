package org.company.csv.converter;

import org.jsefa.common.converter.SimpleTypeConverter;

public class DoubleConverter implements SimpleTypeConverter {

  private static final DoubleConverter INSTANCE = new DoubleConverter();

  public static DoubleConverter create() {
    return INSTANCE;
  }

  private DoubleConverter() {
  }

  @Override
  public Object fromString(String s) {
    if (s != null) {
      return new Double(s);
    } else {
      return null;
    }
  }

  @Override
  public String toString(Object d) {
    if (d != null) {
      return d.toString();
    } else {
      return null;
    }
  }

}