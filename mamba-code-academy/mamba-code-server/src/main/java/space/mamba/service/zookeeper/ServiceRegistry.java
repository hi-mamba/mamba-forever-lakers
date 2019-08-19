package space.mamba.service.zookeeper;

/**
 * @author pankui
 * @date 2019-05-13
 * <pre>
 *
 * </pre>
 */
public interface ServiceRegistry {

    /**
     * 注册服务信息
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     */
    void register(String serviceName, String serviceAddress);
}
