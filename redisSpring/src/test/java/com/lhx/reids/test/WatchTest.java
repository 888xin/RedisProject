package com.lhx.reids.test;

import redis.clients.jedis.Jedis;

/**
 * Created by lhx on 2016/8/29 11:08
 *
 * @Description
 */
public class WatchTest{


    private static String PRE_KEY = WatchTest.class.getClass().getName() + ":";

    public static void main(String[] args) {
        Jedis conn = new Jedis("localhost");

        String status = "disconnected";
        if ("disconnected".equals(status)){
            String streamIdOld = conn.get(PRE_KEY + 4);
            if (streamIdOld == null){
                conn.set(PRE_KEY + 4, "sss");
                conn.expire(PRE_KEY + 4, 50);
                new DisableThread(conn, 4L).run();
            }
        }
    }



    private static class DisableThread extends Thread {

        private Long userId ;
        private Jedis conn ;

        DisableThread(Jedis conn, Long userId) {
            this.conn = conn ;
            this.userId = userId ;
        }

        @Override
        public void run() {
            int n = 0 ;
            while (true){
                String key = PRE_KEY + userId ;
                System.out.println(key);
                String streamId = conn.get(key);
                if (streamId != null){
                    String value = conn.get(key);
                    System.out.println(value);
                    if (n >= 5){
                        System.out.println("save");
                        break;
                    }
                    n ++ ;
                } else {
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            super.run();
        }
    }
}
