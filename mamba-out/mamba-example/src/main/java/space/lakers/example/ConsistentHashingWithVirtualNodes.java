package space.lakers.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 实现一个简单的一致性哈希算法，并添加虚拟节点的功能。
 *
 * 我们将使用TreeMap来模拟哈希环，MD5作为哈希函数，并在每个物理节点上添加一定数量的虚拟节点。
 */
public class ConsistentHashingWithVirtualNodes {

    private final SortedMap<Long, String> hashRing = new TreeMap<>();
    private static final int NUM_VIRTUAL_NODES = 3; // 每个物理节点的虚拟节点数量

    // 添加物理节点到哈希环中
    public void addNode(String node) {
        //每个物理节点的虚拟节点数量 NUM_VIRTUAL_NODES
        for (int i = 0; i < NUM_VIRTUAL_NODES; i++) {
            String virtualNode = node + "-" + i;
            long hash = hash(virtualNode);
            hashRing.put(hash, node);
        }
    }

    // 根据数据的哈希值查找处理节点
    public String getNode(String data) {
        long hash = hash(data);
        // 查找第一个大于或等于 data 哈希值的节点
        SortedMap<Long, String> tailMap = hashRing.tailMap(hash);
        if (tailMap.isEmpty()) {
            return hashRing.get(hashRing.firstKey());
        }
        return tailMap.get(tailMap.firstKey());
    }

    // 使用 MD5 作为哈希函数
    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(key.getBytes());
            // 取中间8个字节计算哈希值
            return ((long) (hash[7] & 0xFF) << 56)
                    | ((long) (hash[6] & 0xFF) << 48)
                    | ((long) (hash[5] & 0xFF) << 40)
                    | ((long) (hash[4] & 0xFF) << 32)
                    | ((long) (hash[3] & 0xFF) << 24)
                    | ((long) (hash[2] & 0xFF) << 16)
                    | ((long) (hash[1] & 0xFF) << 8)
                    | (hash[0] & 0xFF);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        ConsistentHashingWithVirtualNodes ch = new ConsistentHashingWithVirtualNodes();

        // 添加三个物理节点
        ch.addNode("Node-A");
        ch.addNode("Node-B");
        ch.addNode("Node-C");

        // 模拟数据
        String[] dataItems = {
                "Data-1", "Data-2", "Data-3", "Data-4", "Data-5", "Data-6",
                "Data-7", "Data-8", "Data-9", "Data-10"
        };

        // 根据数据查找对应的物理节点
        for (String data : dataItems) {
            String node = ch.getNode(data);
            System.out.println("Data: " + data + " => Node: " + node);
        }
    }
}
