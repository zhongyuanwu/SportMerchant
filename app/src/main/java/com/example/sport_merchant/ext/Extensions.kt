package com.example.sport_merchant.ext

import com.example.sport_merchant.R
import com.panda.flycotablayout.TabEntity
import java.text.DecimalFormat

/**
 * created by jump on 2020/8/24
 * describe: 扩展方法
 */

fun MutableList<TabEntity>.setSportIcon(): List<TabEntity> {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val next = iterator.next()
        if (next.menuType == 0) {
            iterator.remove()
        } else {
            when (next.menuType) {
                5 -> {//足球
                    next.unSelectedIcon = R.drawable.ic_sport_football_no
                    next.selectedIcon = R.drawable.ic_sport_football_yes
                }
                7 -> {//篮球
                    next.unSelectedIcon = R.drawable.ic_sport_basketball_no
                    next.selectedIcon = R.drawable.ic_sport_basketball_yes
                }
                14 -> {//斯诺克
                    next.unSelectedIcon = R.drawable.ic_sport_sinuoke_no
                    next.selectedIcon = R.drawable.ic_sport_sinuoke_yes
                }
                15 -> {//羽毛球
                    next.unSelectedIcon = R.drawable.ic_sport_yumao_no
                    next.selectedIcon = R.drawable.ic_sport_yumao_yes
                }
                16 -> {//乒乓球
                    next.unSelectedIcon = R.drawable.ic_sport_tabletennis_no
                    next.selectedIcon = R.drawable.ic_sport_tabletennis_yes
                }
                17 -> {//电竞
                    next.unSelectedIcon = R.drawable.ic_sport_dianjing_no
                    next.selectedIcon = R.drawable.ic_sport_dianjing_yes
                }
                13 -> {//网球
                    next.unSelectedIcon = R.drawable.ic_sport_tennis_no
                    next.selectedIcon = R.drawable.ic_sport_tennis_yes
                }
            }
        }
    }
    return this
}

fun Double.format(): String {
    return DecimalFormat("0.00").format(this)
}

fun Double.numFormat(): String {
    if (this == 0.0) return "0.00"
    if (this < 1) return this.toString()
    return DecimalFormat("#,###.00").format(this)
}