package com.springcloud.sysadmin.organization.reactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

public class Test {

    @org.junit.jupiter.api.Test
    public void publister(){
    }

    class MySublister implements Flow.Publisher<String>{

        private List<String> list = Arrays.asList("hello", "world", "liang", "jin", "quan");
        int index = 0;

        @Override
        public void subscribe(Flow.Subscriber<? super String> subscriber) {
            if(index == list.size()){
                subscriber.onComplete();
            }else {
//                subscriber.onSubscribe(new MySubscription(subscriber, list));
            }
        }
    }

    class MySubscriber implements Flow.Subscriber<String>{

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            subscription.request(10);
        }

        @Override
        public void onNext(String item) {
            System.out.println("接受到值:"+item);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("出错了");
        }

        @Override
        public void onComplete() {
            System.out.println("结束了");
        }
    }

    class MySubscription implements Flow.Subscription{

        private Flow.Subscriber<String> subscriber;
        private List<String> item;

        public MySubscription(Flow.Subscriber<String> subscriber, List<String> item){
            this.subscriber = subscriber;
            this.item = item;
        }

        @Override
        public void request(long n) {
            List<String> tmp =  new ArrayList<String>(Integer.valueOf(String.valueOf(n)));
            for(int i = 0;i<item.size();i++){
//                if(i = (n - 1)){
//
//                }
            }
        }

        @Override
        public void cancel() {

        }
    }
}
