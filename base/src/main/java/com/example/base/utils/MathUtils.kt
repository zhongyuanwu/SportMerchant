package com.example.base.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * created by jump on 2020/8/29
 * describe:
 */
object MathUtils {
    /**
     * 判断字符串是否是数字
     */
    fun isNumeric(str: String): Boolean {
        for (i in str.indices) {
            println(str[i])
            if (!Character.isDigit(str[i])) {
                return false
            }
        }
        return true
    }
    /**
     * 保留两位小数
     * 传double的时候 精度会损失。。所以tostring
     */
    fun truncate(v1: Float, number: Int): Float {
        val bg = BigDecimal(v1.toString()).setScale(number, RoundingMode.DOWN)
        return bg.toFloat()
    }

    /**
     * 保留两位小数
     * 传double的时候 精度会损失。。所以tostring
     */
    fun truncate(v1: Double, number: Int): Double {
        val bg = BigDecimal(v1.toString()).setScale(number, RoundingMode.DOWN)
        return bg.toDouble()
    }


    /**
     * 两个 double 相减    避免   2.0 - 1.1 问题
     */
    fun subFloat(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.subtract(b2).toFloat()
    }


    /**
     * 两个 double 相减    避免   2.0 - 1.1 问题
     */
    fun subDouble(v1: Double, v2: Double): Double {
        val b1 = BigDecimal.valueOf(v1)
        val b2 = BigDecimal.valueOf(v2)
        return b1.subtract(b2).toDouble()
    }

    /**
     * 两个 float 相加  避免  3.6 + 0.4 问题
     */
    fun addFloat(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.add(b2).toFloat()
    }

    /**
     * 两个 double 相加  避免  3.6 + 0.4 问题
     */
    fun addDouble(v1: Double, v2: Double): Double {
        val b1 = BigDecimal.valueOf(v1)
        val b2 = BigDecimal.valueOf(v2)
        return b1.add(b2).toDouble()
    }

    fun addDouble(v1: String, v2: String): Double {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 乘法
     */
    fun multiplyFloat(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
    }

    fun multiplyFloat(v1: String, v2: String): Float {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
    }



    fun multiplyDouble(v1: Double, v2: Double, scale: Int): Double {
        val b1 = BigDecimal.valueOf(v1)
        val b2 = BigDecimal.valueOf(v2)
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 除法 保留2位小数
     */
    fun divideFloat(v1: Float, v2: Float): Float {
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.divide(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
    }

    /**
     * 请输入数字型字符串
     */
    fun divideFloat(v1: String, v2: String): Float {
        val b1 = BigDecimal(v1)
        val b2 = BigDecimal(v2)
        return b1.divide(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
    }


    fun divideDouble(v1: Double, v2: Double, scale: Int): Double {
        val b1 = BigDecimal.valueOf(v1)
        val b2 = BigDecimal.valueOf(v2)
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
    }
}