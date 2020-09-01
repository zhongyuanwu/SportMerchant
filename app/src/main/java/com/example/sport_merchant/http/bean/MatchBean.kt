package com.example.sport_merchant.http.bean

/**
 * created by jump on 2020/8/23
 * describe:赛事
 */
data class MatchBean(
    val mhid:String,//主隊id
    val mhn:String,//主隊名稱
    val frmhn:String,//主队名称首字母
    val mhlu:String,//主隊logo。圖標的url地址
    val mhlut:String,//主隊logo縮略圖的url地址
    val maid:String,//客隊id
    val man:String,//客隊名稱
    val frman:String,//客队名称首字母
    val malu:String,//客隊logo。圖標的url地址
    val malut:String,//客隊logo縮略圖的url地址
    val mid:String,//賽事id
    val mess:Int,
    val csna:String,//賽種名稱
    val tid:String,//聯賽id
    val mst:String,//賽事進行時間 单位秒
    val srid:String,//第三方赛事ID
    val mcg:Int,//欄目類型（mcg =1滾球mcg=2即將開賽，mcg=3今日賽事，mcg=4早盤）
    val mmp:String,//比賽階段0未開始6上半場31中場休息7下半場41加時賽上半場100全場結束32即將加時
    val seid:String,//赛季id
    val mgt:String,//賽事開始時間
    val mct:Int,//當前是第幾盤 當前是第幾盤或者第幾局
    val tlev:Int,//聯賽等級
    val mat:String,//發球方主：home，客：away
    val mo:Int,//比賽是否結束
    val mp:Int,//是否支持賽前盤
    val csid:String,//種id
    val ms:Int,//是否支持賽前盤賽事狀態
    val cmec:String?=null,//事件编码
    val mng:Int,//是否中立場1:是中立場，0:非中立場
    val mle:Int,//賽節配寘足球：0 = default（默認90分鐘），1 = 2 x 40 minutes，9 = 2 x 35 minutes，10 = 2 x 30 minutes，11 = 2 x 25 minutes，46 = 2 x 45 minutes with ABBA shootout format，籃球：0 = default（默認）四節每節10分鐘，7=12 munutes periods四節每節12分鐘，17=2 X 20 minutes上下半場各20分鐘
    val mvs:Int,//動畫狀態0:不可用1:可用，暫未播放2：可用，播放中
    val mms:Int,//視頻狀態0:不可用1:可用，暫未播放2：可用，播放中
    val lurl:String,//联赛logo
    val cds:String,//数据源
    val mfo:String?=null,//賽制
    val mhs:Int,//盤口狀態
    val mft:Int,//總局數
    val tn:String,//聯賽名稱
    val mlet:String,//获取赛事时长  获取每节比赛的时长
    val msc:List<String>//[{S1|21:30}，{S2|21:30}，{S3|21:30}] --比分（比分類型|比分）
)