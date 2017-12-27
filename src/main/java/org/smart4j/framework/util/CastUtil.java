package org.smart4j.framework.util;

/**
 * 转型操作工具类
 */
public class CastUtil {

    /**
     * 转为String型
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为String型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double型
     * @param obj
     * @return double
     */
    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0);
    }

    /**
     * 转为double型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return double
     */
    public static double castDouble(Object obj, double defaultValue) {
        double douubleValue = defaultValue;
        if (obj != null){
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    douubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    douubleValue = defaultValue;
                }
            }
        }
        return douubleValue;
    }

    /**
     * 转为long型
     * @param obj
     * @return long
     */
    public static long castLong(Object obj){
        return CastUtil.castLong(obj,0);
    }

    /**
     * 转为long型（提供默认值）
     * @param obj
     * @param defaultValuke
     * @return long
     */
    public static long castLong(Object obj, long defaultValuke) {
        long longValue = defaultValuke;
        if (obj != null){
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue = defaultValuke;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为 int 型
     * @param obj
     * @return int
     */
    public static int castInt(Object obj){
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为 int 型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return int
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null){
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为 boolean 型
     * @param obj
     * @return boolean
     */
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * 转为 boolean 型（提供默认值）
     * @param obj
     * @param defaultValue
     * @return boolean
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

    /**
     * 转为 String[] 型
     * @param objArray
     * @return String[]
     */
    public static String[] castStringArray(Object[] objArray){
        if (objArray == null){
            objArray = new Object[0];
        }
        String[] strArray = new String[objArray.length];
        if (ArrayUtil.isNotEmpty(objArray)){
            for (int i=0; i < objArray.length; i++){
                strArray[i] = castString(objArray[i]);
            }
        }
        return strArray;
    }

    /**
     * 转为 double[] 型
     * @param objArray
     * @return double[]
     */
    public static double[] castDoubleArray(Object[] objArray){
        if (objArray == null){
            objArray = new Object[0];
        }
        double[] doubleArray = new double[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)){
            for (int i=0; i < objArray.length; i++){
                doubleArray[i] = castDouble(objArray[i]);
            }
        }
        return doubleArray;
    }

    /**
     * 转换为 long[] 型
     * @param objArray
     * @return long[]
     */
    public static long[] castLongArray(Object[] objArray){
        if (objArray == null){
            objArray = new Object[0];
        }
        long[] longArray = new long[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)){
            for (int i=0; i < objArray.length; i++){
                longArray[i] = castLong(objArray[i]);
            }
        }
        return longArray;
    }

    /**
     * 返回 int[] 型
     * @param objArray
     * @return int[]
     */
    public static int[] castIntArray(Object[] objArray){
        if (objArray == null){
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)){
            for (int i=0; i < objArray.length; i++){
                intArray[i] = castInt(objArray[i]);
            }
        }
        return  intArray;
    }

    /**
     * 转为 boolean[] 型
     * @param objArray
     * @return boolean[]
     */
    public static boolean[] castBooleanArray(Object[] objArray){
        if (objArray == null){
            objArray = new Object[objArray.length];
        }
        boolean[] booleanArray  = new boolean[objArray.length];
        if (ArrayUtil.isNotEmpty(objArray)){
            for (int i=0; i < objArray.length; i++){
                booleanArray[i] = castBoolean(objArray[i]);
            }
        }
        return booleanArray;
    }
}
