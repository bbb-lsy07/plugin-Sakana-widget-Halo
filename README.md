# Sakana Widget 插件 for Halo

本插件将 Sakana Widget（石蒜模拟器）集成为 Halo 博客系统挂件，支持后台自定义位置、尺寸、角色图片等参数。

## 插画出处

大伏アオ [@blue00f4](https://twitter.com/blue00f4) [Pixiv](https://pixiv.me/aoiroblue1340)

## 功能

- 按住立牌拖拽，松手后立牌会向反方向弹跳
- 底座控制栏切换角色和其他功能
- 托管模式，以随机间隔施加一个大小随机的力

## 使用方法
1. 在 Halo 应用市场安装本插件
2. 后台“插件管理”中找到本插件启用即可
3. 启用后自动注入到前端页面，无需手工注入

## 特别说明

目前组件的尺寸和位置是写死的，参数如下：

```
position: fixed;
size: 200,
right: 40px;
bottom: 45px;
z-index: 5;
```

## License

本项目代码基于 MIT 协议授权，本项目是基于上游项目 https://github.com/itorr/sakana 和 https://github.com/dsrkafuu/sakana-widget 的二次（三次？）开发。

插画出处：大伏アオ [@blue00f4](https://twitter.com/blue00f4) [pixiv](https://pixiv.me/aoiroblue1340) | 内置角色图片未经原作者授权**不可商用**。