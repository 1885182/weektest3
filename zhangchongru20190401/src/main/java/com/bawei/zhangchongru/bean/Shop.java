package com.bawei.zhangchongru.bean;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:55
 * @Description:
 */
public class Shop {
        private String masterPic;
        private String commodityName;

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public Shop(String masterPic, String commodityName) {
            this.masterPic = masterPic;
            this.commodityName = commodityName;
        }

}
