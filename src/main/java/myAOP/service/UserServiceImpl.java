package myAOP.service;

import myAOP.annotation.MyTransaction;

@MyTransaction
public class UserServiceImpl implements UserService {
    @Override
    public void getUser() {
        System.out.println("Service执行。。。");
    }
}
