package net.sepidan.common.util;


public class PersianDigitUtils {

  public static String convertToEnglish(String persianDigit) {
    StringBuilder result = new StringBuilder();
    for (char c : persianDigit.toCharArray()) {
      if (Character.isDigit(c)) {
        result.append(Character.getNumericValue(c));
      }
    }
    return result.toString();
  }
}
