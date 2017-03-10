package longwatch.com.mrliuframe.bean;

import java.util.List;

/**
 * Created by a on 2017/2/22.
 */

public class ShopBean {

    /**
     * code : 200
     * msg : success
     * data : [{"id":"121","goods_name":"镇店之宝丨美白嫩肤矿物蚕丝面膜7片","shop_price":39.9,"market_price":99,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/121/goods_img/161201135479872856902510.jpg","sales_volume":121308,"efficacy":"镇店之宝 美白爆款","sort":0},{"id":"389","goods_name":"清爽平衡矿物蚕丝面膜黑面膜21片","shop_price":99.9,"market_price":297,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/389/goods_img/16081909368531961221339216.jpg","sales_volume":101026,"efficacy":"以黑吸黑 净透亮肤","sort":0},{"id":"85","goods_name":"果味鲜饮|水果鲜润亮肤补水面膜套装17片","shop_price":59.9,"market_price":240,"is_coupon_allowed":false,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/85/goods_img/160819085747012099748482408.jpg","sales_volume":90357,"efficacy":"水嫩舒爽 鲜活亮颜","sort":0},{"id":"684","goods_name":"花粹水润面膜套装10片 每人限购1件","shop_price":29.9,"market_price":139,"is_coupon_allowed":false,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/684/goods_img/160819095063413908186337181.jpg","sales_volume":79004,"efficacy":"水润充盈 鲜嫩少女肌","sort":0},{"id":"6","goods_name":"亮颜水润蚕丝面膜（寂地定制版）","shop_price":59.9,"market_price":239.9,"is_coupon_allowed":false,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/6/goods_img/160819084594721181484473699.jpg","sales_volume":65143,"efficacy":"深层补水 提亮肤色","sort":0},{"id":"343","goods_name":"美白嫩肤润肤水150ml","shop_price":49,"market_price":79,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/343/goods_img/170219115176012130589207871.jpg","sales_volume":54849,"efficacy":"补水保湿 美白嫩肤","sort":0},{"id":"772","goods_name":"清爽亮颜黑面膜套装21片","shop_price":99.9,"market_price":297,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/772/goods_img/1608191429278479187604212.jpg","sales_volume":51269,"efficacy":"热销黑膜 净透亮肤","sort":0},{"id":"5","goods_name":"金桂花矿物眼膜贴60片","shop_price":59,"market_price":89,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/5/goods_img/170219115146912130589208812.jpg","sales_volume":51133,"efficacy":"补水靓眼 改善熊猫眼","sort":0},{"id":"9","goods_name":"玫瑰滋养矿物睡眠面膜180g","shop_price":59.9,"market_price":79.9,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/9/goods_img/160819084569920890610621654.jpg","sales_volume":44959,"efficacy":"镇店之宝 彻夜补水","sort":0},{"id":"239","goods_name":"蜂蜜矿物蚕丝面膜7片","shop_price":89,"market_price":109,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/239/goods_img/16081909252442893599737067.jpg","sales_volume":43142,"efficacy":"深层补水 长效保湿","sort":0},{"id":"10","goods_name":"美白嫩肤睡眠面膜180g","shop_price":69,"market_price":99,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/10/goods_img/161109200044614716799834077.jpg","sales_volume":40975,"efficacy":"补水美白 越睡越白","sort":0},{"id":"446","goods_name":"芦荟补水保湿凝胶150g","shop_price":49.9,"market_price":59,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/446/goods_img/16081909409518953549635059.jpg","sales_volume":37318,"efficacy":"水水润润 抗痘修护","sort":0},{"id":"14","goods_name":"矿物泥浆鼻膜60g","shop_price":45,"market_price":69,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/14/goods_img/170219115361810525955092778.jpg","sales_volume":31065,"efficacy":"草莓鼻小救星 收敛毛孔","sort":0},{"id":"8","goods_name":"美白嫩肤矿物面膜20片","shop_price":119.9,"market_price":298,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/8/goods_img/160819084556416115533763311.jpg","sales_volume":26494,"efficacy":"镇店之宝 明星同款","sort":0},{"id":"83","goods_name":"黑玫瑰矿物蚕丝面膜7片","shop_price":119,"market_price":139,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/83/goods_img/16081908576425344499831215.jpg","sales_volume":26490,"efficacy":"深度保养 补水提亮","sort":0},{"id":"95","goods_name":"多效套装丨补水滋养提亮修护多效蚕丝面膜28片","shop_price":139.9,"market_price":416,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/95/goods_img/160819085823018111120147866.jpg","sales_volume":26133,"efficacy":"补水滋养 提亮修护","sort":0},{"id":"16","goods_name":"玫瑰滋养保湿四件套","shop_price":159.9,"market_price":259.9,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/16/goods_img/170219115434912130589203227.jpg","sales_volume":25654,"efficacy":"一整套源源补水","sort":0},{"id":"11","goods_name":"清爽平衡矿物泥浆面膜260g","shop_price":99,"market_price":99,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/11/goods_img/16081908468057836605117116.jpg","sales_volume":25610,"efficacy":"口碑泥浆 清爽控油","sort":0},{"id":"4","goods_name":"黑玫瑰矿物洁面乳100ml","shop_price":49,"market_price":59,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/4/goods_img/160819084471812844778474460.jpg","sales_volume":24085,"efficacy":"温和清洁 提亮肤色","sort":0},{"id":"97","goods_name":"男士黑茶控油矿物洁面乳100ml","shop_price":39.9,"market_price":59,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/97/goods_img/160819085859519379473858091.jpg","sales_volume":23209,"efficacy":"深层清洁 收缩毛孔","sort":0},{"id":"87","goods_name":"玫瑰滋养蚕丝面膜7片","shop_price":79,"market_price":99,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/87/goods_img/16081908586078271308744782.jpg","sales_volume":21639,"efficacy":"密集滋养 补水保湿","sort":0},{"id":"428","goods_name":"多彩水润亮颜蚕丝面膜套装21片","shop_price":79.9,"market_price":270.9,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/428/goods_img/160819094034113421009937866.jpg","sales_volume":21534,"efficacy":"吸黑焕彩 补水保湿","sort":0},{"id":"257","goods_name":"薰衣草蚕丝面膜7片","shop_price":89,"market_price":109,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/257/goods_img/16081909269443843637779188.jpg","sales_volume":21311,"efficacy":"补水保湿 舒缓修护","sort":0},{"id":"3","goods_name":"清爽平衡矿物洁面乳100ml","shop_price":26.5,"market_price":29.9,"is_coupon_allowed":false,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/3/goods_img/160819084315017845139127816.jpg","sales_volume":21141,"efficacy":"深层清洁 平衡水油","sort":0},{"id":"559","goods_name":"热销泥浆丨竹炭净透矿物泥浆面膜110g","shop_price":59,"market_price":99,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/559/goods_img/170219115385012130589205945.jpg","sales_volume":20965,"efficacy":"控油净肤 细腻毛孔","sort":0},{"id":"189","goods_name":"清爽平衡护肤三件套","shop_price":99.9,"market_price":179.9,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","sales_volume":18497,"efficacy":"深层清洁 平衡水油","sort":0},{"id":"593","goods_name":"热销黑膜丨葡萄籽琉璃亮颜黑面膜21片","shop_price":99.9,"market_price":297,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/593/goods_img/1608190945359698973439364.jpg","sales_volume":17893,"efficacy":"葡萄鲜饮 净透亮肤","sort":0},{"id":"101","goods_name":"玫瑰滋养矿物洁面乳100ml","shop_price":24,"market_price":29.9,"is_coupon_allowed":false,"is_allow_credit":false,"goods_img":"https://image.yunifang.com/yunifang/images/goods/101/goods_img/170219115679212130589207085.jpg","sales_volume":17611,"efficacy":"温和清洁 补水保湿","sort":0},{"id":"379","goods_name":"净透美白矿物蚕丝面膜黑面膜14片","shop_price":69.9,"market_price":198,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/379/goods_img/160819093561210755335724384.jpg","sales_volume":17170,"efficacy":"清洁净透 以白养白","sort":0},{"id":"315","goods_name":"玫瑰滋养矿物润肤水150ml","shop_price":65,"market_price":69,"is_coupon_allowed":true,"is_allow_credit":false,"goods_img":"http://image.hmeili.com/yunifang/images/goods/315/goods_img/160819092638220971403079814.jpg","sales_volume":15810,"efficacy":"持久保湿 静享芬芳","sort":0}]
     */

    private int code;
    private String msg;
    private List<DataEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 121
         * goods_name : 镇店之宝丨美白嫩肤矿物蚕丝面膜7片
         * shop_price : 39.9
         * market_price : 99.0
         * is_coupon_allowed : true
         * is_allow_credit : false
         * goods_img : http://image.hmeili.com/yunifang/images/goods/121/goods_img/161201135479872856902510.jpg
         * sales_volume : 121308
         * efficacy : 镇店之宝 美白爆款
         * sort : 0
         */

        private String id;
        private String goods_name;
        private double shop_price;
        private double market_price;
        private boolean is_coupon_allowed;
        private boolean is_allow_credit;
        private String goods_img;
        private int sales_volume;
        private String efficacy;
        private int sort;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public double getShop_price() {
            return shop_price;
        }

        public void setShop_price(double shop_price) {
            this.shop_price = shop_price;
        }

        public double getMarket_price() {
            return market_price;
        }

        public void setMarket_price(double market_price) {
            this.market_price = market_price;
        }

        public boolean isIs_coupon_allowed() {
            return is_coupon_allowed;
        }

        public void setIs_coupon_allowed(boolean is_coupon_allowed) {
            this.is_coupon_allowed = is_coupon_allowed;
        }

        public boolean isIs_allow_credit() {
            return is_allow_credit;
        }

        public void setIs_allow_credit(boolean is_allow_credit) {
            this.is_allow_credit = is_allow_credit;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public int getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(int sales_volume) {
            this.sales_volume = sales_volume;
        }

        public String getEfficacy() {
            return efficacy;
        }

        public void setEfficacy(String efficacy) {
            this.efficacy = efficacy;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
