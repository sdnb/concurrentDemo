package demo12;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 简单实现一个缓存系统
 * 参考：
 *  class CachedData {
 *   Object data;
 *   volatile boolean cacheValid;
 *   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *
 *   void processCachedData() {
 *     rwl.readLock().lock();
 *     if (!cacheValid) {
 *       // Must release read lock before acquiring write lock
 *       rwl.readLock().unlock();
 *       rwl.writeLock().lock();
 *       try {
 *         // Recheck state because another thread might have
 *         // acquired write lock and changed state before we did.
 *         if (!cacheValid) {
 *           data = ...
 *           cacheValid = true;
 *         }
 *         // Downgrade by acquiring read lock before releasing write lock
 *         rwl.readLock().lock();
 *       } finally {
 *         rwl.writeLock().unlock(); // Unlock write, still hold read
 *       }
 *     }
 *
 *     try {
 *       use(data);
 *     } finally {
 *       rwl.readLock().unlock();
 *     }
 *   }
 * }
 * Created by Administrator on 2016/7/4 0004.
 */
public class CacheData<K,V> {
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private V data;
    public V getData(K key){
        rwl.readLock().lock();
        try {
            if(data == null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if(data == null){
                         //set the data
                    }
                }finally {
                    rwl.writeLock().unlock();
                }

                rwl.readLock().lock();
            }
        }finally {
            rwl.readLock().unlock();

        }
        return data;
    }


    public static void main(String[] args) {
        CacheData<String,String> cacheData = new CacheData<>();
        System.out.println(cacheData.getData("hello"));
    }


}
