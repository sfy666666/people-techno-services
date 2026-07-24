#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import random, datetime
random.seed(42)

BRAND_COLORS = {
    "Apple":    ("#2a2a2e","#1a1a1e"),
    "Samsung":  ("#1a2a4a","#0a1a3a"),
    "HUAWEI":   ("#1a2e2e","#0a1e1e"),
    "Xiaomi":   ("#2e1a1a","#1e0a0a"),
    "REDMI":    ("#1a2a1a","#0a1a0a"),
    "OPPO":     ("#2a2a1a","#1a1a0a"),
    "OnePlus":  ("#2e1a1e","#1e0a0e"),
    "vivo":     ("#1a1a2e","#0a0a1e"),
    "iQOO":     ("#2e0a1a","#1e0a1e"),
    "realme":   ("#1a2e2a","#0a1e1a"),
    "努比亚":   ("#2a1a1a","#1a0a0a"),
    "魅族":     ("#2a1a2a","#1a0a1a"),
    "荣耀":     ("#1a2e1a","#0a1e0a"),
}

def grad(c1,c2): return "linear-gradient(135deg, {} 0%, {} 100%)".format(c1,c2)
def brand_bg(b):
    c=BRAND_COLORS.get(b,("#1a1a1a","#0a0a0a")); return grad(c[0],c[1])
def brand_img(b):
    c=BRAND_COLORS.get(b,("#2a2a2a","#1a1a1a")); return grad(c[1],c[0])

BRAND_TAGS = {
    "Apple":  ["iOS","A系列","旗舰"],
    "Samsung":["One UI","骁龙","旗舰"],
    "HUAWEI": ["麒麟","鸿蒙","XMAGE"],
    "荣耀":    ["MagicOS","骁龙","旗舰"],
    "Xiaomi":  ["MIUI","骁龙","徕卡"],
    "REDMI":   ["MIUI","天玑","性价比"],
    "OPPO":    ["ColorOS","骁龙","旗舰"],
    "OnePlus": ["ColorOS","骁龙","旗舰"],
    "vivo":    ["OriginOS","骁龙","影像"],
    "iQOO":    ["OriginOS","骁龙","游戏"],
    "realme":  ["realme UI","天玑","性价比"],
    "努比亚":  ["MyOS","骁龙","游戏"],
    "魅族":    ["Flyme","骁龙","小而美"],
}
def tags(b): return ",".join(BRAND_TAGS.get(b,["旗舰"]))

PROC = {
    "A15 Bionic":    dict(a=820000,gs=2300,gm=5400,mk=9700,ai=2600,ef="优秀",th="控制良好"),
    "A16 Bionic":    dict(a=950000,gs=2500,gm=6100,mk=11600,ai=3200,ef="优秀",th="控制良好"),
    "A17 Pro":       dict(a=1150000,gs=2950,gm=7200,mk=15300,ai=3800,ef="优秀",th="轻微发热"),
    "A18 Pro":       dict(a=1300000,gs=3400,gm=8100,mk=17800,ai=4500,ef="卓越",th="控制良好"),
    "骁龙8+ Gen1":  dict(a=1050000,gs=2600,gm=6000,mk=13500,ai=3100,ef="良好",th="轻微发热"),
    "骁龙8 Gen2":   dict(a=1250000,gs=2900,gm=7100,mk=16500,ai=3700,ef="良好",th="发热明显"),
    "骁龙8 Gen3":   dict(a=2100000,gs=3100,gm=7500,mk=18500,ai=4300,ef="优秀",th="发热明显"),
    "骁龙8s Gen3":  dict(a=1550000,gs=2700,gm=6500,mk=15000,ai=3400,ef="良好",th="轻微发热"),
    "骁龙8 Gen4":   dict(a=2800000,gs=3800,gm=9000,mk=22000,ai=5000,ef="卓越",th="轻微发热"),
    "天玑9000":    dict(a=1000000,gs=2450,gm=5700,mk=12000,ai=2900,ef="良好",th="轻微发热"),
    "天玑9000+":   dict(a=1100000,gs=2650,gm=6200,mk=13500,ai=3200,ef="良好",th="轻微发热"),
    "天玑9200":    dict(a=1200000,gs=2850,gm=6800,mk=15000,ai=3600,ef="优秀",th="轻微发热"),
    "天玑9200+":   dict(a=1350000,gs=3050,gm=7200,mk=16500,ai=3900,ef="优秀",th="发热明显"),
    "天玑9300":    dict(a=2050000,gs=3200,gm=7900,mk=17800,ai=4100,ef="卓越",th="发热明显"),
    "天玑9300+":   dict(a=2200000,gs=3350,gm=8100,mk=18500,ai=4300,ef="卓越",th="发热明显"),
    "天玑9400":    dict(a=2350000,gs=3550,gm=8500,mk=19500,ai=4600,ef="卓越",th="轻微发热"),
    "天玑9400+":   dict(a=2500000,gs=3700,gm=8800,mk=20500,ai=4800,ef="卓越",th="轻微发热"),
    "麒麟9000":    dict(a=780000,gs=1950,gm=4800,mk=8600,ai=2100,ef="良好",th="发热明显"),
    "麒麟9000S":   dict(a=950000,gs=2200,gm=5500,mk=10200,ai=2600,ef="良好",th="发热明显"),
    "麒麟9010":    dict(a=1050000,gs=2450,gm=6200,mk=11500,ai=2900,ef="良好",th="轻微发热"),
    "麒麟9020":    dict(a=1200000,gs=2650,gm=6800,mk=12800,ai=3100,ef="良好",th="轻微发热"),
}

def vp(p):
    d=dict(PROC.get(p,PROC["骁龙8 Gen2"]))
    for k in ["a","gs","gm","mk","ai"]:
        if k in d: d[k]=int(d[k]*random.uniform(.95,1.05))
    return d

GAME_TIERS={
    "A18 Pro":  [59.8,119.9,89.7,58.9,59.5],
    "A17 Pro":  [59.4,119.6,89.2,58.2,59.1],
    "A16 Bionic":[58.5,119.0,88.0,56.0,58.0],
    "A15 Bionic":[56.0,118.0,86.0,52.0,55.0],
    "旗舰":     [59.0,119.5,89.0,57.5,58.8],
    "次旗舰":   [56.5,118.5,87.5,54.0,57.0],
    "中高端":   [52.0,117.0,84.0,48.0,52.0],
    "中端":     [42.0, 90.0,60.0,35.0,40.0],
}
GNAMES=["原神","王者荣耀","和平精英","崩坏：星穹铁道","逆水寒"]
GSETTINGS=["极高画质 60帧","极致画质 120帧","HDR高清 90帧","最高画质 60帧","高画质 60帧"]
PWR_BASE=[4.2,3.0,3.5,4.0,4.5]
REMARKS=["接近满帧","流畅稳定","流畅","轻微波动","发热明显"]

def games(tier):
    fl=GAME_TIERS.get(tier,GAME_TIERS["旗舰"])
    return [(GNAMES[i],round(fl[i]*random.uniform(.98,1.02),1),GSETTINGS[i],round(PWR_BASE[i]*random.uniform(.9,1.1),2),REMARKS[i]) for i in range(5)]

def batt(cap,ef):
    efm={"卓越":1.2,"优秀":1.1,"良好":1.0,"一般":0.9}
    f=efm.get(ef,1.0); cf=cap/4500.0
    h=[round(r*cf*f,1) for r in [5.0,13.0,11.5,30.0,0.8]]
    d=[round(100/h[i] if h[i]>1 else h[i]*100,1) for i in range(5)]
    return h,d

SCREEN={
    "旗舰":   [("分辨率","2K+ 3168x1440",""),("刷新率","1-120Hz LTPO",""),("峰值亮度","4500nit",""),("触控采样率","480Hz",""),("PWM调光","2160Hz",""),("色域","DCI-P3 100%","")],
    "次旗舰": [("分辨率","1.5K 2712x1220",""),("刷新率","1-144Hz LTPO",""),("峰值亮度","4000nit",""),("触控采样率","360Hz",""),("PWM调光","1920Hz",""),("色域","DCI-P3 100%","")],
    "中高端": [("分辨率","FHD+ 2400x1080",""),("刷新率","120Hz AMOLED",""),("峰值亮度","3000nit",""),("触控采样率","240Hz",""),("PWM调光","1440Hz",""),("色域","DCI-P3 90%","")],
    "中端":   [("分辨率","FHD+ 2400x1080",""),("刷新率","90Hz AMOLED",""),("峰值亮度","1800nit",""),("触控采样率","180Hz",""),("PWM调光","960Hz",""),("色域","DCI-P3 85%","")],
}

def others(brand,tier,charger):
    w="198g" if tier in("旗舰","次旗舰") else "210g"
    cam="5000万像素 1英寸大底" if tier=="旗舰" else ("5000万像素 大底" if tier in("次旗舰","中高端") else "6400万像素")
    wr="50W" if tier=="旗舰" else ("30W" if tier in("次旗舰","中高端") else "不支持")
    ip="IP68" if tier=="旗舰" else ("IP54" if tier in("次旗舰","中高端") else "IP53")
    os="iOS 17" if brand=="Apple" else ("HarmonyOS 4.2" if brand=="HUAWEI" else "Android 14")
    unlock="Face ID + 超声波指纹" if brand=="Apple" else "短焦指纹"
    return [("机身重量",w),("有线充电","{}W".format(charger)),("无线充电",wr),("主摄像头",cam),("防水防尘",ip),("系统",os),("解锁方式",unlock),("扬声器","立体声双扬声器")]

def sc(t):
    b={"旗舰":4.9,"次旗舰":4.7,"中高端":4.5,"中端":4.2}.get(t,4.5)
    return round(b+random.uniform(-.15,.15),1)
PHONES = [
  # Apple iPhone (16款)
  ("Apple","iPhone 13","2021-09",5199,"A15 Bionic","旗舰",3227,20),
  ("Apple","iPhone 13 mini","2021-09",4499,"A15 Bionic","旗舰",2438,20),
  ("Apple","iPhone 13 Pro","2021-09",7999,"A15 Bionic","旗舰",3095,27),
  ("Apple","iPhone 13 Pro Max","2021-09",8999,"A15 Bionic","旗舰",4352,27),
  ("Apple","iPhone 14","2022-09",5399,"A15 Bionic","旗舰",3279,27),
  ("Apple","iPhone 14 Plus","2022-10",5999,"A15 Bionic","旗舰",4325,27),
  ("Apple","iPhone 14 Pro","2022-09",7999,"A16 Bionic","旗舰",3200,27),
  ("Apple","iPhone 14 Pro Max","2022-09",8999,"A16 Bionic","旗舰",4323,27),
  ("Apple","iPhone 15","2023-09",5999,"A16 Bionic","旗舰",3349,27),
  ("Apple","iPhone 15 Plus","2023-09",6999,"A16 Bionic","旗舰",4383,27),
  ("Apple","iPhone 15 Pro","2023-09",7999,"A17 Pro","旗舰",3274,27),
  ("Apple","iPhone 15 Pro Max","2023-09",9999,"A17 Pro","旗舰",4422,27),
  ("Apple","iPhone 16","2024-09",5999,"A18","旗舰",3561,45),
  ("Apple","iPhone 16 Plus","2024-09",6999,"A18","旗舰",4674,45),
  ("Apple","iPhone 16 Pro","2024-09",7999,"A18 Pro","旗舰",3582,45),
  ("Apple","iPhone 16 Pro Max","2024-09",9999,"A18 Pro","旗舰",4685,45),
  # Samsung (18款)
  ("Samsung","Galaxy S21 5G","2021-01",4999,"骁龙888","次旗舰",4000,25),
  ("Samsung","Galaxy S21+ 5G","2021-01",5999,"骁龙888","次旗舰",4800,25),
  ("Samsung","Galaxy S21 Ultra 5G","2021-01",9699,"骁龙888","旗舰",5000,25),
  ("Samsung","Galaxy S22","2022-02",4999,"骁龙8 Gen1","次旗舰",3700,45),
  ("Samsung","Galaxy S22+","2022-02",6499,"骁龙8 Gen1","次旗舰",4500,45),
  ("Samsung","Galaxy S22 Ultra","2022-02",10199,"骁龙8 Gen1","旗舰",5000,45),
  ("Samsung","Galaxy S23","2023-02",5699,"骁龙8 Gen2","次旗舰",3900,45),
  ("Samsung","Galaxy S23+","2023-02",7499,"骁龙8 Gen2","次旗舰",4700,45),
  ("Samsung","Galaxy S23 Ultra","2023-02",9499,"骁龙8 Gen2","旗舰",5000,45),
  ("Samsung","Galaxy S24","2024-01",5999,"骁龙8 Gen3","旗舰",4000,45),
  ("Samsung","Galaxy S24+","2024-01",7499,"骁龙8 Gen3","旗舰",4900,45),
  ("Samsung","Galaxy S24 Ultra","2024-01",9699,"骁龙8 Gen3","旗舰",5000,45),
  ("Samsung","Galaxy Z Flip4","2022-08",7499,"骁龙8+ Gen1","次旗舰",3700,25),
  ("Samsung","Galaxy Z Flip5","2023-08",7499,"骁龙8 Gen2","次旗舰",3700,25),
  ("Samsung","Galaxy Z Flip6","2024-07",7999,"骁龙8 Gen3","旗舰",4000,25),
  ("Samsung","Galaxy Z Fold4","2022-08",12999,"骁龙8+ Gen1","旗舰",4400,25),
  ("Samsung","Galaxy Z Fold5","2023-08",12999,"骁龙8 Gen2","旗舰",4400,25),
  ("Samsung","Galaxy Z Fold6","2024-07",14999,"骁龙8 Gen3","旗舰",4400,25),
  # HUAWEI (26款)
  ("HUAWEI","P50","2021-08",4488,"麒麟9000","旗舰",4100,66),
  ("HUAWEI","P50 Pro","2021-08",5988,"麒麟9000","旗舰",4360,66),
  ("HUAWEI","P50 Pocket","2021-12",8988,"麒麟9000","旗舰",4000,40),
  ("HUAWEI","Mate 50","2022-09",4999,"骁龙8+ Gen1 4G","旗舰",4460,66),
  ("HUAWEI","Mate 50 Pro","2022-09",6799,"骁龙8+ Gen1 4G","旗舰",4700,66),
  ("HUAWEI","P60","2023-03",4488,"骁龙8+ Gen1 4G","旗舰",4815,66),
  ("HUAWEI","P60 Pro","2023-03",6988,"骁龙8+ Gen1 4G","旗舰",4815,88),
  ("HUAWEI","P60 Art","2023-03",8988,"骁龙8+ Gen1 4G","旗舰",5100,88),
  ("HUAWEI","Mate 60","2023-08",5499,"麒麟9000S","旗舰",4750,88),
  ("HUAWEI","Mate 60 Pro","2023-08",6499,"麒麟9000S","旗舰",5000,88),
  ("HUAWEI","Mate 60 Pro+","2023-09",8999,"麒麟9000S","旗舰",5000,88),
  ("HUAWEI","Pura 70","2024-04",5499,"麒麟9010","旗舰",4900,66),
  ("HUAWEI","Pura 70 Pro","2024-04",6499,"麒麟9010","旗舰",5050,100),
  ("HUAWEI","Pura 70 Pro+","2024-04",7999,"麒麟9010","旗舰",5050,100),
  ("HUAWEI","Pura 70 Ultra","2024-04",9999,"麒麟9010","旗舰",5200,100),
  ("HUAWEI","Mate 70","2024-10",5499,"麒麟9020","旗舰",5300,100),
  ("HUAWEI","Mate 70 Pro","2024-10",6999,"麒麟9020","旗舰",5500,100),
  ("HUAWEI","Mate 70 Pro+","2024-10",8999,"麒麟9020","旗舰",5700,100),
  ("HUAWEI","nova 11","2023-04",2499,"骁龙778G 4G","中高端",4500,66),
  ("HUAWEI","nova 12","2023-12",2999,"麒麟8000","中高端",4600,100),
  ("HUAWEI","Pocket 2","2024-02",7499,"麒麟9000S","旗舰",4520,66),
  ("HUAWEI","Mate X5","2023-08",12999,"麒麟9000S","旗舰",5060,66),
  ("HUAWEI","Mate X6","2024-12",12999,"麒麟9020","旗舰",5200,66),
  ("HUAWEI","nova 12 Pro","2023-12",3999,"麒麟8000","中高端",4600,100),
  ("HUAWEI","nova 12 Ultra","2024-01",4699,"麒麟9000SL","中高端",4600,100),
  # 荣耀 (12款)
  ("荣耀","Magic 4","2022-02",3999,"骁龙8 Gen1","次旗舰",4800,66),
  ("荣耀","Magic 4 Pro","2022-02",5499,"骁龙8 Gen1","旗舰",4600,100),
  ("荣耀","Magic 5","2023-03",3999,"骁龙8 Gen2","旗舰",5100,66),
  ("荣耀","Magic 5 Pro","2023-03",5199,"骁龙8 Gen2","旗舰",5450,66),
  ("荣耀","Magic 6","2024-01",4399,"骁龙8 Gen3","旗舰",5450,66),
  ("荣耀","Magic 6 Pro","2024-01",5699,"骁龙8 Gen3","旗舰",5600,80),
  ("荣耀","Magic 7","2024-10",4499,"骁龙8 Gen3","旗舰",5650,100),
  ("荣耀","Magic 7 Pro","2024-10",5699,"骁龙8 Gen3","旗舰",5850,100),
  ("荣耀","荣耀200","2024-05",2699,"骁龙7s Gen3","中高端",5200,100),
  ("荣耀","X50","2023-07",1399,"骁龙6 Gen1","中端",5800,35),
  ("荣耀","X50 GT","2024-01",1999,"骁龙8+ Gen1","中高端",5800,35),
  ("荣耀","90 GT","2024-01",2599,"骁龙8 Gen2","中高端",5000,100),
  # Xiaomi (14款)
  ("Xiaomi","小米12","2021-12",3699,"骁龙8 Gen1","次旗舰",4500,67),
  ("Xiaomi","小米12 Pro","2021-12",4699,"骁龙8 Gen1","旗舰",4600,120),
  ("Xiaomi","小米12S","2022-07",3999,"骁龙8+ Gen1","次旗舰",4500,67),
  ("Xiaomi","小米12S Ultra","2022-07",5999,"骁龙8+ Gen1","旗舰",4860,67),
  ("Xiaomi","小米13","2022-12",3999,"骁龙8 Gen2","旗舰",4500,67),
  ("Xiaomi","小米13 Pro","2022-12",4999,"骁龙8 Gen2","旗舰",4820,120),
  ("Xiaomi","小米13 Ultra","2023-04",5999,"骁龙8 Gen2","旗舰",5000,90),
  ("Xiaomi","小米14","2023-10",3999,"骁龙8 Gen3","旗舰",4610,90),
  ("Xiaomi","小米14 Pro","2023-10",4999,"骁龙8 Gen3","旗舰",4880,120),
  ("Xiaomi","小米14 Ultra","2024-02",6499,"骁龙8 Gen3","旗舰",5300,90),
  ("Xiaomi","小米15","2024-10",4499,"骁龙8 Gen4","旗舰",5400,90),
  ("Xiaomi","小米15 Pro","2024-10",5499,"骁龙8 Gen4","旗舰",6100,90),
  ("Xiaomi","小米15 Ultra","2025-01",6499,"骁龙8 Gen4","旗舰",5500,90),
  ("Xiaomi","小米Civi 4 Pro","2024-03",2999,"骁龙8s Gen3","中高端",4700,67),
  # REDMI (14款)
  ("REDMI","K50","2022-03",2399,"天玑8100","中高端",5500,67),
  ("REDMI","K50 Pro","2022-03",2999,"天玑9000","次旗舰",5000,120),
  ("REDMI","K50 至尊版","2022-08",2999,"骁龙8+ Gen1","次旗舰",5000,120),
  ("REDMI","K60","2023-01",2099,"骁龙8+ Gen1","次旗舰",5500,67),
  ("REDMI","K60 Pro","2023-01",3299,"骁龙8 Gen2","旗舰",5000,120),
  ("REDMI","K60 至尊版","2023-08",2599,"天玑9200+","次旗舰",5000,120),
  ("REDMI","K70","2023-11",2499,"骁龙8 Gen2","旗舰",5000,120),
  ("REDMI","K70 Pro","2023-11",3299,"骁龙8 Gen3","旗舰",5000,120),
  ("REDMI","K70 至尊版","2024-07",2599,"天玑9300+","旗舰",5500,120),
  ("REDMI","K80","2024-12",2499,"骁龙8 Gen3","旗舰",6550,90),
  ("REDMI","K80 Pro","2024-12",3699,"骁龙8 Gen4","旗舰",6000,120),
  ("REDMI","Note 12 Turbo","2023-03",1999,"骁龙7+ Gen2","中高端",5000,67),
  ("REDMI","Note 13 Pro+","2023-09",1899,"天玑7200 Ultra","中端",5000,120),
  ("REDMI","Note 14 Pro+","2024-09",1899,"天玑7300 Ultra","中端",5500,120),
  # OPPO (14款)
  ("OPPO","Find X5","2022-02",3999,"骁龙888","次旗舰",4800,80),
  ("OPPO","Find X5 Pro","2022-02",5999,"骁龙8 Gen1","旗舰",5000,80),
  ("OPPO","Find X6","2023-03",4499,"天玑9200","旗舰",4800,80),
  ("OPPO","Find X6 Pro","2023-03",5999,"骁龙8 Gen2","旗舰",5000,100),
  ("OPPO","Find X7","2024-01",3999,"天玑9300","旗舰",5000,100),
  ("OPPO","Find X7 Ultra","2024-01",5999,"骁龙8 Gen3","旗舰",5000,100),
  ("OPPO","Find X8","2024-10",3999,"天玑9400","旗舰",5630,80),
  ("OPPO","Find X8 Pro","2024-10",5299,"天玑9400","旗舰",5910,80),
  ("OPPO","Reno9 Pro+","2022-11",3999,"骁龙8+ Gen1","次旗舰",4700,80),
  ("OPPO","Reno11","2023-11",2499,"天玑8200","中高端",4600,67),
  ("OPPO","Reno12","2024-05",2699,"天玑8300 Ultra","中高端",5000,80),
  ("OPPO","A1 Pro","2023-03",1799,"骁龙695","中端",4800,67),
  ("OPPO","Find N3","2023-10",9999,"骁龙8 Gen2","旗舰",4800,67),
  ("OPPO","Reno13","2024-11",2699,"天玑9300","中高端",5600,80),
  # OnePlus (16款)
  ("OnePlus","OnePlus 9","2021-03",3799,"骁龙888","次旗舰",4500,65),
  ("OnePlus","OnePlus 9 Pro","2021-03",4999,"骁龙888","旗舰",4500,65),
  ("OnePlus","OnePlus 10 Pro","2022-01",4699,"骁龙8 Gen1","旗舰",5000,80),
  ("OnePlus","OnePlus 11","2023-01",3999,"骁龙8 Gen2","旗舰",5000,100),
  ("OnePlus","OnePlus 12","2023-12",4299,"骁龙8 Gen3","旗舰",5400,100),
  ("OnePlus","OnePlus 13","2024-10",4499,"骁龙8 Gen4","旗舰",6000,100),
  ("OnePlus","OnePlus Open","2023-10",8999,"骁龙8 Gen2","旗舰",4800,67),
  ("OnePlus","Ace 2","2023-02",2799,"骁龙8+ Gen1","次旗舰",5000,100),
  ("OnePlus","Ace 2 Pro","2023-08",2999,"骁龙8 Gen2","旗舰",5000,150),
  ("OnePlus","Ace 3","2024-01",2599,"骁龙8+ Gen1","次旗舰",5500,100),
  ("OnePlus","Ace 3V","2024-03",1999,"骁龙7+ Gen3","中高端",5500,100),
  ("OnePlus","Ace 3 Pro","2024-07",3199,"骁龙8 Gen3","旗舰",6100,100),
  ("OnePlus","Ace 5","2024-11",2799,"骁龙8 Gen3","旗舰",6400,80),
  ("OnePlus","Ace 5 Pro","2024-11",3799,"骁龙8 Gen4","旗舰",6100,100),
  ("OnePlus","Nord CE 3","2023-08",1999,"骁龙782G","中端",5000,80),
  ("OnePlus","Nord 4","2024-07",2499,"骁龙7+ Gen3","中高端",5500,80),
  # vivo (18款)
  ("vivo","X80","2022-04",3699,"天玑9000","旗舰",4500,80),
  ("vivo","X80 Pro","2022-04",5499,"骁龙8 Gen1","旗舰",4700,80),
  ("vivo","X90","2022-11",3999,"天玑9200","旗舰",4810,120),
  ("vivo","X90 Pro+","2022-11",6499,"骁龙8 Gen2","旗舰",4700,80),
  ("vivo","X90 Pro","2022-11",4999,"天玑9200","旗舰",4870,120),
  ("vivo","X Fold+","2022-09",9999,"骁龙8+ Gen1","旗舰",4730,80),
  ("vivo","X Fold 2","2023-04",8999,"骁龙8 Gen2","旗舰",4800,120),
  ("vivo","X Flip","2023-04",5999,"骁龙8+ Gen1","旗舰",4400,44),
  ("vivo","X100","2023-11",3999,"天玑9300","旗舰",5000,120),
  ("vivo","X100 Pro","2023-11",4999,"天玑9300","旗舰",5400,100),
  ("vivo","X100 Ultra","2024-05",6499,"骁龙8 Gen3","旗舰",5500,80),
  ("vivo","X200","2024-10",3999,"天玑9400","旗舰",5800,90),
  ("vivo","X200 Pro","2024-10",5299,"天玑9400","旗舰",6000,90),
  ("vivo","X200 Pro mini","2024-10",4699,"天玑9400","旗舰",5700,90),
  ("vivo","S18 Pro","2023-12",3199,"天玑9200+","中高端",5000,80),
  ("vivo","S19 Pro","2024-05",3299,"天玑9200+","中高端",5500,80),
  ("vivo","X Fold 3","2024-03",6999,"骁龙8 Gen2","旗舰",5500,80),
  ("vivo","X Fold 3 Pro","2024-03",9999,"骁龙8 Gen3","旗舰",5700,100),
  # iQOO (18款)
  ("iQOO","iQOO 9","2022-01",3999,"骁龙8 Gen1","旗舰",4700,120),
  ("iQOO","iQOO 9 Pro","2022-01",4999,"骁龙8 Gen1","旗舰",4700,120),
  ("iQOO","iQOO 10","2022-07",3699,"骁龙8+ Gen1","旗舰",4700,120),
  ("iQOO","iQOO 10 Pro","2022-07",4999,"骁龙8+ Gen1","旗舰",4700,200),
  ("iQOO","iQOO 11","2022-12",3799,"骁龙8 Gen2","旗舰",5000,120),
  ("iQOO","iQOO 11 Pro","2022-12",4999,"骁龙8 Gen2","旗舰",4700,200),
  ("iQOO","iQOO Neo7","2022-10",2699,"天玑9000+","次旗舰",5000,120),
  ("iQOO","iQOO Neo8","2023-05",2499,"骁龙8+ Gen1","旗舰",5000,120),
  ("iQOO","iQOO Neo8 Pro","2023-05",3099,"天玑9200+","旗舰",5000,120),
  ("iQOO","iQOO 12","2023-11",3999,"骁龙8 Gen3","旗舰",5000,120),
  ("iQOO","iQOO 12 Pro","2023-11",4999,"骁龙8 Gen3","旗舰",5100,120),
  ("iQOO","iQOO Neo9","2023-12",2299,"骁龙8 Gen2","旗舰",5160,120),
  ("iQOO","iQOO Neo9 Pro","2023-12",2999,"天玑9300","旗舰",5160,120),
  ("iQOO","iQOO Z9 Turbo","2024-04",1999,"骁龙8s Gen3","中高端",6000,80),
  ("iQOO","iQOO 13","2024-10",3999,"骁龙8 Gen4","旗舰",6150,120),
  ("iQOO","iQOO Neo10","2024-11",2499,"骁龙8 Gen3","旗舰",6100,120),
  ("iQOO","iQOO Neo10 Pro","2024-11",3299,"天玑9400","旗舰",6100,120),
  ("iQOO","iQOO Z9","2024-03",1599,"骁龙7s Gen3","中端",6000,80),
  # realme (9款)
  ("realme","realme GT Neo3","2022-03",2299,"天玑8100","中高端",5000,80),
  ("realme","realme GT Neo5","2023-02",2499,"骁龙8+ Gen1","旗舰",5000,150),
  ("realme","realme GT Neo5 SE","2023-04",2099,"骁龙7+ Gen2","中高端",5500,100),
  ("realme","realme GT6","2024-06",2799,"骁龙8s Gen3","中高端",5500,120),
  ("realme","realme GT7 Pro","2024-11",3699,"骁龙8 Gen4","旗舰",6500,120),
  ("realme","realme 11 Pro+","2023-05",1999,"天玑7050","中端",5000,100),
  ("realme","realme 12 Pro+","2024-01",1699,"骁龙7s Gen2","中端",5000,67),
  ("realme","realme 13 Pro+","2024-07",1999,"骁龙7s Gen3","中端",5200,80),
  ("realme","realme C67","2023-12",1199,"骁龙6 Gen1","中端",5000,33),
  # 努比亚 (10款)
  ("努比亚","红魔7","2022-02",3999,"骁龙8 Gen1","旗舰",4500,120),
  ("努比亚","红魔7 Pro","2022-02",4799,"骁龙8 Gen1","旗舰",5000,135),
  ("努比亚","红魔8 Pro","2022-12",3999,"骁龙8 Gen2","旗舰",6000,80),
  ("努比亚","红魔8 Pro+","2022-12",5199,"骁龙8 Gen2","旗舰",5000,165),
  ("努比亚","红魔9 Pro","2023-12",4799,"骁龙8 Gen3","旗舰",6500,80),
  ("努比亚","红魔9S Pro","2024-07",4799,"骁龙8 Gen3","旗舰",6500,80),
  ("努比亚","努比亚 Z50 Ultra","2023-03",3999,"骁龙8 Gen2","旗舰",5000,80),
  ("努比亚","努比亚 Z60 Ultra","2024-01",4299,"骁龙8 Gen3","旗舰",6000,80),
  ("努比亚","努比亚 Z60S Pro","2024-07",3299,"骁龙8 Gen2","旗舰",5100,80),
  ("努比亚","努比亚 Flip","2024-04",2999,"骁龙7 Gen1","次旗舰",4310,33),
  # 魅族 (10款)
  ("魅族","魅族18","2021-03",3999,"骁龙888","次旗舰",4000,36),
  ("魅族","魅族18 Pro","2021-03",4999,"骁龙888","旗舰",4500,40),
  ("魅族","魅族19","2023-03",2999,"骁龙8 Gen2","旗舰",5000,80),
  ("魅族","魅族20","2023-03",2999,"骁龙8 Gen2","旗舰",4700,67),
  ("魅族","魅族20 Pro","2023-03",3999,"骁龙8 Gen2","旗舰",5000,80),
  ("魅族","魅族20 INFINITY","2023-03",5999,"骁龙8 Gen2","旗舰",4800,80),
  ("魅族","魅族21","2023-11",3399,"骁龙8 Gen3","旗舰",4800,80),
  ("魅族","魅族21 Note","2024-05",2599,"骁龙8 Gen2","旗舰",5500,66),
  ("魅族","魅族21 Pro","2024-02",4999,"骁龙8 Gen3","旗舰",5050,80),
  ("魅族","魅族22 Pro","2025-03",3999,"骁龙8 Gen4","旗舰",5200,80),
]

def esc(s):
    if s is None: return "NULL"
    return "'" + str(s).replace("\\","\\\\").replace("'","''") + "'"

def gen_sql():
    lines = []
    lines.append("-- ============================================")
    lines.append("-- 百姓数码 近5年主流手机数据 (2021-2025)")
    lines.append("-- Generated: " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
    lines.append("-- Total: {} phones".format(len(PHONES)))
    lines.append("-- ============================================")
    lines.append("SET NAMES utf8mb4;")
    lines.append("SET FOREIGN_KEY_CHECKS = 0;")
    lines.append("")
    pid = 100
    for (brand, name, date, price, proc, tier, battery, charger) in PHONES:
        bg = brand_bg(brand)
        img = brand_img(brand)
        sc_v = sc(tier)
        tg = tags(brand)
        proc_d = vp(proc)
        lines.append("-- {} {}".format(brand, name))
        lines.append("INSERT INTO phone (id,brand,name,price,bg_color,image_color,score,score_label,category,sort,show_home,enabled,create_time,update_time) VALUES ({},{},{},{},{},{},{},{},{},{},1,1,NOW(),NOW());".format(
            pid, esc(brand), esc(name), price, esc(bg), esc(img), repr(sc_v), esc(tg), esc(tier), pid-99))
        lines.append("INSERT INTO phone_basic (phone_id,test_model,launch_date,processor,memory_config,price,test_version,battery_capacity,screen_size) VALUES ({},{},{},{},{},{},{},{},{});".format(
            pid, esc(name+" 12+256GB"), esc(date), esc(proc), esc("12GB+256GB"), price, esc("测试软件 V3.0"), battery, esc("6.7英寸")))
        for (gn,gf,gs,gp,gr) in games(tier):
            lines.append("INSERT INTO phone_game_test (phone_id,game_name,avg_fps,settings,power,remark) VALUES ({},{},{},{},{},{});".format(
                pid, esc(gn), gf, esc(gs), gp, esc(gr)))
        bm = [
            ("安兔兔","V{}".format(11+pid%3), proc_d["a"], 98-pid%5),
            ("GeekBench6 单核","6.3",proc_d["gs"],97-pid%5),
            ("GeekBench6 多核","6.3",proc_d["gm"],98-pid%5),
            ("3DMark WildLife","Extreme",proc_d["mk"],96-pid%5),
            ("AI Benchmark","V5",proc_d["ai"],95-pid%5),
        ]
        for(bn,bv,bs,bp) in bm:
            lines.append("INSERT INTO phone_benchmark (phone_id,name,version,score,percentile) VALUES ({},{},{},{},{});".format(
                pid, esc(bn), esc(bv), bs, bp))
        bh,bd = batt(battery, proc_d["ef"])
        scenes=["游戏续航","在线视频","网页浏览","微信通话","待机续航"]
        for i in range(5):
            dur = "{:.1f}小时".format(bh[i]) if i<4 else "{:.1f}%/天".format(bd[i])
            lines.append("INSERT INTO phone_battery (phone_id,scene,duration,discharge_rate) VALUES ({},{},{},{});".format(
                pid, esc(scenes[i]), esc(dur), bd[i]))
        for(sn,sv,sr) in SCREEN.get(tier, SCREEN["中高端"]):
            lines.append("INSERT INTO phone_screen (phone_id,name,val,remark) VALUES ({},{},{},{});".format(
                pid, esc(sn), esc(sv), esc(sr)))
        for(on,ov) in others(brand, tier, charger):
            lines.append("INSERT INTO phone_other (phone_id,name,val) VALUES ({},{},{});".format(
                pid, esc(on), esc(ov)))
        lines.append("")
        pid += 1
    lines.append("SET FOREIGN_KEY_CHECKS = 1;")
    return "\n".join(lines)

if __name__ == "__main__":
    sql = gen_sql()
    out = "G:/JavaWorkSpace/people-techno-service/sql/seed_phones.sql"
    with open(out, "w", encoding="utf-8") as f:
        f.write(sql)
    print("OK! {} phones, {} lines".format(len(PHONES), sql.count("\n")))