package net.sepidan.common.util;

import org.springframework.util.StringUtils;

public class LatinToPersianConverter {

  public static String convertLatinToPersian(String latinNumber) {
    if (!StringUtils.hasText(latinNumber)) {
      return null;
    }
    char[] persianDigits = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
    StringBuilder persianNumber = new StringBuilder();

    for (int i = 0; i < latinNumber.length(); i++) {
      char latinChar = latinNumber.charAt(i);

      if (Character.isDigit(latinChar)) {
        int digit = latinChar - '0';
        persianNumber.append(persianDigits[digit]);
      } else {
        persianNumber.append(latinChar);
      }
    }
    return persianNumber.toString();
  }

}