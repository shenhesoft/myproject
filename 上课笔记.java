


同学们好，欢迎来到享学课堂，我是今天的主讲 Leo老师，

我们正式 上课的时间 20：05，已经进来的同学请耐心等候下其他同学。




加载插件的资源

宿主的资源到底怎么加载


AssetManager --> Resource --> Context

Application
Activity
Service


final ResourcesKey key = new ResourcesKey(
        resDir, --- 资源文件


assets.addAssetPath(key.mResDir)  --- 把宿主的资源 添加到集合中

宿主的代码  --- dexElements


assets.addAssetPath(插件的资源)  --- 把宿主的资源 添加到集合中
使用资源  -- 直接使用到插件的资源了


实现方式：
1. 插件的资源 和 宿主的资源 直接合并 -- 资源冲突  0x7f0a000a -- aapt  7f -- 70~7e ~ff
2. 专门创建一个 （Resource）AssetManger 加载插件的资源

7f:apk 包的 id
0a：资源类型的 id --- 01++
000a： 统一类型下的 id  0000++


资源冲突 


类加载器去加载的时候  --- 


Application  --- Boot


怎么让插件  获取  宿主创建的 Resource

1.会不会影响到宿主 --- 肯定会 -- 在插件中创建 Resource
2.宿主和插件的 Application 是同一个 --- 插件自定义的 Application 会执行吗？ 不会执行


能不能在插件中去创建？





.ap_ 文件

四字节对齐
zipalign --- 
1. 运行快 --- mmap
2. RAM 内存


// 这块代码执行的是宿主的
// mDecorContentParent == null
mDecorContentParent = (DecorContentParent) subDecor
    .findViewById(R.id.decor_content_parent);
mDecorContentParent.setWindowCallback(getWindowCallback());

// 宿主
mDecorContentParent = (DecorContentParent) subDecor
    .findViewById(0x7f07004e);

// 宿主的
0x7f07004e	decor_content_parent	false

// 插件的
0x7f07004d	decor_content_parent	false

aapt  --- 单独给插件创建一个 Resource  --- 都会产生

都是宿主的 context  --- 插件自己创建一个 context -- 绑定 启动插件资源的  Resource 


包名类名全部相同  --- 加载的就是宿主的 


宿主的 dexElements（既有宿主的代码也有插件的代码）

创建一个 包含插件的代码的 dexElements
获取 宿主的 dexElements

怎么去找 Hook点 --- activity启动流程 --- Intent  --- 容易替换 Intent的地方 

Activity 代码 -- 宿主的 -- 影响宿主