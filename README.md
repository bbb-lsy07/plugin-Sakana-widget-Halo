# 「Sakana! Widget」插件 for Halo

本插件将原 Web 版的 Sakana Widget 集成为 Halo 博客系统挂件，在 Halo 应用市场可一键安装。

## 功能

- 按住角色立牌拖拽，松手后立牌会弹跳
- 底座控制栏切换角色和其他功能
- 托管模式，以随机间隔施加一个大小随机的力

## 使用方法
1. 在 Halo 应用市场安装本插件
2. 后台“插件管理”中找到本插件启用即可
3. 启用后自动注入到前端页面，无需手工注入

## 特别说明

前端 min.js 文件是基于原 Web 版项目直接 rollup 打包的，所以目前小组件的尺寸和位置是固定的，参数如下：

```
size: 200,
```
```
position: fixed;
right: 40px;
bottom: 45px;
z-index: 5;
```

## License

本项目代码基于 MIT 协议授权，本项目是基于上游项目 [「Sakana!」石蒜模拟器](https://github.com/itorr/sakana) 和 [Sakana widget for Web](https://github.com/dsrkafuu/sakana-widget) 的二次（三次？）开发。

插画出处：大伏アオ [@blue00f4](https://twitter.com/blue00f4) [pixiv](https://pixiv.me/aoiroblue1340)
Logo 图片出处：[@Sanmon](https://www.pixiv.net/artworks/111233508)
内置图片未经原作者授权**不可商用**。
