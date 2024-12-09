


同学们好，欢迎来到享学课堂，我是今天的主讲 Leo老师，

我们正式 上课的时间 20：05，已经进来的同学请耐心等候下其他同学。



Hook点 并不是唯一的  --- 

动态代理 --- 反射实现


ActivityManager.getService()  --- 静态方法，可以获取返回值 --- IActivityManager



IActivityManager 对象 --- Singleton类的get方法获取的 -- private T mInstance;

proxyInstance  替换系统的 mInstance 对象


mInstance 非静态 -- Singleton 的对象 -- IActivityManagerSingleton 是静态的，可以通过反射获取

判断进程  包名  

找到 Intent的方便替换的地方  --- 在这个类里面 ActivityClientRecord --- Intent intent 非静态

	-- 获取 ActivityClientRecord 对象
	final ActivityClientRecord r = (ActivityClientRecord) msg.obj;

Handler源码

if (mCallback != null) {
    if (mCallback.handleMessage(msg)) {
        return;
    }
}
handleMessage(msg);


系统的 mCallback == null

创建 mCallback 替换系统的 -- 从而拿到 msg  -- msg.obj; -- intent

ClientTransaction == msg.obj   ---- private List<ClientTransactionItem> mActivityCallbacks;private List<ClientTransactionItem> mActivityCallbacks; --  -- ClientTransactionItem的子类

private Intent mIntent; -- LaunchActivityItem 对象  -- private List<ClientTransactionItem> mActivityCallbacks; 
		-- ClientTransaction == msg.obj 

Hook点不是唯一的

动态代理  替换的对象  系统的 IActivityManager


找到 静态的变量或者直接拿到类的对象




















