package com.lhx.util;

public enum Achievement {

    CHU_LAI_ZHA_DAO(1, "初来乍到", "注册成功触发成就"),

    Bian_Lian(2, "变脸", "第一次上传头像"),

    Ling_Ju_Li(3, "零距离", "关注数和被关注数都达到10个以上"),

    Wai_Jiao_Guan(4, "外交官", "关注数和被关注数都达到200个以上"),

    Zha_Yi_Kan(5, "乍一看", "第一次关注彩民"),

    Ren_Mai_Kuo_Zhang(6, "人脉扩张", "关注100个彩民"),

    Jiao_Ji_Hua(7, "交际花", "关注1999个彩民"),

    Fen_Si_Lai_Le(8, "粉丝来了", "被第1个彩民关注"),

    Bai_Gan_Jiao_Ji(9, "百感交集", "被第100个彩民关注"),

    Gao_Peng_Man_Zuo(10, "高朋满座", "被第5000个彩民关注"),

    Wan_Ren_Mi(11, "万人迷", "被第10000个彩民关注"),

    Fei_Cheng_Wu_Rao(12, "非诚勿扰", "第一次收到私信"),

    Yong_Qi(13, "勇气", "第一次发送私信"),

    You_Tu_You_Zhen_Xiang(14, "有图有真相", "发布内容时包含图片"),

    Han_Yi_Han(15, "喊一喊", "第一次使用声音"),

    You_Lai_Le(16, "又来了", "连续第三天登陆"),

    Zai_Jie_Zai_Li(17, "再接再励", "连续第五天登陆"),

    Hao_Yang_De(18, "好样的", "连续7天登陆"),

    Lai_Qu_Zi_Ru(19, "来去自如", "连续15天登陆"),

    Nai_Bu_Zhu_Ji_Mo(20, "耐不住寂寞", "连续30天登陆"),

    Jia_De_Gan_Jue(21, "家的感觉", "登陆至少40天"),

    Lao_Shou(22, "老手", "登陆至少70天"),

    Wan_Jia(23, "玩家", "登陆至少90天"),

    Da_Wan_Jia(24, "大玩家", "登陆至少120天"),

    Lao_Wan_Jia(25, "老玩家", "登陆至少160天"),

    Zhi_Ye_Wan_Jia(26, "职业玩家", "登陆至少200天"),

    Chao_Ji_Wan_Jia(27, "超级玩家", "登陆至少240天"),

    Zui_Qiang_Da_Ren(28, "最强达人", "连续365天登陆"),

    San_Ren_Xing(29, "三人行", "三个彩民同时在\"赛事交易所\"在线十分钟以上"),

    Guan_Shui(30, "灌水", "第一次针对赛事或对应下单进行评论"),

    Wo_Cai(31, "我猜", "第一次参与下单"),

    Xi_Yi_Wei_Chang(32, "习以为常", "第10次参与下单"),

    Bai_Ren_Zhan(33, "百人斩", "第100次参与下单"),

    Er_Bai_Wu(34, "二百五", "第250次参与下单"),

    Qian_Shou_Guan_Yin(35, "千手观音", "第1000次参与下单"),

    Ma_La_Song(36, "马拉松", "第3000次参与下单"),

    Zhan_Shen(37, "战神", "第5000次参与下单"),

    Kai_Men_Hong(38, "开门红", "第一次下单胜利"),

    Mei_Kai_Er_Du(39, "梅开二度", "二连胜"),

    Mao_Zi_Xi_Fa(40, "帽子戏法", "三连胜"),

    Da_Sha_Si_Fang(41, "大杀四方", "四连胜"),

    Guo_Wu_Guan(42, "过五关", "五连胜"),

    Zhan_Liu_Jiang(43, "斩六将", "六连胜"),

    Wu_Ren_Neng_Dang(44, "无人能挡", "七连胜"),

    Zhu_Zai_Bi_Sai(45, "主宰比赛", "八连胜"),

    Xiang_Shen_Yi_Yang(46, "像神一样", "九连胜"),

    Wo_Jiu_Shi_Shen(47, "我就是神", "十连胜"),

    Du_Shen(48, "赌神", "二十连胜"),

    Xia_Mang_Huo(49, "瞎忙活", "第一次下单走盘"),

    Bo_Leng(50, "搏冷", "第一次参与赔率大于等于4.0的下单"),

    Da_Leng(51, "大冷", "第一次参与赔率大于等于9.0的下单"),

    Wen_Zi_Ye_Shi_Rou(52, "蚊子也是肉", "第一次赢得一场赔率小于等于1.3的下单"),

    Ma_Que_Ye_Shi_Rou(53, "麻雀也是肉", "第一次赢得一场赔率小于等于1.6的下单"),

    Kuang_Ren(54, "狂人", "第一次赢得赔率大于等于15.0的下单"),

    Kai_Pan(55, "开盘", "第一次参与亚盘下单"),

    Chai_Pan_Zhuan_Jia(56, "拆盘专家", "赢得100次亚盘下单胜利"),

    Cao_Pan_Shou(57, "操盘手", "赢得500次亚盘下单胜利"),

    Zou_Shang_Pan(58, "走上盘", "第一次参与亚盘的\"上盘\"下单并胜利"),

    Qu_Xia_Pan(59, "去下盘", "第一次参与亚盘的\"下盘\"下单并胜利"),

    Shang_Pan_Wang(60, "上盘王", "赢得200次亚盘的\"上盘\"下单胜利"),

    Xia_Pan_Wang(61, "下盘王", "赢得200次亚盘的\"下盘\"下单胜利"),

    Shu_Xue(62, "数学", "第一次参与单双数下单"),

    Shu_Zi_You_Xi(63, "数字游戏", "参与了100次单双数的下单"),

    Shen_Suan_Zi(64, "神算子", "赢得100次单双数下单胜利"),

    Shu_Xue_Jia(65, "数学家", "赢得500次单双数下单胜利"),

    Xue_Hao_Shu_Li_Hua(66, "学好数理化", "参与过单双数和大小球下单"),

    Lao_Da_Bu_Xiao(67, "老大不小", "第一次参与大小球下单"),

    Cai_Da_Qi_Cu(68, "财大气粗", "参与了200场\"大球\"下单"),

    Ju_Ren(69, "巨人", "赢得100场\"大球\"下单胜利"),

    Xiao_Shi_Dai(70, "小时代", "参与了200场\"小球\"下单"),

    Xiao_Qiao_Ling_Long(71, "小巧玲珑", "赢得100场\"小球\"下单胜利"),

    Da_Xiao_Tong_Chi(72, "大小通吃", "赢得100场大小球下单胜利"),

    Da_Xiao_Qiu_Zhi_Wang(73, "大小球之王", "赢得500场大小球下单胜利"),

    Ou_Pei(74, "310", "第一次参与胜平负下单"),

    Shi_Jian_Jiu_Shi_Jin_Qian(75, "时间就是金钱", "赶在比赛开始前1分钟参与下单"),

    Xiao_Du_Yi_Qing(76, "小赌怡情", "用1个龙筹参与下单"),

    Da_Du_Shang_Shen(77, "大赌伤身", "用最大上限龙筹参与下单且失败"),

    Chen_Lian(78, "晨练", "北京时间早上6时至7时参与一场下单"),

    Ban_Ye_San_Geng(79, "半夜三更", "北京时间凌晨2时到4时之间参与一场下单"),

    Xin_Wen_Lian_Bo(80, "新闻联播", "北京时间19时至19时30分之间参与一场下单"),

    Ni_Bu_Li_Cai_Cai_Bu_Li_Ni(81, "你不理彩，彩不理你", "参与300场比赛下单后投资回报率达到1%"),

    Ai_Cai_Ru_Ming(82, "爱彩如命", "参与300场比赛下单后投资回报率达到5%"),

    Cai_Shen_Ye(83, "彩神爷", "参与300场比赛下单后投资回报率达到10%"),

    Zu_Qiu_Mi(84, "足球迷", "参与了100场足球比赛的竞猜"),

    Lan_Qiu_Mi(85, "篮球迷", "参与了100场篮球比赛的竞猜"),

    Shou_Wu_Zu_Dao(86, "手舞足蹈", "参与了300场足球比赛和300场篮球比赛的竞猜"),

    Zhen_Qiu_Mi(87, "真球迷", "参与了500场足球比赛和500场篮球比赛的竞猜"),

    Zhen_Cai_Min(88, "真彩民", "赢得100场足球比赛和100场篮球比赛的竞猜胜利"),

    Cai_Niao(89, "菜鸟", "第一次竞猜失败"),

    Han(90, "汗", "二连败"),

    Bei_Cui_Ming(91, "悲催命", "三连败"),

    Shu_De_Qi(92, "输得起", "四连败"),

    Gou_Hei(93, "够黑", "五连败"),

    Hei_Chu_Xiang(94, "黑出翔", "六连败"),

    Keng_Die_De_Jie_Zou(95, "坑爹的节奏", "七连败"),

    Tai_Sui(96, "太衰", "八连败"),

    Sui_Shen_Fu_Ti(97, "衰神附体", "九连败"),

    Bai_Jia_Zi(98, "败家子", "十连败"),

    Deng_Shen(99, "灯神", "二十连败"),

    Kong_Han_Zheng(100, "恐韩症", "参与任何一场中韩国家队的足球比赛下单，并支持韩国队获胜或赢盘"),

    Ai_Guo_Zhu(101, "爱国注", "参与任何一场中国国家队的比赛下单"),

    Liang_Yi_Zhan_Zheng(102, "两伊战争", "参与任何一场伊朗国家队VS伊拉克国家队的比赛下单"),

    Wu_Da_Lian_Sai(103, "五大联赛", "参与任何一场德甲、意甲、法甲、英超或西甲的足球赛事下单"),

    Gui_Zi_Lai_Le(104, "鬼子来了", "参与任何一场中国国家队主场对阵日本国家队的比赛下单"),

    Ling_Jiu_Ji(105, "领救济", "用成就兑换龙筹"),

    Feng_Mian_Xiu(106, "封面秀", "第一次上传封面");

    /**
     * 成就ID
     */
    private long achieveId;
    /**
     * 成就名称
     */
    private String achieveName;
    /**
     * 如何获取成就
     */
    private String achieveExplain;

    Achievement(long achieveId, String achieveName, String achieveExplain) {
	this.achieveId = achieveId;
	this.achieveName = achieveName;
	this.achieveExplain = achieveExplain;
    }

    public long getAchieveId() {
	return this.achieveId;
    }

    public String getAchieveName() {
	return this.achieveName;
    }

    public String getAchieveExplain() {
	return this.achieveExplain;
    }

}
