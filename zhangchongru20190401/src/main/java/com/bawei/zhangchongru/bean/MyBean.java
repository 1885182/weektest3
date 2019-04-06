package com.bawei.zhangchongru.bean;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:22
 * @Description:
 */
public class MyBean {
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public class ResultBean {
        private ShopBean rxxp;
        private ShopBean pzsh;
        private ShopBean mlss;

        public ShopBean getRxxp() {
            return rxxp;
        }

        public void setRxxp(ShopBean rxxp) {
            this.rxxp = rxxp;
        }

        public ShopBean getPzsh() {
            return pzsh;
        }

        public void setPzsh(ShopBean pzsh) {
            this.pzsh = pzsh;
        }

        public ShopBean getMlss() {
            return mlss;
        }

        public void setMlss(ShopBean mlss) {
            this.mlss = mlss;
        }

        public class ShopBean {
            private List<Shop> commodityList;
            private String name;

            public List<Shop> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<Shop> commodityList) {
                this.commodityList = commodityList;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }


        }
    }
}
