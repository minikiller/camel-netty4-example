package com.kalix.testing;

import org.springframework.stereotype.Service;

@Service
public class LockService {
    /**
     * 广播发送解锁命令
     * @param lock_id
     * @return
     */
    public String unlock(String lock_id) {
        //todo 这里需要加入验证条件

        if (ChannelGroups.size() > 0) {
            ChannelGroups.broadcast(lock_id, new BroadCastChannelHandler.ChannelMatchers());
        }
        System.out.println("Input lock id is : "+ lock_id);
        return "finished unlock " + lock_id;
    }
}