package com.bawei.yk.contro;

import com.bawei.yk.model.MyModel;

/**
 * @Author: zhang
 * @Date: 2019/4/4 8:51
 * @Description:
 */
public interface MyInterface {
    public interface ModelInterface{
        void toGetRequest(String url, MyModel.MyCallBack myCallBack,int i);
        void toDetailRequest(String url, MyModel.MyCallBack myCallBack,int i);
        void toPostRequest(String url, String phone, String pwd, MyModel.MyCallBack myCallBack);
    }
    public interface PresenterInterface{
        void toLogin(String phone,String pwd);
        void toRegister(String phone,String pwd);
        void toModel();
        void toDetailModel(String url);
        void onDestroy();
    }
    public interface ViewInterface{
        void reFreDisplay(Object object);
        public interface LoginInterface{
            void showLogin(String string);
        }
        public interface RegisterInterface{
            void showRegister(String string);
        }
        public interface DetailInterface{
            void reFreDisplay(Object object);
        }
    }
}
