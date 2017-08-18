# PermissionDemo
关于API &lt; 23的权限解决适配

由于公司的项目并没有做权限适配，然后客户反馈如果禁用掉权限，就没有什么反应（并不会崩溃，所以无法使用try catch的方法），因此还要另想解决方案解决这个问题，当然我这个方案并不是最好，如果大家还有什么其他方案，可以与我联系分享哈。以下文字仅以记录解决该问题的过程，以拨号权限为例。
基本思路 
直接拨打电话的代码：

    Uri uri = Uri.parse("tel:15008001234");
    Intent intent = new Intent(Intent.ACTION_CALL, uri);
    startActivity(intent);


假设有权限 
拨打电话在之后肯定会在通话界面，因此使用到该权限的Activity就会进入onPause，所以判断有没有权限的关键就是： 
1、点击了需要拨号权限的地方； 
2、Activity进入onPause； 
只要同时满足这两个条件，就是有了拨号权限；
解决办法

设置一个int变量，当点击需要权限的地方，与1执行或操作；当进入onPause方法，与2进行或操作；如果500ms后这个变量不等于3，则没有拨号权限。 
当然，在进入onResume方法还需要重置这个int变量为0。 
进入onDestroy方法执行removeCallBack操作。

我将具体的代码都写在了PermissionHandler这个类中，详见PermissionHandler.java

Android4.4版本之前的兼容 
在测试这个方案的过程中，由于部分国产手机在4.4之后都能够手动控制权限，所以一开始这个方案对4.4之后的版本是有效果的，但是4.4之前（包括4.4）的版本是一开始就授权的，只有在第一次使用的时候才会弹出是否授权的对话框。而且在4.4之前没有插入SIM卡的时候也不会有任何提示，因此新增了一个判断是否有插入SIM卡的工具类。

测试机型&系统版本

oppo R9m Android5.1
华为Mate9 Android7.0
vivo X3V Android4.4.2
vivo Y18L Android4.3
小米 红米 Note 4X Android6.0.1
