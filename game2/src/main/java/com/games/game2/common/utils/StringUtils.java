package com.games.game2.common.utils;

import lombok.extern.java.Log;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

@Log
public class StringUtils {
    public static String phoneFormat(String pPhone) {
        String result = "";

        pPhone = pPhone.replaceAll("[(-)]","");

        if(!pPhone.trim().isEmpty()) {
            if (pPhone.length() == 8) {
                result = pPhone.replaceFirst("(\\d{4})(\\d+)", "$1-$2");
            } else if (pPhone.substring(0, 2).equals("02")) {
                if (pPhone.length() == 9) {
                    result = pPhone.replaceFirst("(\\d{2})(\\d{3})(\\d+)", "$1-$2-$3");
                } else {
                    result = pPhone.replaceFirst("(\\d{2})(\\d{4})(\\d+)", "$1-$2-$3");
                }
            } else if (pPhone.length() == 10) {
                result = pPhone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
            } else {
                result = pPhone.replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1-$2-$3");
            }
        }

       return result;
    }

    public static String phoneMask(String pPhone) {
        String result = "";

        pPhone = pPhone.replaceAll("[(-)]","");

        if(!pPhone.trim().isEmpty()) {
            if (pPhone.length() == 8) {
                result = pPhone.replaceFirst("(\\d{4})(\\d+)", "$1-****");
            } else if (pPhone.substring(0, 2).equals("02")) {
                if (pPhone.length() == 9) {
                    result = pPhone.replaceFirst("(\\d{2})(\\d{3})(\\d+)", "$1-***-$3");
                } else {
                    result = pPhone.replaceFirst("(\\d{2})(\\d{4})(\\d+)", "$1-****-$3");
                }
            } else if (pPhone.length() == 10) {
                result = pPhone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-***-$3");
            } else {
                result = pPhone.replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1-****-$3");
            }
        }

        return result;
    }


    public static String nameMask(String pName) {
        String result = "";


        if(!pName.trim().isEmpty()) {
            if (pName.length() == 2) {
                result = pName.charAt(0)+"*";
            } else {
                result = pName.charAt(0)+"*"+pName.substring(2);
            }
        }

        return result;
    }

    public static String getCoupon() {
        final char[] possibleCharacters =
                {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
                        'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
                        'W','X','Y','Z'};

        final int possibleCharacterCount = possibleCharacters.length;
        Random rnd = new Random();
        StringBuilder buf = new StringBuilder(16);
        //i는 8자리의 랜덤값을 의미
        for (int i= 8; i > 0; i--) {
            buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
        }

        return buf.toString().trim().toUpperCase(Locale.ROOT);
    }

    // 공백만 입력된 경우 검사기
    public boolean isOnlySpace(String str){
        if(str == null) str = "";
        return str.replaceAll(" ", "").equals("");
    }

    //길이만 검사
    public boolean isCharacterLimit(String str, int minLength, int maxLength){
        if(str == null) str = "";
        return !(str.length() >= minLength && str.length() <= maxLength);
    }

    //부계정을 위한 아이디 전용 특수문자 검사기(느낌표(!)외 특수문자가 있는 경우 true, 공백은 통과)
    public boolean isSpecialForDrId(String str, int minLength, int maxLength){
        if(str == null) str = "";
        String boo = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|!|]" + "{" + minLength + "," +  maxLength + "}" + "$";
        return !Pattern.matches(boo, str);
    }

    //특수문자 검사기(특수문자가 있는 경우 true, 공백은 통과)
    public boolean isSpecial(String str, int minLength, int maxLength){
        if(str == null) str = "";
        String boo = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|]" + "{" + minLength + "," +  maxLength + "}" + "$";
        return !Pattern.matches(boo, str);
    }

    // 숫자가 있다면 true
    public boolean isNumeric(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[0-9]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }
    
    // 숫자 이외가 있다면 true
    public boolean isNotNumeric(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[0-9]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return !Pattern.matches(boo, str);
    }

    // 영어 검사기
    public boolean isAlpha(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[a-zA-Z]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }

    // 한국어 혹은 영어 검사기
    public boolean isAlphaNumeric(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[a-zA-Z0-9]" + "{" + minLength + "," + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }

    // 한국어 검사기
    public boolean isKorean(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[가-힣]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }

    // 대문자 검사기
    public boolean isUpper(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[A-Z]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }

    // 소문자 검사기
    public boolean isDowner(String str, int minLength, int maxLength) {
        if(str == null) str = "";
        String boo = "^[a-z]" + "{" + minLength + ","  + maxLength + "}" + "$";
        return Pattern.matches(boo, str);
    }

    // 이메일 검사기
    public boolean isEmail(String str) {
        if(str == null) str = "";
        return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", str);
    }

    // URL 검사기 (Http, Https 제외)
    public boolean isURL(String str) {
        if(str == null) str = "";
        return Pattern.matches("^[^((http(s?))\\:\\/\\/)]([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$",
                str);
    }

    // phone number checker
    // xxx-xxx-xxxx (형식만 비교)
    // - 은 없어야 함.
    public boolean isMob(String str) {
        if(str == null) str = "";
        return Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", str);
    }

    // 일반 전화 검사기
    public boolean isPhone(String str) {
        if(str == null) str = "";
        return Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$", str);
    }

    // IP 검사기
    public boolean isIp(String str) {
        if(str == null) str = "";
        return Pattern.matches("([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})", str);
    }

    // 우편번호 검사기
    public boolean isPost(String str) {
        if(str == null) str = "";
        return Pattern.matches("^\\d{3}\\d{2}$", str);
    }

    // 주민등록번호 검사기
    public boolean isPersonalID(String str) {
        if(str == null) str = "";
        return Pattern.matches("^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$", str);
    }
}
