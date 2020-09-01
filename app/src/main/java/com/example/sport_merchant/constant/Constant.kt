package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/21
 * describe:
 */
object Constant {
    //模拟商户 预发布环境 http://api.sportxxx278gwf4.com/yewu6/
    //模拟商户 测试环境 http://api.sportxxxifbdxm2.com/yewu6/
    const val USER_BASE_URL = "http://api.sportxxx278gwf4.com/yewu6/"

    //赛事  联调环境 http://api.sportxxx278gwf4.com/yewu11/
    //赛事 测试环境  http://api.sportxxxifbdxm2.com/yewu11/
    //group1/M00/00/3F/CgUUSV8v6DiAJXMPAD7qDvdp3To397.png
    const val MATCH_BASE_URL = "http://api.sportxxx278gwf4.com/yewu11/"

    //图片加载路径
    var IMAGE_HOST_URL = "http://api.sportxxx278gwf4.com/file/fastdfs/download/image?filePath="

    const val DEVICE_TOKEN = "androidDeviceID"

    const val KEY_FIRST_INSTALL = "key_first_install"

    const val WEB_TO_MAIN_REQUEST_CODE = 100
    const val WEB_TO_MAIN_RESULT_CODE = 101

}